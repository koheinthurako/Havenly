package com.Havenly.Backend.Repo;

import java.time.Instant;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.Entity.Reg_user;

import jakarta.transaction.Transactional;

@Repository
public interface TokenRepo extends JpaRepository<Reg_user, Long> {
    // Custom method to save the token
    @Modifying
    @Transactional
    @Query("UPDATE Reg_user u SET u.resetToken = :token, u.tokenExpiryTime = :expiryTime WHERE u.email = :email")
    void savePasswordResetToken(@Param("email") String email, @Param("token") String token, @Param("expiryTime") Instant expiryTime);

    @Modifying
    @Transactional
    @Query("UPDATE Reg_user u SET u.resetToken = null, u.tokenExpiryTime = null WHERE u.email = :email")
    void invalidateToken(@Param("email") String email);
    // Method to find user by token
    Optional<Reg_user> findByResetToken(String token);
}

