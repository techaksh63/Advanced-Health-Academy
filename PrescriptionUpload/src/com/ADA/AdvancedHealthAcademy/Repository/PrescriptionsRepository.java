package com.ADA.AdvancedHealthAcademy.Repository;

import com.ADA.AdvancedHealthAcademy.Entity.Prescriptions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionsRepository extends JpaRepository<Prescriptions, Long> {
}
