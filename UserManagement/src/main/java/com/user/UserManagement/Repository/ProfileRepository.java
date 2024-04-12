package com.user.UserManagement.Repository;

import com.user.UserManagement.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
//    @Query(value = "select * from profile where user_id =:userid and id =:profileid",nativeQuery = true)
//    Optional<Profile>findProfileAllInfo(@Param(value = "userid") long userid, @Param(value = "profileid") long profileid);

    @Query(value = "select * from profile where user_id =:userid and relationship =:relationship",nativeQuery = true)
    Optional<Profile> findProfileByRelationship(@Param(value = "userid") long userid,@Param(value = "relationship")String relationship);

    @Query(value = "select * from profile where user_id =:userid and id =:profileid and is_active=:isActive",nativeQuery = true)
    Optional<Profile>findActiveProfileAllInfo(@Param(value = "userid") long userid, @Param(value = "profileid") long profileid, @Param("isActive") boolean isActive);

    @Query(value = "select full_name,relationship,gender,birth_date,blood_group,diabetes_status,blood_pressure_status,current_disease,previous_surgeries,previously_cured_diseases,height,weight,is_active from profile where user_id =:userid and id =:profileid",nativeQuery = true)
    Optional<Object> findProfileDetailsById(@Param(value = "userid") long userid, @Param(value = "profileid") long profileid);
    @Query(value = "select full_name,relationship,gender,birth_date,blood_group,diabetes_status,blood_pressure_status,current_disease,previous_surgeries,previously_cured_diseases,height,weight,is_active from profile where user_id =:userid and id =:profileid and is_active=:isActive",nativeQuery = true)
    Optional<Object>findActiveProfileDetailsById(@Param(value = "userid") long userid, @Param(value = "profileid") long profileid, @Param("isActive") boolean isActive);


    @Query(value = "select count(*) from profile where user_id =:userid and is_active=:isActive", nativeQuery = true)
    Long countActiveProfilesByUserid(@Param(value = "userid") long userid, @Param("isActive") boolean isActive);


    @Query(value = "select full_name,relationship,gender,is_active from profile where user_id =:userid",nativeQuery = true)
    List<Object> findAllProfilesInfoByUserid(@Param(value = "userid") long id);
    @Query(value = "select full_name,relationship,gender,is_active from profile where user_id =:userid and is_active=:isActive",nativeQuery = true)
    List<Object> findAllActiveProfilesInfoByUserid(@Param(value = "userid") long id, @Param("isActive") boolean isActive);


    @Query(value = "SELECT count(id) FROM profile WHERE id=:id",nativeQuery = true)
    Long profileCountById(@Param("id")long id);
    @Query(value = "SELECT is_active FROM profile WHERE id=:id",nativeQuery = true)
    boolean isActiveProfile(@Param("id")long id);
}
