package com.kmuniz.lostserver.service;

import com.kmuniz.lostserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  {

    @Autowired
    private UserRepository userRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                AuthorityUtils.createAuthorityList("ROLE_" + user.getRole()));
//    }
}
