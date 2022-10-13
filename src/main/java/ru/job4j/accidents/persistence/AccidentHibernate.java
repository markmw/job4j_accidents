package ru.job4j.accidents.persistence;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentHibernate {
    private final CrudRepository crudRepository;

    public Accident save(Accident accident) {
        crudRepository.run(session -> session.save(accident));
        return accident;
    }

    public List<Accident> getAccidents() {
        return crudRepository.query("from Accident", Accident.class);
    }

    public Optional<Accident> get(int id) {
            return crudRepository.optional(
                    "from Accident where id = fId",
                    Accident.class,
                    Map.of("fId", id));
    }

    public void update(Accident accident) {
        crudRepository.run(session -> session.createQuery(
                "update Accident set name = fName, text = fText, address = fAddress, accident_type_id = fAccident_type_id, rules = fRules")
                .setParameter("fName", accident.getName())
                .setParameter("fText", accident.getText())
                .setParameter("fAddress", accident.getAddress())
                .setParameter("fAccident_type_id", accident.getType())
                .setParameter("fRules", accident.getRules())
                .executeUpdate());
    }

    public void delete(int id) {
        crudRepository.run(
                "delete from Accident where id = fId",
                        Map.of("fId", id));
    }
}
