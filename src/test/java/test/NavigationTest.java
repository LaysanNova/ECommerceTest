package test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestData;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static utils.TestData.*;


public final class NavigationTest extends BaseTest {
    @Test
    public void TestBaseUrlLanding() {

        assertThat(getPage()).hasURL(BASE_URL + HOME_END_POINT);
    }

    @Test
    public void testWomenNavigatesToWomenPage() {
        if (getIsOnHomePage()) {

            getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(WOMEN).setExact(true)).click();

            assertThat(getPage()).hasURL(BASE_URL + WOMEN_END_POINT);
        } else {
            Assert.fail();
        }
    }

    @Test
    public void testMenNavigatesToMenPage() {

        getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(TestData.MEN).setExact(true)).click();

        assertThat(getPage()).hasURL(BASE_URL + MEN_END_POINT);
    }

    @Test
    public void testAccessoriesNavigatesToAccessoriesPage() {

        getPage().getByRole(AriaRole.NAVIGATION).getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName(TestData.ACCESSORIES)).click();

        assertThat(getPage()).hasURL(BASE_URL + ACCESSORIES_END_POINT);
    }

    @Test
    public void testAboutNavigatesToAboutPage() {
        getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(ABOUT).setExact(true)).click();

        assertThat(getPage()).hasURL(BASE_URL + ABOUT_END_POINT);

    }
}
