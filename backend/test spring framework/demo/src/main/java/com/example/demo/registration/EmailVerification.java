package com.example.demo.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailVerification implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return true;
    }
}
