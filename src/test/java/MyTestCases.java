import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class MyTestCases  extends MyDatabase{

    WebDriver driver = new EdgeDriver();

    String Url = "https://practicesoftwaretesting.com/";


    @BeforeTest
    public void MySetup () throws SQLException {

        driver.get(Url);

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","0000");

    }





    @Test (priority = 1 ,enabled = true)
    public void Create () throws SQLException {

        String query = "Insert into customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city, country , postalCode) Values (999, 'Abc Company', 'Ali', 'Ahmad', '962797700235', '123 Main St', 'Amman', 'Jordan', '18881') ";

        stmt = con.createStatement();

        int RowsInserted = stmt.executeUpdate(query);




    }
    @Test (priority = 3 ,enabled = true)
    public void Read () throws SQLException {

        String Query = "Select * from customers where customerNumber = 999";
        stmt = con.createStatement();
        Res = stmt.executeQuery(Query);

        while (Res.next()) {

            TheFirstName = Res.getString("contactFirstName").toString().trim();

            TheLastName = Res.getString("contactLastName").toString().trim();

            ThePhone = Res.getString("phone").toString().trim();

            TheAddress = Res.getString("addressLine1").toString().trim();

            TheCountry = Res.getString("country").toString().trim();

            TheCity = Res.getString("city").toString().trim();

            ThePostalCode = Res.getString("postalCode").toString().trim();

            TheEmail = TheFirstName + TheLastName + RandomNumberForEmail + Domain ;


        }






    }
    @Test (priority = 2 ,enabled = true)
    public void Update () throws SQLException {

        String Query = "UPDATE customers SET contactLastName = 'mohammad' WHERE customerNumber = 999";

        stmt = con.createStatement();

        int rowInserted = stmt.executeUpdate(Query);


    }

    @Test (priority = 4 ,enabled = true)
    public void Delete () throws SQLException {

        String query = "delete from customers where customerNumber =999";

        stmt = con.createStatement();

        int rowInserted = stmt.executeUpdate(query);

    }


    @Test (priority = 5 ,enabled = true)
    public void SignInTest () throws InterruptedException {

        WebElement LogInPage = driver.findElement(By.cssSelector(".nav-link[data-test='nav-sign-in']"));
        LogInPage.click();

        WebElement Register = driver.findElement(By.linkText("Register your account"));
        Register.click();


        WebElement FirstName = driver.findElement(By.id("first_name"));
        WebElement LastName = driver.findElement(By.id("last_name"));
        WebElement DateOfBirth = driver.findElement(By.id("dob"));
        WebElement Street = driver.findElement(By.id("street"));
        WebElement PostalCode = driver.findElement(By.id("postal_code"));
        WebElement City = driver.findElement(By.id("city"));
        WebElement State = driver.findElement(By.id("state"));

        WebElement Country = driver.findElement(By.id("country"));

        Thread.sleep(1000);

        Select SelectedCountry = new Select(Country);
        SelectedCountry.selectByVisibleText(TheCountry);


        WebElement Phone = driver.findElement(By.id("phone"));
        WebElement Email = driver.findElement(By.id("email"));
        WebElement Password = driver.findElement(By.id("password"));

        FirstName.sendKeys(TheFirstName);
        LastName.sendKeys(TheLastName);
        Street.sendKeys(TheAddress);
        PostalCode.sendKeys(ThePostalCode);
        City.sendKeys(TheCity);
        DateOfBirth.sendKeys(TheDateOfBirth);
        Phone.sendKeys(ThePhone);
        Email.sendKeys(TheEmail);
        Password.sendKeys(ThePassword);
        State.sendKeys(TheCity);

        WebElement RegisterButton = driver.findElement(By.cssSelector("button[type='submit']"));
        RegisterButton.click();

    }

    @Test (priority = 6, enabled = true)
    public void LoginTest () throws InterruptedException {

        Thread.sleep(1500);

        WebElement Email = driver.findElement(By.id("email"));
        Email.sendKeys(TheEmail);

        WebElement Password = driver.findElement(By.id("password"));
        Password.sendKeys(ThePassword);

        WebElement LoginButton = driver.findElement(By.xpath("//input[@value='Login']"));
        LoginButton.click();


        boolean ActualMessageForSignUp = driver.getPageSource().contains(ExpectedLogInMassage);


        Assert.assertEquals(ActualMessageForSignUp ,true);


    }


}
