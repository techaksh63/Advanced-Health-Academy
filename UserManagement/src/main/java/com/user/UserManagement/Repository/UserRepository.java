package com.user.UserManagement.Repository;

import com.user.UserManagement.DTO.UserInfoDTO;
import com.user.UserManagement.Entity.Profile;
import com.user.UserManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "select user_name,email,user_mobile_number,address,total_family_members from user where user_id =:userid",nativeQuery = true)
    Optional<Object> findUserInfoById(@Param(value = "userid") long id);
}
