package com.ADA.AdvancedHealthAcademy.Repository;

import com.ADA.AdvancedHealthAcademy.Entity.PrescriptionsUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionsUploadRepository extends JpaRepository<PrescriptionsUpload, Long> {

    @Query(value = "SELECT count(id) FROM profile WHERE id=:id",nativeQuery = true)
    Long profileCountById(@Param("id")long id);
    @Query(value = "SELECT is_active FROM profile WHERE id=:id",nativeQuery = true)
    boolean isActiveProfile(@Param("id")long id);

}
