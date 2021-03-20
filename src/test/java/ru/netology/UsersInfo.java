package ru.netology;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Locale;

@Data
@RequiredArgsConstructor

public class UsersInfo {
    Faker faker = new Faker(new Locale("ru"));
    private String name;
    private String phone;
    private String city;
}
