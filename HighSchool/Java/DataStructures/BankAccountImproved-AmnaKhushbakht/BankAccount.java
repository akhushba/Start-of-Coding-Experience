import java.time.LocalDateTime;
import java.util.ArrayList;

public class BankAccount {

  String accountNumber, description;
  double balance, withdrawalFee, annualInterestRate;
  ArrayList<Transaction> allTransactions = new ArrayList<Transaction>();
  Transaction trans;

  // accessor for account number
  public String getAccountNumber() {
    return accountNumber;
  }

  // accessor for balance
  public double getBalance() {
    return balance;
  }

  // accessor for withdrawal fee
  public double getWithdrawalFee() {
    return withdrawalFee;
  }

  // accessor for annual interest rate
  public double getAnnualInterestRate() {
    return annualInterestRate;
  }

  // mutator for withdrawal fee
  public void setWithdrawalFee(double withdrawal) {
    withdrawalFee = withdrawal;
  }

  // mutator for annual interest rate
  public void setAnnualInterestRate(double interestRate) {
    annualInterestRate = interestRate;
  }

  // first constructor
  public BankAccount(String accountNum) {
    accountNumber = accountNum;
  }

  // second constructor
  public BankAccount(String accountNum, double initialBalance) {
    accountNumber = accountNum;
    balance = initialBalance;
  }

  // third constructor for everything
  public BankAccount(String accountNum, double initialBalance, double withdrawFee, double annualRate) {
    accountNumber = accountNum;
    balance = initialBalance;
    withdrawalFee = withdrawFee;
    annualInterestRate = annualRate;
  }

  // desposit added to balance
  public void deposit(double amount) {
    balance += amount;
    description = "deposit";

    // add it to the list of overall transactions
    trans = new Transaction(amount, description);
    allTransactions.add(trans);
  }

  // new deposit for all parameters
  public void deposit(LocalDateTime transactionTime, double amount, String description) {

    balance += amount;
    trans = new Transaction(transactionTime, amount, description);

    // adding it to the end, or starting to add to the list
    if (allTransactions.size() == 0 || transactionTime.isAfter(allTransactions.get(allTransactions.size() - 1).getTransactionTime())) {
      allTransactions.add(trans);

      // adding it in the middle
    } else {
      for (int i = 0; i < allTransactions.size(); i++) {
        if (transactionTime.compareTo(allTransactions.get(i).getTransactionTime()) < 0) {
          allTransactions.add(i, trans);
          break;
        }
      }
    }
  }

  // new depsoit for only amount and description
  public void deposit(double amount, String description) {
    balance += amount;

    // add it to the list of overall transactions
    trans = new Transaction(amount, description);
    allTransactions.add(trans);
  }

  // withdrawal and withdrawal fee taken away from balance
  public void withdraw(double amount) {
    balance -= (amount + withdrawalFee);
    description = "withdrawal";

    // add it to the list of overall transactions
    trans = new Transaction(amount, description);
    allTransactions.add(trans);
  }

  // new withdrawal for all parameters
  public void withdraw(LocalDateTime transactionTime, double amount, String description) {
    balance -= (amount + withdrawalFee);
    trans = new Transaction(transactionTime, amount, description);

    // adding it to the end, or starting to add to the list
    if (allTransactions.size() == 0
        || transactionTime.isAfter(allTransactions.get(allTransactions.size() - 1).getTransactionTime())) {
      allTransactions.add(trans);

      // adding it in the middle
    } else {
      for (int i = 0; i < allTransactions.size(); i++) {
        if (transactionTime.compareTo(allTransactions.get(i).getTransactionTime()) < 0) {
          allTransactions.add(i, trans);
          break;
        }
      }
    }

  }

  // new withdrawal for only amount and description
  public void withdraw(double amount, String description) {
    balance -= (amount + withdrawalFee);

    // add it to the list of overall transactions
    trans = new Transaction(amount, description);
    allTransactions.add(trans);
  }

  // testing to see if you have a negative balance
  public boolean isOverDrawn() {
    if (balance <= 0) {
      return true;
    } else {
      return false;
    }
  }

  // toString method
  public String toString() {
    if (balance >= 0) {
      return "BankAccount " + accountNumber + ": $" + String.format("%.02f", balance);

    } else {
      balance *= -1;
      return "BankAccount " + accountNumber + ": ($" + String.format("%.02f", balance) + ")";
    }
  }

  // Gets you a list of every transaction within a defined time
  public ArrayList getTransactions(LocalDateTime startTime, LocalDateTime endTime) {

    // creating the list for selected transactions every single time the method is called
    ArrayList<Transaction> selectTransactions = new ArrayList<Transaction>();

    // running through all the options
    for (int i = 0; i < allTransactions.size(); i++) {

      // defining only an end time
      if (startTime == null && endTime != null) {
        if (allTransactions.get(i).getTransactionTime().compareTo(endTime) <= 0) {
          selectTransactions.add(allTransactions.get(i));
        }

        // defining only a start time
      } else if (startTime != null && endTime == null) {
        if (allTransactions.get(i).getTransactionTime().compareTo(startTime) >= 0) {
          selectTransactions.add(allTransactions.get(i));
        }

        // no definite start or end
      } else if (startTime == null && endTime == null) {
        selectTransactions.add(allTransactions.get(i));

        // defining a start time and an end time
      } else if (startTime != null && endTime != null) {
        if (allTransactions.get(i).getTransactionTime().compareTo(endTime) <= 0
            && allTransactions.get(i).getTransactionTime().compareTo(startTime) >= 0) {
          selectTransactions.add(allTransactions.get(i));
        }
      }
    }

    return selectTransactions;
  }
}