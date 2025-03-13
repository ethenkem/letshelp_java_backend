package com.bookmie.iscriminal.auths.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookmie.iscriminal.auths.dtos.EmailAuthRequest;
import com.bookmie.iscriminal.auths.dtos.SignupRequest;
import com.bookmie.iscriminal.auths.models.AuthModel;
import com.bookmie.iscriminal.auths.models.EmailAuthCodeModel;
import com.bookmie.iscriminal.auths.repositories.AuthUserRepository;
import com.bookmie.iscriminal.auths.repositories.EmailAuthCodesRepository;
import com.bookmie.iscriminal.utils.common.CommonUtils;

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
