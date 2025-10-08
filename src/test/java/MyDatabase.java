
import java.sql.*;
import java.util.Random;

public class MyDatabase {


    Connection con ;

    Statement stmt ;

    ResultSet Res ;


    String TheFirstName ;

    String TheLastName ;

    String ThePhone ;

    String TheAddress;

    String TheCountry ;

    String TheCity ;

    String ThePostalCode;

    String TheEmail;

    String ThePassword = "123!@#P@ssw0rd";

    String Domain = "@gmail.com";

    Random rand = new Random();

    int RandomNumberForEmail = rand.nextInt(3698);

    int Year = rand.nextInt(1950,2008);

    int Month = rand.nextInt(1,13);


    int Day = rand.nextInt(1,31);

    String TheDateOfBirth = String.format("%04d-%02d-%02d", Year, Month, Day);

   String ExpectedLogInMassage = "My account";





    }

