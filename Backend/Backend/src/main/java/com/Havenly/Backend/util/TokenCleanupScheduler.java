//package com.Havenly.Backend.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import java.time.Instant;
//import com.Havenly.Backend.Repo.TokenRepo;
//
//@Component
//public class TokenCleanupScheduler {
//
//    @Autowired
//    private TokenRepo tokenRepo;
//
//    @Scheduled(fixedRate = 86400000)  // Run once a day
//    public void cleanupExpiredTokens() {
//        Instant now = Instant.now();
//        tokenRepo.cleanupExpiredTokens(now);
//    }
//}
