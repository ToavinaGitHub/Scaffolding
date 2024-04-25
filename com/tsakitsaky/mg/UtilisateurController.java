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

import com.tsakitsaky.mg.Model.Utilisateur;
import com.tsakitsaky.mg.Repository. UtilisateurRepository;

import org.springframework.ui.Model;

@RequestMapping("/v1/utilisateur")
@Controller
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository  utilisateurRepository;

    @GetMapping("")
    public String index(Model model , @RequestParam(name = "keyword" ,required = false) String key,
      @RequestParam(defaultValue = "1" , required = false ,name = "page") int page, @RequestParam(defaultValue = "3" , required = false ,name = "size") int size)  {
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

        Pageable pageable =PageRequest.of(page-1,size);

        Page<Utilisateur> pageCateg;
        if(key==null){
            pageCateg = utilisateurRepository.findAll(pageable);
        }else{
            //pageCateg =  utilisateurRepository.findCategorieByNomCategorieContainingIgnoreCase(key,pageable);
        }

        Utilisateur c = new Utilisateur();

        utilisateurs = pageCateg.getContent();

        model.addAttribute("keyword" , key);
        model.addAttribute("utilisateur" , c);
        model.addAttribute("utilisateurs", utilisateurs);

        model.addAttribute("currentPage", pageCateg.getNumber() + 1);
        model.addAttribute("totalItems", pageCateg.getTotalElements());
        model.addAttribute("totalPages", pageCateg.getTotalPages());
        model.addAttribute("pageSize", size);

        return "Utilisateur/index";
    }

    @PostMapping("")
    public String save(@Valid Utilisateur utilisateur , BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            String message = "";
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/utilisateur";
        }
        try{
            utilisateur.setEtat(1);
            utilisateurRepository.save(utilisateur);
            redirectAttributes.addFlashAttribute("success", "Utilisateur ajoutée avec succès");
            redirectAttributes.addFlashAttribute("message" , "Insertion avec succes");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
        }

        return "redirect:/v1/utilisateur";
    }
    @GetMapping("/{id}")
    public String editTutorial(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Utilisateur t = utilisateurRepository.findById(id).get();

            model.addAttribute("utilisateur", t);
            model.addAttribute("pageTitle", "Edit Utilisateur (ID: " + id + ")");

            //List<Utilisateur> utilisateurs = utilisateurRepository.findAll();

            List<Utilisateur> utilisateurs = utilisateurRepository.findUtilisateurByEtat(1);
            

            model.addAttribute("isUpdate" , 1);
            model.addAttribute("utilisateurs", utilisateurs);
            return "Utilisateur/index";
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
            return "redirect:/v1/utilisateur";
        }
    }

    @GetMapping("/delOption")
    public String delete(@RequestParam String id , RedirectAttributes redirectAttributes){
        Utilisateur ct =  utilisateurRepository.findById(id).get();
        //utilisateurRepository.delete(ct);
        ct.setEtat(0);
        utilisateurRepository.save(ct);

        redirectAttributes.addFlashAttribute("message" , "Supprimé avec succés");
        return "redirect:/v1/utilisateur";
    }

    @PostMapping("/edit")
    public String modifier(@Valid @ModelAttribute Utilisateur utilisateur,BindingResult bindingResult,RedirectAttributes redirectAttributes){
        String message = "";
        if(bindingResult.hasErrors()){
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
    
            return "redirect:/v1/utilisateur";
        }
        try{
             utilisateurRepository.save(utilisateur);
        }catch (Exception e){
            message += e.getMessage()+" ; ";
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/utilisateur";
        }

        redirectAttributes.addFlashAttribute("message" , "Modification avec succes");
        return "redirect:/v1/utilisateur";
    }

}
