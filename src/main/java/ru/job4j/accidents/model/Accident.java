package ru.job4j.accidents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Accident {
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    private String text;
    private String address;
    private AccidentType type;
    private final Set<Rule> rules = new HashSet<>();

    public Set<Rule> getRules() {
        return Set.copyOf(rules);
    }

    public void setRules(Collection<Rule> collection) {
        rules.clear();
        rules.addAll(collection);
    }
}
