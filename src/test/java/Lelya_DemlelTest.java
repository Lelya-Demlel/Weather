import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Lelya_DemlelTest {

    //    TC_1_1  - Тест кейс:
    //    //1. Открыть страницу https://openweathermap.org/
    //    //2. Набрать в строке поиска город Paris
    //    //3. Нажать пункт меню Search
    //    //4. Из выпадающего списка выбрать Paris, FR
    //    //5. Подтвердить, что заголовок изменился на "Paris, FR"

    @Test
    public void testH2TagText_WhenSearchCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Documents\\2022_QA\\ChromDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";


        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id= 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id= 'weather-widget']// button[@type= 'submit']")
        );
        searchButton.click();
        Thread.sleep(1000);

        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class= 'search-dropdown-menu']/li/span[text()= 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id= 'weather-widget']//h2")
        );
        Thread.sleep(2000);

        String actualResult = h2CityCountryHeader.getText();


        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /*TC_11_01
1.  Открыть базовую ссылку
2.  Нажать на пункт меню Guide
3.  Подтвердить, что вы перешли на страницу со ссылкой
https://openweathermap.org/guide и что title этой страницы OpenWeatherMap
API guide - OpenWeatherMap
     */
    @Test
    public void titlePage_WhenClickTabGuide() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Documents\\2022_QA\\ChromDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(url);
        Thread.sleep(5000);

        WebElement buttonGuide = driver.findElement(
                By.xpath("//div[@id= 'desktop-menu']//a[text()= 'Guide']")
        );
        buttonGuide.click();

        String actualResult1 = driver.getCurrentUrl();
        String actualResult2 = driver.getTitle();


        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);


        driver.quit();
    }

    /*
TC_11_02
1.  Открыть базовую ссылку
2.  Нажать на единицы измерения Imperial: °F, mph
3.  Подтвердить, что температура для города показана в Фарингейтах
     */
    @Test
    public void temperatureFahrenheit_WhenClickButtonImperial() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Documents\\2022_QA\\ChromDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultstr = "°F";
        char expectedResult = 'F';
        String fTempSymbol = "°F";


        driver.get(url);
        Thread.sleep(5000);

        WebElement buttonImperial = driver.findElement(
                By.xpath("//div[@id='weather-widget']//div[text()='Imperial: °F, mph']")
        );
        buttonImperial.click();
        Thread.sleep(2000);

        WebElement cityTemperatureInFahrenheit = driver.findElement(
                By.xpath("//div[@class='current-temp']/span[@class='heading']")
        );
        String temperatureFahrenheit = cityTemperatureInFahrenheit.getText();


        char actualResult = temperatureFahrenheit.charAt(temperatureFahrenheit.length() - 1);

        String actualResultstr = temperatureFahrenheit
                .substring(temperatureFahrenheit.length() - 2);

        Boolean actualResultstrbool = temperatureFahrenheit.contains("F");


        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(actualResultstr, expectedResultstr);
        Assert.assertTrue(cityTemperatureInFahrenheit.getText().contains(fTempSymbol));

        driver.quit();
    }

    /* TC_11_03
1.  Открыть базовую ссылку
2. Подтвердить, что внизу страницы есть панель с текстом
“We use cookies which are essential for the site to work. We also
use non-essential cookies to help us improve our services. Any data collected
 is anonymised. You can allow all cookies or manage them individually.”
3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all”
и “Manage cookies”
*/
    @Test
    public void allowCookies() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Documents\\2022_QA\\ChromDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
//        driver.manage().window().maximize();
        String expectedResult1 = "We use cookies which are essential for the"
                + " site to work. We also use non-essential cookies to help us "
                + "improve our services. Any data collected is anonymised. You "
                + "can allow all cookies or manage them individually.";
        String expectedResult2 = "Allow all";
        String expectedResult3 = "Manage cookies";

        driver.get(url);
        WebElement panelText = driver.findElement(
                By.xpath("//div[@class='stick-footer-panel__container']"
                        + "/p[@class='stick-footer-panel__description']")
        );
        String actualResult1 = panelText.getText();

        WebElement buttonAllowAll = driver.findElement(
                By.xpath("//div[@class='stick-footer-panel__btn-container']"
                        + "/button[@class='stick-footer-panel__link']")
        );
        String actualResult2 = buttonAllowAll.getText();

        WebElement buttonManageCookies = driver.findElement(
                By.xpath("//a[@class='stick-footer-panel__link']")
        );
        String actualResult3 = buttonManageCookies.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);
        Assert.assertEquals(driver.findElements(
                        By.xpath("//div[@class='stick-footer-panel__btn-container']/*"))
                .size(), 2);

        driver.quit();
    }

    /*TC_11_04
1.  Открыть базовую ссылку
2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”,
“How to start” и “Ask a question”
     */
    @Test
    public void menuSupportContainsThreeButton() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Documents\\2022_QA\\ChromDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        driver.manage().window().maximize();
        String expectedResult1 = "FAQ";
        String expectedResult2 = "How to start";
        String expectedResult3 = "Ask a question";

        driver.get(url);
        Thread.sleep(5000);
        WebElement buttonSupport = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
        buttonSupport.click();
        Thread.sleep(2000);
        WebElement buttonFAQ = driver.findElement(
                By.xpath("//li/ul[@id='support-dropdown-menu']//a")
        );
        String actualResult1 = buttonFAQ.getText();
        WebElement buttonHowToStart = driver.findElement(
                By.xpath("//li/ul[@id='support-dropdown-menu']//li[2]/a")         ////a[@href='/appid']
        );
        String actualResult2 = buttonHowToStart.getText();
        WebElement buttonAskQuestion = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//li[3]/a")
        );
        String actualResult3 = buttonAskQuestion.getText();

        Assert.assertEquals(driver.findElements(
                        By.xpath("//div[@id='desktop-menu']"
                                + "//ul[@id='support-dropdown-menu']/li"))                                       ////ul[@id='support-dropdown-menu']
                .size(), 3);
        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);

        driver.quit();
    }

    /*TC_11_05
1. Открыть базовую ссылку
2. Нажать пункт меню Support → Ask a question
3. Заполнить поля Email, Subject, Message
4. Не подтвердив CAPTCHA, нажать кнопку Submit
5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification
 failed, please try again.”
     */
    @Test
    public void checkSubmitWithoutCAPTCHA() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Documents\\2022_QA\\ChromDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        String eMail = "test@gmail.com";
        String message = "test";
        String expectedResult = "reCAPTCHA verification failed, please try again.";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement buttonSupport = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
        buttonSupport.click();
        Thread.sleep(2000);
        WebElement buttonAsqQuestion = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']"
                        + "//a[@href='https://home.openweathermap.org/questions']"));
        buttonAsqQuestion.click();
        Thread.sleep(2000);
        driver.get("https://home.openweathermap.org/questions");  //  driver.getWindowHandle();
               Thread.sleep(4000);

        WebElement eMailInput = driver.findElement(
                By.xpath("//div[@class='col-sm-8']/input[@id='question_form_email']"));
        eMailInput.sendKeys(message);
        eMailInput.click();
        WebElement fieldSubject = driver.findElement(
                By.xpath("//div[@class='col-sm-8']/select [@class='form-control select required']"));
        fieldSubject.click();
        WebElement choiceFieldSubject = driver.findElement(
                By.xpath("//select[@class='form-control select required']"
                        + "/option[text()='Questions about weather data: data "
                        + "quality, data sources, data issues, etc.']"));
        choiceFieldSubject.click();
        WebElement fieldMessage = driver.findElement(
                By.xpath("//div[@class='col-sm-8']/textarea[@id='question_form_message']"));
        fieldMessage.sendKeys(message);

        WebElement buttonSubmit = driver.findElement(
                By.xpath("//div[@class='col-sm-8']/input[@class='btn btn-default']"));
        buttonSubmit.click();

        WebElement reCaptcha = driver.findElement(By.xpath("//div[@class='help-block']"));

        String actualResult = reCaptcha.getText();

        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }
}