package com.bookmie.letshelp.auths.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bookmie.letshelp.auths.models.AuthModel;

public interface AuthUserRepository extends JpaRepository<AuthModel, Long> {
    Optional<AuthModel> findUserByEmail(String email); 
}
