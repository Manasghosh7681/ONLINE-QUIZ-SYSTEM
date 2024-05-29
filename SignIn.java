
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class SignIn implements ActionListener{
    JFrame frame;
    JLabel welcomeLabel,imageLabel,nameLabel,gmailLabel,gender,couseLebel,passwordLabel,loginLabel;
    JPasswordField passwordtf;
    JTextField nametf,gmailtf;
    JRadioButton male,female;
    JComboBox courses;
    JButton login,exit,signup;
    ButtonGroup bg;

    SignIn(){
        frame = new JFrame("Quiz Minds");
        frame.setLocation(250,200);
        frame.setSize(1000,500);
        frame.setLayout(null);

        ImageIcon logoIcon = new ImageIcon("logo.jpg");
        Image logo =logoIcon.getImage();
        frame.setIconImage(logo);

        ImageIcon imgIcon = new ImageIcon("FrontImage.jpg");
        imgIcon = new ImageIcon(imgIcon.getImage().getScaledInstance(500, 500, 0));
        imageLabel = new JLabel();
        imageLabel.setIcon(imgIcon);
        imageLabel.setBounds(0,0,500,500);
        frame.add(imageLabel);

       frame.getContentPane().setBackground(Color.GRAY);

       welcomeLabel = new JLabel("Welecome To Our Quiz Minds");
       welcomeLabel.setBounds(525,10,450,30);
       frame.add(welcomeLabel);

       Font font = new Font("Forte", Font.BOLD, 30);
       welcomeLabel.setFont(font);
       welcomeLabel.setForeground(Color.YELLOW);
    
       nameLabel = new JLabel("Enter Your Name  ");
       nameLabel.setBounds(700,50,150,20);
       frame.add(nameLabel);
       nameLabel.setFont(new Font("Ariel", Font.BOLD, 16));

       nametf = new JTextField();
       nametf.setBounds(600, 80, 300, 25);
       frame.add(nametf);

       gmailLabel = new JLabel("Enter Your Gmail  ");
       gmailLabel.setBounds(700,115,150,20);
       frame.add(gmailLabel);
       gmailLabel.setFont(new Font("Ariel", Font.BOLD, 16));

       gmailtf = new JTextField();
       gmailtf.setBounds(600, 145, 300, 25);
       frame.add(gmailtf);

       passwordLabel= new JLabel("Enter Password");
       passwordLabel.setBounds(700,175,150,20);
       frame.add(passwordLabel);
       passwordLabel.setFont(new Font("Ariel", Font.BOLD, 16));

       passwordtf = new JPasswordField();
       passwordtf.setBounds(600,205,300,25);
       frame.add(passwordtf);
    
       gender = new JLabel("Gender");
       gender.setBounds(600,240,80,30);
       frame.add(gender);
       gender.setFont(new Font("Ariel", Font.BOLD, 16));
       
       male = new JRadioButton("Male");
       male.setBounds(700,240,100,30);
       male.setBackground(Color.GRAY);
       frame.add(male);
       male.setFont(new Font("Ariel", Font.BOLD, 16));
    

       female = new JRadioButton("Female");
       female.setBounds(810,240,100,30);
       female.setBackground(Color.GRAY);
       frame.add(female);
       female.setFont(new Font("Ariel", Font.BOLD, 16));

        bg = new ButtonGroup();
       bg.add(male);
       bg.add(female);

       couseLebel = new JLabel("Qualification");
       couseLebel.setBounds(600,280,100,30);
       frame.add(couseLebel);
       couseLebel.setFont(new Font("Ariel", Font.BOLD, 16));

       String [] courseName = {" ","MCA","CS","EEE","ECE","OTHERS"};
       courses = new JComboBox<>(courseName);
       courses.setBounds(730, 280, 170, 30);
       frame.add(courses);
       
       exit = new JButton("Exit");
       exit.setBounds(630,340,80,30);
       frame.add(exit);
       exit.addActionListener(this);

       signup = new JButton("Sign Up");
       signup.setBounds(780,340,80,30);
       frame.add(signup);
       signup.addActionListener(this);

       loginLabel = new JLabel("If you have an account then");
       loginLabel.setBounds(600,402,160,30);
       frame.add(loginLabel);

       login = new JButton("Log in");
       login.setBounds(761,400,80,30);
       frame.add(login);
       login.addActionListener(this);

       exit.setBackground(Color.RED);
       exit.setForeground(Color.white);

       signup.setBackground(Color.BLACK);
       signup.setForeground(Color.white);

       login.setBackground(Color.GREEN);
       login.setForeground(Color.white);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
  
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource().equals(exit)){
            frame.setVisible(false);
        }
        else if(ae.getSource().equals(login)){
            frame.dispose();
            new LoginFrame();
        }
        else if(ae.getSource().equals(signup)){
            try{
                String name=nametf.getText();
                String gmail=gmailtf.getText();
                char[] passwordArray = passwordtf.getPassword();
                String password = new String(passwordArray);

                String gender=null;
                if(male.isSelected())
                {
                    gender="MALE";
                }else if(female.isSelected())
                {
                    gender="FEMALE";  
                }
                String qualification=(String)courses.getSelectedItem();

                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Manas","12345");

                String qry="INSERT INTO  STUDENT VALUES('"+name+"','"+gmail+"','"+password+"','"+gender+"','"+qualification+"')";
                Statement s1=con.createStatement();
                int i=s1.executeUpdate(qry);
                if(i>0)
                {
                    JOptionPane.showMessageDialog(frame,"You have  Succesfully registered ");
                    nametf.setText("");
                    gmailtf.setText("");
                    passwordtf.setText("");
                    bg.clearSelection();
                    courses.setSelectedItem(" ");

                }

            }catch(ClassNotFoundException ce)
            {
                System.out.println(ce);
            }catch(SQLException se)
            {
                System.out.println(se);
                JOptionPane.showMessageDialog(frame,"This mail id alread exist");
            }
        }
    }
    public static void main(String[] args) {
        SignIn obj = new SignIn();
    }
}