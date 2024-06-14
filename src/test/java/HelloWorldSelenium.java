import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloWorldSelenium {

    @Test
    public void hello(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver-win64/chromedriver.exe"); // informa onde está o driver do Chrome
        WebDriver browser = new ChromeDriver(); // abre o navegador
        browser.navigate().to("http://localhost:8080/leiloes"); // navega até a página desejada
        browser.quit(); // fecha o navegador
    }

}
