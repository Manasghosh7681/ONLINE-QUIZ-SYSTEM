
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class LastFrame implements ActionListener {
    JFrame flast;
    JLabel lcongo, lcomplete, lscore;
    JButton btnclose;

    LastFrame(int correctAnswersCount) {
        flast = new JFrame();
        flast.setLocation(450, 200);
        flast.setSize(620, 350);
        flast.setLayout(null);

        lcongo = new JLabel();
        lcongo.setText("CONGRATULATIONS");
        lcongo.setBounds(150, 50, 320, 40);
        flast.add(lcongo);
        lcongo.setFont(new Font("Ariel Black", Font.BOLD, 26));
        lcongo.setForeground(Color.BLUE);

        lcomplete = new JLabel("You have Successfully Completed Quiz test");
        lcomplete.setBounds(175, 100, 400, 40);
        flast.add(lcomplete);

        lscore = new JLabel("Your score: " + correctAnswersCount);
        lscore.setBounds(230, 150, 150, 40);
        flast.add(lscore);

        btnclose = new JButton();
        btnclose.setText("Close");
        btnclose.setBounds(470, 220, 70, 25);
        btnclose.setBackground(Color.GRAY);
        flast.add(btnclose);
        btnclose.addActionListener(this);
        btnclose.setForeground(Color.GREEN);

        flast.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        flast.setVisible(true);

        
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource().equals(btnclose))
        {
            flast.dispose();
        }
    }
    
}
