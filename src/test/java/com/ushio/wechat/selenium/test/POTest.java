package com.ushio.wechat.selenium.test;

import com.ushio.wechat.selenium.MainPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author: ushio
 * @description:
 **/
public class POTest {

    private static MainPage mainPage;

    @BeforeAll
    public static void beforeAll(){
        mainPage = new MainPage();
    }

    @Test
    void menuContactsTabTest() {
        mainPage.click(By.id("menu_contacts"));
    }

    @Test
    void addDepartTest() {
        String departName = "java5";
        assertTrue(mainPage.goToContact().addDepart(departName).searchDepart(departName).getPartyInfo().contains(departName));
    }

    @Test
    void addDepartNameTest() {
        String departName = "java6";
        assertThat(departName,equalTo(mainPage.goToContact().addDepart(departName).searchDepart(departName).getPartyName()));
    }

}
