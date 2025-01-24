import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertData
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
            
            String insertCustomers = "INSERT INTO customers (name, PhoneNumber, address, city, province, pincode, customerID)"+
                                    "VALUES ('John Smith', 1234567890, '123 Main St', 'Anytown', 'Anystate', '12345', 100001),"+
                                    "('Jane Doe', 2345678901, '456 Elm St', 'Othertown', 'Otherstate', '23456', 100002),"+
                                    "('Alice Johnson', 3456789012, '789 Oak St', 'Somewhere', 'Someplace', '34567', 100003),"+
                                    "('Bob Williams', 4567890123, '321 Pine St', 'Nowhere', 'Noplace', '45678', 100004),"+
                                    "('Mary Brown', 5678901234, '654 Cedar St', 'Everywhere', 'Everyplace', '56789', 100005)";
            statement.executeUpdate(insertCustomers);
            
            String insertAccountDetails = "INSERT INTO accountDetails (customerID, accNumber, debitNumber, balance)"+
                                        "VALUES (100001, 12345678, 1234567890123456, 1000),"+
                                               "(100002, 23456789, 2345678901234567, 3000),"+
                                               "(100003, 34567890, 3456789012345678, 21000),"+
                                               "(100004, 45678901, 4567890123456789, 8000),"+
                                               "(100005, 56789012, 5678901234567890, 7510)";
            statement.executeUpdate(insertAccountDetails);
            
            String insertDebitDetails = "INSERT INTO debitDetails (debitNumber, cvv, accNumber, name)"+
                                        "VALUES (1234567890123456, 123, 12345678, 'John Smith'),"+
                                               "(2345678901234567, 234, 23456789, 'Jane Doe'),"+
                                               "(3456789012345678, 345, 34567890, 'Alice Johnson'),"+
                                               "(4567890123456789, 456, 45678901, 'Bob Williams'),"+
                                               "(5678901234567890, 567, 56789012, 'Mary Brown')";
            statement.executeUpdate(insertDebitDetails);
            
            String insertTransactions = "INSERT INTO transactions (transactionID, accNumber, transactionType, amount, transactionTime)"+
                                        "VALUES (10000, 12345678, 'debit', 50.00, '2024-04-06 10:00:00'),"+
                                       "(10001, 23456789, 'credit', 100.00, '2024-04-06 11:00:00'),"+
                                       "(10002, 34567890, 'debit', 75.00, '2024-04-06 12:00:00'),"+
                                       "(10003, 45678901, 'credit', 200.00, '2024-04-06 13:00:00'),"+
                                       "(10004, 56789012, 'debit', 30.00, '2024-04-06 14:00:00')";
            statement.executeUpdate(insertTransactions);
            
            String insertLoan = "INSERT INTO loan (loanID, accNumber, loanAmount, interestRate, loanTerm)"+
                                "VALUES (10000, 12345678, 5000.00, 0.05, 12),"+
                               "(10001, 23456789, 10000.00, 0.06, 24),"+
                               "(10002, 34567890, 7500.00, 0.04, 18),"+
                               "(10003, 45678901, 20000.00, 0.07, 36),"+
                               "(10004, 56789012, 3000.00, 0.03, 6)";
            statement.executeUpdate(insertLoan);
            
            String insertBranch = "INSERT INTO branch (branchID, branchName, branchAddress, branchPhone)"+
                                "VALUES (10000, 'Main Branch', '123 Main St', 1234567890),"+
                               "(10001, 'Downtown Branch', '456 Elm St', 2345678901),"+
                               "(10002, 'Uptown Branch', '789 Oak St', 3456789012),"+
                               "(10003, 'Westside Branch', '321 Pine St', 4567890123),"+
                               "(10004, 'Eastside Branch', '654 Cedar St', 5678901234)";
            statement.executeUpdate(insertBranch);
            
            String insertEmployee = "INSERT INTO employee (employeeID, salary, employeeName, branchID)"+
                                    "VALUES (1, 50000, 'Michael Johnson', 10000),"+
                                   "(2, 60000, 'Emily White', 10001),"+
                                   "(3, 55000, 'David Martinez', 10002),"+
                                   "(4, 70000, 'Jessica Lee', 10003),"+
                                   "(5, 52000, 'Daniel Clark', 10004)";
            statement.executeUpdate(insertEmployee);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
