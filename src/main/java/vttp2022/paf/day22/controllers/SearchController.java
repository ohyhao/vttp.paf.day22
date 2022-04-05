package vttp2022.paf.day22.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.paf.day22.services.GiphyService;

@Controller
@RequestMapping(path = "/search")
public class SearchController {

    @Autowired
    private GiphyService GiphySvc;

    @GetMapping
    public String searchGiphy(
        @RequestParam(required = true) String q,
        @RequestParam Integer limit,
        @RequestParam(defaultValue = "g") String rating,
        Model model) {

        model.addAttribute("q", q);
        model.addAttribute("limit", limit);
        model.addAttribute("rating", rating);
        model.addAttribute("results", GiphySvc.getGiphs(q, rating, limit));

        return "search";
    }
    
}
