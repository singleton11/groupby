package com.gbconnect.groupby.services;

import com.gbconnect.groupby.domain.Role;
import com.gbconnect.groupby.domain.User;
import com.gbconnect.groupby.domain.Register;
import com.gbconnect.groupby.repositories.RoleRepository;
import com.gbconnect.groupby.repositories.UserRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    public boolean checkPassword(UserDetails user, String password) {
        return new BCryptPasswordEncoder().matches(password, user.getPassword());
    }

    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public boolean isUserExists(String email) {
        return userRepository.existsByUsername(email);
    }
}
