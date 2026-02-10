package com.user.repositry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByRole(String role);
}
