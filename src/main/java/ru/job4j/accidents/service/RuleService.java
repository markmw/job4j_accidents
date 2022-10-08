package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.persistence.RuleMem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class RuleService {
    private final RuleMem ruleMem;

    public RuleService(RuleMem ruleMem) {
        this.ruleMem = ruleMem;
    }

    public Optional<Rule> get(int id) {
        return ruleMem.get(id);
    }

    public Rule put(Rule rule) {
        return ruleMem.put(rule);
    }

    public Collection<Rule> getRules() {
        return ruleMem.getRules();
    }

    public boolean update(Rule rule) {
        return ruleMem.update(rule);
    }

    public void setRules(Accident accident) {
        if (accident != null && !accident.getRules().isEmpty()) {
            final List<Integer> ids = new ArrayList<>();
            for (Rule rule : accident.getRules()) {
                ids.add(rule.getId());
            }
            accident.setRules(ruleMem.getByIds(ids));
        }
    }
}
