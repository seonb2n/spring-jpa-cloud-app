package com.example.demo.common.util.log;

import com.example.demo.domain.log.UserLog;
import com.example.demo.infrastructure.log.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public UserLog logInTryLog(String userEmail, LocalDateTime triedTime) {
        UserLog log = new UserLog(userEmail, triedTime, "유저 로그인 시도");
        logRepository.save(log);
        return log;
    }
}
