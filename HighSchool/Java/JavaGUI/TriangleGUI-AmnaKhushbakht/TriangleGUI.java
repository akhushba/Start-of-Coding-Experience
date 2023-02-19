//various imports
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TriangleGUI extends JFrame {

  //variables
  private JPanel contentPane;
  private JTextField textFieldAngleA;
  private JTextField textFieldAngleB;
  private JTextField textFieldAngleC;
  private JLabel lblResult;

  // Launches Application
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          TriangleGUI frame = new TriangleGUI();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  // Create the frame
  public TriangleGUI() {
    setTitle("TC"); //title of frame
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //default close function
    setBounds(0, 0, 165, 220); //size of frame
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); //size of border around frame
    contentPane.setLayout(null); //type of layout
    setContentPane(contentPane);


    //labels Angle A
    JLabel lblNumberOne = new JLabel("Angle A:");  
    lblNumberOne.setBounds(10, 15, 90, 20);
    contentPane.add(lblNumberOne);

    //labels Angle B
    JLabel lblNumberTwo = new JLabel("Angle B:"); 
    lblNumberTwo.setBounds(10, 45, 90, 20);
    contentPane.add(lblNumberTwo);

    //labels Angle C
    JLabel lblNumberThree = new JLabel("Angle C:"); 
    lblNumberThree.setBounds(10, 75, 90, 20);
    contentPane.add(lblNumberThree);

    //input value for Angle A 
    textFieldAngleA = new JTextField();
    textFieldAngleA.setBounds(75, 15, 80, 20); 
    textFieldAngleA.setColumns(10);
    contentPane.add(textFieldAngleA);

    //input value for Angle B
    textFieldAngleB = new JTextField();
    textFieldAngleB.setBounds(75, 45, 80, 20); 
    textFieldAngleB.setColumns(10); 
    contentPane.add(textFieldAngleB);

    //input value for Angle C
    textFieldAngleC = new JTextField();
    textFieldAngleC.setBounds(75, 75, 80, 20); 
    textFieldAngleC.setColumns(10); 
    contentPane.add(textFieldAngleC);
    
    //classify button
    JButton btnClassify = new JButton("Classify");
    btnClassify.addMouseListener(new MouseAdapter() { 
      public void mouseClicked(MouseEvent e) {
        classify();
      }
    });
    btnClassify.setBounds(10, 110, 145, 25);
    contentPane.add(btnClassify);

    //creating the space for the result to be printed out on
    lblResult = new JLabel(""); 
    lblResult.setBounds(10, 145, 145, 35);
    lblResult.setHorizontalAlignment(JTextField.CENTER);
    lblResult.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
    contentPane.add(lblResult);
  }

  //running the classifer code
  public void classify() {
    try {
     
      //defining angles
      int angleA = Integer.valueOf(textFieldAngleA.getText());
      int angleB = Integer.valueOf(textFieldAngleB.getText());
      int angleC = Integer.valueOf(textFieldAngleC.getText());

      //getting the right classification
      TriangleClassifier triangle = new TriangleClassifier();
      String result = triangle.getClassification(angleA, angleB, angleC);

      //output classification
      lblResult.setForeground(Color.BLACK); 
      lblResult.setText(result);

      //catching exceptions
    } catch(NumberFormatException e) {
      lblResult.setForeground(Color.RED); 
      lblResult.setText("Enter valid angles.");
    }
  
  }
}