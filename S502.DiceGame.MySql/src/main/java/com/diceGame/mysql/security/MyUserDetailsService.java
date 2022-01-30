package com.diceGame.mysql.security;

import com.diceGame.mysql.model.domain.Player;
import com.diceGame.mysql.model.persistance.PlayerMysqlRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

	private PlayerMysqlRepository playerRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        final Player user = playerRepository.findByName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + userName + "' not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(userName)
                .password(user.getPassword())
                .authorities(user.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}