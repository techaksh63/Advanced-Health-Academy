package com.user.UserManagement.Repository;

import com.user.UserManagement.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    @Query(value = "select * from profile where user_id =:userid",nativeQuery = true)
    List<Profile> findByUserid(@Param(value = "userid") long id);
}
