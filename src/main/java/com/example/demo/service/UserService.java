package com.example.demo.service;

import com.example.demo.dto.UserEnrollDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User userEnrollService(UserEnrollDto userEnrollDto) {
        User user = new User();
        user.setUserEmail(userEnrollDto.getUserEmail());
        user.setUserNickName(userEnrollDto.getUserNickName());
        user.setPassword(userEnrollDto.getPassword());
        user.setStartDayTime(userEnrollDto.getStartDayTime());
        user.setEndDayTime(userEnrollDto.getEndDayTime());

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUserEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
