package com.bookmie.letshelp.auths.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "user_profiles")
@Entity
public class UserProfileModel {
  @Id
  @Column(name = "user_id")
  private UUID userId; 

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @OneToOne
  @MapsId
  @JoinColumn(name = "user_id", referencedColumnName = "user_id" )
  private AuthModel user;

}
