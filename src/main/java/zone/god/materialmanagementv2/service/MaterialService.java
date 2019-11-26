package zone.god.materialmanagementv2.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zone.god.materialmanagementv2.model.Material;
import zone.god.materialmanagementv2.model.Supplier;

public interface MaterialService {
    Page<Material> findAll(Pageable pageable);
    Material findById(int id);
    void save(Material material);
    void delete(int id);
    Page<Material> findAllByOrderByPriceAsc(Pageable pageable);
    Page<Material> findAllByOrderByPriceDesc(Pageable pageable);
    Page<Material> findAllBySupplier(Pageable pageable, Supplier supplier);
}
