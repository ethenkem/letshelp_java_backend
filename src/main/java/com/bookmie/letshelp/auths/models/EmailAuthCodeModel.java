package com.bookmie.letshelp.auths.models;

import java.time.Instant;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "email_auth_codes")
public class EmailAuthCodeModel {

  @Id
  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String code;

  @Column(name = "created_at", nullable = false)
  @CreationTimestamp
  private Instant createdAt;

  @Column(name = "expires_in", nullable = false)
  private Instant expiresIn;

  @PrePersist
  protected void onCreate() {
    this.expiresIn = Instant.now().plusSeconds(600);
  }

  public EmailAuthCodeModel(String email, String code) {
    this.email = email;
    this.code = code;
  }

  public String getEmail() {
    return this.email;
  }

  public String getCode() {
    return this.code;
  }
}
