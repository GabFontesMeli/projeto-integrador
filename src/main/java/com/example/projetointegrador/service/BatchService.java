package com.example.projetointegrador.service;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.exceptions.*;
import com.example.projetointegrador.model.*;
import com.example.projetointegrador.repository.BatchRepository;
import com.example.projetointegrador.service.interfaces.IBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Service
public class BatchService implements IBatchService {

    // TODO: trocar as chamadas dos repositórios por serviços
    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BatchProductService batchProductService;

    /**
     * método que valida e insere um novo batch
     * @param batchDTO
     * @return
     * @throws SectionInvalidException
     */
    @Override
    public Batch createBatch(BatchDTO batchDTO) throws SectionInvalidException, ProductNotFoundException, CategoryInvalidException, InsuficientVolumeException, StorageInvalidException {
        Batch batch = new Batch(batchDTO);

        Storage storage = storageService.findById(batchDTO.getStorageId());


        Map<Section, Float> sectionExpectedVolume = new HashMap<Section, Float>();

        Set<BatchProduct> batchProducts = batchDTO.getProducts();

        for (BatchProduct batchProduct : batchProducts) {
            Product product = productService.findById(batchProduct.getProduct().getId());

            Section section = sectionService.findById(batchProduct.getSection().getId());


            if(!product.getCategory().equals(section.getCategory())) throw new
                    CategoryInvalidException("The product and section categories are different.");

            if(sectionExpectedVolume.containsKey(section)) {
                Float expectedVolume = sectionExpectedVolume.get(section);
                expectedVolume += product.getVolume() * batchProduct.getQuantity();
                sectionExpectedVolume.put(section, expectedVolume);
            } else {
                sectionExpectedVolume.put(section, product.getVolume() * batchProduct.getQuantity());
            }
        }
        hasRemainigVolume(sectionExpectedVolume);
        batch.setStorage(storage);
        return batchRepository.save(batch);
    }

    @Override
    public void hasRemainigVolume(Map<Section, Float> sectionsVolumes) throws InsuficientVolumeException {
        for(Section section : sectionsVolumes.keySet()) {
            Float usedVolume = batchProductService.findVolumeBySection(section.getId());
            if(sectionsVolumes.get(section)>(section.getVolume() - usedVolume)) {
                throw new InsuficientVolumeException("expected volume not found");
            }
        }
    }

    @Override
    public Batch findById(Long id) throws BatchInvalidException {
        return batchRepository.findById(id).orElseThrow(() -> new BatchInvalidException("Batch doesn't exists"));
    }

    /***
     * método que atualiza o batch existente
     * @param id
     * @param batchProductList
     * @return
     */
    @Override
    public Batch update(Long id, Set<BatchProduct> batchProductList) throws BatchInvalidException, ProductNotFoundException, SectionInvalidException, InsuficientVolumeException {
        Batch batch = this.findById(id);
        Map<Section, Float> sectionExpectedVolume = new HashMap<Section, Float>();

        for (BatchProduct batchProduct : batchProductList) {

            Product product = productService.findById(batchProduct.getProduct().getId());
            Section section = sectionService.findById(batchProduct.getSection().getId());
            if(sectionExpectedVolume.containsKey(section)) {
                Float expectedVolume = sectionExpectedVolume.get(section);
                expectedVolume += product.getVolume() * batchProduct.getQuantity();
                sectionExpectedVolume.put(section, expectedVolume);
            } else {
                sectionExpectedVolume.put(section, product.getVolume() * batchProduct.getQuantity());
            }

        }
        hasRemainigVolume(sectionExpectedVolume);

        batch.addProducts(batchProductList);

        return batchRepository.save(batch);
    }

}
