import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTests {

    public static void main(String[] args) {

    }

    @Test
    public void TC002B() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "E:\\Program Files/geckodriver.exe");

        // Set expected error messages
        String errorMessageEmail = "Nie możemy znaleźć takiego konta Google";
        String errorMessagePhone = "Wpisz prawidłowy adres e-mail lub numer telefonu";

        // Initialize driver and go to gmail.com
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("https://gmail.com/");

        // Locate text field and button, provide incorrect e-mail and press the button
        WebElement element = driver.findElement(By.xpath("//input[@id='identifierId']"));
        element.sendKeys("abc@gmail.com");

        WebElement button = driver.findElement(By.className("VfPpkd-RLmnJb"));
        button.click();

        // Sleep as the page needs to refresh...
        Thread.sleep(1000);

        //Locate error message and assert value
        WebElement error = driver.findElement(By.className("o6cuMc"));
        Assert.assertEquals(errorMessageEmail, error.getText());

        // Clear text field and provide another incorrect value
        element.clear();
        element.sendKeys("fasdfdsfs@op.pl");
        button.click();

        Thread.sleep(1000);

        //Get new error message and assert value
        error = driver.findElement(By.className("o6cuMc"));
        Assert.assertEquals(errorMessageEmail, error.getText());

        // Clear text field and provide another incorrect value
        element.clear();
        element.sendKeys("fasdfdsfsgmail.com");
        button.click();

        Thread.sleep(1000);

        //Get new error message and assert value
        error = driver.findElement(By.className("o6cuMc"));
        Assert.assertEquals(errorMessageEmail, error.getText());

        // Clear text field and provide another incorrect value
        element.clear();
        element.sendKeys("65664663");
        button.click();

        Thread.sleep(1000);

        //Get new error message and assert value
        error = driver.findElement(By.className("o6cuMc"));
        Assert.assertEquals(errorMessagePhone, error.getText());

        // Clear text field and provide another incorrect value
        element.clear();
        element.sendKeys("656646636626");
        button.click();

        Thread.sleep(1000);

        //Get new error message and assert value
        error = driver.findElement(By.className("o6cuMc"));
        Assert.assertEquals(errorMessagePhone, error.getText());

        // Clear text field and provide another incorrect value
        element.clear();
        element.sendKeys("dolinski.piotr.95@gmail.com");
        button.click();

        // Here the Google throw message stating that browser is not secure, so rest of the test would fail anyway...

        driver.close();
    }

    @Test
    public void TC004A() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "E:\\Program Files/geckodriver.exe");

        // Set expected error message
        String errorMessageNameInUse = "Ta nazwa użytkownika jest już zajęta. Wybierz inną.";

        // Initialize driver and go to gmail.com Registration page
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("https://accounts.google.com/signup/v2/webcreateaccount?service=mail&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F%3Fpc%3Dtopnav-about-n-en&flowName=GlifWebSignIn&flowEntry=SignUp");

        // Enetr values for fields - Imię, Nazwisko, Nazwa użytkownika, Hasło and Potwierdź
        WebElement name = driver.findElement(By.xpath("//input[@id='firstName']"));
        name.sendKeys("Piotr");

        WebElement lastname = driver.findElement(By.xpath("//input[@id='lastName']"));
        lastname.sendKeys("Doliński");

        WebElement username = driver.findElement(By.xpath("//input[@id='username']"));
        username.sendKeys("dolinski.piotr.95");

        WebElement password = driver.findElement(By.name("Passwd"));
        password.sendKeys("SecretPassword");

        WebElement confirmPassword = driver.findElement(By.name("ConfirmPasswd"));
        confirmPassword.sendKeys("SecretPassword");

        // Sleep to make sure page is reloaded
        Thread.sleep(1000);

        // Locate and assert error message
        WebElement error = driver.findElement(By.className("o6cuMc"));
        Assert.assertEquals(errorMessageNameInUse, error.getText());

        // Locate and assert that suggestions for different name are shown
        WebElement avaiable = driver.findElement(By.className("yY9Csf"));
        Assert.assertNotNull(avaiable);

        driver.quit();

    }
}
