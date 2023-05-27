package com.MTransaction.microservice.repositories;

import com.MTransaction.microservice.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
   User findUserByMobileNumber(String mobileNumber);
   User findUserByEmail(String email);
}
