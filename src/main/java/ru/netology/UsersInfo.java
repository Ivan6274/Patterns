package ru.netology;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class UsersInfo {
    private String name;
    private String phone;
    private String city;
}
