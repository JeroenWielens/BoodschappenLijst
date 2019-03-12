package com.jwielens.grocery.controllers;

import com.jwielens.grocery.domain.Boodschapper;
import com.jwielens.grocery.services.BoodschapperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/sendemail")
public class BoodschapperController {
    private BoodschapperService boodschapperService;

    public BoodschapperController(BoodschapperService boodschapperService) {
        this.boodschapperService = boodschapperService;
    }

    @GetMapping
    public String getBoodschappers(Model model) {
        model.addAttribute("boodschappers", boodschapperService.getBoodschappers());
        return "boodschappers";
    }

    @GetMapping("/new")
    public String newBoodschapperTemplate(Model model){
        model.addAttribute("boodschapper", new Boodschapper());
        return "newBoodschapper";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBoodschapper(Boodschapper boodschapper){
        Boodschapper savedBoodschapper = boodschapperService.saveBoodschapper(boodschapper);
        return "redirect:/sendemail";
    }


}
