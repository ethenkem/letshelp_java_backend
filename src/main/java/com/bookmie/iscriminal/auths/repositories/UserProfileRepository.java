package com.bookmie.iscriminal.auths.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmie.iscriminal.auths.models.UserProfileModel;

public interface UserProfileRepository extends JpaRepository<UserProfileModel, Long> {
  
}

