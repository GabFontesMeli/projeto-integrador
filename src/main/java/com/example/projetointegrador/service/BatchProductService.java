package com.example.projetointegrador.service;

import com.example.projetointegrador.dto.BatchProductDTO;
import com.example.projetointegrador.dto.ProductInBatchDTO;
import com.example.projetointegrador.dto.SectionDTO;
import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.repository.BatchProductRepository;
import com.example.projetointegrador.service.interfaces.IBatchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

                    batchProductDTO.setId(batchProduct1.getId());
                    batchProductDTO.setRemainingQuantity(batchProduct1.getRemainingQuantity());
                    batchProductDTO.setExpirationDate(batchProduct1.getExpirationDate());

                    return batchProductDTO;
                }).collect(Collectors.toList()))
                .build();
    }

}
