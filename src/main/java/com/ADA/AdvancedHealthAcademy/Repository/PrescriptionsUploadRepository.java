package com.ADA.AdvancedHealthAcademy.Repository;

import com.ADA.AdvancedHealthAcademy.Entity.PrescriptionsUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionsUploadRepository extends JpaRepository<PrescriptionsUpload, Long> {
}
