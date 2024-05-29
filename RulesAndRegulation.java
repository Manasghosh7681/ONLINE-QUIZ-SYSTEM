
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class RulesAndRegulation implements ActionListener
{
    JFrame frame;
    JLabel rule,rule1,rule2,rule3,rule4,rule5,rule6,rule7;
    Font f1,f2,f3;
    JButton exit,start;
    RulesAndRegulation()
    {
        frame=new JFrame();
        frame.setLocation(250,200);
        frame.setSize(900,500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.BLACK);

        ImageIcon icon=new ImageIcon("Rules.jpeg");
        Image logo=icon.getImage();
        frame.setIconImage(logo);

        
        f1=new Font("Arial",Font.BOLD,40);
        rule=new JLabel("Rules and Regulation");
        rule.setBounds(250,10,600,50);
        rule.setFont(f1);
        rule.setForeground(Color.BLUE);
        frame.add(rule);

        f2=new Font("Arial",Font.PLAIN,20);
        rule1=new JLabel("1. Each candidate get 15 questions .");
        rule1.setBounds(40,90,400,30);
        rule1.setForeground(Color.PINK);
        rule1.setFont(f2);
        frame.add(rule1);

        rule2=new JLabel("2.You have 15 seconds for one question.");
        rule2.setBounds(40,130,400,30);
        rule2.setForeground(Color.PINK);
        rule2.setFont(f2);
        frame.add(rule2);

        rule3=new JLabel("3. No electronic device are allowed .");
        rule3.setBounds(40,170,400,30);
        rule3.setForeground(Color.PINK);
        rule3.setFont(f2);
        frame.add(rule3);

        rule4=new JLabel("4. Each question carried 4 possible answer .");
        rule4.setBounds(40,210,400,30);
        rule4.setForeground(Color.PINK);
        rule4.setFont(f2);
        frame.add(rule4);

        rule5=new JLabel("5. Each correct answer awards 1 mark .");
        rule5.setBounds(40,250,400,30);
        rule5.setForeground(Color.PINK);
        rule5.setFont(f2);
        frame.add(rule5);

        rule6=new JLabel("6. No negative marking for wrong answer.");
        rule6.setBounds(40,290,400,30);
        rule6.setForeground(Color.PINK);
        rule6.setFont(f2);
        frame.add(rule6);

        rule7=new JLabel("7. Candidate submit the answer within the time.");
        rule7.setBounds(40,330,450,30);
        rule7.setForeground(Color.PINK);
        rule7.setFont(f2);
        frame.add(rule7);

        f3=new Font("Arial",Font.PLAIN,15);
        exit=new JButton("Exit");
        exit.setFont(f3);
        exit.setBounds(60,400,70,30);
        exit.setForeground(Color.WHITE);
        exit.setBackground(Color.MAGENTA);
        exit.addActionListener(this);
        frame.add(exit);
        
        start=new JButton("Start");
        start.setFont(f3);
        start.setBounds(650,400,70,30);
        start.setForeground(Color.WHITE);
        start.setBackground(Color.MAGENTA);
        start.addActionListener(this);
        frame.add(start);
       
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource().equals(exit))
        {
            System.exit(0);
        }else if(ae.getSource().equals(start))
        {
            frame.dispose();
            new ExamFrame();
        }
    }

    

   
}

