package com.example.demo.service;

import com.auth0.jwt.JWT;
import com.example.demo.dto.UserEnrollDto;
import com.example.demo.entity.User;
import com.example.demo.entity.UserAuthority;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public User userEnrollService(UserEnrollDto userEnrollDto) {
        User user = new User();
        user.setUserEmail(userEnrollDto.getUserEmail());
        user.setUserNickName(userEnrollDto.getUserNickName());
        user.setPassword(userEnrollDto.getPassword());
        user.setStartDayTime(userEnrollDto.getStartDayTime());
        user.setEndDayTime(userEnrollDto.getEndDayTime());

        User user1 = userRepository.save(user);
        addAuthority(user1.getId(), "ROLE_USER");
        return userRepository.save(user1);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUserEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public void addAuthority(Long userId, String authority) {
        userRepository.findUserById(userId).ifPresent(user -> {
            UserAuthority role = new UserAuthority(userId, authority);
            HashSet<UserAuthority> authorityHashSet = new HashSet<>();
            if(user.getAuthorities() == null){
                authorityHashSet.add(role);
                user.setAuthorities(authorityHashSet);
                userRepository.save(user);
            } else if(!user.getAuthorities().contains(role)) {
                authorityHashSet.addAll(user.getAuthorities());
                authorityHashSet.add(role);
                userRepository.save(user);
            }
        });
    }

    public User logIn(String userEmail, String password) {
        userRepository.findUserByUserEmail(userEmail).ifPresent(user -> {
            if(user.getPassword().equals(password)) {
                return;
            } else {
                throw new IllegalArgumentException("아이디 혹은 비밀번호가 잘못됐습니다.");
            }
        });

        return userRepository.findUserByUserEmail(userEmail).get();
    }
}
