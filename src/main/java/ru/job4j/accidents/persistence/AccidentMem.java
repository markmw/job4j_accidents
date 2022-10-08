package ru.job4j.accidents.persistence;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final HashMap<Integer, Accident> accidents = new HashMap<>();
    private final AtomicInteger size = new AtomicInteger(accidents.size());

    public Optional<Accident> get(int id) {
        return Optional.of(accidents.get(id));
    }

    public Accident create(Accident accident) {
        int id = size.incrementAndGet();
        accident.setId(id);
        accidents.put(id, accident);
        return accident;
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public boolean update(Accident accident) {
        return accidents.replace(accident.getId(), accident) != null;
    }
}
