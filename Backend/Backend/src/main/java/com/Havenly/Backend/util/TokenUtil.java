package com.Havenly.Backend.util;

import java.util.UUID;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TokenUtil {
    public static String generateToken() {
        return UUID.randomUUID().toString();
    }

    public static Instant getExpiryTime(int minutes) {
        return Instant.now().plus(minutes, ChronoUnit.MINUTES);
    }
}