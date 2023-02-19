import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.io.*;

public class WindowOne extends JFrame implements WindowListener{

  // variables
  private JPanel contentPane;
  private JTextField textFieldCurrentBalance;
  private WindowWithdraw withdrawWin;
  private WindowDeposit depositWin;
  private WindowTransaction transactionsWin;
  protected JLabel lblResult;
  BankAccount chequing = new BankAccount("Chequing");
  BankAccount savings = new BankAccount("Savings");
  BankAccount current;
  Serializer chequingSerialized = new Serializer();
  Serializer savingsSerialized = new Serializer();
  int account;

  // Launch Application
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          WindowOne frame = new WindowOne();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  // setting up the window
  public WindowOne() throws IOException {

    //deriving the serialized chequing account
    if (chequingSerialized.deserialize("Chequing") == null){
      chequingSerialized.serialize(chequing, "Chequing");
    } else {
      chequing = chequingSerialized.deserialize("Chequing");

    }
    //deriving the serialized savings account
    if (savingsSerialized.deserialize("Savings") == null){
      savingsSerialized.serialize(savings, "Savings");
    } else {
      savings = savingsSerialized.deserialize("Savings");
    }
    
    //setting up the frame
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(0, 0, 250, 175);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(null);
    setContentPane(contentPane);

    //dialogue to help navigate GUI
    JLabel lblDialogue1 = new JLabel("");
    lblDialogue1.setForeground(Color.BLUE);
    lblDialogue1.setBounds(10, 65, 230, 20);
    lblDialogue1.setHorizontalAlignment(JTextField.CENTER);
    contentPane.add(lblDialogue1);

    // account label
    JLabel lblAccount = new JLabel("Account:");
    lblAccount.setBounds(10, 10, 90, 20);
    contentPane.add(lblAccount);
    // drop down menu to select the account
    String[] accounts = { "", "Chequing", "Savings" };
    JComboBox comboBox = new JComboBox(accounts);
    comboBox.setBackground(Color.WHITE);
    // actions taken when an account gets chosen
    comboBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        
        // if they chose a chequing account
        if (comboBox.getSelectedIndex() == 1) {
          current = chequing;
          lblDialogue1.setText("");
          textFieldCurrentBalance.setText(chequing.balanceToString());
          displayBalance();
        
          // if they chose a savings account
        } else if (comboBox.getSelectedIndex() == 2) {
          current = savings;
          lblDialogue1.setText("");
          account = 2;
          textFieldCurrentBalance.setText(savings.balanceToString());
          displayBalance();

          // if they haven't chosen an account
        } else if (comboBox.getSelectedIndex() == 0) {
          current = null;
          textFieldCurrentBalance.setText("");
        }
      }
    });
    comboBox.setBounds(135, 10, 105, 20);
    contentPane.add(comboBox);

    // current balance label
    JLabel lblCurrentBalance = new JLabel("Current Balance: ");
    lblCurrentBalance.setBounds(10, 40, 150, 20);
    contentPane.add(lblCurrentBalance);

    // current balance text field
    textFieldCurrentBalance = new JTextField();
    textFieldCurrentBalance.setBackground(Color.WHITE);
    textFieldCurrentBalance.setHorizontalAlignment(JTextField.RIGHT);
    textFieldCurrentBalance.setEditable(false);
    textFieldCurrentBalance.setBounds(135, 40, 105, 20);
    textFieldCurrentBalance.setColumns(10);
    textFieldCurrentBalance.setText("");
    contentPane.add(textFieldCurrentBalance);

    // Withdraw button
    JButton btnWithdraw = new JButton("Withdraw");
    btnWithdraw.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        //opens the right window/gives right prompt
        if (current == chequing || current == savings) {
          openWithdrawWindow();
        } else {
          lblDialogue1.setText("Please select an account");
        }
      }
    });
    btnWithdraw.setBounds(10, 115, 110, 20);
    contentPane.add(btnWithdraw);

    // Deposit button
    JButton btnDeposit = new JButton("Deposit");
    btnDeposit.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        //opens the right window/gives right prompt
        if (current == chequing || current == savings) {
          openDepositWindow();
        } else {
          lblDialogue1.setText("Please select an account");
        }
      }
    });
    btnDeposit.setBounds(130, 115, 110, 20);
    contentPane.add(btnDeposit);

    // Transaction button
    JButton btnTransactions = new JButton("Browse Transactions");
    btnTransactions.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        //opens the right window/gives right prompt
        if (current == chequing || current == savings) {
          openTransactionsWindow();
        } else {
          lblDialogue1.setText("Please select an account");
        }
      }
    });
    btnTransactions.setBounds(10, 90, 230, 20);
    contentPane.add(btnTransactions);
  }

  // function to open withdraw window
  public void openWithdrawWindow() {
    withdrawWin = new WindowWithdraw(current);
    withdrawWin.addWindowListener(this); 
    withdrawWin.setVisible(true);
  }

  // function to open deposit window
  public void openDepositWindow() {
    depositWin = new WindowDeposit(current);
    depositWin.addWindowListener(this);
    depositWin.setVisible(true);
  }

  // function to open transaction window
  public void openTransactionsWindow() {
    transactionsWin = new WindowTransaction(current);
    transactionsWin.addWindowListener(this); 
    transactionsWin.setVisible(true);
  }

  public void windowOpened(WindowEvent e) {
  }

  public void windowClosing(WindowEvent e) {
    //serializing Bank Account
    chequingSerialized.serialize(chequing, "Chequing");
    savingsSerialized.serialize(savings, "Savings");
    
  }

  public void windowClosed(WindowEvent e) {
    //showing the chequing balance if selected with the right colour
    if (current == chequing) {
      textFieldCurrentBalance.setText(chequing.balanceToString());
      displayBalance();

    //showing the savings balance if selected with the right colour
    } else if (current == savings) {
     textFieldCurrentBalance.setText(savings.balanceToString());
      displayBalance();
    }
  }

  public void windowIconified(WindowEvent e) {
  }

  public void windowDeiconified(WindowEvent e) {
  }

  public void windowActivated(WindowEvent e) {
  }

  public void windowDeactivated(WindowEvent e) {
  }

  // displaying the balance in red if it's negative, black if it's positive
  public void displayBalance(){
    if (current.getBalance() >= 0) {
        textFieldCurrentBalance.setForeground(Color.BLACK);
    } else {
        textFieldCurrentBalance.setForeground(Color.RED);
    }
  }
}