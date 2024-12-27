package bankingSystem;
import java.util.*;
class Account {
    private int accountNumber; 
    private String holderName;
    private double balance;
    private List<String> transactions;

    public Account(int accountNumber, String holderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        transactions.add("Account created with balance: " + initialBalance);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add("Deposited: " + amount);
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add("Withdrew: " + amount);
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void transfer(Account target, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            target.deposit(amount);
            transactions.add("Transferred " + amount + " to Account " + target.getAccountNumber());
            System.out.println("Transfer successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient balance or invalid transfer amount.");
        }
    }

    public void printTransactionHistory() {
        System.out.println("Transaction history for Account " + accountNumber + ":");
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }
}

// Class to manage the banking system
class Bank {
    private Map<Integer, Account> accounts;
    private int accountCounter;

    public Bank() {
        accounts = new HashMap<>();
        accountCounter = 1001; // Starting account number
    }

    public void createAccount(String holderName, double initialBalance) {
        Account account = new Account(accountCounter, holderName, initialBalance);
        accounts.put(accountCounter, account);
        System.out.println("Account created successfully! Account Number: " + accountCounter);
        accountCounter++;
    }

    public void deleteAccount(int accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            accounts.remove(accountNumber);
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    public void deposit(int accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdraw(int accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void transfer(int sourceAccount, int targetAccount, double amount) {
        Account source = accounts.get(sourceAccount);
        Account target = accounts.get(targetAccount);
        if (source != null && target != null) {
            source.transfer(target, amount);
        } else {
            System.out.println("One or both accounts not found.");
        }
    }

    public void viewAccountDetails(int accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Holder Name: " + account.getHolderName());
            System.out.println("Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void printTransactionHistory(int accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.printTransactionHistory();
        } else {
            System.out.println("Account not found.");
        }
    }
}

// Main class to run the banking system
public class BankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("\nWelcome to the Banking System");
            System.out.println("1. Create Account");
            System.out.println("2. Delete Account");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Transfer Funds");
            System.out.println("6. View Account Details");
            System.out.println("7. Transaction History");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter holder name: ");
                    scanner.nextLine(); // Consume newline
                    String holderName = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double initialBalance = scanner.nextDouble();
                    bank.createAccount(holderName, initialBalance);
                    break;
                case 2:
                    System.out.print("Enter account number to delete: ");
                    int accountToDelete = scanner.nextInt();
                    bank.deleteAccount(accountToDelete);
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    int depositAccount = scanner.nextInt();
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    bank.deposit(depositAccount, depositAmount);
                    break;
                case 4:
                    System.out.print("Enter account number: ");
                    int withdrawAccount = scanner.nextInt();
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    bank.withdraw(withdrawAccount, withdrawAmount);
                    break;
                case 5:
                    System.out.print("Enter source account number: ");
                    int sourceAccount = scanner.nextInt();
                    System.out.print("Enter target account number: ");
                    int targetAccount = scanner.nextInt();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    bank.transfer(sourceAccount, targetAccount, transferAmount);
                    break;
                case 6:
                    System.out.print("Enter account number: ");
                    int viewAccount = scanner.nextInt();
                    bank.viewAccountDetails(viewAccount);
                    break;
                case 7:
                    System.out.print("Enter account number: ");
                    int historyAccount = scanner.nextInt();
                    bank.printTransactionHistory(historyAccount);
                    break;
                case 8:
                    System.out.println("Exiting Banking System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
