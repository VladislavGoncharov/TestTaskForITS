package com.vladislavgoncharov.testtaskforits.controller;

import com.vladislavgoncharov.testtaskforits.entity.EntitySearchSubstring;
import com.vladislavgoncharov.testtaskforits.entity.EntitySemiMagicSquare;
import com.vladislavgoncharov.testtaskforits.main_package.SearchSubstring;
import com.vladislavgoncharov.testtaskforits.main_package.SemiMagicSquare;
import com.vladislavgoncharov.testtaskforits.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FirstViewController {

    @Autowired
    ProgramService programService;

    @RequestMapping("/")
    public String firstView(Model model,String error) {

        model.addAttribute("searchSubstring", new SearchSubstring());
        model.addAttribute("semiMagicSquare", new SemiMagicSquare());

        model.addAttribute("listSearchSubstring", programService.getAllEntityObject(EntitySearchSubstring.class));
        model.addAttribute("listSemiMagicSquare", programService.getAllEntityObject(EntitySemiMagicSquare.class));

        model.addAttribute("error", error);

        return "first-view";
    }
}
