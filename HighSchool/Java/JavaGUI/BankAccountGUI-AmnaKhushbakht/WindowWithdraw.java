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

public class WindowWithdraw extends JFrame {

  private JPanel contentPane;
  protected JTextField textField;
  private JLabel lblDialogue;
  double withdrawAmount;

  // creating the window
  public WindowWithdraw(BankAccount current) {
    // creating the frame
    setTitle("Withdraw");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(0, 0, 300, 160); 
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); 
    contentPane.setLayout(null);
    setContentPane(contentPane);

    //providing info on the window
    JLabel lblWithdrawInfo = new JLabel("Please provide the amount");
    lblWithdrawInfo.setBounds(15, 5, 250, 20);
    contentPane.add(lblWithdrawInfo);
    JLabel lblWithdrawInfoMore = new JLabel("to be withdrawn:");
    lblWithdrawInfoMore.setBounds(15, 20, 250, 20);
    contentPane.add(lblWithdrawInfoMore);

    // withdraw amount input
    textField = new JTextField();
    textField.setBounds(15, 45, 270, 25);
    textField.setColumns(10);
    contentPane.add(textField);

    // creating the space for necessary dialogue to be printed out
    lblDialogue = new JLabel("");
    lblDialogue.setBounds(15, 70, 270, 20);
    lblDialogue.setHorizontalAlignment(JTextField.CENTER);
    contentPane.add(lblDialogue);

    // wtihdrawal confirmation button
    JButton btnOK = new JButton("OK");
    btnOK.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        try {
          withdrawAmount = Double.valueOf(textField.getText());
          current.withdraw(withdrawAmount);
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

    // cancel withdrawal button
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