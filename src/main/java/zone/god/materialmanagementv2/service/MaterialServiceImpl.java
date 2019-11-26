package zone.god.materialmanagementv2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zone.god.materialmanagementv2.model.Material;
import zone.god.materialmanagementv2.model.Supplier;
import zone.god.materialmanagementv2.repository.MaterialRepository;

public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialRepository materialRepository;
    @Override
    public Page<Material> findAll(Pageable pageable) {
        return materialRepository.findAll(pageable);
    }

    @Override
    public Material findById(int id) {
        if(materialRepository.findById(id).isPresent()){
            return materialRepository.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    public void save(Material material) {
        materialRepository.save(material);
    }

    @Override
    public void delete(int id) {
        materialRepository.deleteById(id);
    }

    @Override
    public Page<Material> findAllByOrderByPriceAsc(Pageable pageable) {
        return materialRepository.findAllByOrderByPriceAsc(pageable);
    }

    @Override
    public Page<Material> findAllByOrderByPriceDesc(Pageable pageable) {
        return materialRepository.findAllByOrderByPriceDesc(pageable);
    }

    @Override
    public Page<Material> findAllBySupplier(Pageable pageable , Supplier supplier) {
        return materialRepository.findAllBySupplier(pageable,supplier);
    }
}
