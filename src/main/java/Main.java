import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        // Disable ALL logging from Selenium, Netty, and ChromeDriver
        System.setProperty("webdriver.chrome.silentOutput", "true");
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        Logger.getLogger("org.asynchttpclient").setLevel(Level.OFF);
        Logger.getLogger("io.netty").setLevel(Level.OFF);
        Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF); // HTMLUnit (if used)

        // Configure Chrome to suppress logs
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--log-level=3"); // Chrome logs: 3=FATAL (suppress all)
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-logging"});

        WebDriver driver = new ChromeDriver(options);

        try {
            // driver.get("https://www.google.com");
            driver.get("https://x.com");
            System.out.println("Page Title: " + driver.getTitle()); 

            /*
            to run(add the below in terminal)
            mvn compile exec:java -Dorg.slf4j.simpleLogger.defaultLogLevel=off -Dio.netty.noUnsafe=true -Dio.netty.leakDetection.level=DISABLED
            */
        } finally {
            driver.quit();
        }
    }
}