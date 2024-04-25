package com.cinepax.mg.Controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cinepax.mg.Model.Region;
import com.cinepax.mg.Repository. RegionRepository;

import org.springframework.ui.Model;

@RequestMapping("/v1/region")
@Controller
public class RegionController {

    @Autowired
    private RegionRepository  regionRepository;

    @GetMapping("")
    public String index(Model model , @RequestParam(name = "keyword" ,required = false) String key,
      @RequestParam(defaultValue = "1" , required = false ,name = "page") int page, @RequestParam(defaultValue = "3" , required = false ,name = "size") int size)  {
        List<Region> regions = new ArrayList<Region>();

        Pageable pageable =PageRequest.of(page-1,size);

        Page<Region> pageCateg;
        if(key==null){
            //pageCateg = regionRepository.findRegionByEtat(pageable);
        }else{
            //pageCateg =  regionRepository.findCategorieByNomCategorieContainingIgnoreCase(key,pageable);
        }

        Region c = new Region();

        regions = pageCateg.getContent();

        model.addAttribute("keyword" , key);
        model.addAttribute("region" , c);
        model.addAttribute("regions", regions);

        model.addAttribute("currentPage", pageCateg.getNumber() + 1);
        model.addAttribute("totalItems", pageCateg.getTotalElements());
        model.addAttribute("totalPages", pageCateg.getTotalPages());
        model.addAttribute("pageSize", size);

        return "Region/index";
    }

    @PostMapping("")
    public String save(@Valid Region region , BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            String message = "";
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/region";
        }
        try{
            region.setEtat(1);
            regionRepository.save(region);
            redirectAttributes.addFlashAttribute("success", "Region ajoutée avec succès");
            redirectAttributes.addFlashAttribute("message" , "Insertion avec succes");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
        }

        return "redirect:/v1/region";
    }
    @GetMapping("/{id}")
    public String editTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Region t = regionRepository.findById(id).get();

            model.addAttribute("region", t);
            model.addAttribute("pageTitle", "Edit Region (ID: " + id + ")");

            //List<Region> regions = regionRepository.findAll();

            List<Region> regions = regionRepository.findRegionByEtat(1);
            
            model.addAttribute("isUpdate" , 1);
            model.addAttribute("regions", regions);
            return "Region/index";
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
            return "redirect:/v1/region";
        }
    }

    @GetMapping("/delOption")
    public String delete(@RequestParam Integer id , RedirectAttributes redirectAttributes){
        Region ct =  regionRepository.findById(id).get();
        //regionRepository.delete(ct);
        ct.setEtat(0);
        regionRepository.save(ct);

        redirectAttributes.addFlashAttribute("message" , "Supprimé avec succés");
        return "redirect:/v1/region";
    }

    @PostMapping("/edit")
    public String modifier(@Valid @ModelAttribute Region region,BindingResult bindingResult,RedirectAttributes redirectAttributes){
        String message = "";
        if(bindingResult.hasErrors()){
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
    
            return "redirect:/v1/region";
        }
        try{
             regionRepository.save(region);
        }catch (Exception e){
            message += e.getMessage()+" ; ";
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/region";
        }

        redirectAttributes.addFlashAttribute("message" , "Modification avec succes");
        return "redirect:/v1/region";
    }

}
