package com.digitalholics.appointmentservice.domain.persistence;

import com.digitalholics.appointmentservice.domain.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    //List<Appointment> findAppointmentsByTherapyId(Integer theraphyId);
    Appointment findByTopic(String topic);





}
