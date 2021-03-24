package ru.netology;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }
    @Value
    public static class Registration {
        private Registration (){}

        public static UserInfo getRegistrationInfo() {
            Faker faker = new Faker(new Locale("ru"));
            return new UserInfo(
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber(),
                    faker.address().city()

            );
        }
    }
}
