package com.annieryannel.userssecure.service;

import com.annieryannel.userssecure.entities.Role;
import com.annieryannel.userssecure.entities.User;
import com.annieryannel.userssecure.repositories.RoleRepository;
import com.annieryannel.userssecure.repositories.UserRepository;
import com.annieryannel.userssecure.utils.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService implements UserDetailsService, ApplicationListener<AuthenticationSuccessEvent> {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public List<User> loadAllUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {return false;}
        userRepository.save(setRegistrationParams(user));
        return true;
    }

    private User setRegistrationParams(User user) {
        user.addRole(roleRepository.findByRole(Roles.ROLE_USER.getAuthority()));
        user.addRole(roleRepository.findByRole(Roles.ROLE_ACTIVE.getAuthority()));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRegistrationDate(new Date());
        return user;
    }

    public boolean isUserActive(User user) {
        return user != null && user.isAccountNonLocked();
    }

    public User getCurrentUser(){
        String username = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUsername();
        return getUserByUsername(username);
    }

    public void addRoleToUser(Long id, Roles roleName) {
        Role role = roleRepository.findByRole(roleName.toString());
        User user = userRepository.findById(id).get();
        user.addRole(role);
        userRepository.save(user);
    }

    public void removeRoleFromUser(Long id, Roles roleName) {
        Role role = roleRepository.findByRole(roleName.toString());
        User user = userRepository.findById(id).get();
        user.removeRole(role);
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String userName = ((UserDetails) event.getAuthentication()
                .getPrincipal()).getUsername();
        User user = userRepository.findByUsername(userName);
        user.setLastLoginDate(new Date());
        userRepository.save(user);
    }
}
