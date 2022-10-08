package ru.job4j.accidents.persistence;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class RuleMem {
    private final Map<Integer, Rule> rules = new HashMap<>();
    private final AtomicInteger size = new AtomicInteger(rules.size());

    public Optional<Rule> get(int id) {
        return Optional.of(rules.get(id));
    }

    public List<Rule> getByIds(List<Integer> ids) {
        return ids.stream().map(rules::get).collect(Collectors.toList());
    }

    public Rule put(Rule rule) {
        int id = size.incrementAndGet();
        rule.setId(id);
        rules.put(id, rule);
        return rule;
    }

    public Collection<Rule> getRules() {
        return rules.values();
    }

    public boolean update(Rule rule) {
        return rules.replace(rule.getId(), rule) != null;
    }
}
