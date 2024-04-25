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

import com.cinepax.mg.Model.PlaceSalle;
import com.cinepax.mg.Repository. PlaceSalleRepository;

import org.springframework.ui.Model;

@RequestMapping("/v1/placeSalle")
@Controller
public class PlaceSalleController {

    @Autowired
    private PlaceSalleRepository  placeSalleRepository;

    @GetMapping("")
    public String index(Model model , @RequestParam(name = "keyword" ,required = false) String key,
      @RequestParam(defaultValue = "1" , required = false ,name = "page") int page, @RequestParam(defaultValue = "3" , required = false ,name = "size") int size)  {
        List<PlaceSalle> placeSalles = new ArrayList<PlaceSalle>();

        Pageable pageable =PageRequest.of(page-1,size);

        Page<PlaceSalle> pageCateg;
        if(key==null){
            //pageCateg = placeSalleRepository.findPlaceSalleByEtat(pageable);
        }else{
            //pageCateg =  placeSalleRepository.findCategorieByNomCategorieContainingIgnoreCase(key,pageable);
        }

        PlaceSalle c = new PlaceSalle();

        placeSalles = pageCateg.getContent();

        model.addAttribute("keyword" , key);
        model.addAttribute("placeSalle" , c);
        model.addAttribute("placeSalles", placeSalles);

        model.addAttribute("currentPage", pageCateg.getNumber() + 1);
        model.addAttribute("totalItems", pageCateg.getTotalElements());
        model.addAttribute("totalPages", pageCateg.getTotalPages());
        model.addAttribute("pageSize", size);

        return "PlaceSalle/index";
    }

    @PostMapping("")
    public String save(@Valid PlaceSalle placeSalle , BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            String message = "";
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/placeSalle";
        }
        try{
            placeSalle.setEtat(1);
            placeSalleRepository.save(placeSalle);
            redirectAttributes.addFlashAttribute("success", "PlaceSalle ajoutée avec succès");
            redirectAttributes.addFlashAttribute("message" , "Insertion avec succes");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
        }

        return "redirect:/v1/placeSalle";
    }
    @GetMapping("/{id}")
    public String editTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            PlaceSalle t = placeSalleRepository.findById(id).get();

            model.addAttribute("placeSalle", t);
            model.addAttribute("pageTitle", "Edit PlaceSalle (ID: " + id + ")");

            //List<PlaceSalle> placeSalles = placeSalleRepository.findAll();

            List<PlaceSalle> placeSalles = placeSalleRepository.findPlaceSalleByEtat(1);
            
            model.addAttribute("isUpdate" , 1);
            model.addAttribute("placeSalles", placeSalles);
            return "PlaceSalle/index";
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
            return "redirect:/v1/placeSalle";
        }
    }

    @GetMapping("/delOption")
    public String delete(@RequestParam Integer id , RedirectAttributes redirectAttributes){
        PlaceSalle ct =  placeSalleRepository.findById(id).get();
        //placeSalleRepository.delete(ct);
        ct.setEtat(0);
        placeSalleRepository.save(ct);

        redirectAttributes.addFlashAttribute("message" , "Supprimé avec succés");
        return "redirect:/v1/placeSalle";
    }

    @PostMapping("/edit")
    public String modifier(@Valid @ModelAttribute PlaceSalle placeSalle,BindingResult bindingResult,RedirectAttributes redirectAttributes){
        String message = "";
        if(bindingResult.hasErrors()){
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
    
            return "redirect:/v1/placeSalle";
        }
        try{
             placeSalleRepository.save(placeSalle);
        }catch (Exception e){
            message += e.getMessage()+" ; ";
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/placeSalle";
        }

        redirectAttributes.addFlashAttribute("message" , "Modification avec succes");
        return "redirect:/v1/placeSalle";
    }

}
