package com.gbconnect.groupby.services;

import com.gbconnect.groupby.domain.Role;
import com.gbconnect.groupby.domain.User;
import com.gbconnect.groupby.domain.UserRequest;
import com.gbconnect.groupby.domain.UserResponse;
import com.gbconnect.groupby.repositories.RoleRepository;
import com.gbconnect.groupby.repositories.UserRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(s);
    }

    public UserResponse createUser(UserRequest user) {
        Role role = this.roleRepository.findByAuthority("ROLE_USER");
        User userBuilt = User
                .builder()
                .authorities(ImmutableList.of(role))
                .username(user.getEmail())
                .password(user.getPassword())
                .enabled(true)
                .credentialsNonExpired(true)
                .accountNonLocked(true)
                .accountNonExpired(true)
                .build();
        userBuilt = this.userRepository.save(userBuilt);

        return UserResponse
                .builder()
                .id(userBuilt.getId())
                .authorities(userBuilt.getAuthorities())
                .email(userBuilt.getUsername())
                .accountNonExpired(userBuilt.isAccountNonExpired())
                .accountNonLocked(userBuilt.isAccountNonLocked())
                .credentialsNonExpired(userBuilt.isCredentialsNonExpired())
                .build();
    }
}
