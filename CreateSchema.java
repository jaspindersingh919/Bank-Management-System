import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class CreateSchema
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
             
            //create customers table
            String createCustomers = "create table customers(" +
                                        "name varchar(20),"+
                                        "PhoneNumber numeric(10,0) unique,"+
                                        "constraint chk1 check (PhoneNumber >= 1000000000),"+
                                        "address varchar(30),"+
                                        "city varchar(20),"+
                                        "province varchar(20),"+
                                        "pincode varchar(6),"+
                                        "customerID numeric(6,0) primary key,"+
                                        "constraint chk3 check (customerID >=100000)"+
                                    ")";
            
            statement.executeUpdate(createCustomers);
            
            //create accountDetails table
            String createAccountDetails = "create table accountDetails"+
                                        "("+
                                            "customerID numeric(6,0),"+
                                            "accNumber numeric(8,0) unique,"+
                                            "constraint chk4 check (accNumber >= 10000000),"+
                                            "debitNumber numeric(16,0) unique,"+
                                            "constraint chk5 check (debitNumber >= 1000000000000000),"+
                                            "balance int"+
                                        ")";
            statement.executeUpdate(createAccountDetails);
            
            //creates debitDetails table
            String createDebitDetails = "create table debitDetails"+
                                        "("+
                                            "debitNumber numeric(16,0),"+
                                            "cvv numeric(3,0),"+
                                            "constraint chk6 check ( cvv >= 100 ),"+
                                            "accNumber numeric(8,0),"+
                                            "name varchar(20)"+
                                        ")";
            statement.executeUpdate(createDebitDetails);
            
            //create transactions table
            String createTransactions = "create table transactions"+
                                        "("+
                                            "transactionID numeric(5,0) primary key,"+
                                            "constraint chk7 check ( transactionID >= 10000 ),"+
                                            "accNumber numeric(8,0) unique,"+
                                            "constraint chk8 check (accNumber >= 10000000),"+
                                            "transactionType varchar(6),"+
                                            "constraint chk9 check ( transactionType in ('credit' , 'debit')),"+
                                            "amount decimal(10,2),"+
                                            "transactionTime datetime"+
                                        ")";
            statement.executeUpdate(createTransactions);
            
            //create loan table
            String createLoan = "create table loan"+
                                "("+
                                    "loanID numeric(5,0) primary key,"+
                                    "constraint chk10 check ( loanID >= 10000 ),"+
                                    "accNumber numeric(8,0) unique,"+
                                    "constraint chk11 check (accNumber >= 10000000),"+
                                    "loanAmount decimal(10,2),"+
                                    "interestRate decimal,"+
                                    "loanTerm int"+
                                ")";
            statement.executeUpdate(createLoan);
            
            //create branch table
            String createBranch = "create table branch"+
                                    "("+
                                        "branchID numeric(5) primary key,"+
                                        "constraint chk12 check ( branchID >= 10000 ),"+
                                        "branchName varchar(20),"+
                                        "branchAddress varchar(30),"+
                                        "branchPhone numeric(10,0),"+
                                        "constraint chk13 check ( branchPhone >= 1000000000 )"+
                                    ")";
            statement.executeUpdate(createBranch);
            
            //create employee table
            String createEmployee = "create table employee"+
                                    "("+
                                        "employeeID int primary key ,"+
                                        "salary int,"+
                                        "employeeName varchar(30),"+
                                        "branchID numeric(5)"+
                                    ")";
            statement.executeUpdate(createEmployee);
            
            //adding all relationships to the table
            
            String r1 = "ALTER TABLE accountDetails "+
                         "ADD constraint FK_customerid "+
                         "FOREIGN KEY (customerID) References customers(customerID);";
                         
            statement.executeUpdate(r1);
            
            String r2 = "ALTER TABLE debitDetails "+
                         "ADD constraint FK_cardnumber "+
                         "FOREIGN KEY (debitNumber) References accountDetails(debitNumber),\n\n"+
                         "ADD constraint FK_accnumber "+
                         "FOREIGN KEY (accNumber) References accountDetails(accNumber)";
            statement.executeUpdate(r2);
            
            
            String r3 = "alter table transactions "+
                        "ADD constraint tFK_accNumber "+
                        "Foreign Key(accNumber) References accountDetails(accNumber)";
            statement.executeUpdate(r3);
            
            String r4 = "ALTER TABLE loan "+
                        "ADD constraint lFK_accnumber "+
                    	"FOREIGN KEY (accNumber) References accountDetails(accNumber)";
            statement.executeUpdate(r4);
            
            String r5 = "Alter table employee "+
                        "add constraint fk_branchid "+
                    	"FOREIGN KEY (branchID) REFERENCES branch(branchID)";
            statement.executeUpdate(r5);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
