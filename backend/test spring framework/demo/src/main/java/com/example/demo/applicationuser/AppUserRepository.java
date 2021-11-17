package com.example.demo.applicationuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<UpdatedUser, String> {
    Optional<UpdatedUser> findByEmail(String email);
}
