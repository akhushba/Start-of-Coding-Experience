import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent; 
import java.awt.Color;

public class WindowDeposit extends JFrame {

  private JPanel contentPane;
  protected JTextField textField;
  double depositAmount;
  private JLabel lblDialogue;

  // creating the deposit window
  public WindowDeposit(BankAccount current) {
    // frame
    setTitle("Deposit");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(0, 0, 300, 160); 
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); 
    contentPane.setLayout(null); 
    setContentPane(contentPane);

    //displaying info needed
    JLabel lblDepositInfo = new JLabel("Please provide the amount");
    lblDepositInfo.setBounds(15, 5, 250, 20);
    contentPane.add(lblDepositInfo);
    JLabel lblDepositInfoMore = new JLabel("to be deposited:");
    lblDepositInfoMore.setBounds(15, 20, 250, 20);
    contentPane.add(lblDepositInfoMore);

    // deposit amount input 
    textField = new JTextField();
    textField.setBounds(15, 45, 270, 25);
    textField.setColumns(10);
    contentPane.add(textField);

    // creating the space for necessary dialogue to be printed out
    lblDialogue = new JLabel("");
    lblDialogue.setBounds(15, 70, 270, 20);
    lblDialogue.setHorizontalAlignment(JTextField.CENTER);
    contentPane.add(lblDialogue);

    // deposit confirmation button
    JButton btnOK = new JButton("OK");
    btnOK.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        try {
          depositAmount = Double.valueOf(textField.getText());
          current.deposit(depositAmount);
          done();
          // catching exceptions
        } catch (NumberFormatException a) {
          lblDialogue.setForeground(Color.RED);
          lblDialogue.setText("Please enter valid amount");

        }
      }
    });
    btnOK.setBounds(60, 95, 85, 25);
    contentPane.add(btnOK);

    // desposit cancellation button
    JButton btnCancel = new JButton("Cancel");
    btnCancel.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        done();
      }
    });
    btnCancel.setBounds(155, 95, 85, 25);
    contentPane.add(btnCancel);
  }
  
  public void done() {
    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
  }

}