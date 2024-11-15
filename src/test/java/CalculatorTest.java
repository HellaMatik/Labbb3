import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.locators.RelativeLocator.with;


public class CalculatorTest {
    private static WebDriver driver;

    @BeforeAll
    static void setup() {

        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origin=*");
        option.addArguments("incognito");
        driver = new ChromeDriver(option);

    }

    @BeforeEach
    public void navigate() throws InterruptedException {
        driver.get("https://www.iths.se");
        Thread.sleep(3000);


        driver.manage().window().maximize(); //maximera skärmen
        Thread.sleep(3000);


    }


    //Test1
    @Test
    public void websiteTitle() {

        String websiteTitle = driver.getTitle();
        System.out.println("titeln på ITHS.se: " + websiteTitle);
        assertEquals("IT-Högskolan – Här startar din IT-karriär!", websiteTitle);


    }

    //Test2
    @Test
    public void checkEmail() {

        WebElement preHeading = driver.findElement(By.className("preheading"));

        WebElement contactInfoDiv = driver.findElement(with(By.className("contact-site--info")).below(preHeading));

        List<WebElement> emailElements = contactInfoDiv.findElements(By.tagName("a"));
        WebElement emailAddress = emailElements.get(0);
        String ithsMail = emailAddress.getAttribute("href");
        assertEquals("mailto:info@iths.se", ithsMail);


    }

    //Test3
    @Test
    public void clickUtbildningar() throws InterruptedException {
        WebElement myID = driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"));
        Thread.sleep(3000);
        myID.click();


        WebElement utbildningarLink = driver.findElement(By.linkText("ALLA UTBILDNINGAR"));
        utbildningarLink.click();
        assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("utbildningar"));

    }

    //Test4
    @Test
    public void clickKontaktAndVerify() throws InterruptedException {
        Thread.sleep(3000);

        WebElement kontaktLink = driver.findElement(By.linkText("KONTAKT"));
        kontaktLink.click();

    }

    //Test5
    @Test
    public void utbildningarSthlm() {

        WebElement sthlm = driver.findElement(By.xpath("//*[@id=\"frontpage\"]/div/div[1]/div/div/div[2]/div/a[3]"));
        sthlm.click();


    }

    //Test6
    @Test
    public void checkTagName() {

        // Hämta alla länkar med TagName
        List<WebElement> links = driver.findElements(By.tagName("i"));

        System.out.println("Antal länkar: " + links.size());
        assertTrue(links.size() > 20, "Det finns färre än 5 länkar på sidan");


    }
}
