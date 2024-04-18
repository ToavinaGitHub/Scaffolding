package com.kombarika.mg.Controller;

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

import com.kombarika.mg.Model.District;
import com.kombarika.mg.Repository. DistrictRepository;

import org.springframework.ui.Model;

@RequestMapping("/v1/district")
@Controller
public class DistrictController {

    @Autowired
    private DistrictRepository  districtRepository;

    @GetMapping("")
    public String index(Model model , @RequestParam(name = "keyword" ,required = false) String key,
      @RequestParam(defaultValue = "1" , required = false ,name = "page") int page, @RequestParam(defaultValue = "3" , required = false ,name = "size") int size)  {
        List<District> districts = new ArrayList<District>();

        Pageable pageable =PageRequest.of(page-1,size);

        Page<District> pageCateg;
        if(key==null){
            //pageCateg = districtRepository.findDistrictByEtat(pageable);
        }else{
            //pageCateg =  districtRepository.findCategorieByNomCategorieContainingIgnoreCase(key,pageable);
        }

        District c = new District();

        districts = pageCateg.getContent();

        model.addAttribute("keyword" , key);
        model.addAttribute("district" , c);
        model.addAttribute("districts", districts);

        model.addAttribute("currentPage", pageCateg.getNumber() + 1);
        model.addAttribute("totalItems", pageCateg.getTotalElements());
        model.addAttribute("totalPages", pageCateg.getTotalPages());
        model.addAttribute("pageSize", size);

        return "District/index";
    }

    @PostMapping("")
    public String save(@Valid District district , BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            String message = "";
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/district";
        }
        try{
            district.setEtat(1);
            districtRepository.save(district);
            redirectAttributes.addFlashAttribute("success", "District ajoutée avec succès");
            redirectAttributes.addFlashAttribute("message" , "Insertion avec succes");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
        }

        return "redirect:/v1/district";
    }
    @GetMapping("/{id}")
    public String editTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            District t = districtRepository.findById(id).get();

            model.addAttribute("district", t);
            model.addAttribute("pageTitle", "Edit District (ID: " + id + ")");

            //List<District> districts = districtRepository.findAll();

            List<District> districts = districtRepository.findDistrictByEtat(1);
            
            model.addAttribute("isUpdate" , 1);
            model.addAttribute("districts", districts);
            return "District/index";
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
            return "redirect:/v1/district";
        }
    }

    @GetMapping("/delOption")
    public String delete(@RequestParam Integer id , RedirectAttributes redirectAttributes){
        District ct =  districtRepository.findById(id).get();
        //districtRepository.delete(ct);
        ct.setEtat(0);
        districtRepository.save(ct);

        redirectAttributes.addFlashAttribute("message" , "Supprimé avec succés");
        return "redirect:/v1/district";
    }

    @PostMapping("/edit")
    public String modifier(@Valid @ModelAttribute District district,BindingResult bindingResult,RedirectAttributes redirectAttributes){
        String message = "";
        if(bindingResult.hasErrors()){
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
    
            return "redirect:/v1/district";
        }
        try{
             districtRepository.save(district);
        }catch (Exception e){
            message += e.getMessage()+" ; ";
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/district";
        }

        redirectAttributes.addFlashAttribute("message" , "Modification avec succes");
        return "redirect:/v1/district";
    }

}
