package ru.job4j.accidents.persistence;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public Accident save(Accident accident) {
        jdbc.update(
                "insert into accidents (name, text, address, accident_type_id, rules_id) values(?, ?, ?, ?, ?)",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType(),
                accident.getRules());
        return accident;
    }

    public Accident update(Accident accident) {
        jdbc.update(
                "update accidents set name = ?, text = ?, address = ?, accident_type_id = ?, rules_id = ? where id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType(),
                accident.getRules(),
                accident.getId());
        return accident;
    }

    public List<Accident> getAll() {
        return jdbc.query("select * from accidents",
                (rs, row) -> {
            Accident accident = new Accident();
            accident.setId(rs.getInt("id"));
            accident.setName(rs.getString("name"));
            accident.setText(rs.getString("text"));
            accident.setAddress(rs.getString("address"));
            accident.setType(new AccidentType(rs.getInt("accident_type_id")));
            accident.setRules(new ArrayList<>(rs.getInt("rules_id")));
            return accident;
                });
    }

    public Accident get(int id) {
        Accident rsl = jdbc.queryForObject("select * from accidents where id = ?", (rs, row) -> {
            Accident accident = new Accident();
            accident.setId(rs.getInt("id"));
            accident.setName(rs.getString("name"));
            accident.setText(rs.getString("text"));
            accident.setAddress(rs.getString("address"));
            accident.setType(new AccidentType(rs.getInt("accident_type_id")));
            accident.setRules(new ArrayList<>(rs.getInt("rules_id")));
            return accident;}, id);
        return rsl;
    }
}
