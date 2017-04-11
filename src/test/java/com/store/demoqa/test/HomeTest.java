package com.store.demoqa.test;

import com.store.demoqa.BaseTest;
import com.store.demoqa.pages.HomePage;
import com.store.demoqa.rules.ScreenShotOnFailRule;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.store.demoqa.pages.WarningMessages.INVALID_SEARCH;
import static io.qala.datagen.RandomShortApi.numeric;
import static io.qala.datagen.RandomShortApi.unicode;
import static io.qala.datagen.RandomValue.length;
import static io.qala.datagen.StringModifier.Impls.prefix;
import static io.qala.datagen.StringModifier.Impls.specialSymbol;

@RunWith(DataProviderRunner.class)
public class HomeTest extends BaseTest {

    private HomePage homePage;

    @DataProvider
    public static Object[][] bordersOfFieldValuesSearch() {
        // @formatter:off
        return new Object[][]{
                {length(10).with(prefix("Search")).numeric(), INVALID_SEARCH.getWarningMessages()},
                {length(20).with(specialSymbol()).english(), INVALID_SEARCH.getWarningMessages()},
                {unicode(5, 10), INVALID_SEARCH.getWarningMessages()},
                {numeric(5, 19), INVALID_SEARCH.getWarningMessages()},
        };
        // @formatter:on
    }

    @Rule
    public ScreenShotOnFailRule screenShotOnFailRule = new ScreenShotOnFailRule();

    @Before
    public void setUp() {
        homePage = new HomePage();
    }

    @Test
    public void verifyMainMenu() {
        homePage.verifyMainMenuElements();
    }

    @Test
    @UseDataProvider("bordersOfFieldValuesSearch")
    public void checkTheInvalidSearch(String valueSearch, String errorMessage) {
        homePage.checkTheProductSearch(valueSearch)
                .verifyInvalidResultSearch(errorMessage);
    }

}
