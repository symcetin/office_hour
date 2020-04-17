package com.automation.tests.mira;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

public class March_17_Vytrack {
    private WebDriver driver;
    private String url = "https://qa3.vytrack.com/user/login";
    private String username = "storemanager85";   //   valid username
    private String password = "UserUser123";      //   valid password
    private By usernameBy = By.id("prependedInput");
    private By passwordBy = By.id("prependedInput2");
    private By clickLogin = By.id("_submit");

    @BeforeMethod
    protected void setup() {
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        BrowserUtils.wait(3);
    }

    @Test
    protected void login() {
        driver.get(url);
        driver.findElement(usernameBy).sendKeys(username);
        driver.findElement(passwordBy).sendKeys(password);
        driver.findElement(clickLogin).submit();
    }

    @Test
    public void createContact() {
        login();

       BrowserUtils.wait(2);
        //click on contacts
        //create contact
        //Use map to store information and use it later to enter in UI
        //   //tag[@attribute = 'value']
        WebElement contacts_link = driver.findElement(By.xpath("//span[.='Contacts']/following-sibling::a"));
        BrowserUtils.wait(5);
        contacts_link.click();
        BrowserUtils.wait(2);
        WebElement create_contact = driver.findElement(By.linkText("Create Contact"));
        create_contact.click();
        BrowserUtils.wait(3);
        String currentTitle = driver.getTitle();
        if (currentTitle.equalsIgnoreCase("Create Contact - Contacts - Customers")){
            System.out.println("Title is expected");
        }else {
            System.out.println("Title is NOT expected");
        }
        //MEETING ID FOR TODAY CLASS: 949992072
        HashMap <String, String> contact1 = new HashMap<>();
        contact1.put("First Name", "John");
        contact1.put("Last Name", "Smith");
        contact1.put("Phone", "571-236-4545");
        contact1.put("Street", "400 Main Street");
        contact1.put("City", "Tysons");
        contact1.put("State", "VA");
        contact1.put("Zip Code", "22102");
        contact1.put("Sales Group", "true");
        contact1.put("Country", "US");
        System.out.println("Contact 1: " + contact1);
        WebElement first_name = driver.findElement(By.xpath("(//input[@data-name = 'field__first-name'])[1]"));
        WebElement last_name = driver.findElement(By.xpath("(//input[@data-name = 'field__last-name'])[1]"));
        WebElement phone = driver.findElement(By.name("oro_contact_form[phones][0][phone]"));
        WebElement street = driver.findElement(By.name("oro_contact_form[addresses][0][street]"));
        WebElement city = driver.findElement(By.name("oro_contact_form[addresses][0][city]"));
        WebElement state = driver.findElement(By.xpath("//select[@data-name = 'field__region']"));
        WebElement zipCode = driver.findElement(By.name("oro_contact_form[addresses][0][postalCode]"));
        WebElement salesGroup = driver.findElement(By.xpath("(//input[@data-name = 'field__1'])[2]"));
        first_name.sendKeys(contact1.get("First Name"));
        last_name.sendKeys(contact1.get("Last Name"));
        phone.sendKeys(contact1.get("Phone"));
        street.sendKeys(contact1.get("Street"));
        city.sendKeys(contact1.get("City"));
//        state.sendKeys(contact1.get("State"));
        zipCode.sendKeys(contact1.get("Zip Code"));
        /*
            To handle dropdowns in selenium we are using Select class
            to use it we have to ensure we have <select></select> tag in our dropdown
            to create Select class we are using webelement of <select></select> element from html (we need to locate our
            dropdown which should have select tag)
         */
        WebElement country = driver.findElement(By.name("oro_contact_form[addresses][0][country]"));
        Select country_dropdwn = new Select(country); //this is special class in selenium to handle dropdowns
        /*
        it has different methods that help us interact with dropdown
         */
        country_dropdwn.selectByValue(contact1.get("Country"));
        Select state_list = new Select(state);
        state_list.selectByValue("US-" + contact1.get("State"));
        if (contact1.get("Sales Group").equalsIgnoreCase("true")){
            salesGroup.click();
        }
        driver.findElement(By.xpath("(//button[contains(text(), 'Save and Close')])[1]")).click();
        BrowserUtils.wait(3);
        String fullName = contact1.get("First Name") + " " + contact1.get("Last Name");
        String uiFullName = driver.findElement(By.xpath("//h1[@class='user-name']")).getText();
        Assert.assertEquals(uiFullName, fullName);
        System.out.println("Actual: " + uiFullName + " | Expected: " + fullName);
        String uiPhone = driver.findElement(By.className("phone")).getText();
        Assert.assertEquals(uiPhone, contact1.get("Phone"));
        System.out.println("Actual: " + uiPhone + " | Expected: " + contact1.get("Phone"));
       BrowserUtils.wait(3);
        String uiCompleteAddress = driver.findElement(By.xpath("//address")).getText();
        String cityWithState = (contact1.get("City") + " " + contact1.get("State") +
                " " + contact1.get("Country") + " " + contact1.get("Zip Code")).toUpperCase();
        String completeAddress = contact1.get("Street") + "\n" + cityWithState;
        Assert.assertEquals(uiCompleteAddress, completeAddress);

    }
}
