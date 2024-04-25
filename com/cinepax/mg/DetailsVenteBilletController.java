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

import com.cinepax.mg.Model.DetailsVenteBillet;
import com.cinepax.mg.Repository. DetailsVenteBilletRepository;

import org.springframework.ui.Model;

@RequestMapping("/v1/detailsVenteBillet")
@Controller
public class DetailsVenteBilletController {

    @Autowired
    private DetailsVenteBilletRepository  detailsVenteBilletRepository;

    @GetMapping("")
    public String index(Model model , @RequestParam(name = "keyword" ,required = false) String key,
      @RequestParam(defaultValue = "1" , required = false ,name = "page") int page, @RequestParam(defaultValue = "3" , required = false ,name = "size") int size)  {
        List<DetailsVenteBillet> detailsVenteBillets = new ArrayList<DetailsVenteBillet>();

        Pageable pageable =PageRequest.of(page-1,size);

        Page<DetailsVenteBillet> pageCateg;
        if(key==null){
            //pageCateg = detailsVenteBilletRepository.findDetailsVenteBilletByEtat(pageable);
        }else{
            //pageCateg =  detailsVenteBilletRepository.findCategorieByNomCategorieContainingIgnoreCase(key,pageable);
        }

        DetailsVenteBillet c = new DetailsVenteBillet();

        detailsVenteBillets = pageCateg.getContent();

        model.addAttribute("keyword" , key);
        model.addAttribute("detailsVenteBillet" , c);
        model.addAttribute("detailsVenteBillets", detailsVenteBillets);

        model.addAttribute("currentPage", pageCateg.getNumber() + 1);
        model.addAttribute("totalItems", pageCateg.getTotalElements());
        model.addAttribute("totalPages", pageCateg.getTotalPages());
        model.addAttribute("pageSize", size);

        return "DetailsVenteBillet/index";
    }

    @PostMapping("")
    public String save(@Valid DetailsVenteBillet detailsVenteBillet , BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            String message = "";
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/detailsVenteBillet";
        }
        try{
            detailsVenteBillet.setEtat(1);
            detailsVenteBilletRepository.save(detailsVenteBillet);
            redirectAttributes.addFlashAttribute("success", "DetailsVenteBillet ajoutée avec succès");
            redirectAttributes.addFlashAttribute("message" , "Insertion avec succes");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
        }

        return "redirect:/v1/detailsVenteBillet";
    }
    @GetMapping("/{id}")
    public String editTutorial(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        try {
            DetailsVenteBillet t = detailsVenteBilletRepository.findById(id).get();

            model.addAttribute("detailsVenteBillet", t);
            model.addAttribute("pageTitle", "Edit DetailsVenteBillet (ID: " + id + ")");

            //List<DetailsVenteBillet> detailsVenteBillets = detailsVenteBilletRepository.findAll();

            List<DetailsVenteBillet> detailsVenteBillets = detailsVenteBilletRepository.findDetailsVenteBilletByEtat(1);
            
            model.addAttribute("isUpdate" , 1);
            model.addAttribute("detailsVenteBillets", detailsVenteBillets);
            return "DetailsVenteBillet/index";
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
            return "redirect:/v1/detailsVenteBillet";
        }
    }

    @GetMapping("/delOption")
    public String delete(@RequestParam String id , RedirectAttributes redirectAttributes){
        DetailsVenteBillet ct =  detailsVenteBilletRepository.findById(id).get();
        //detailsVenteBilletRepository.delete(ct);
        ct.setEtat(0);
        detailsVenteBilletRepository.save(ct);

        redirectAttributes.addFlashAttribute("message" , "Supprimé avec succés");
        return "redirect:/v1/detailsVenteBillet";
    }

    @PostMapping("/edit")
    public String modifier(@Valid @ModelAttribute DetailsVenteBillet detailsVenteBillet,BindingResult bindingResult,RedirectAttributes redirectAttributes){
        String message = "";
        if(bindingResult.hasErrors()){
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
    
            return "redirect:/v1/detailsVenteBillet";
        }
        try{
             detailsVenteBilletRepository.save(detailsVenteBillet);
        }catch (Exception e){
            message += e.getMessage()+" ; ";
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/detailsVenteBillet";
        }

        redirectAttributes.addFlashAttribute("message" , "Modification avec succes");
        return "redirect:/v1/detailsVenteBillet";
    }

}
