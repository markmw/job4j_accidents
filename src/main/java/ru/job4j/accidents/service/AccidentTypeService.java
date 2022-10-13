package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.persistence.AccidentTypeRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class AccidentTypeService {
    private final AccidentTypeRepository accidentTypeRepository;

    public AccidentTypeService(AccidentTypeRepository accidentTypeRepository) {
        this.accidentTypeRepository = accidentTypeRepository;
    }

    public Optional<AccidentType> get(int id) {
        return accidentTypeRepository.findById(id);
    }

    public AccidentType put(AccidentType type) {
        return accidentTypeRepository.save(type);
    }

    public Collection<AccidentType> getTypes() {
        var rsl = new ArrayList<AccidentType>();
        for (var accidentType : accidentTypeRepository.findAll()) {
            rsl.add(accidentType);
        }
        return rsl;
    }
}
