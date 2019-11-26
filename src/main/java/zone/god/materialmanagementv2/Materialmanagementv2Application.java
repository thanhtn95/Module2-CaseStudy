package zone.god.materialmanagementv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import zone.god.materialmanagementv2.service.MaterialService;
import zone.god.materialmanagementv2.service.MaterialServiceImpl;
import zone.god.materialmanagementv2.service.SupplierService;
import zone.god.materialmanagementv2.service.SupplierServiceImpl;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableWebMvc
@EnableTransactionManagement
public class Materialmanagementv2Application {

    public static void main(String[] args) {
        SpringApplication.run(Materialmanagementv2Application.class, args);
    }
    @Bean
    public SupplierService supplierService(){
        return new SupplierServiceImpl();
    }
    @Bean
    public MaterialService materialService(){
        return new MaterialServiceImpl();
    }
}
