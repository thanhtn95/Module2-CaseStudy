package zone.god.materialmanagementv2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zone.god.materialmanagementv2.model.Supplier;
import zone.god.materialmanagementv2.repository.SupplierRepository;


public class SupplierServiceImpl  implements  SupplierService{
    @Autowired
    private SupplierRepository supplierRepository;
    @Override
    public Iterable<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Page<Supplier> findAll(Pageable pageable) {
        return supplierRepository.findAll(pageable);
    }

    @Override
    public Supplier findById(int id) {
        if(supplierRepository.findById(id).isPresent()){
            return supplierRepository.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    public void save(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    public void delete(int id) {
        supplierRepository.deleteById(id);
    }
}
