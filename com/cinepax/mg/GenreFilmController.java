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

import com.cinepax.mg.Model.GenreFilm;
import com.cinepax.mg.Repository. GenreFilmRepository;

import org.springframework.ui.Model;

@RequestMapping("/v1/genreFilm")
@Controller
public class GenreFilmController {

    @Autowired
    private GenreFilmRepository  genreFilmRepository;

    @GetMapping("")
    public String index(Model model , @RequestParam(name = "keyword" ,required = false) String key,
      @RequestParam(defaultValue = "1" , required = false ,name = "page") int page, @RequestParam(defaultValue = "3" , required = false ,name = "size") int size)  {
        List<GenreFilm> genreFilms = new ArrayList<GenreFilm>();

        Pageable pageable =PageRequest.of(page-1,size);

        Page<GenreFilm> pageCateg;
        if(key==null){
            //pageCateg = genreFilmRepository.findGenreFilmByEtat(pageable);
        }else{
            //pageCateg =  genreFilmRepository.findCategorieByNomCategorieContainingIgnoreCase(key,pageable);
        }

        GenreFilm c = new GenreFilm();

        genreFilms = pageCateg.getContent();

        model.addAttribute("keyword" , key);
        model.addAttribute("genreFilm" , c);
        model.addAttribute("genreFilms", genreFilms);

        model.addAttribute("currentPage", pageCateg.getNumber() + 1);
        model.addAttribute("totalItems", pageCateg.getTotalElements());
        model.addAttribute("totalPages", pageCateg.getTotalPages());
        model.addAttribute("pageSize", size);

        return "GenreFilm/index";
    }

    @PostMapping("")
    public String save(@Valid GenreFilm genreFilm , BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            String message = "";
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/genreFilm";
        }
        try{
            genreFilm.setEtat(1);
            genreFilmRepository.save(genreFilm);
            redirectAttributes.addFlashAttribute("success", "GenreFilm ajoutée avec succès");
            redirectAttributes.addFlashAttribute("message" , "Insertion avec succes");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
        }

        return "redirect:/v1/genreFilm";
    }
    @GetMapping("/{id}")
    public String editTutorial(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        try {
            GenreFilm t = genreFilmRepository.findById(id).get();

            model.addAttribute("genreFilm", t);
            model.addAttribute("pageTitle", "Edit GenreFilm (ID: " + id + ")");

            //List<GenreFilm> genreFilms = genreFilmRepository.findAll();

            List<GenreFilm> genreFilms = genreFilmRepository.findGenreFilmByEtat(1);
            
            model.addAttribute("isUpdate" , 1);
            model.addAttribute("genreFilms", genreFilms);
            return "GenreFilm/index";
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
            return "redirect:/v1/genreFilm";
        }
    }

    @GetMapping("/delOption")
    public String delete(@RequestParam String id , RedirectAttributes redirectAttributes){
        GenreFilm ct =  genreFilmRepository.findById(id).get();
        //genreFilmRepository.delete(ct);
        ct.setEtat(0);
        genreFilmRepository.save(ct);

        redirectAttributes.addFlashAttribute("message" , "Supprimé avec succés");
        return "redirect:/v1/genreFilm";
    }

    @PostMapping("/edit")
    public String modifier(@Valid @ModelAttribute GenreFilm genreFilm,BindingResult bindingResult,RedirectAttributes redirectAttributes){
        String message = "";
        if(bindingResult.hasErrors()){
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
    
            return "redirect:/v1/genreFilm";
        }
        try{
             genreFilmRepository.save(genreFilm);
        }catch (Exception e){
            message += e.getMessage()+" ; ";
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/genreFilm";
        }

        redirectAttributes.addFlashAttribute("message" , "Modification avec succes");
        return "redirect:/v1/genreFilm";
    }

}
