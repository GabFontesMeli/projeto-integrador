package com.example.projetointegrador.service;

import com.example.projetointegrador.dto.ReportBatchProductDTO;
import com.example.projetointegrador.dto.ReportProductDTO;
import com.example.projetointegrador.exceptions.ExpiredProductException;
import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.repository.BatchProductRepository;
import com.example.projetointegrador.service.interfaces.IBatchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

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
        return volumes.stream().reduce(0f,Float::sum);
    }

    @Override
    public BatchProduct save(BatchProduct batchProduct) {
        return batchProductRepository.save(batchProduct);
    }

    @Override
    public void verifyExpirationDate(LocalDate expirationDate) throws ExpiredProductException {
        LocalDate today = LocalDate.now();
        long difference =  ChronoUnit.DAYS.between(today, expirationDate);

        if (difference < 21) {
            throw new ExpiredProductException("product about to expire or expired");
        }
    }

    @Override
    public ReportBatchProductDTO getBatchProductExpiring(Long days, Long sectionId) {
        LocalDate expirationDate = LocalDate.now().plusDays(days);

        List<BatchProduct> batchProducts = batchProductRepository.findBatchProductByExpirationDateAndSectionId(expirationDate, sectionId);

        return buildReportBatchProduct(batchProducts);
    }

    @Override
    public ReportBatchProductDTO getBatchProductExpiringOrdered(Long days, Long categoryId, String order) {
        LocalDate expirationDate = LocalDate.now().plusDays(days);        

        //Sort sort = Sort.by("asc".equalsIgnoreCase(order) ? Sort.Order.asc("expiration_date") :  Sort.Order.desc("expiration_date"));

        List<BatchProduct> batchProducts = batchProductRepository.findBatchProductByExpirationOrdered(expirationDate, categoryId);
        
        return buildReportBatchProduct(batchProducts);
    }

    private ReportBatchProductDTO buildReportBatchProduct(List<BatchProduct> batchProducts){
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
