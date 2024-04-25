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

import com.kombarika.mg.Model.Pays;
import com.kombarika.mg.Repository. PaysRepository;

import org.springframework.ui.Model;

@RequestMapping("/v1/pays")
@Controller
public class PaysController {

    @Autowired
    private PaysRepository  paysRepository;

    @GetMapping("")
    public String index(Model model , @RequestParam(name = "keyword" ,required = false) String key,
      @RequestParam(defaultValue = "1" , required = false ,name = "page") int page, @RequestParam(defaultValue = "3" , required = false ,name = "size") int size)  {
        List<Pays> payss = new ArrayList<Pays>();

        Pageable pageable =PageRequest.of(page-1,size);

        Page<Pays> pageCateg;
        if(key==null){
            //pageCateg = paysRepository.findPaysByEtat(pageable);
        }else{
            //pageCateg =  paysRepository.findCategorieByNomCategorieContainingIgnoreCase(key,pageable);
        }

        Pays c = new Pays();

        payss = pageCateg.getContent();

        model.addAttribute("keyword" , key);
        model.addAttribute("pays" , c);
        model.addAttribute("payss", payss);

        model.addAttribute("currentPage", pageCateg.getNumber() + 1);
        model.addAttribute("totalItems", pageCateg.getTotalElements());
        model.addAttribute("totalPages", pageCateg.getTotalPages());
        model.addAttribute("pageSize", size);

        return "Pays/index";
    }

    @PostMapping("")
    public String save(@Valid Pays pays , BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            String message = "";
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/pays";
        }
        try{
            pays.setEtat(1);
            paysRepository.save(pays);
            redirectAttributes.addFlashAttribute("success", "Pays ajoutée avec succès");
            redirectAttributes.addFlashAttribute("message" , "Insertion avec succes");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
        }

        return "redirect:/v1/pays";
    }
    @GetMapping("/{id}")
    public String editTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Pays t = paysRepository.findById(id).get();

            model.addAttribute("pays", t);
            model.addAttribute("pageTitle", "Edit Pays (ID: " + id + ")");

            //List<Pays> payss = paysRepository.findAll();

            List<Pays> payss = paysRepository.findPaysByEtat(1);
            
            model.addAttribute("isUpdate" , 1);
            model.addAttribute("payss", payss);
            return "Pays/index";
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
            return "redirect:/v1/pays";
        }
    }

    @GetMapping("/delOption")
    public String delete(@RequestParam Integer id , RedirectAttributes redirectAttributes){
        Pays ct =  paysRepository.findById(id).get();
        //paysRepository.delete(ct);
        ct.setEtat(0);
        paysRepository.save(ct);

        redirectAttributes.addFlashAttribute("message" , "Supprimé avec succés");
        return "redirect:/v1/pays";
    }

    @PostMapping("/edit")
    public String modifier(@Valid @ModelAttribute Pays pays,BindingResult bindingResult,RedirectAttributes redirectAttributes){
        String message = "";
        if(bindingResult.hasErrors()){
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
    
            return "redirect:/v1/pays";
        }
        try{
             paysRepository.save(pays);
        }catch (Exception e){
            message += e.getMessage()+" ; ";
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/pays";
        }

        redirectAttributes.addFlashAttribute("message" , "Modification avec succes");
        return "redirect:/v1/pays";
    }

}
