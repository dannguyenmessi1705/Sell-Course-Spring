package com.learn.course.auth;

import com.learn.course.exception.UnAuthorizedException;
import com.learn.course.model.entity.UsersEntity;
import com.learn.course.repository.UserRepository;
import com.learn.course.util.ValidateUtils;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, UnAuthorizedException {
    String usernameTrim = StringUtils.trimAllWhitespace(username);
    UsersEntity user = null;
    if (ValidateUtils.isValidEmail(usernameTrim)) {
      Optional<UsersEntity> userOptional = userRepository.findUsersEntityByEmail(usernameTrim);
      if (userOptional.isPresent()) {
        user = userOptional.get();
      }
    } else {
      Optional<UsersEntity> userOptional = userRepository.findUsersEntityByUsername(usernameTrim);
      if (userOptional.isPresent()) {
        user = userOptional.get();
      }
    }
    if (user == null) {
      throw new UnAuthorizedException("User not found");
    }
    return new CustomUserPrincipal(user);
  }
}
