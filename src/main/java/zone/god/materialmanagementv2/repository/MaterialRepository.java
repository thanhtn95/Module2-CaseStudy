package zone.god.materialmanagementv2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import zone.god.materialmanagementv2.model.Material;
import zone.god.materialmanagementv2.model.Supplier;


public interface MaterialRepository extends PagingAndSortingRepository<Material,Integer> {
    Page<Material> findAllByOrderByPriceAsc(Pageable pageable);
    Page<Material> findAllByOrderByPriceDesc(Pageable pageable);
    Page<Material> findAllBySupplier(Pageable pageable, Supplier supplier);
}
