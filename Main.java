import java.util.Scanner;

class BankAccount {
    private String username;
    private int accountNumber;
    private String uniqueId;
    private String branch;
    private String accountType;
    private double balance;

    // Constructor
    BankAccount(String username, int accountNumber, String uniqueId, String accountType, double balance) {
        this.username = username;
        this.accountNumber = accountNumber;
        this.uniqueId = uniqueId;
        this.accountType = accountType;
        this.balance = balance;
        this.branch = findBranch(uniqueId);
    }

    // Determine branch based on Unique ID
    private String findBranch(String uniqueId) {
        if (uniqueId.startsWith("DEL")) return "Delhi Branch";
        else if (uniqueId.startsWith("MUM")) return "Mumbai Branch";
        else if (uniqueId.startsWith("BLR")) return "Bangalore Branch";
        else return "Unknown Branch";
    }

    // Deposit method with validation
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount Deposited Successfully!");
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Withdraw method with validation
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Amount Withdrawn Successfully!");
        } else {
            System.out.println("Insufficient Balance or Invalid Amount!");
        }
    }

    // Overdraft method with validation
    public void overdraft(double amount) {
        if (amount > 0) {
            balance -= amount;
            System.out.println("Overdraft Successful!");
        } else {
            System.out.println("Invalid overdraft amount!");
        }
    }

    // Display account details
    public void display() {
        System.out.println("\nUpdated Account Information:");
        System.out.println("Name: " + username);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Branch: " + branch);
        System.out.println("Account Type: " + accountType);
        System.out.println("Current Balance: " + balance);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // User Input
            System.out.print("Enter Username: ");
            String username = sc.nextLine();
            
            System.out.print("Enter Account Number: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a valid account number.");
                sc.next();
            }
            int accNo = sc.nextInt();
            sc.nextLine(); // Consume newline

            System.out.print("Enter Unique ID: ");
            String uniqueId = sc.nextLine();
            
            System.out.print("Enter Account Type (Saving/Current): ");
            String accType = sc.nextLine();
            
            System.out.print("Enter Initial Balance: ");
            while (!sc.hasNextDouble()) {
                System.out.println("Invalid input! Please enter a valid balance.");
                sc.next();
            }
            double balance = sc.nextDouble();

            BankAccount account = new BankAccount(username, accNo, uniqueId, accType, balance);

            // Menu for operations
            int choice;
            do {
                System.out.println("\nChoose Operation:");
                System.out.println("1. Deposit Cash");
                System.out.println("2. Withdraw Cash");
                System.out.println("3. Overdraft");
                System.out.println("4. Display Account Details");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                
                while (!sc.hasNextInt()) {
                    System.out.println("Invalid choice! Please enter a number between 1 and 5.");
                    sc.next();
                }
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to deposit: ");
                        while (!sc.hasNextDouble()) {
                            System.out.println("Invalid input! Enter a valid amount.");
                            sc.next();
                        }
                        double dep = sc.nextDouble();
                        account.deposit(dep);
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        while (!sc.hasNextDouble()) {
                            System.out.println("Invalid input! Enter a valid amount.");
                            sc.next();
                        }
                        double wd = sc.nextDouble();
                        account.withdraw(wd);
                        break;
                    case 3:
                        System.out.print("Enter amount for overdraft: ");
                        while (!sc.hasNextDouble()) {
                            System.out.println("Invalid input! Enter a valid amount.");
                            sc.next();
                        }
                        double od = sc.nextDouble();
                        account.overdraft(od);
                        break;
                    case 4:
                        account.display();
                        break;
                    case 5:
                        System.out.println("Thank you for banking with us!");
                        break;
                    default:
                        System.out.println("Invalid Choice! Try Again.");
                }
            } while (choice != 5);
        } finally {
            sc.close();
        }
    }
}