package ro.siit.clothes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.siit.clothes.model.Category;
import ro.siit.clothes.model.Clothes;
import ro.siit.clothes.model.ClothesRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("clothes")
public class ClothesController {
    @Autowired
    private ClothesRepository repository;

    @GetMapping
    public ModelAndView getClothes(){
        ModelAndView modelAndView = new ModelAndView("clothes-table");
        List<Clothes> clothesList = repository.findAll();
        modelAndView.addObject("clothesAdd",clothesList);
        return modelAndView;
    }
    @GetMapping(path = "add")
    public ModelAndView clothesAddForm() {
        Clothes clothes = new Clothes();
        ModelAndView modelAndView = new ModelAndView("add-form");
        clothes.setDateCreated(LocalDate.now());
        modelAndView.addObject("todayDate", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        modelAndView.addObject("clothesAdd", new Clothes());
        return modelAndView;
    }

    @PostMapping(path = "add")
    public String addClothes(@ModelAttribute(name = "clothesAdd") @Valid Clothes clothes, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-form";
        }
        clothes.setDateCreated(LocalDate.now());
        repository.save(clothes);
        return "redirect:/clothes";
    }
    @GetMapping(path = "/{id}/view")
    public ModelAndView viewClothes(@PathVariable Long id) {
        Clothes clothes = repository.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("view-form");
        modelAndView.addObject("clothesView", clothes.getDateUpdate());
        modelAndView.addObject("clothesView", clothes);

        return modelAndView;
    }
    @PostMapping(path = "/{id}/view")
    public String deleteClothesById(@RequestParam("idHaine") Long id) {
        Clothes clothes = repository.findById(id).get();
        repository.deleteById(id);
        return "redirect:/clothes";
    }
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView getEditClothes(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit-form");
        Clothes clothes = repository.findById(id).get();
        clothes.setDateCreated(clothes.getDateCreated());
        clothes.setDateUpdate(LocalDate.now());
        modelAndView.addObject("clothesEdit", clothes);
        modelAndView.addObject("updatedDate", LocalDate.now());
        return modelAndView;
    }

    @PostMapping(path = "/{id}/edit")
    public String updateClothes(@ModelAttribute("recipieEdit") @Valid Clothes clothes, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-form";
        }
        clothes.setDateUpdate(LocalDate.now());
        repository.save(clothes);
        return "redirect:/clothes/{id}/view";
    }
    @GetMapping(path = "underwear")
    public ModelAndView getClothesUnderware() {
        ModelAndView modelAndView = new ModelAndView("clothes-table");

        List<Clothes> clothesList = repository.findAllByCategoryOrderByDateUpdate(Category.UNDERWEAR);
        modelAndView.addObject("clothesAdd", clothesList);
        return modelAndView;
    }
    @GetMapping(path = "pants")
    public ModelAndView getClothesPants() {
        ModelAndView modelAndView = new ModelAndView("clothes-table");

        List<Clothes> clothesList = repository.findAllByCategoryOrderByDateUpdate(Category.PANTS);
        modelAndView.addObject("clothesAdd", clothesList);
        return modelAndView;
    }
    @GetMapping(path = "shoes")
    public ModelAndView getClothesShoes() {
        ModelAndView modelAndView = new ModelAndView("clothes-table");

        List<Clothes> clothesList = repository.findAllByCategoryOrderByDateUpdate(Category.SHOES);
        modelAndView.addObject("clothesAdd", clothesList);
        return modelAndView;
    }
    @GetMapping(path = "shirt")
    public ModelAndView getClothesShirt() {
        ModelAndView modelAndView = new ModelAndView("clothes-table");

        List<Clothes> clothesList = repository.findAllByCategoryOrderByDateUpdate(Category.TSHIRT);
        modelAndView.addObject("clothesAdd", clothesList);
        return modelAndView;
    }
    @GetMapping(path = "blouse")
    public ModelAndView getClothesBlouse() {
        ModelAndView modelAndView = new ModelAndView("clothes-table");

        List<Clothes> clothesList = repository.findAllByCategoryOrderByDateUpdate(Category.BLOUSE);
        modelAndView.addObject("clothesAdd", clothesList);
        return modelAndView;
    }

    @GetMapping(path = "traning")
    public ModelAndView getClothesTrening() {
        ModelAndView modelAndView = new ModelAndView("clothes-table");

        List<Clothes> clothesList = repository.findAllByCategoryOrderByDateUpdate(Category.TRANING);
        modelAndView.addObject("clothesAdd", clothesList);
        return modelAndView;
    }

}
