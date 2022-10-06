import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AccountTransaction {
    public void accountTransaction(int account, String name, int balance) {

        AccountTransaction object = new AccountTransaction();
        Scanner scan = new Scanner(System.in);
        System.out.println("----------------------------------------------------------");
        System.out.println("                Welcome " + name);
        System.out.println("----------------------------------------------------------");
        int loop;
        do {
            System.out.println("     Press 1 for Deposit");
            System.out.println("     Press 2 for Withdraw");
            System.out.println("     Press 3 for Check Balance");
            System.out.println("     Press 4 for Exit");
            System.out.println("----------------------------------------------------------");
            System.out.print("     Enter : ");
            loop = scan.nextInt();
            switch (loop) {
                case 1:
                    System.out.println("     Current Balance : " + balance);
                    System.out.print("     Enter the amount : ");
                    int amount = scan.nextInt();
                    object.changeBalance(account,(balance+amount));
                    balance = balance+amount;
                    System.out.println("\n     Your Money has been successfully deposited");
                    System.out.println("     Now Balance : " + balance);
                    System.out.println("----------------------------------------------------------");

                    break;
                case 2:
                    System.out.println("     Current Balance : " + balance);
                    System.out.print("     Enter the amount : ");
                    amount = scan.nextInt();
                    if (amount <balance)  object.changeBalance(account,(balance - amount));
                    else {
                        System.out.println("\"Insufficient Balance\"\n");
                        break;
                    }
                    balance = balance-amount;
                    System.out.println("     Please collect your money");
                    System.out.println("\n     Amount withdraw Successfully");
                    System.out.println("     Now Balance : " +balance);
                    System.out.println("----------------------------------------------------------");

                    break;
                case 3:
                    System.out.println("     Account Number : " + account);
                    System.out.println("     Name : " + name);
                    System.out.println("     Balance : " + balance);
                    System.out.println("----------------------------------------------------------");
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Please give the valid option");

            }
        } while (loop != 4);

    }
    private void changeBalance(int account, int balance){
        try {
            Connection connection = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","Varun@1234");
            PreparedStatement statement = connection.prepareStatement("update account set balance='"+balance+"' where account_no='"+account+"'");
            statement.executeUpdate();
            connection.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
