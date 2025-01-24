import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class DatabaseApp
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
            Scanner input = new Scanner(System.in);
            
            while(true)
            {
                System.out.println("Bank Database Menu");
                System.out.println("1. Add customer");
                System.out.println("2. Update customer");
                System.out.println("3. Remove customer");
                System.out.println("4. View customers");
                System.out.println("5. Add account");
                System.out.println("6. Remove account");
                System.out.println("7. View accounts");
                System.out.println("8. View debit details");
                System.out.println("9. View transactions of an account");
                System.out.println("10. Add transaction");
                System.out.println("11. View loan");
                System.out.println("12. Add loan");
                System.out.println("13. View Branches");
                System.out.println("14. Update branch");
                System.out.println("15. Add branch");
                System.out.println("16. View Employees");
                System.out.println("17. Add employee");
                System.out.println("18. Update employee");
                System.out.println("19. Exit");
                System.out.print("Enter your choice: ");
                int choice = input.nextInt();
                
                switch (choice) {
                    case 1:
                        addCustomer(connection);
                        break;
                    case 2:
                        updateCustomerDetails(connection);
                        break;
                    case 3:
                        removeCustomer(connection);
                        break;
                    case 4:
                        viewAllCustomers(connection);
                        break;
                    case 5:
                        addAccount(connection);
                        break;
                    case 6:
                        removeAccount(connection);
                        break;
                    case 7:
                        viewAllAccounts(connection);
                        break;
                    case 8:
                        viewDebitDetails(connection);
                        break;
                    case 9:
                        viewAccountTransactions(connection);
                        break;
                    case 10:
                        addTransaction(connection);
                        break;
                    case 11:
                        viewLoans(connection);
                        break;
                    case 12:
                        addLoan(connection);
                        break;
                    case 13:
                        viewBranches(connection);
                        break;
                    case 14:
                        updateBranch(connection);
                        break;
                    case 15:
                        addBranch(connection);
                        break;
                    case 16:
                        viewEmployees(connection);
                        break;
                    case 17:
                        addEmployee(connection);
                        break;
                    case 18:
                        updateEmployee(connection);
                        break;
                    case 19:
                        System.out.println("Exiting the menu...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                System.out.println();
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void addCustomer(Connection conn) throws SQLException
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter customer name:");
        String name = scanner.nextLine();

        System.out.println("Enter customer phone number:");
        long phoneNumber = Long.parseLong(scanner.nextLine());

        System.out.println("Enter customer address:");
        String address = scanner.nextLine();

        System.out.println("Enter customer city:");
        String city = scanner.nextLine();

        System.out.println("Enter customer province:");
        String province = scanner.nextLine();

        System.out.println("Enter customer pincode:");
        String pincode = scanner.nextLine();

        System.out.println("Enter customer ID:");
        int customerID = Integer.parseInt(scanner.nextLine());

        String sql = "INSERT INTO customers (name, PhoneNumber, address, city, province, pincode, customerID) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        statement.setLong(2, phoneNumber);
        statement.setString(3, address);
        statement.setString(4, city);
        statement.setString(5, province);
        statement.setString(6, pincode);
        statement.setInt(7, customerID);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new customer has been added successfully.");
        } else {
            System.out.println("Failed to add a new customer.");
        }
    }
    
    public static void updateCustomerDetails(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("Enter customer ID:");
        int customerID = Integer.parseInt(scanner.nextLine());
    
        System.out.println("What do you want to update?");
        System.out.println("1. Name");
        System.out.println("2. Phone Number");
        System.out.println("3. Address");
        System.out.println("4. City");
        System.out.println("5. Province");
        System.out.println("6. Pincode");
    
        int choice = Integer.parseInt(scanner.nextLine());
    
        String columnName = "";
        switch (choice) {
            case 1:
                columnName = "name";
                break;
            case 2:
                columnName = "PhoneNumber";
                break;
            case 3:
                columnName = "address";
                break;
            case 4:
                columnName = "city";
                break;
            case 5:
                columnName = "province";
                break;
            case 6:
                columnName = "pincode";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
    
        System.out.println("Enter new value:");
        String newValue = scanner.nextLine();
    
        String sql = "UPDATE customers SET " + columnName + " = ? WHERE customerID = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, newValue);
        statement.setInt(2, customerID);
    
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Customer details updated successfully.");
        } else {
            System.out.println("Failed to update customer details.");
        }
    }
    
    public static void removeCustomer(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter customer ID to remove:");
        int customerID = Integer.parseInt(scanner.nextLine());

        String sql = "DELETE FROM customers WHERE customerID = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, customerID);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Customer with ID " + customerID + " has been removed successfully.");
        } else {
            System.out.println("No customer found with ID " + customerID + ".");
        }
    }
    
    public static void viewAllCustomers(Connection conn) throws SQLException {
        String sql = "SELECT * FROM customers";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        System.out.println("CustomerID | Name | PhoneNumber | Address | City | Province | Pincode");
        while (result.next()) {
            int customerID = result.getInt("customerID");
            String name = result.getString("name");
            long phoneNumber = result.getLong("PhoneNumber");
            String address = result.getString("address");
            String city = result.getString("city");
            String province = result.getString("province");
            String pincode = result.getString("pincode");

            System.out.println(customerID + " | " + name + " | " + phoneNumber + " | " +
                               address + " | " + city + " | " + province + " | " + pincode);
        }
    }
    
    public static void addAccount(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter customer ID:");
        int customerID = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter account number:");
        long accountNumber = Long.parseLong(scanner.nextLine());

        System.out.println("Enter debit number:");
        long debitNumber = Long.parseLong(scanner.nextLine());

        System.out.println("Enter balance:");
        double balance = Double.parseDouble(scanner.nextLine());

        String sql = "INSERT INTO accountDetails (customerID, accNumber, debitNumber, balance) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, customerID);
        statement.setLong(2, accountNumber);
        statement.setLong(3, debitNumber);
        statement.setDouble(4, balance);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Account added successfully.");
        } else {
            System.out.println("Failed to add account.");
        }
    }
    
    public static void removeAccount(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter account number to remove:");
        long accountNumber = Long.parseLong(scanner.nextLine());

        String sql = "DELETE FROM accountDetails WHERE accNumber = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, accountNumber);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Account with account number " + accountNumber + " has been removed successfully.");
        } else {
            System.out.println("No account found with account number " + accountNumber + ".");
        }
    }
    
    public static void viewAllAccounts(Connection conn) throws SQLException {
        String sql = "SELECT * FROM accountDetails";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        System.out.println("CustomerID | AccountNumber | DebitNumber | Balance");
        while (result.next()) {
            int customerID = result.getInt("customerID");
            long accountNumber = result.getLong("accNumber");
            long debitNumber = result.getLong("debitNumber");
            double balance = result.getDouble("balance");

            System.out.println(customerID + " | " + accountNumber + " | " + debitNumber + " | " + balance);
        }
    }
    
    public static void viewDebitDetails(Connection conn) throws SQLException {
        String sql = "SELECT * FROM debitDetails";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        System.out.println("DebitNumber | CVV | AccNumber | Name");
        while (result.next()) {
            long debitNumber = result.getLong("debitNumber");
            int cvv = result.getInt("cvv");
            long accNumber = result.getLong("accNumber");
            String name = result.getString("name");

            System.out.println(debitNumber + " | " + cvv + " | " + accNumber + " | " + name);
        }
    }
    
    public static void viewAccountTransactions(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter account number:");
        long accNumber = Long.parseLong(scanner.nextLine());

        String sql = "SELECT * FROM transactions WHERE accNumber = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, accNumber);

        ResultSet result = statement.executeQuery();

        System.out.println("TransactionID | AccNumber | TransactionType | Amount | TransactionTime");
        while (result.next()) {
            int transactionID = result.getInt("transactionID");
            String transactionType = result.getString("transactionType");
            double amount = result.getDouble("amount");
            String transactionTime = result.getString("transactionTime");

            System.out.println(transactionID + " | " + accNumber + " | " + transactionType + " | " + amount + " | " + transactionTime);
        }
    }
    
    public static void addTransaction(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter account number:");
        long accNumber = Long.parseLong(scanner.nextLine());

        System.out.println("Enter transaction type (credit/debit):");
        String transactionType = scanner.nextLine();

        System.out.println("Enter amount:");
        double amount = Double.parseDouble(scanner.nextLine());

        // Get current timestamp
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String sql = "INSERT INTO transactions (accNumber, transactionType, amount, transactionTime) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, accNumber);
        statement.setString(2, transactionType);
        statement.setDouble(3, amount);
        statement.setTimestamp(4, timestamp);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Transaction added successfully.");
        } else {
            System.out.println("Failed to add transaction.");
        }
    }
    
    public static void viewLoans(Connection conn) throws SQLException {
        String sql = "SELECT * FROM loan";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        System.out.println("LoanID | AccNumber | LoanAmount | InterestRate | LoanTerm");
        while (result.next()) {
            int loanID = result.getInt("loanID");
            long accNumber = result.getLong("accNumber");
            double loanAmount = result.getDouble("loanAmount");
            double interestRate = result.getDouble("interestRate");
            int loanTerm = result.getInt("loanTerm");

            System.out.println(loanID + " | " + accNumber + " | " + loanAmount + " | " + interestRate + " | " + loanTerm);
        }
    }
    
    public static void addLoan(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter loan ID:");
        int loanID = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter account number:");
        long accNumber = Long.parseLong(scanner.nextLine());

        System.out.println("Enter loan amount:");
        double loanAmount = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter interest rate:");
        double interestRate = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter loan term (in months):");
        int loanTerm = Integer.parseInt(scanner.nextLine());

        String sql = "INSERT INTO loan (loanID, accNumber, loanAmount, interestRate, loanTerm) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, loanID);
        statement.setLong(2, accNumber);
        statement.setDouble(3, loanAmount);
        statement.setDouble(4, interestRate);
        statement.setInt(5, loanTerm);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Loan added successfully.");
        } else {
            System.out.println("Failed to add loan.");
        }
    }
    
    public static void viewBranches(Connection conn) throws SQLException {
        String sql = "SELECT * FROM branch";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        System.out.println("BranchID | BranchName | BranchAddress | BranchPhone");
        while (result.next()) {
            int branchID = result.getInt("branchID");
            String branchName = result.getString("branchName");
            String branchAddress = result.getString("branchAddress");
            long branchPhone = result.getLong("branchPhone");

            System.out.println(branchID + " | " + branchName + " | " + branchAddress + " | " + branchPhone);
        }
    }
    
    public static void updateBranch(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter branch ID to update:");
        int branchID = Integer.parseInt(scanner.nextLine());

        System.out.println("What information would you like to update?");
        System.out.println("1. Branch Name");
        System.out.println("2. Branch Address");
        System.out.println("3. Branch Phone Number");
        int choice = Integer.parseInt(scanner.nextLine());

        String columnToUpdate = "";
        String newValue = "";
        switch (choice) {
            case 1:
                columnToUpdate = "branchName";
                System.out.println("Enter new branch name:");
                newValue = scanner.nextLine();
                break;
            case 2:
                columnToUpdate = "branchAddress";
                System.out.println("Enter new branch address:");
                newValue = scanner.nextLine();
                break;
            case 3:
                columnToUpdate = "branchPhone";
                System.out.println("Enter new branch phone number:");
                newValue = scanner.nextLine();
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        String sql = "UPDATE branch SET " + columnToUpdate + " = ? WHERE branchID = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, newValue);
        statement.setInt(2, branchID);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Branch updated successfully.");
        } else {
            System.out.println("Failed to update branch.");
        }
    }
    
    public static void addBranch(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter branch name:");
        String branchName = scanner.nextLine();

        System.out.println("Enter branch address:");
        String branchAddress = scanner.nextLine();

        System.out.println("Enter branch phone number:");
        long branchPhone = Long.parseLong(scanner.nextLine());

        String sql = "INSERT INTO branch (branchName, branchAddress, branchPhone) VALUES (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, branchName);
        statement.setString(2, branchAddress);
        statement.setLong(3, branchPhone);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Branch added successfully.");
        } else {
            System.out.println("Failed to add branch.");
        }
    }
    
    public static void viewEmployees(Connection conn) throws SQLException {
        String sql = "SELECT * FROM employee";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        System.out.println("EmployeeID | Salary | EmployeeName | BranchID");
        while (result.next()) {
            int employeeID = result.getInt("employeeID");
            int salary = result.getInt("salary");
            String employeeName = result.getString("employeeName");
            int branchID = result.getInt("branchID");

            System.out.println(employeeID + " | " + salary + " | " + employeeName + " | " + branchID);
        }
    }
    
    public static void addEmployee(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter employee ID:");
        int employeeID = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter salary:");
        int salary = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter employee name:");
        String employeeName = scanner.nextLine();

        System.out.println("Enter branch ID:");
        int branchID = Integer.parseInt(scanner.nextLine());

        String sql = "INSERT INTO employee (employeeID, salary, employeeName, branchID) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, employeeID);
        statement.setInt(2, salary);
        statement.setString(3, employeeName);
        statement.setInt(4, branchID);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Employee added successfully.");
        } else {
            System.out.println("Failed to add employee.");
        }
    }
    
    public static void updateEmployee(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter employee ID to update:");
        int employeeID = Integer.parseInt(scanner.nextLine());

        System.out.println("What information would you like to update?");
        System.out.println("1. Salary");
        System.out.println("2. Employee Name");
        System.out.println("3. Branch ID");
        int choice = Integer.parseInt(scanner.nextLine());

        String columnToUpdate = "";
        String newValue = "";
        switch (choice) {
            case 1:
                columnToUpdate = "salary";
                System.out.println("Enter new salary:");
                newValue = scanner.nextLine();
                break;
            case 2:
                columnToUpdate = "employeeName";
                System.out.println("Enter new employee name:");
                newValue = scanner.nextLine();
                break;
            case 3:
                columnToUpdate = "branchID";
                System.out.println("Enter new branch ID:");
                newValue = scanner.nextLine();
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        String sql = "UPDATE employee SET " + columnToUpdate + " = ? WHERE employeeID = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, newValue);
        statement.setInt(2, employeeID);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Employee details updated successfully.");
        } else {
            System.out.println("Failed to update employee details.");
        }
    }
}
