public class BankAccount {
  // Create your BankAccount object here and run your unit tests!
  String accountNumber;
  double balance;
  double withdrawalFee;
  double annualInterestRate;

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
  }

  // withdrawal and withdrawal fee taken away from balance
  public void withdraw(double amount) {
    balance -= (amount + withdrawalFee);
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
}