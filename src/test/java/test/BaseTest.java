package test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.LoggerUtils;
import utils.ReportUtils;
import utils.runner.BrowserManager;
import utils.runner.ConfigProperties;

import java.lang.reflect.Method;

import static utils.TestData.BASE_URL;
import static utils.TestData.HOME_END_POINT;

public abstract class BaseTest {

    private final Playwright playwright = Playwright.create();
    private final Browser browser = BrowserManager.createBrowser(playwright, ConfigProperties.ENVIRONMENT_CHROMIUM);

    private BrowserContext context;
    private Page page;

    @BeforeSuite
    void checkIfPlaywrightCreatedAndBrowserLaunched() {
        ReportUtils.logReportHeader();

        if (playwright != null) {
            LoggerUtils.logInfo("Playwright created.");
        } else {

            LoggerUtils.logFatal("FATAL: Playwright is NOT created.");
            System.exit(1);
        }

        if (browser.isConnected()) {
            LoggerUtils.logInfo("Browser " + browser.browserType().name() + " is connected.");
        } else {
            LoggerUtils.logFatal("FATAL: Browser is NOT connected.");
            System.exit(1);
        }
    }

    @BeforeMethod
    void createContextAndPage(Method method) {

        ReportUtils.logTestName(method);

        context = browser.newContext();
        LoggerUtils.logInfo("Context created.");

        page = context.newPage();
        LoggerUtils.logInfo("Page created.");

        getPage().navigate(BASE_URL);

        if (isOnHomePage()) {
            LoggerUtils.logInfo("Base URL is opened and content is not empty.");
        } else {
            LoggerUtils.logError("ERROR: Base URL is NOT opened OR content is EMPTY.");
        }
    }

    @AfterMethod
    void closeContext(Method method, ITestResult result) {


        if (page != null) {
            page.close();
            LoggerUtils.logInfo("Page closed.");
        }
        if (context != null) {
            context.close();
            LoggerUtils.logInfo("Context closed.");
        }

        ReportUtils.logTestResult(method, result);
    }

    @AfterSuite
    void closeBrowserAndPlaywright() {
        if (browser != null) {
            browser.close();
            LoggerUtils.logInfo("Browser closed.");
        }
        if (playwright != null) {
            playwright.close();
            LoggerUtils.logInfo("Playwright closed.");
        }
    }

    private boolean isOnHomePage() {
        getPage().waitForLoadState();

        return getPage().url().equals(BASE_URL + HOME_END_POINT) && !page.content().isEmpty();
    }

    protected Page getPage() {
        return page;
    }

    protected boolean getIsOnHomePage() {

        return isOnHomePage();
    }
}

