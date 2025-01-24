import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Tax
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
            
            String sqlQuery = "Select employeeID, employeeName, salary, (salary * 0.1) as tax_amount from employee Group By employeeID";
            
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Iterating over the result set
            while (resultSet.next()) {
                // Retrieving values from the result set
                int id = resultSet.getInt("employeeID");
                String name = resultSet.getString("employeeName");
                int salary = resultSet.getInt("Salary");
                double taxAmount = resultSet.getDouble("tax_amount");

                // Printing the results
                System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary + ", Tax Amount: " + taxAmount);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
