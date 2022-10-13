package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.persistence.RuleRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class RuleService {
    private final RuleRepository ruleRepository;

    public RuleService(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public Optional<Rule> get(int id) {
        return ruleRepository.findById(id);
    }

    public Rule put(Rule rule) {
        return ruleRepository.save(rule);
    }

    public Collection<Rule> getRules() {
        var rsl = new ArrayList<Rule>();
        for (var rule : ruleRepository.findAll()) {
            rsl.add(rule);
        }
        return rsl;
    }
}
