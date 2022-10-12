package ru.job4j.accidents.persistence;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Accident;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {
}
