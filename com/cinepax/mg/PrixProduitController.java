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

import com.cinepax.mg.Model.PrixProduit;
import com.cinepax.mg.Repository. PrixProduitRepository;

import org.springframework.ui.Model;

@RequestMapping("/v1/prixProduit")
@Controller
public class PrixProduitController {

    @Autowired
    private PrixProduitRepository  prixProduitRepository;

    @GetMapping("")
    public String index(Model model , @RequestParam(name = "keyword" ,required = false) String key,
      @RequestParam(defaultValue = "1" , required = false ,name = "page") int page, @RequestParam(defaultValue = "3" , required = false ,name = "size") int size)  {
        List<PrixProduit> prixProduits = new ArrayList<PrixProduit>();

        Pageable pageable =PageRequest.of(page-1,size);

        Page<PrixProduit> pageCateg;
        if(key==null){
            //pageCateg = prixProduitRepository.findPrixProduitByEtat(pageable);
        }else{
            //pageCateg =  prixProduitRepository.findCategorieByNomCategorieContainingIgnoreCase(key,pageable);
        }

        PrixProduit c = new PrixProduit();

        prixProduits = pageCateg.getContent();

        model.addAttribute("keyword" , key);
        model.addAttribute("prixProduit" , c);
        model.addAttribute("prixProduits", prixProduits);

        model.addAttribute("currentPage", pageCateg.getNumber() + 1);
        model.addAttribute("totalItems", pageCateg.getTotalElements());
        model.addAttribute("totalPages", pageCateg.getTotalPages());
        model.addAttribute("pageSize", size);

        return "PrixProduit/index";
    }

    @PostMapping("")
    public String save(@Valid PrixProduit prixProduit , BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            String message = "";
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/prixProduit";
        }
        try{
            prixProduit.setEtat(1);
            prixProduitRepository.save(prixProduit);
            redirectAttributes.addFlashAttribute("success", "PrixProduit ajoutée avec succès");
            redirectAttributes.addFlashAttribute("message" , "Insertion avec succes");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
        }

        return "redirect:/v1/prixProduit";
    }
    @GetMapping("/{id}")
    public String editTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            PrixProduit t = prixProduitRepository.findById(id).get();

            model.addAttribute("prixProduit", t);
            model.addAttribute("pageTitle", "Edit PrixProduit (ID: " + id + ")");

            //List<PrixProduit> prixProduits = prixProduitRepository.findAll();

            List<PrixProduit> prixProduits = prixProduitRepository.findPrixProduitByEtat(1);
            
            model.addAttribute("isUpdate" , 1);
            model.addAttribute("prixProduits", prixProduits);
            return "PrixProduit/index";
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
            return "redirect:/v1/prixProduit";
        }
    }

    @GetMapping("/delOption")
    public String delete(@RequestParam Integer id , RedirectAttributes redirectAttributes){
        PrixProduit ct =  prixProduitRepository.findById(id).get();
        //prixProduitRepository.delete(ct);
        ct.setEtat(0);
        prixProduitRepository.save(ct);

        redirectAttributes.addFlashAttribute("message" , "Supprimé avec succés");
        return "redirect:/v1/prixProduit";
    }

    @PostMapping("/edit")
    public String modifier(@Valid @ModelAttribute PrixProduit prixProduit,BindingResult bindingResult,RedirectAttributes redirectAttributes){
        String message = "";
        if(bindingResult.hasErrors()){
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
    
            return "redirect:/v1/prixProduit";
        }
        try{
             prixProduitRepository.save(prixProduit);
        }catch (Exception e){
            message += e.getMessage()+" ; ";
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/prixProduit";
        }

        redirectAttributes.addFlashAttribute("message" , "Modification avec succes");
        return "redirect:/v1/prixProduit";
    }

}
