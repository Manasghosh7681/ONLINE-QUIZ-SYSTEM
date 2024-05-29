
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

class LoginFrame implements ActionListener{
    JFrame frame2;
    JLabel lname,lemail,lpassword,imageLabel,errormsg;
    JTextField tfemail;
    JPasswordField pfpassword;
    JButton btnlogin,btn2;
    LoginFrame(){
        frame2 = new JFrame("Welcome");
        frame2.setLocation(500,170);
        frame2.setSize(520,400);
        frame2.setLayout(null);

        ImageIcon logoIcon = new ImageIcon("Logo.jpg");
        Image logo = logoIcon.getImage();
        frame2.setIconImage(logo);

        frame2.getContentPane().setBackground(Color.DARK_GRAY);

        lname=new JLabel("Enter Details To Login");
        lname.setBounds(100,30,300,40);
        frame2.add(lname);
        lname.setFont(new Font("Ariel Black", Font.BOLD, 28));
        lname.setForeground(Color.CYAN);

        lemail = new JLabel("Enter Your E-mail");
        lemail.setBounds(100,90,200,35);
        frame2.add(lemail);
        lemail.setFont(new Font("Ariel", Font.BOLD, 16));
        lemail.setForeground(Color.black);

        tfemail = new JTextField();
        tfemail.setBounds(100,120,300,30);
        frame2.add(tfemail);

        lpassword = new JLabel("Enter Your Password");
        lpassword.setBounds(100,170,300,35);
        frame2.add(lpassword);
        lpassword.setFont(new Font("Ariel", Font.BOLD, 16));
        lpassword.setForeground(Color.BLACK);

        pfpassword = new JPasswordField();
        pfpassword.setBounds(100,200,300,30);
        frame2.add(pfpassword);

        btnlogin = new JButton("Log in");
        btnlogin.setBounds(210,260,100,30);
        btnlogin.addActionListener(this);
        frame2.add(btnlogin);
        btnlogin.setBackground(Color.black);
        btnlogin.setForeground(Color.WHITE);


        errormsg=new JLabel();
        errormsg.setBounds(210,300,100,30);
        errormsg.setForeground(Color.RED);
        frame2.add(errormsg);

        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        try{
            String mail=tfemail.getText();
            char pass[]=pfpassword.getPassword();
            String password=new String(pass);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Manas","12345");
            String qry="SELECT GMAIL,PASSWORD FROM STUDENT WHERE GMAIL='"+mail+"' AND PASSWORD='"+password+"'";
            Statement s1=con.createStatement();
            ResultSet rs=s1.executeQuery(qry);
            if(rs.next())
            {
                frame2.dispose();
                new RulesAndRegulation();
            }else{
                errormsg.setText("Invalid user");
            }

        }catch(ClassNotFoundException ce)
        {
            System.out.println(ce);
        }catch(SQLException se)
        {
            System.out.println(se);
        }
    }
    
}