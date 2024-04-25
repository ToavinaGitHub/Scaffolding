package com.tsakitsaky.mg.Controller;

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

import com.tsakitsaky.mg.Model.Produit;
import com.tsakitsaky.mg.Repository. ProduitRepository;

import org.springframework.ui.Model;

@RequestMapping("/v1/produit")
@Controller
public class ProduitController {

    @Autowired
    private ProduitRepository  produitRepository;

    @GetMapping("")
    public String index(Model model , @RequestParam(name = "keyword" ,required = false) String key,
      @RequestParam(defaultValue = "1" , required = false ,name = "page") int page, @RequestParam(defaultValue = "3" , required = false ,name = "size") int size)  {
        List<Produit> produits = new ArrayList<Produit>();

        Pageable pageable =PageRequest.of(page-1,size);

        Page<Produit> pageCateg;
        if(key==null){
            pageCateg = produitRepository.findAll(pageable);
        }else{
            //pageCateg =  produitRepository.findCategorieByNomCategorieContainingIgnoreCase(key,pageable);
        }

        Produit c = new Produit();

        produits = pageCateg.getContent();

        model.addAttribute("keyword" , key);
        model.addAttribute("produit" , c);
        model.addAttribute("produits", produits);

        model.addAttribute("currentPage", pageCateg.getNumber() + 1);
        model.addAttribute("totalItems", pageCateg.getTotalElements());
        model.addAttribute("totalPages", pageCateg.getTotalPages());
        model.addAttribute("pageSize", size);

        return "Produit/index";
    }

    @PostMapping("")
    public String save(@Valid Produit produit , BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            String message = "";
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/produit";
        }
        try{
            produit.setEtat(1);
            produitRepository.save(produit);
            redirectAttributes.addFlashAttribute("success", "Produit ajoutée avec succès");
            redirectAttributes.addFlashAttribute("message" , "Insertion avec succes");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
        }

        return "redirect:/v1/produit";
    }
    @GetMapping("/{id}")
    public String editTutorial(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Produit t = produitRepository.findById(id).get();

            model.addAttribute("produit", t);
            model.addAttribute("pageTitle", "Edit Produit (ID: " + id + ")");

            //List<Produit> produits = produitRepository.findAll();

            List<Produit> produits = produitRepository.findProduitByEtat(1);
            

            model.addAttribute("isUpdate" , 1);
            model.addAttribute("produits", produits);
            return "Produit/index";
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
            return "redirect:/v1/produit";
        }
    }

    @GetMapping("/delOption")
    public String delete(@RequestParam String id , RedirectAttributes redirectAttributes){
        Produit ct =  produitRepository.findById(id).get();
        //produitRepository.delete(ct);
        ct.setEtat(0);
        produitRepository.save(ct);

        redirectAttributes.addFlashAttribute("message" , "Supprimé avec succés");
        return "redirect:/v1/produit";
    }

    @PostMapping("/edit")
    public String modifier(@Valid @ModelAttribute Produit produit,BindingResult bindingResult,RedirectAttributes redirectAttributes){
        String message = "";
        if(bindingResult.hasErrors()){
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
    
            return "redirect:/v1/produit";
        }
        try{
             produitRepository.save(produit);
        }catch (Exception e){
            message += e.getMessage()+" ; ";
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/produit";
        }

        redirectAttributes.addFlashAttribute("message" , "Modification avec succes");
        return "redirect:/v1/produit";
    }

}
