package com.apollo.appointment.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AppointmentRepository {

    Map<String, Object> save(Map<String, Object> appointment);

    Optional<Map<String, Object>> findById(Long id);

    List<Map<String, Object>> findAll();

    Optional<Map<String, Object>> update(Long id, Map<String, Object> appointment);

    boolean deleteById(Long id);

    boolean existsById(Long id);

    public int updateAppointmentStatus(int appointmentId, String status);

    public int bookSlot(int slotId);

    public int releaseSlot(int slotId);
}
