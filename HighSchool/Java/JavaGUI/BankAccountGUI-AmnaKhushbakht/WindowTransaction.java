import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent; 
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JComboBox;
import java.util.Arrays;
import javax.swing.JList;
import javax.swing.JScrollBar;

public class WindowTransaction extends JFrame implements ActionListener{

  // varianles
  private JPanel contentPane;
  private JLabel lblDialogue;
  ArrayList<Transaction> transactions;
  LocalTime general = LocalTime.of(0, 0, 0);
  int startYear, startMonth, startDay, endYear, endMonth, endDay;
  LocalDateTime startTime, endTime;
  LocalDate start, end;
  String[] transOutput;
  String[] blank = {""};
  JList list;
  JScrollPane scroll;

  // Create the window
  public WindowTransaction(BankAccount current) {
    setTitle("View Transactions");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(0, 0, 300, 300); 
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(null); 
    setContentPane(contentPane);

    // info
    JLabel lblInfo = new JLabel("Enter start times and end times.");
    lblInfo.setBounds(10, 2, 250, 25);
    contentPane.add(lblInfo);

    // more info
    JLabel lblInfoMore = new JLabel("Leave as is if unspecified.");
    lblInfoMore.setBounds(10, 17, 250, 25);
    contentPane.add(lblInfoMore);

    //start time labels and inputs
    JLabel lblStartTime = new JLabel("Start time:");
    lblStartTime.setBounds(10, 45, 85, 25);
    contentPane.add(lblStartTime);
    // year
    JTextField textFieldStartYear = new JTextField();
    textFieldStartYear.setBounds(130, 45, 50, 25);
    textFieldStartYear.setText("Year");
    textFieldStartYear.setForeground(Color.GRAY);
    textFieldStartYear.setHorizontalAlignment(JTextField.CENTER);
    textFieldStartYear.setColumns(10);
    contentPane.add(textFieldStartYear);
    // month
    JTextField textFieldStartMonth = new JTextField();
    textFieldStartMonth.setBounds(185, 45, 50, 25);
    textFieldStartMonth.setText("Month");
    textFieldStartMonth.setForeground(Color.GRAY);
    textFieldStartMonth.setHorizontalAlignment(JTextField.CENTER);
    textFieldStartMonth.setColumns(10);
    contentPane.add(textFieldStartMonth);
    // day
    JTextField textFieldStartDay = new JTextField();
    textFieldStartDay.setBounds(240, 45, 50, 25);
    textFieldStartDay.setText("Day");
    textFieldStartDay.setForeground(Color.GRAY);
    textFieldStartDay.setHorizontalAlignment(JTextField.CENTER);
    textFieldStartDay.setColumns(10);
    contentPane.add(textFieldStartDay);

    // end time labels and inputs
    JLabel lblEndTime = new JLabel("End time:");
    lblEndTime.setBounds(10, 75, 85, 25);
    contentPane.add(lblEndTime);
    // year
    JTextField textFieldEndYear = new JTextField();
    textFieldEndYear.setBounds(130, 75, 50, 25);
    textFieldEndYear.setText("Year");
    textFieldEndYear.setForeground(Color.GRAY);
    textFieldEndYear.setHorizontalAlignment(JTextField.CENTER);
    textFieldEndYear.setColumns(10);
    contentPane.add(textFieldEndYear);
    // month
    JTextField textFieldEndMonth = new JTextField();
    textFieldEndMonth.setBounds(185, 75, 50, 25);
    textFieldEndMonth.setText("Month");
    textFieldEndMonth.setForeground(Color.GRAY);
    textFieldEndMonth.setHorizontalAlignment(JTextField.CENTER);
    textFieldEndMonth.setColumns(10);
    contentPane.add(textFieldEndMonth);
    // day
    JTextField textFieldEndDay = new JTextField();
    textFieldEndDay.setBounds(240, 75, 50, 25);
    textFieldEndDay.setText("Day");
    textFieldEndDay.setForeground(Color.GRAY);
    textFieldEndDay.setHorizontalAlignment(JTextField.CENTER);
    textFieldEndDay.setColumns(10);
    contentPane.add(textFieldEndDay);

    // used for any necessary dialogue
    lblDialogue = new JLabel(""); 
    lblDialogue.setBounds(10, 175, 280, 25);
    lblDialogue.setHorizontalAlignment(JTextField.CENTER);
    contentPane.add(lblDialogue);

    
    list = new JList(blank);
    scroll = new JScrollPane(list);
    scroll.setBounds(10, 105, 280, 95);
    scroll.setBackground(Color.WHITE);
    contentPane.add(scroll);
    
    // button that shows all transactions
    JButton btnTransactions = new JButton("Show Transactions");
    btnTransactions.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        //if start time and end time is null
        if ((textFieldStartYear.getText() == "Year" || textFieldStartMonth.getText() == "Month" || textFieldStartMonth.getText() == "Day") && (textFieldEndYear.getText() == "Year" || textFieldEndMonth.getText() == "Month" || textFieldEndDay.getText() == "Day")) {
          startTime = null; 
          endTime = null;
        // if only end time is null
        } else if (textFieldEndYear.getText() == "Year" || textFieldEndMonth.getText() == "Month" || textFieldEndDay.getText() == "Day") {
          endTime = null;
        // if only start time is null
        } else if (textFieldStartYear.getText() == "Year" || textFieldStartMonth.getText() == "Month" || textFieldStartMonth.getText() == "Day") {
          startTime = null;
        } 

        // if values have been given
        if (startTime != null && endTime != null){
          // try catch or excpetions
          try {
            // retrieving startTime values
            if (startTime != null){
              startYear = Integer.valueOf(textFieldStartYear.getText());
              startMonth = Integer.valueOf(textFieldStartMonth.getText());
              startDay = Integer.valueOf(textFieldStartDay.getText());
            }
            // retrieving endTime values
            if (endTime != null){
              endYear = Integer.valueOf(textFieldEndYear.getText());
              endMonth = Integer.valueOf(textFieldEndMonth.getText());
              endDay = Integer.valueOf(textFieldEndDay.getText());
            }
            
            // handling other exceptions
            if (startYear < 0 || startMonth < 1 || startMonth > 12 || startDay < 1 || startDay > 31 || endYear < 0 || endMonth < 1 || endMonth > 12 || endDay < 1 || endDay > 31){
              setDialogue();
            
            } else {
              // setting the start time and end time

              if (endTime != null){
                end = LocalDate.of(endYear, endMonth, endDay);
                endTime = LocalDateTime.of(end, general);
              }

              if (startTime != null){
                start = LocalDate.of(startYear, startMonth, startDay);
                startTime = LocalDateTime.of(start, general);
              }
          }
          // catching exceptions
          } catch (NumberFormatException a) {
            setDialogue();
            
        }
      }

        //setting up the String Array
        transactions = current.getTransactions(startTime, endTime);
        transOutput = new String[transactions.size()];
        for(int i = 0; i < transOutput.length; i++){
          double amount = transactions.get(i).getAmount();
          transOutput[i] = transactions.get(i).getDescription().toUpperCase() + ": $" + String.format("%.02f", amount);
          
          lblDialogue.setText("");
          
        }
        //displaying the String Array for transactions
        list.setListData(transOutput);

      }
    });
    btnTransactions.setBounds(10, 205, 280, 25);
    contentPane.add(btnTransactions);
    
    // back button
    JButton btnBack = new JButton("Back");
    btnBack.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        done();
      }
    });
    btnBack.setBounds(10, 235, 280, 25);
    contentPane.add(btnBack);
  }

  public void done() {
    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
  }

  //if errors occur, it displays an error message
  public void setDialogue(){
    if (startTime != null || endTime != null){
      lblDialogue.setForeground(Color.RED);
      lblDialogue.setText("Please enter valid dates");
      list.setListData(blank);
    }
  }
  
  public void actionPerformed(ActionEvent e) {
                
  }

}