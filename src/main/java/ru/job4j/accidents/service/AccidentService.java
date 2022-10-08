package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.persistence.AccidentMem;

import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {
    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public Optional<Accident> get(int id) {
        return accidentMem.get(id);
    }

    public Accident create(Accident accident) {
        return accidentMem.create(accident);
    }

    public List<Accident> getAccidents() {
        return accidentMem.getAccidents().stream().toList();
    }

    public boolean update(Accident accident) {
        return accidentMem.update(accident);
    }
}
