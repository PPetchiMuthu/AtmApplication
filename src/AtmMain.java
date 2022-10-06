import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class AtmMain {
    private void runProgram(){
        AccountTransaction object = new AccountTransaction();
        Scanner scan = new Scanner(System.in);
        int i=0;
        a:while (i!=2) {
            System.out.println("                   ATM Application         ");
            System.out.print("----------------------------------------------------------");
            System.out.print("\nEnter Account No : ");
            int account = scan.nextInt();
            System.out.print("Enter the 4 digit pin :");
            int pinCode = scan.nextInt();
            try {
                Connection connection = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Varun@1234");
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM account");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    if (account == resultSet.getInt(1) && pinCode == resultSet.getInt(3)) {
                        object.accountTransaction(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(4));
                        System.out.println("        Thanking You :)    ");
                        break a;
                    }
                }
                System.out.println("Enter a valid Account Number and Password");
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Something Error Please try again");
        }
    }
    public static void main(String[] args) {
        AtmMain atmMain = new AtmMain();
        atmMain.runProgram();
    }
}


