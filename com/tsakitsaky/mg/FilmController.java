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

import com.tsakitsaky.mg.Model.Film;
import com.tsakitsaky.mg.Repository. FilmRepository;

import org.springframework.ui.Model;

@RequestMapping("/v1/film")
@Controller
public class FilmController {

    @Autowired
    private FilmRepository  filmRepository;

    @GetMapping("")
    public String index(Model model , @RequestParam(name = "keyword" ,required = false) String key,
      @RequestParam(defaultValue = "1" , required = false ,name = "page") int page, @RequestParam(defaultValue = "3" , required = false ,name = "size") int size)  {
        List<Film> films = new ArrayList<Film>();

        Pageable pageable =PageRequest.of(page-1,size);

        Page<Film> pageCateg;
        if(key==null){
            pageCateg = filmRepository.findAll(pageable);
        }else{
            //pageCateg =  filmRepository.findCategorieByNomCategorieContainingIgnoreCase(key,pageable);
        }

        Film c = new Film();

        films = pageCateg.getContent();

        model.addAttribute("keyword" , key);
        model.addAttribute("film" , c);
        model.addAttribute("films", films);

        model.addAttribute("currentPage", pageCateg.getNumber() + 1);
        model.addAttribute("totalItems", pageCateg.getTotalElements());
        model.addAttribute("totalPages", pageCateg.getTotalPages());
        model.addAttribute("pageSize", size);

        return "Film/index";
    }

    @PostMapping("")
    public String save(@Valid Film film , BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            String message = "";
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/film";
        }
        try{
            film.setEtat(1);
            filmRepository.save(film);
            redirectAttributes.addFlashAttribute("success", "Film ajoutée avec succès");
            redirectAttributes.addFlashAttribute("message" , "Insertion avec succes");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
        }

        return "redirect:/v1/film";
    }
    @GetMapping("/{id}")
    public String editTutorial(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Film t = filmRepository.findById(id).get();

            model.addAttribute("film", t);
            model.addAttribute("pageTitle", "Edit Film (ID: " + id + ")");

            //List<Film> films = filmRepository.findAll();

            List<Film> films = filmRepository.findFilmByEtat(1);
            

            model.addAttribute("isUpdate" , 1);
            model.addAttribute("films", films);
            return "Film/index";
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
            return "redirect:/v1/film";
        }
    }

    @GetMapping("/delOption")
    public String delete(@RequestParam String id , RedirectAttributes redirectAttributes){
        Film ct =  filmRepository.findById(id).get();
        //filmRepository.delete(ct);
        ct.setEtat(0);
        filmRepository.save(ct);

        redirectAttributes.addFlashAttribute("message" , "Supprimé avec succés");
        return "redirect:/v1/film";
    }

    @PostMapping("/edit")
    public String modifier(@Valid @ModelAttribute Film film,BindingResult bindingResult,RedirectAttributes redirectAttributes){
        String message = "";
        if(bindingResult.hasErrors()){
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
    
            return "redirect:/v1/film";
        }
        try{
             filmRepository.save(film);
        }catch (Exception e){
            message += e.getMessage()+" ; ";
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/film";
        }

        redirectAttributes.addFlashAttribute("message" , "Modification avec succes");
        return "redirect:/v1/film";
    }

}
