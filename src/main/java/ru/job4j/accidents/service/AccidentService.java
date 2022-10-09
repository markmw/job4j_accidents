package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.persistence.AccidentJdbcTemplate;

import java.util.List;

@Service
public class AccidentService {
    private final AccidentJdbcTemplate accidentJdbcTemplate;

    public AccidentService(AccidentJdbcTemplate accidentJdbcTemplate) {
        this.accidentJdbcTemplate = accidentJdbcTemplate;
    }

    public Accident get(int id) {
        return accidentJdbcTemplate.get(id);
    }

    public Accident save(Accident accident) {
        return accidentJdbcTemplate.save(accident);
    }

    public List<Accident> getAll() {
        return accidentJdbcTemplate.getAll();
    }

    public Accident update(Accident accident) {
        return accidentJdbcTemplate.update(accident);
    }
}
