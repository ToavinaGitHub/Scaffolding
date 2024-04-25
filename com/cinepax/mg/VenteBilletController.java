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

import com.cinepax.mg.Model.VenteBillet;
import com.cinepax.mg.Repository. VenteBilletRepository;

import org.springframework.ui.Model;

@RequestMapping("/v1/venteBillet")
@Controller
public class VenteBilletController {

    @Autowired
    private VenteBilletRepository  venteBilletRepository;

    @GetMapping("")
    public String index(Model model , @RequestParam(name = "keyword" ,required = false) String key,
      @RequestParam(defaultValue = "1" , required = false ,name = "page") int page, @RequestParam(defaultValue = "3" , required = false ,name = "size") int size)  {
        List<VenteBillet> venteBillets = new ArrayList<VenteBillet>();

        Pageable pageable =PageRequest.of(page-1,size);

        Page<VenteBillet> pageCateg;
        if(key==null){
            //pageCateg = venteBilletRepository.findVenteBilletByEtat(pageable);
        }else{
            //pageCateg =  venteBilletRepository.findCategorieByNomCategorieContainingIgnoreCase(key,pageable);
        }

        VenteBillet c = new VenteBillet();

        venteBillets = pageCateg.getContent();

        model.addAttribute("keyword" , key);
        model.addAttribute("venteBillet" , c);
        model.addAttribute("venteBillets", venteBillets);

        model.addAttribute("currentPage", pageCateg.getNumber() + 1);
        model.addAttribute("totalItems", pageCateg.getTotalElements());
        model.addAttribute("totalPages", pageCateg.getTotalPages());
        model.addAttribute("pageSize", size);

        return "VenteBillet/index";
    }

    @PostMapping("")
    public String save(@Valid VenteBillet venteBillet , BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            String message = "";
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/venteBillet";
        }
        try{
            venteBillet.setEtat(1);
            venteBilletRepository.save(venteBillet);
            redirectAttributes.addFlashAttribute("success", "VenteBillet ajoutée avec succès");
            redirectAttributes.addFlashAttribute("message" , "Insertion avec succes");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
        }

        return "redirect:/v1/venteBillet";
    }
    @GetMapping("/{id}")
    public String editTutorial(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        try {
            VenteBillet t = venteBilletRepository.findById(id).get();

            model.addAttribute("venteBillet", t);
            model.addAttribute("pageTitle", "Edit VenteBillet (ID: " + id + ")");

            //List<VenteBillet> venteBillets = venteBilletRepository.findAll();

            List<VenteBillet> venteBillets = venteBilletRepository.findVenteBilletByEtat(1);
            
            model.addAttribute("isUpdate" , 1);
            model.addAttribute("venteBillets", venteBillets);
            return "VenteBillet/index";
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
            return "redirect:/v1/venteBillet";
        }
    }

    @GetMapping("/delOption")
    public String delete(@RequestParam String id , RedirectAttributes redirectAttributes){
        VenteBillet ct =  venteBilletRepository.findById(id).get();
        //venteBilletRepository.delete(ct);
        ct.setEtat(0);
        venteBilletRepository.save(ct);

        redirectAttributes.addFlashAttribute("message" , "Supprimé avec succés");
        return "redirect:/v1/venteBillet";
    }

    @PostMapping("/edit")
    public String modifier(@Valid @ModelAttribute VenteBillet venteBillet,BindingResult bindingResult,RedirectAttributes redirectAttributes){
        String message = "";
        if(bindingResult.hasErrors()){
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
    
            return "redirect:/v1/venteBillet";
        }
        try{
             venteBilletRepository.save(venteBillet);
        }catch (Exception e){
            message += e.getMessage()+" ; ";
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/venteBillet";
        }

        redirectAttributes.addFlashAttribute("message" , "Modification avec succes");
        return "redirect:/v1/venteBillet";
    }

}
