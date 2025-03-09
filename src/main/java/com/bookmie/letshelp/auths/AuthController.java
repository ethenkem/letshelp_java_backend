package com.bookmie.letshelp.auths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmie.letshelp.auths.dtos.EmailAuthRequest;
import com.bookmie.letshelp.auths.dtos.ResponseDto;
import com.bookmie.letshelp.auths.dtos.SignupRequest;
import com.bookmie.letshelp.auths.models.AuthModel;
import com.bookmie.letshelp.auths.services.AuthService;

@RestController
@RequestMapping("/auths")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<ResponseDto> signUp(@RequestBody SignupRequest request) {
    AuthModel authModel = this.authService.createAuthUser(request);
    if (authModel != null) {
      return ResponseEntity.ok(new ResponseDto(true, "success", request));
    }
    return ResponseEntity.badRequest().body(new ResponseDto(false, "user with email already exists", null));
  }

  @PostMapping("/verify-email")
  public ResponseEntity<ResponseDto> verifyEmail(@RequestBody EmailAuthRequest request) {
    if (this.authService.verifyEmail(request)) {
      return ResponseEntity.ok(new ResponseDto(true, "email is verified", null));
    }
    return ResponseEntity.badRequest().body(new ResponseDto(false, "incorrect email or code", null));
  }
}
