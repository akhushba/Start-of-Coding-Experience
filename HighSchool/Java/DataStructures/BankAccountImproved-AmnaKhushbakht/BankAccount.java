import java.time.LocalDateTime;
import java.util.ArrayList;

public class BankAccount {

  String accountNumber;
  double balance, withdrawalFee, annualInterestRate;
  ArrayList<Transaction> allTransactions = new ArrayList<Transaction>();

  private void addTransList(Transaction newTrans){ //helper to add transaction to right spot
    for(int i = 0; i <= allTransactions.size(); i++){
      if(i == allTransactions.size()) 
      allTransactions.add(newTrans);
      else if(newTrans.getTransactionTime().compareTo(allTransactions.get(i).getTransactionTime()) < 0) 
      allTransactions.add(i, newTrans);
    }
  }

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
    Transaction trans = new Transaction(amount, "deposit");
    allTransactions.add(trans); //add to end, no time specified
  }

  // new deposit for all parameters
  public void deposit(LocalDateTime transactionTime, double amount, String description) {
    balance += amount;
    Transaction trans = new Transaction(transactionTime, amount, description);
    addTransList(trans) //add to correct spot as time specified
  }

  // new depsoit for only amount and description
  public void deposit(double amount, String description) {
    balance += amount;
    Transaction trans = new Transaction(amount, description);
    allTransactions.add(trans); //add to end, no time specified
  }

  // withdrawal and withdrawal fee taken away from balance
  public void withdraw(double amount) {
    balance -= (amount + withdrawalFee);
    Transaction trans = new Transaction(amount, "withdrawal");
    allTransactions.add(trans); //add to end, no time specified
  }

  // new withdrawal for all parameters
  public void withdraw(LocalDateTime transactionTime, double amount, String description) {
    balance -= (amount + withdrawalFee);
    Transaction trans = new Transaction(transactionTime, amount, description);
    addTransList(trans) //add to correct spot as time specified
  }

  // new withdrawal for only amount and description
  public void withdraw(double amount, String description) {
    balance -= (amount + withdrawalFee);
    Transaction trans = new Transaction(amount, description);
    allTransactions.add(trans); //add to end, no time specified
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
      return "BankAccount " + accountNumber + ": ($" + String.format("%.02f", (balance * -1)) + ")";
    }
  }

  // Gets you a list of every transaction within a defined time
  public ArrayList getTransactions(LocalDateTime startTime, LocalDateTime endTime) {
    ArrayList<Transaction> selectTransactions = new ArrayList<Transaction>();
    for (int i = 0; i < allTransactions.size(); i++) {
      if ((startTime == null || allTransactions.get(i).getTransactionTime().compareTo(startTime) >= 0) && 
      (endTime == null || allTransactions.get(i).getTransactionTime().compareTo(endTime) <= 0)) {
          selectTransactions.add(allTransactions.get(i));
      }
    }
    return selectTransactions;
  }
}