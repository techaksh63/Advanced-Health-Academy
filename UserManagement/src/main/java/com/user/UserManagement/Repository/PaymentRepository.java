package com.user.UserManagement.Repository;

import com.user.UserManagement.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query(value = "select count(*) from payment where user_id =:userid and is_payment_success=:isPending", nativeQuery = true)
    Long countPendingPaymentOfProfilesByUserid(@Param(value = "userid") long userid, @Param("isPending") boolean isPending);
    @Query(value = "select payment_id,user_id,profile_id,amount,is_payment_success from payment where user_id =:userid and is_payment_success=:isPending", nativeQuery = true)
    List<Object> AllPendingPaymentOfProfilesByUserid(@Param(value = "userid") long userid, @Param("isPending") boolean isPending);

}
