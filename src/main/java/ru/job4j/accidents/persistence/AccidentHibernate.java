package ru.job4j.accidents.persistence;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentHibernate {
    private final SessionFactory sf;

    public Accident save(Accident accident) {
        try (Session session = sf.openSession()) {
            session.save(accident);
            return accident;
        }
    }

    public List<Accident> getAccidents() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from Accident", Accident.class).list();
        }
    }

    public Optional<Accident> get(int id) {
        try (Session session = sf.openSession()) {
            return session.createQuery(
                    "from Accident where id = fId", Accident.class)
                    .setParameter("fId", id)
                    .uniqueResultOptional();
        }
    }

    public void update(Accident accident) {
        try (Session session = sf.openSession()) {
            session.update(accident);
        }
    }

    public boolean delete(int id) {
        try (Session session = sf.openSession()) {
            return session.createQuery("delete from Accident where id = fId", Accident.class)
                    .setParameter("fId", id).executeUpdate() > 0;
        }
    }
}
