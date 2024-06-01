//package com.Havenly.Backend.Config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import jakarta.servlet.MultipartConfigElement;
//
//@Configuration
//public class DebugConfig {
//
//    private static final Logger logger = LoggerFactory.getLogger(DebugConfig.class);
//
//    @Bean
//    public CommandLineRunner logMultipartConfig(MultipartConfigElement multipartConfigElement) {
//        return args -> {
//            logger.info("Max file size: {}", multipartConfigElement.getMaxFileSize());
//            logger.info("Max request size: {}", multipartConfigElement.getMaxRequestSize());
//        };
//    }
//}
//
