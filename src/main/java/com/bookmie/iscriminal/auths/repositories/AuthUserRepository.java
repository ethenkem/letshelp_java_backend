package com.bookmie.iscriminal.auths.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bookmie.iscriminal.auths.models.AuthModel;

public interface AuthUserRepository extends JpaRepository<AuthModel, Long> {
    Optional<AuthModel> findUserByEmail(String email); 
}
