package zone.god.materialmanagementv2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import zone.god.materialmanagementv2.model.Material;
import zone.god.materialmanagementv2.model.MaterialForm;
import zone.god.materialmanagementv2.model.Supplier;
import zone.god.materialmanagementv2.service.MaterialService;
import zone.god.materialmanagementv2.service.SupplierService;

import javax.annotation.security.PermitAll;
import java.io.File;

@Controller
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    Environment env;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private SupplierService supplierService;
    @ModelAttribute("suppliers")
    private Iterable<Supplier>getSuppliers(){
        return supplierService.findAll();
    }

    @GetMapping("/newMaterial")
    public ModelAndView getNewMaterialForm(){
        ModelAndView modelAndView = new ModelAndView("material/create");
        modelAndView.addObject("materialForm",new MaterialForm());
        modelAndView.addObject("suppliers",getSuppliers());
        return modelAndView;
    }

    @PostMapping("/newMaterial")
    public ModelAndView addNewMaterial(@ModelAttribute("materialForm") MaterialForm materialForm){
        doUploadMaterial(materialForm, new Material());
        return new ModelAndView("redirect:/material/materialList");
    }

    @GetMapping("/materialList")
    public ModelAndView getMaterialList(@PageableDefault(size = 10)Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("material/list");
        Page<Material> materials = materialService.findAll(pageable);
        modelAndView.addObject("materials", materials);
        modelAndView.addObject("suppliers",getSuppliers());
        return modelAndView;
    }

    @GetMapping("{id}/viewMaterial")
    public ModelAndView getMaterialView(@PathVariable int id){
        Material material = materialService.findById(id);
        ModelAndView modelAndView = new ModelAndView("material/view");
        modelAndView.addObject("material",material);
        return modelAndView;
    }

    @GetMapping("{id}/deleteMaterial")
    public ModelAndView deleteMaterial(@PathVariable int id){
        materialService.delete(id);
        return new ModelAndView("redirect:/material/materialList");
    }

    @GetMapping("{id}/editMaterial")
    public ModelAndView getMaterialEditView(@PathVariable int id){
        Material material = materialService.findById(id);
        ModelAndView modelAndView = new ModelAndView("material/edit");
        MaterialForm materialForm = new MaterialForm(material.getId(),material.getCode(),material.getName(),material.getImportDate(),material.getDescription(),material.getPrice(),material.getQuantity(),null,material.getSupplier());
        modelAndView.addObject("materialForm",materialForm);
        modelAndView.addObject("suppliers",getSuppliers());
        modelAndView.addObject("material",material);
        return modelAndView;
    }

    @PostMapping("/UpdateMaterial")
    public ModelAndView updateMaterial(@ModelAttribute("materialForm") MaterialForm materialForm){
        Material material = new Material();
        material.setId(materialForm.getId());
        doUploadMaterial(materialForm,material);
        return new ModelAndView("redirect:/material/materialList");
    }

    @GetMapping("/searchBySupplier")
    public ModelAndView searchBySupplier(@RequestParam("supplierSearch") int supId, @PageableDefault(size = 10) Pageable pageable){
        Page<Material> materials;
        if(supId == -1){
            materials = materialService.findAll(pageable);
        }else{
            Supplier supplier = supplierService.findById(supId);
            materials = materialService.findAllBySupplier(pageable,supplier);
        }
        ModelAndView modelAndView = new ModelAndView("material/list");
        modelAndView.addObject("materials",materials);
        modelAndView.addObject("supplierSearch",supId);
        return modelAndView;
    }

    @GetMapping("/sortByPrice")
    public ModelAndView searchBySupplier(@RequestParam("sortDirection") String sort, @PageableDefault(size = 10) Pageable pageable){
        Page<Material> materials;
        if(sort.equals("no")){
            materials = materialService.findAll(pageable);
        }else if(sort.equals("asc")){
            materials = materialService.findAllByOrderByPriceAsc(pageable);
        }else{
            materials = materialService.findAllByOrderByPriceDesc(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("material/list");
        modelAndView.addObject("materials",materials);
        modelAndView.addObject("sortDirection",sort);
        return modelAndView;
    }


    private void doUploadMaterial(MaterialForm materialForm, Material material){
        MultipartFile multipartFile = materialForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("uploadPath").toString();
        try{
            FileCopyUtils.copy(materialForm.getImage().getBytes(),new File(fileUpload+fileName));
        }catch (Exception e){
            e.printStackTrace();
        }
        material.setCode(materialForm.getCode());
        material.setName(materialForm.getName());
        material.setDescription(materialForm.getDescription());
        material.setImage(fileName);
        material.setImportDate(materialForm.getImportDate());
        material.setPrice(materialForm.getPrice());
        material.setSupplier(materialForm.getSupplier());
        material.setQuantity(materialForm.getQuantity());
        materialService.save(material);
    }
}
