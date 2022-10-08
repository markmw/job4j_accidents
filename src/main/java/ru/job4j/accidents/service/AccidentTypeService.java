package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.persistence.AccidentTypeMem;

import java.util.Collection;
import java.util.Optional;

@Service
public class AccidentTypeService {
    private final AccidentTypeMem accidentTypeMem;

    public AccidentTypeService(AccidentTypeMem accidentTypeMem) {
        this.accidentTypeMem = accidentTypeMem;
    }

    public Optional<AccidentType> get(int id) {
        return accidentTypeMem.get(id);
    }

    public AccidentType put(AccidentType type) {
        return accidentTypeMem.put(type);
    }

    public Collection<AccidentType> getTypes() {
        return accidentTypeMem.getTypes();
    }

    public boolean update(AccidentType type) {
        return accidentTypeMem.update(type);
    }
}
