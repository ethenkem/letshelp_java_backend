package com.bookmie.letshelp.auths.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookmie.letshelp.auths.dtos.EmailAuthRequest;
import com.bookmie.letshelp.auths.dtos.SignupRequest;
import com.bookmie.letshelp.auths.models.AuthModel;
import com.bookmie.letshelp.auths.models.EmailAuthCodeModel;
import com.bookmie.letshelp.auths.repositories.AuthUserRepository;
import com.bookmie.letshelp.auths.repositories.EmailAuthCodesRepository;
import com.bookmie.letshelp.utils.common.CommonUtils;

@Service
public class AuthService {

  @Autowired
  private AuthUserRepository authRepository;

  @Autowired
  private EmailAuthCodesRepository emailAuthCodesRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthModel createAuthUser(SignupRequest requestData) {
    String authCode = CommonUtils.generateEmailAuthCode();
    AuthModel authModel = new AuthModel(requestData.email(), passwordEncoder.encode(requestData.password()));
    EmailAuthCodeModel emailAuth = new EmailAuthCodeModel(requestData.email(), passwordEncoder.encode(authCode));
    Optional<AuthModel> user = authRepository.findUserByEmail(requestData.email());
    if (user.isEmpty()) {
      this.emailAuthCodesRepository.save(emailAuth);
      return this.authRepository.save(authModel);
    }
    return null;
  }

  public boolean verifyEmail(EmailAuthRequest requestData) {
    Optional<EmailAuthCodeModel> emailAuthCodeModel = this.emailAuthCodesRepository
        .findEmailAuthCodeByEmail(requestData.email());
    if (emailAuthCodeModel.isPresent()) {
      EmailAuthCodeModel codeAuth = emailAuthCodeModel.get();
      if (passwordEncoder.matches(requestData.code(), codeAuth.getCode())) {
        return true;
      } else {
        return false;
      }
    }
    return false;
  }
}
