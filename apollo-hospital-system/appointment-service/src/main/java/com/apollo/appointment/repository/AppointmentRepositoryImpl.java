package com.apollo.appointment.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class AppointmentRepositoryImpl implements AppointmentRepository {

    private static final String SELECT_COLUMNS =
            "id, patient_id, patient_name, doctor_id, department, appointment_time, status, created_on, updated_on";

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public AppointmentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public Map<String, Object> save(Map<String, Object> appointment) {
        String sql = """
                INSERT INTO appointments
                (patient_id, patient_name, doctor_id, department, appointment_time, status, created_on, updated_on)
                VALUES (:patientId, :patientName, :doctorId, :department, :appointmentTime, :status, NOW(), NOW())
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("patientId", appointment.get("patientId"))
                .addValue("patientName", appointment.get("patientName"))
                .addValue("doctorId", appointment.get("doctorId"))
                .addValue("department", appointment.get("department"))
                .addValue("appointmentTime", appointment.get("appointmentTime"))
                .addValue("status", appointment.get("status"));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(sql, params, keyHolder, new String[]{"id"});

        Long generatedId = keyHolder.getKey().longValue();

        // read the row back so we get the actual created_on/updated_on set by NOW()
        return findById(generatedId)
                .orElseThrow(() -> new IllegalStateException("Failed to load appointment right after insert, id=" + generatedId));
    }

    @Override
    public Optional<Map<String, Object>> findById(Long id) {
        String sql = "SELECT " + SELECT_COLUMNS + " FROM appointments WHERE id = ?";

        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, id);
        return results.stream().findFirst();
    }

    @Override
    public List<Map<String, Object>> findAll() {
        String sql = "SELECT " + SELECT_COLUMNS + " FROM apollo_hospitals.appointments ORDER BY id DESC";

        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public Optional<Map<String, Object>> update(Long id, Map<String, Object> appointment) {
        String sql = """
                UPDATE appointments
                SET patient_id = :patientId,
                    patient_name = :patientName,
                    doctor_id = :doctorId,
                    department = :department,
                    appointment_time = :appointmentTime,
                    status = :status,
                    updated_on = NOW()
                WHERE id = :id
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("patientId", appointment.get("patientId"))
                .addValue("patientName", appointment.get("patientName"))
                .addValue("doctorId", appointment.get("doctorId"))
                .addValue("department", appointment.get("department"))
                .addValue("appointmentTime", appointment.get("appointmentTime"))
                .addValue("status", appointment.get("status"))
                .addValue("id", id);

        int rowsAffected = namedJdbcTemplate.update(sql, params);
        if (rowsAffected == 0) {
            return Optional.empty();
        }

        return findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM appointments WHERE id = ?";

        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(*) FROM appointments WHERE id = ?";

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    @Override
    public int updateAppointmentStatus(int appointmentId, String status) {

        String sql = """
        UPDATE appointments
        SET status = ?
        WHERE id = ?
        """;

        return jdbcTemplate.update(sql, status, appointmentId);
    }
    @Override
    public int bookSlot(int slotId) {

        String sql = """
        UPDATE slot
        SET status = 'BOOKED'
        WHERE id = ?
        """;

        return jdbcTemplate.update(sql, slotId);
    }
    @Override
    public int releaseSlot(int slotId) {

        String sql = """
        UPDATE slot
        SET status = 'AVAILABLE'
        WHERE id = ?
        """;

        return jdbcTemplate.update(sql, slotId);
    }
}
