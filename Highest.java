import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Highest
{
    public static void main(String[] args)
    {
        // JDBC URL, username, and password of the database
        String url = "jdbc:mysql://localhost:3306/bank";
        String username = "root";
        String password = "Dadyal@45";
        
        try
        {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            
            String sqlQuery = "SELECT c.name, a.balance, a.accNumber "+
                                "FROM customers c "+
                                "JOIN accountDetails a ON c.customerID = a.customerID "+
                                "ORDER BY a.balance DESC "+
                                "LIMIT 1";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double balance = resultSet.getDouble("balance");
                int accnum = resultSet.getInt("accNumber");
                System.out.println("Person with highest balance:");
                System.out.println("Name: " + name);
                System.out.println("Balance: " + balance);
                System.out.println("Account Number: "+ accnum);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
