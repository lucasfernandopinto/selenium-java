import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

    public class LoginTest {
    
    private static final String URL_LOGIN = "http://localhost:8080/login";
    private WebDriver browser;

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver-win64/chromedriver.exe"); // informa onde está o driver do Chrome
    }

    @BeforeEach
    public void beforeEach(){
        this.browser = new ChromeDriver();
        this.browser.navigate().to(URL_LOGIN); //  abrir o navegador
    }

    @AfterEach
    public void afterEach(){
        this.browser.quit();
    }

    @Test
    public void efetuarLoginComDadosValidos(){
        
        browser.findElement(By.xpath("//*[@id=\"username \"]")).sendKeys("fulano"); // captura o input de username e escreve "fulano"
        browser.findElement(By.id("password")).sendKeys("pass"); // captura o input de password e escreve "pass"
        browser.findElement(By.id("login-form")).submit(); // captura o formulário e submete

        Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/leiloes")); // verifica se a URL do site alterou
        Assert.assertEquals("fulano",  browser.findElement(By.id("usuario-logado")).getText()); // captura o texto do usuário logado e verifica se bate com o usuário "fulano"

    }

    @Test
    public void efetuarLoginComDadosInvalidos(){
        
        browser.findElement(By.xpath("//*[@id=\"username \"]")).sendKeys("invalido"); 
        browser.findElement(By.id("password")).sendKeys("1234"); 
        browser.findElement(By.id("login-form")).submit();
    
        Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));  // verifica se o site está na URL informada
        Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));  // verifica se tem o texto informado na página
        Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));  
    
    }
    
    @Test
    public void acessarPaginaRestritaSemEstarLogado(){

        this.browser.navigate().to("http://localhost:8080/leiloes/2");
        Assert.assertTrue(browser.getCurrentUrl().equals(URL_LOGIN));
        Assert.assertFalse(browser.getPageSource().contains("Dados do Leilão"));
    }

}