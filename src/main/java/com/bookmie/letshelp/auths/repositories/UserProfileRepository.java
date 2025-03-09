package com.bookmie.letshelp.auths.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmie.letshelp.auths.models.UserProfileModel;

public interface UserProfileRepository extends JpaRepository<UserProfileModel, Long> {
  
}

