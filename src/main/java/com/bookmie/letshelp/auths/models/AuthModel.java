package com.bookmie.letshelp.auths.models;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.query.sqm.CastType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "users")
public class AuthModel {
  @Id
  @GeneratedValue
  @Column(name = "user_id", unique = true, nullable = false)
  private UUID userId;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(name = "joined_on", nullable = false, updatable = false)
  @CreationTimestamp
  private Instant joinedOn;

  @UpdateTimestamp
  @Column(name = "last_updated", nullable = false)
  private Instant lastUpdated;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private UserProfileModel userProfile;

  public AuthModel(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }
}
