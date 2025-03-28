package org.codenova.start.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.codenova.start.entity.Comment;
import org.codenova.start.model.travel.Item;
import org.codenova.start.repository.CommentRepository;
import org.codenova.start.service.TravelWarningAPIService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/travel")
public class TravelController {


    private TravelWarningAPIService travelWarningAPIService;
    private CommentRepository commentRepository;


    @GetMapping("/warning")
    public String waringHandle(@RequestParam("p") Optional<Integer> p, Model model) throws JsonProcessingException {
        int pValue = p.orElse(1);;

        Item[] items = travelWarningAPIService.findAll(pValue);
        model.addAttribute("items",items);

        return "travel/warning";
    }

    @GetMapping("/warning/detail")
    public String warningDetailHandle(@RequestParam("isoCode") String isoCode, Model model) throws JsonProcessingException {

        Item item = travelWarningAPIService.findByIsoCode(isoCode);

        model.addAttribute("comments",commentRepository.findByIsoCode(isoCode));
        model.addAttribute("data", item);

        return "travel/warning/detail";
    }

    @GetMapping("/warning/comment")
    public String createHandle(@ModelAttribute Comment comment){

        commentRepository.create(comment);

        return "redirect:/warning/comment";
    }


}
