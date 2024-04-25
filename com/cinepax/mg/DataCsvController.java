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

import com.cinepax.mg.Model.DataCsv;
import com.cinepax.mg.Repository. DataCsvRepository;

import org.springframework.ui.Model;

@RequestMapping("/v1/dataCsv")
@Controller
public class DataCsvController {

    @Autowired
    private DataCsvRepository  dataCsvRepository;

    @GetMapping("")
    public String index(Model model , @RequestParam(name = "keyword" ,required = false) String key,
      @RequestParam(defaultValue = "1" , required = false ,name = "page") int page, @RequestParam(defaultValue = "3" , required = false ,name = "size") int size)  {
        List<DataCsv> dataCsvs = new ArrayList<DataCsv>();

        Pageable pageable =PageRequest.of(page-1,size);

        Page<DataCsv> pageCateg;
        if(key==null){
            //pageCateg = dataCsvRepository.findDataCsvByEtat(pageable);
        }else{
            //pageCateg =  dataCsvRepository.findCategorieByNomCategorieContainingIgnoreCase(key,pageable);
        }

        DataCsv c = new DataCsv();

        dataCsvs = pageCateg.getContent();

        model.addAttribute("keyword" , key);
        model.addAttribute("dataCsv" , c);
        model.addAttribute("dataCsvs", dataCsvs);

        model.addAttribute("currentPage", pageCateg.getNumber() + 1);
        model.addAttribute("totalItems", pageCateg.getTotalElements());
        model.addAttribute("totalPages", pageCateg.getTotalPages());
        model.addAttribute("pageSize", size);

        return "DataCsv/index";
    }

    @PostMapping("")
    public String save(@Valid DataCsv dataCsv , BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            String message = "";
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/dataCsv";
        }
        try{
            dataCsv.setEtat(1);
            dataCsvRepository.save(dataCsv);
            redirectAttributes.addFlashAttribute("success", "DataCsv ajoutée avec succès");
            redirectAttributes.addFlashAttribute("message" , "Insertion avec succes");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
        }

        return "redirect:/v1/dataCsv";
    }
    @GetMapping("/{id}")
    public String editTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            DataCsv t = dataCsvRepository.findById(id).get();

            model.addAttribute("dataCsv", t);
            model.addAttribute("pageTitle", "Edit DataCsv (ID: " + id + ")");

            //List<DataCsv> dataCsvs = dataCsvRepository.findAll();

            List<DataCsv> dataCsvs = dataCsvRepository.findDataCsvByEtat(1);
            
            model.addAttribute("isUpdate" , 1);
            model.addAttribute("dataCsvs", dataCsvs);
            return "DataCsv/index";
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error", "Transaction echouée");
            return "redirect:/v1/dataCsv";
        }
    }

    @GetMapping("/delOption")
    public String delete(@RequestParam Integer id , RedirectAttributes redirectAttributes){
        DataCsv ct =  dataCsvRepository.findById(id).get();
        //dataCsvRepository.delete(ct);
        ct.setEtat(0);
        dataCsvRepository.save(ct);

        redirectAttributes.addFlashAttribute("message" , "Supprimé avec succés");
        return "redirect:/v1/dataCsv";
    }

    @PostMapping("/edit")
    public String modifier(@Valid @ModelAttribute DataCsv dataCsv,BindingResult bindingResult,RedirectAttributes redirectAttributes){
        String message = "";
        if(bindingResult.hasErrors()){
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                message += bindingResult.getAllErrors().get(i).getDefaultMessage()+";";
            }
            redirectAttributes.addFlashAttribute("error", message);
    
            return "redirect:/v1/dataCsv";
        }
        try{
             dataCsvRepository.save(dataCsv);
        }catch (Exception e){
            message += e.getMessage()+" ; ";
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/v1/dataCsv";
        }

        redirectAttributes.addFlashAttribute("message" , "Modification avec succes");
        return "redirect:/v1/dataCsv";
    }

}
