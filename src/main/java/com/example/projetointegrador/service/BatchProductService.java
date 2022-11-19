package com.example.projetointegrador.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetointegrador.dto.BatchProductDTO;
import com.example.projetointegrador.dto.ProductInBatchDTO;
import com.example.projetointegrador.dto.SectionDTO;
import com.example.projetointegrador.exceptions.ExpiredProductException;
import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.repository.BatchProductRepository;
import com.example.projetointegrador.service.interfaces.IBatchProductService;

@Service
public class BatchProductService implements IBatchProductService {

    @Autowired
    private BatchProductRepository batchProductRepository;

    @Override
    public BatchProduct getBatchProductByProductId(Long productId, Integer quantity) {
        return batchProductRepository.findBatchProductByProductId(productId, quantity);
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
    public void verifyExpirationDate(LocalDate expirationDate) throws ExpiredProductException {
        LocalDate today = LocalDate.now();
        long difference = ChronoUnit.DAYS.between(today, expirationDate);

        if (difference < 21) {
            throw new ExpiredProductException("product about to expire or expired");
        }
    }

    public ProductInBatchDTO findAllByProductId(Long productId) {
        List<BatchProduct> batchProducts = batchProductRepository.findAllByProductId(productId);

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
    public ProductInBatchDTO findAllByProductIdOrdered(Long productId, String order) {
        ProductInBatchDTO productInBatchDTO = findAllByProductId(productId);

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
}
