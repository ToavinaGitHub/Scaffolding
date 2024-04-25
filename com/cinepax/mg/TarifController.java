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

import com.cinepax.mg.Model.Tarif;
import com.cinepax.mg.Repository. TarifRepository;

import org.springframework.ui.Model;

@RequestMapping("/v1/tarif")
@Controller
public class TarifController {

    @Autowired
    private TarifRepository  tarifRepository;

    @GetMapping("")
    public String index(Model model , @RequestParam(name = "keyword" ,required = false) String key,
      @RequestParam(defaultValue = "1" , required = false ,name = "page") int page, @RequestParam(defaultValue = "3" , required = false ,name = "size") int size)  {
        List<Tarif> tarifs = new ArrayList<Tarif>();

        Pageable pageable =PageRequest.of(page-1,size);

        Page<Tarif> pageCateg;
        if(key==null){
            //pageCateg = tarifRepository.findTarifByEtat(pageable);
        }else{
            //pageCateg =  tarifRepository.findCategorieByNomCategorieContainingIgnoreCase(key,pageable);
        }

        Tarif c = new Tarif();

        tarifs = pageCateg.getContent();

        model.addAttribute("keyword" , key);
        model.addAttribute("tarif" , c);
        model.addAttribute("tarifs", tarifs);

        model.addAttribute("currentPage", pageCateg.getNumber() + 1);
        model.addAttribute("totalItems", pageCateg.getTotalElements());
        model.addAttribute("totalPages", pageCateg.getTotalPages());
        model.addAttribute("pageSize", size);

        return "Tarif/index";
    }

    @PostMapping("")
    public String save(@Valid Tarif tarif , BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            String message = "";
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/tarif";
        }
        try{
            tarif.setEtat(1);
            tarifRepository.save(tarif);
            redirectAttributes.addFlashAttribute("success", "Tarif ajoutée avec succès");
            redirectAttributes.addFlashAttribute("message" , "Insertion avec succes");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
        }

        return "redirect:/v1/tarif";
    }
    @GetMapping("/{id}")
    public String editTutorial(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Tarif t = tarifRepository.findById(id).get();

            model.addAttribute("tarif", t);
            model.addAttribute("pageTitle", "Edit Tarif (ID: " + id + ")");

            //List<Tarif> tarifs = tarifRepository.findAll();

            List<Tarif> tarifs = tarifRepository.findTarifByEtat(1);
            
            model.addAttribute("isUpdate" , 1);
            model.addAttribute("tarifs", tarifs);
            return "Tarif/index";
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
            return "redirect:/v1/tarif";
        }
    }

    @GetMapping("/delOption")
    public String delete(@RequestParam String id , RedirectAttributes redirectAttributes){
        Tarif ct =  tarifRepository.findById(id).get();
        //tarifRepository.delete(ct);
        ct.setEtat(0);
        tarifRepository.save(ct);

        redirectAttributes.addFlashAttribute("message" , "Supprimé avec succés");
        return "redirect:/v1/tarif";
    }

    @PostMapping("/edit")
    public String modifier(@Valid @ModelAttribute Tarif tarif,BindingResult bindingResult,RedirectAttributes redirectAttributes){
        String message = "";
        if(bindingResult.hasErrors()){
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
    
            return "redirect:/v1/tarif";
        }
        try{
             tarifRepository.save(tarif);
        }catch (Exception e){
            message += e.getMessage()+" ; ";
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/tarif";
        }

        redirectAttributes.addFlashAttribute("message" , "Modification avec succes");
        return "redirect:/v1/tarif";
    }

}
