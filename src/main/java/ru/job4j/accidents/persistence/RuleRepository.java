package ru.job4j.accidents.persistence;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Rule;

public interface RuleRepository extends CrudRepository<Rule, Integer> {
}
