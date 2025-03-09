package com.bookmie.letshelp.auths.services;

import com.bookmie.letshelp.auths.models.AuthModel;
import com.bookmie.letshelp.auths.repositories.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService  implements UserDetailsService {
    @Autowired
    private AuthUserRepository authUserRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AuthModel> user = authUserRepository.findUserByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found with username: " + email);
        }
        return new org.springframework.security.core.userdetails.User(
                user.get().getEmail(),
                user.get().getPassword(),
                Collections.emptyList()
        );
    }
}
