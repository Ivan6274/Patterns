package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class Patterns {

    @Test
    void cardDeliveryWithCalendar() {

        open("http://localhost:9999");
//        $("[data-test-id='city'] .input__control").setValue(new UsersInfo().getCity());
        $("[data-test-id='city'] .input__control").setValue(new UsersInfo().faker.address().cityName());
        $(By.cssSelector(("[data-test-id=\"date\"]  .input__box"))).click();
        int addDay = 7;
        LocalDate today = LocalDate.now();
        LocalDate needDay = today.plusDays(addDay);
        if ((needDay.getDayOfMonth() + addDay) >= LocalDate.MAX.getDayOfMonth()) {
            $(withText(String.valueOf(needDay.getDayOfMonth()))).click();
        } else {
            $(By.cssSelector(".calendar__title [class='calendar__arrow calendar__arrow_direction_right']")).click();
            $(byText(String.valueOf(needDay.getDayOfMonth()))).click();
        }

        $("[data-test-id='name'] .input__control").setValue(new UsersInfo().faker.name().fullName());
//        $("[data-test-id='name'] .input__control").setValue(new UsersInfo().getName());
        $("[data-test-id=\"phone\"] .input__control").setValue(new UsersInfo().faker.phoneNumber().phoneNumber());
//        $("[data-test-id=\"phone\"] .input__control").setValue(new UsersInfo().getPhone());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("[role=\"button\"] .button__content").click();
        $(withText("Успешно")).shouldBe(Condition.visible, Duration.ofSeconds(25));
        $(withText(needDay.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))).shouldBe(Condition.visible, Duration.ofSeconds(25));
        $(By.cssSelector(("[data-test-id=\"date\"]  .input__box"))).click();
        int addDay1 = 10;
        LocalDate newDay = today.plusDays(addDay1);
        if ((newDay.getDayOfMonth() + addDay1) >= LocalDate.MAX.getDayOfMonth()) {
            $(withText(String.valueOf(newDay.getDayOfMonth()))).click();
        } else {
            $(By.cssSelector(".calendar__title [class='calendar__arrow calendar__arrow_direction_right']")).click();
            $(byText(String.valueOf(newDay.getDayOfMonth()))).click();
        }
        $("[role=\"button\"] .button__content").click();
        $(withText("Перепланировать")).shouldBe(Condition.visible, Duration.ofSeconds(25)).click();
        $(withText(newDay.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))).shouldBe(Condition.visible, Duration.ofSeconds(25));

    }
}
