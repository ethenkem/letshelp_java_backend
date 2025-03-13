package com.bookmie.iscriminal.auths.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmie.iscriminal.auths.models.EmailAuthCodeModel;

public interface EmailAuthCodesRepository extends JpaRepository<EmailAuthCodeModel,Long> {
  Optional<EmailAuthCodeModel> findEmailAuthCodeByEmail (String email);   
}
