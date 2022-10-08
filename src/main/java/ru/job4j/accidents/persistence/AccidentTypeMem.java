package ru.job4j.accidents.persistence;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentTypeMem {
    private final Map<Integer, AccidentType> types = new HashMap<>();
    private final AtomicInteger size = new AtomicInteger(types.size());

    public Optional<AccidentType> get(int id) {
        return Optional.of(types.get(id));
    }

    public AccidentType put(AccidentType type) {
        int id = size.incrementAndGet();
        type.setId(id);
        types.put(id, type);
        return type;
    }

    public Collection<AccidentType> getTypes() {
        return types.values();
    }

    public boolean update(AccidentType type) {
        return types.replace(type.getId(), type) != null;
    }
}
