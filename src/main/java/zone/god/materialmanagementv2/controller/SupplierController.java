package zone.god.materialmanagementv2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import zone.god.materialmanagementv2.model.Supplier;
import zone.god.materialmanagementv2.service.SupplierService;



@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;
    @GetMapping("/newSupplier")
    public ModelAndView getNewSupplierForm(){
        return new ModelAndView("supplier/create","supplier", new Supplier());
    }

    @PostMapping("/newSupplier")
    public ModelAndView addNewSupplier(@ModelAttribute("supplier") Supplier supplier){
        supplierService.save(supplier);
        return new ModelAndView("redirect:supplierList");
    }

    @GetMapping("/supplierList")
    public ModelAndView getSupplierList(@PageableDefault(size = 10) Pageable pageable){
        Page<Supplier> suppliers = supplierService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("supplier/list");
        modelAndView.addObject("suppliers",suppliers);
        return modelAndView;
    }

    @GetMapping("{id}/viewSupplier")
    public ModelAndView getSupplierView(@PathVariable int id){
        Supplier supplier = supplierService.findById(id);
        ModelAndView modelAndView = new ModelAndView("supplier/view");
        modelAndView.addObject("supplier",supplier);
        return modelAndView;
    }

    @GetMapping("{id}/editSupplier")
    public ModelAndView getSupplierEditView(@PathVariable int id){
        Supplier supplier = supplierService.findById(id);
        ModelAndView modelAndView = new ModelAndView("supplier/edit");
        modelAndView.addObject("supplier",supplier);
        return modelAndView;
    }

    @PostMapping("/editSupplier")
    public ModelAndView EditNewSupplier(@ModelAttribute("supplier") Supplier supplier){
        supplierService.save(supplier);
        return new ModelAndView("redirect:/supplier/supplierList");
    }


    @GetMapping("{id}/deleteSupplier")
    public ModelAndView deleteSupplier(@PathVariable int id){
        supplierService.delete(id);
        return new ModelAndView("redirect:/supplier/supplierList");
    }
}
