package zone.god.materialmanagementv2.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zone.god.materialmanagementv2.model.Supplier;


public interface SupplierService {
    Iterable<Supplier> findAll();
    Page<Supplier> findAll(Pageable pageable);
    Supplier findById(int id);
    void save(Supplier supplier);
    void delete(int id);
}
