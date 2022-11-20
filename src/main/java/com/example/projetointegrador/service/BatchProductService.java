package com.example.projetointegrador.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.example.projetointegrador.exceptions.BatchProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetointegrador.dto.BatchProductDTO;
import com.example.projetointegrador.dto.ProductDTO;
import com.example.projetointegrador.dto.ProductInBatchDTO;
import com.example.projetointegrador.dto.ReportBatchProductDTO;
import com.example.projetointegrador.dto.ReportProductDTO;
import com.example.projetointegrador.dto.SectionDTO;
import com.example.projetointegrador.dto.StorageDTO;
import com.example.projetointegrador.exceptions.ExpiredProductException;
import com.example.projetointegrador.exceptions.ProductNotFoundException;
import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.repository.BatchProductRepository;
import com.example.projetointegrador.service.interfaces.IBatchProductService;

@Service
public class BatchProductService implements IBatchProductService {

    @Autowired
    private BatchProductRepository batchProductRepository;

    @Override
    public BatchProduct getBatchProductByProductId(Long productId, Integer quantity) {
        return batchProductRepository.findBatchProductByProductIdAndRemainingQuantity(productId, quantity);
    }

    @Override
    public Float findVolumeBySection(Long sectionId) {
        List<Float> volumes = batchProductRepository.findVolumeBySectionId(sectionId);
        return volumes.stream().reduce(0f, Float::sum);
    }

    @Override
    public BatchProduct save(BatchProduct batchProduct) {
        return batchProductRepository.save(batchProduct);
    }

    @Override
    public void saveAll(List<BatchProduct> batchProducts) {
       batchProductRepository.saveAll(batchProducts);
    }

    @Override
    public void verifyExpirationDate(LocalDate expirationDate) throws ExpiredProductException {
        LocalDate today = LocalDate.now();
        long difference = ChronoUnit.DAYS.between(today, expirationDate);

        if (difference < 21) {
            throw new ExpiredProductException("product about to expire or expired");
        }
    }

    public ProductInBatchDTO findBatchProductsByProductId(Long productId) {
        List<BatchProduct> batchProducts = batchProductRepository.findBatchProductsByProductId(productId);
        System.out.println(batchProducts);

        return ProductInBatchDTO.builder()
                .productId(productId)
                .section(SectionDTO.builder()
                        .id(batchProducts.get(0).getSection().getId())
                        .storageId(batchProducts.get(0).getSection().getStorage().getId())
                        .build())
                .batchProducts(batchProducts.stream().map(batchProduct1 -> {
                    BatchProductDTO batchProductDTO = new BatchProductDTO();

                    batchProductDTO.setBatchId(batchProduct1.getBatch().getId());
                    batchProductDTO.setRemainingQuantity(batchProduct1.getRemainingQuantity());
                    batchProductDTO.setExpirationDate(batchProduct1.getExpirationDate());

                    return batchProductDTO;
                }).collect(Collectors.toList()))
                .build();
    }

    @Override
    public ProductInBatchDTO findBatchProductsByProductIdOrdered(Long productId, String order) {
        ProductInBatchDTO productInBatchDTO = findBatchProductsByProductId(productId);

        List<BatchProductDTO> orderedBatchProducts = productInBatchDTO.getBatchProducts();

        if (order.equalsIgnoreCase("L"))
            productInBatchDTO.setBatchProducts(orderedBatchProducts.stream()
                    .sorted(Comparator.comparing(BatchProductDTO::getBatchId)).collect(Collectors.toList()));

        if (order.equalsIgnoreCase("Q"))
            productInBatchDTO.setBatchProducts(orderedBatchProducts.stream()
                    .sorted(Comparator.comparing(BatchProductDTO::getRemainingQuantity)).collect(Collectors.toList()));

        if (order.equalsIgnoreCase("V"))
            productInBatchDTO.setBatchProducts(orderedBatchProducts.stream()
                    .sorted(Comparator.comparing(BatchProductDTO::getExpirationDate)).collect(Collectors.toList()));

        return productInBatchDTO;

    }

    @Override
    public ProductDTO getStorageQuantityByProductId(Long productId) throws ProductNotFoundException {
        List<BatchProduct> batchProductList = batchProductRepository.findBatchProductsByProductId(productId);
        if (batchProductList.isEmpty()) {
            throw new ProductNotFoundException("product not found in our stock");
        }
        ProductDTO productDTO = new ProductDTO();
        List<StorageDTO> storageDTOList = new ArrayList<>();
        productDTO.setProductId(batchProductList.get(0).getProduct().getId());

        for (BatchProduct batchProduct : batchProductList) {
            StorageDTO storageDTO = new StorageDTO(batchProduct.getBatch().getStorage().getId(),
                    batchProduct.getRemainingQuantity());
            storageDTOList.add(storageDTO);
        }

        productDTO.setStorages(storageDTOList);

        return productDTO;
    }

    @Override
    public ReportBatchProductDTO getBatchProductExpiring(Long days, Long sectionId) {
        LocalDate expirationDate = LocalDate.now().plusDays(days);

        List<BatchProduct> batchProducts = batchProductRepository
                .findBatchProductByExpirationDateAndSectionId(expirationDate, sectionId);

        return buildReportBatchProduct(batchProducts);
    }

    @Override
    public ReportBatchProductDTO getBatchProductExpiringOrdered(Long days, Long categoryId, String order) {
        LocalDate expirationDate = LocalDate.now().plusDays(days);

        // Sort sort = Sort.by("asc".equalsIgnoreCase(order) ?
        // Sort.Order.asc("expiration_date") : Sort.Order.desc("expiration_date"));

        List<BatchProduct> batchProducts = batchProductRepository.findBatchProductByExpirationOrdered(expirationDate,
                categoryId);

        return buildReportBatchProduct(batchProducts);
    }

    @Override
    public BatchProduct getBatchProductByProductIdAndBatchId(Long productId, Long batchId) throws BatchProductNotFoundException {
        return batchProductRepository.findBatchProductByProductIdAndBatchId(productId, batchId).orElseThrow(() ->
                new BatchProductNotFoundException("batch product not found"));
    }

    private ReportBatchProductDTO buildReportBatchProduct(List<BatchProduct> batchProducts) {
        ReportBatchProductDTO reportBatchProductDTO = new ReportBatchProductDTO();

        reportBatchProductDTO.setProducts(batchProducts.stream().map(bp -> {
            return ReportProductDTO.builder()
                    .batchId(bp.getBatch().getId())
                    .productId(bp.getProduct().getId())
                    .categoryId(bp.getProduct().getCategory().getId())
                    .expirationDate(bp.getExpirationDate())
                    .quantity(bp.getQuantity())
                    .build();
        }).collect(Collectors.toList()));

        return reportBatchProductDTO;
    }
}
