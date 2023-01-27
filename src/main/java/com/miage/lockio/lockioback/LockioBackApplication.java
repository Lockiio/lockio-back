package com.miage.lockio.lockioback;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LockioBackApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LockioBackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
