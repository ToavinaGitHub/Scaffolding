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

import com.tsakitsaky.mg.Model.Salle;
import com.tsakitsaky.mg.Repository. SalleRepository;

import org.springframework.ui.Model;

@RequestMapping("/v1/salle")
@Controller
public class SalleController {

    @Autowired
    private SalleRepository  salleRepository;

    @GetMapping("")
    public String index(Model model , @RequestParam(name = "keyword" ,required = false) String key,
      @RequestParam(defaultValue = "1" , required = false ,name = "page") int page, @RequestParam(defaultValue = "3" , required = false ,name = "size") int size)  {
        List<Salle> salles = new ArrayList<Salle>();

        Pageable pageable =PageRequest.of(page-1,size);

        Page<Salle> pageCateg;
        if(key==null){
            pageCateg = salleRepository.findAll(pageable);
        }else{
            //pageCateg =  salleRepository.findCategorieByNomCategorieContainingIgnoreCase(key,pageable);
        }

        Salle c = new Salle();

        salles = pageCateg.getContent();

        model.addAttribute("keyword" , key);
        model.addAttribute("salle" , c);
        model.addAttribute("salles", salles);

        model.addAttribute("currentPage", pageCateg.getNumber() + 1);
        model.addAttribute("totalItems", pageCateg.getTotalElements());
        model.addAttribute("totalPages", pageCateg.getTotalPages());
        model.addAttribute("pageSize", size);

        return "Salle/index";
    }

    @PostMapping("")
    public String save(@Valid Salle salle , BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            String message = "";
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/salle";
        }
        try{
            salle.setEtat(1);
            salleRepository.save(salle);
            redirectAttributes.addFlashAttribute("success", "Salle ajoutée avec succès");
            redirectAttributes.addFlashAttribute("message" , "Insertion avec succes");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
        }

        return "redirect:/v1/salle";
    }
    @GetMapping("/{id}")
    public String editTutorial(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Salle t = salleRepository.findById(id).get();

            model.addAttribute("salle", t);
            model.addAttribute("pageTitle", "Edit Salle (ID: " + id + ")");

            //List<Salle> salles = salleRepository.findAll();

            List<Salle> salles = salleRepository.findSalleByEtat(1);
            

            model.addAttribute("isUpdate" , 1);
            model.addAttribute("salles", salles);
            return "Salle/index";
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
            return "redirect:/v1/salle";
        }
    }

    @GetMapping("/delOption")
    public String delete(@RequestParam String id , RedirectAttributes redirectAttributes){
        Salle ct =  salleRepository.findById(id).get();
        //salleRepository.delete(ct);
        ct.setEtat(0);
        salleRepository.save(ct);

        redirectAttributes.addFlashAttribute("message" , "Supprimé avec succés");
        return "redirect:/v1/salle";
    }

    @PostMapping("/edit")
    public String modifier(@Valid @ModelAttribute Salle salle,BindingResult bindingResult,RedirectAttributes redirectAttributes){
        String message = "";
        if(bindingResult.hasErrors()){
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
    
            return "redirect:/v1/salle";
        }
        try{
             salleRepository.save(salle);
        }catch (Exception e){
            message += e.getMessage()+" ; ";
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/salle";
        }

        redirectAttributes.addFlashAttribute("message" , "Modification avec succes");
        return "redirect:/v1/salle";
    }

}
