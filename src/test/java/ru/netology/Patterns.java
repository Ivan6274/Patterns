package ru.netology;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Locale;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class Patterns {


    private final Faker faker = new Faker(new Locale("ru"));


    @Test
    void cardDeliveryWithCalendar() {
        open("http://localhost:9999");
        $("[data-test-id='city'] .input__control").setValue(faker.address().cityName());

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
        $("[data-test-id='name'] .input__control").setValue(faker.name().fullName());
        $("[data-test-id=\"phone\"] .input__control").setValue(faker.phoneNumber().phoneNumber());
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("[role=\"button\"] .button__content").click();
        $(withText("Успешно")).shouldBe(Condition.visible, Duration.ofSeconds(25));
        $(withText(new DecimalFormat("00").format(needDay.getDayOfMonth()) + "." + new DecimalFormat("00").format(needDay.getMonthValue()) + "." + needDay.getYear())).shouldBe(Condition.visible, Duration.ofSeconds(25));
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
        $(withText(new DecimalFormat("00").format(newDay.getDayOfMonth()) + "." + new DecimalFormat("00").format(newDay.getMonthValue()) + "." + newDay.getYear())).shouldBe(Condition.visible, Duration.ofSeconds(25));

    }
}
