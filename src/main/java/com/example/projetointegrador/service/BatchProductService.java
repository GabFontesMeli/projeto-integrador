package com.example.projetointegrador.service;

import com.example.projetointegrador.dto.ProductDTO;
import com.example.projetointegrador.dto.StorageDTO;
import com.example.projetointegrador.exceptions.ExpiredProductException;
import com.example.projetointegrador.exceptions.ProductNotFoundException;
import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.repository.BatchProductRepository;
import com.example.projetointegrador.service.interfaces.IBatchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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
    public ProductDTO getBatchProductsByProductIdAndStorage(Long productId) throws ProductNotFoundException {
        List<BatchProduct> batchProductList = batchProductRepository.findBatchProductsByProductId(productId);
        if(batchProductList.isEmpty()) {
            throw new ProductNotFoundException("product not found in our stock");
        }
        ProductDTO productDTO = new ProductDTO();
        List<StorageDTO> storageDTOList = new ArrayList<>();
        productDTO.setProductId(batchProductList.get(0).getProduct().getId());

        for (BatchProduct batchProduct : batchProductList) {
            StorageDTO storageDTO = new StorageDTO(batchProduct.getBatch().getStorage().getId(), batchProduct.getRemainingQuantity());
            storageDTOList.add(storageDTO);
        }

        productDTO.setStorages(storageDTOList);

        return productDTO;
    }
}
