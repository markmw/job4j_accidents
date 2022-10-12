package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.persistence.AccidentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {
    private final AccidentRepository accidentRepository;

    public AccidentService(AccidentRepository accidentRepository) {
        this.accidentRepository = accidentRepository;
    }

    public Optional<Accident> get(int id) {
        return accidentRepository.findById(id);
    }

    public void save(Accident accident) {
        accidentRepository.save(accident);
    }

    public List<Accident> getAll() {
        var rsl = new ArrayList<Accident>();
        for (var accident : accidentRepository.findAll()) {
            rsl.add(accident);
        }
        return rsl;
    }
}
