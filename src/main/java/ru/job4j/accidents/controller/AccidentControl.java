package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;
import ru.job4j.accidents.service.RuleService;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

@Controller
@AllArgsConstructor
public class AccidentControl {
    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    @GetMapping("createAccidentForm")
    public String viewCreateAccident(Model model) {
        model.addAttribute(new Accident());
        model.addAttribute("types", accidentTypeService.getTypes());
        model.addAttribute("rules", ruleService.getRules());
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        takeAction(accident, act -> accidentService.create(accident));
        return "redirect:/index";
    }

    @GetMapping("updateAccidentForm")
    public String updateAccident(Model model, @RequestParam("id") int id) {
        model.addAttribute("accident", accidentService.get(id).orElseThrow(NoSuchElementException::new));
        model.addAttribute("types", accidentTypeService.getTypes());
        model.addAttribute("rules", ruleService.getRules());
        return "updateAccident";
    }

    @PostMapping("updateAccident")
    public String update(@ModelAttribute Accident accident) {
        takeAction(accident, act -> accidentService.update(accident));
        return "redirect:/index";
    }

    private void takeAction(Accident accident, Consumer<Accident> action) {
        accident.setType(accidentTypeService.get(accident.getType().getId())
                .orElseThrow(NoSuchElementException::new));
        ruleService.setRules(accident);
        action.accept(accident);
    }
}
