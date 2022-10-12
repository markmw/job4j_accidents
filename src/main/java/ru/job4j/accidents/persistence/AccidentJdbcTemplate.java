package ru.job4j.accidents.persistence;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public Accident save(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into accidents (name, text, address, accident_type_id)"
                            + " values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, accident.getName());
            statement.setString(2, accident.getText());
            statement.setString(3, accident.getAddress());
            statement.setInt(4, accident.getType().getId());
            return statement;
        }, keyHolder);
        int id = keyHolder.getKey().intValue();
        accident.setId(id);
        saveRule(accident.getRules(), id);
        return accident;
    }

    public void update(Accident accident) {
        int id = accident.getId();
        jdbc.update("update accidents set "
                        + "name = ?, text = ?, address = ?, accident_type_id = ? where id = ?",
                accident.getName(), accident.getText(), accident.getAddress(),
                accident.getType().getId(), id);
        updateType(accident.getType());
        updateAccidentRules(accident.getRules(), id);
    }

    public List<Accident> getAll() {
        return jdbc.query(
                "select * from accidents",
                (rs, row) -> {
                    int id = rs.getInt("id");
                    Accident accident = new Accident(
                            id,
                            rs.getString("name"),
                            rs.getString("text"),
                            rs.getString("address"),
                            getType(rs.getInt("accident_type_id")));
                    accident.setRules(getRules(id));
                    return accident;
                });
    }

    public Optional<Accident> get(int id) {
        Accident rsl = jdbc.queryForObject("select * from accidents where id = ?",
                (rs, row) -> new Accident(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("text"),
                        rs.getString("address"),
                        getType(rs.getInt("type_id"))), id);
        boolean isNotNull = rsl != null;
        if (isNotNull) {
            rsl.setRules(getRules(id));
        }
        return isNotNull ? Optional.of(rsl) : Optional.empty();
    }

    private void saveRule(Collection<Rule> rules, int accidentId) {
        rules.stream().map(Rule::getId).forEach(i -> jdbc.update(
                "insert into accident_rule (rule_id, accident_id) values (?, ?)",
                i, accidentId));
    }

    private AccidentType getType(int typeId) {
        return jdbc.queryForObject("select * from types where id = ?",
                (rs, row) -> new AccidentType(
                        rs.getInt("id"),
                        rs.getString("name")), typeId);
    }

    private List<Integer> getRuleIds(int accidentId) {
        return jdbc.query(
                "select rule_id from accident_rule where accident_id = ?",
                (rs, row) -> rs.getInt("id"), accidentId);
    }

    private List<Rule> getRules(int accidentId) {
        return getRuleIds(accidentId)
                .stream()
                .map(i -> jdbc.queryForObject("select * from rules_table where id = ?",
                        (rs, row) -> new Rule(
                                rs.getInt("id"),
                                rs.getString("name")), i))
                .collect(Collectors.toList());
    }

    private void updateType(AccidentType type) {
        jdbc.update("update rules_table set name = ? where id = ?",
                type.getName(), type.getId());
    }

    private void updateAccidentRules(Collection<Rule> rules, int accidentId) {
        clearAccidentRules(accidentId);
        saveRule(rules, accidentId);
    }

    private void clearAccidentRules(int accidentId) {
        jdbc.update("delete from accident_rule where accident_id = ?", accidentId);
    }
}
