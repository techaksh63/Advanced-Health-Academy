package com.user.UserManagement.Repository;

import com.user.UserManagement.DTO.ProfileInfoDTO;
import com.user.UserManagement.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    @Query(value = "select * from profile where user_id =:userid and id =:profileid",nativeQuery = true)
    Optional<Profile>findProfileAllByUserid(@Param(value = "userid") long userid, @Param(value = "profileid") long profileid);
//    @Query(value = "select * from profile where user_id =:userid and is_active=:true",nativeQuery = true)
//    List<Profile>findActiveProfilesByUserid(@Param(value = "userid") long userid, @Param(value = "profileid") long profileid);
//    @Query(value = "select count(*) from profile where user_id =:userid and is_active=:true",nativeQuery = true)
//    Integer countActiveProfilesByUserid(@Param(value = "userid") long userid);
// After (corrected):
@Query(value = "select count(*) from profile where user_id =:userid and is_active=:isActive", nativeQuery = true)
Long countActiveProfilesByUserid(@Param(value = "userid") long userid, @Param("isActive") boolean isActive);

    @Query(value = "select full_name,relationship,gender from profile where user_id =:userid",nativeQuery = true)
    List<Object> findProfileInfoByUserid(@Param(value = "userid") long id);

}
