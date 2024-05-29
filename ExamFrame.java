
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ExamFrame implements ActionListener {
    JFrame frame;
    JLabel qnoLabel, questionLabel, timerLabel, timeChangeLabel, secondLabel;
    JRadioButton op1, op2, op3, op4;
    JButton submit, next;
    ButtonGroup bg;
    Timer timer;
    int timeLeft = 15; // Time in seconds
    int questionNumber = 1;
    int correctAnswersCount = 0;
    String answer = "";
    Connection con;
    Statement smt;
    ResultSet rs;

    ExamFrame() {
        frame = new JFrame("Exam Question Page");
        frame.setLocation(150, 80);
        frame.setSize(900, 700);
        frame.setLayout(null);

        ImageIcon logoIcon = new ImageIcon("Logo.jpg");
        Image logo = logoIcon.getImage();
        frame.setIconImage(logo);

        ImageIcon imgIcon = new ImageIcon("Quiz.jpg");
        imgIcon = new ImageIcon(imgIcon.getImage().getScaledInstance(900, 300, Image.SCALE_SMOOTH));
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(imgIcon);
        imageLabel.setBounds(0, 0, 900, 300);
        frame.add(imageLabel);

        timerLabel = new JLabel("Timer :");
        timerLabel.setBounds(600, 310, 70, 30);
        frame.add(timerLabel);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        timerLabel.setForeground(Color.red);

        timeChangeLabel = new JLabel("15");
        timeChangeLabel.setBounds(675, 310, 30, 30);
        frame.add(timeChangeLabel);
        timeChangeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        timeChangeLabel.setForeground(Color.red);

        secondLabel = new JLabel("second left");
        secondLabel.setBounds(705, 310, 150, 30);
        frame.add(secondLabel);
        secondLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        secondLabel.setForeground(Color.red);

        qnoLabel = new JLabel();
        qnoLabel.setBounds(20, 340, 80, 30);
        frame.add(qnoLabel);
        qnoLabel.setFont(new Font("Arial", Font.PLAIN, 25));

        questionLabel = new JLabel();
        questionLabel.setBounds(70, 340, 900, 30);
        frame.add(questionLabel);
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 25));

        op1 = new JRadioButton();
        op1.setBounds(50, 400, 500, 20);
        op1.addActionListener(this);
        frame.add(op1);
        op1.setFont(new Font("Arial", Font.PLAIN, 16));

        op2 = new JRadioButton();
        op2.setBounds(50, 430, 500, 20);
        op2.addActionListener(this);
        frame.add(op2);
        op2.setFont(new Font("Arial", Font.PLAIN, 16));

        op3 = new JRadioButton();
        op3.setBounds(50, 460, 500, 20);
        op3.addActionListener(this);
        frame.add(op3);
        op3.setFont(new Font("Arial", Font.PLAIN, 16));

        op4 = new JRadioButton();
        op4.setBounds(50, 490, 500, 20);
        op4.addActionListener(this);
        frame.add(op4);
        op4.setFont(new Font("Arial", Font.PLAIN, 16));

        bg = new ButtonGroup();
        bg.add(op1);
        bg.add(op2);
        bg.add(op3);
        bg.add(op4);

        next = new JButton("Next");
        next.setBounds(750, 550, 100, 30);
        frame.add(next);
        next.setBackground(Color.green);
        next.addActionListener(this);

        submit = new JButton("Submit");
        submit.setBounds(750, 550, 100, 30);
        submit.setBackground(Color.blue);
        submit.addActionListener(this);
        submit.setVisible(false); // Initially hidden
        frame.add(submit);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        timer = new Timer(1000, this);
        timer.start();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "Manas", "12345");
            System.out.println("Connected to database");

            smt = con.createStatement();
            fetchQuestion(questionNumber);
        } catch (ClassNotFoundException ce) {
            System.out.println(ce);
        }catch(SQLException e)
        {
            System.out.println(e);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            timeLeft--;
            if (timeLeft >= 0) {
                timeChangeLabel.setText(Integer.toString(timeLeft));
            } else {
                timer.stop();
                hideQuestionAndOptions();
                next.setEnabled(true);
                if (questionNumber == 15) {
                    submit.setEnabled(true);
                    next.setEnabled(false);
                } else {
                    next.setEnabled(true);
                }
            }
        } else if (e.getSource() == next) {
            if (questionNumber < 15) {
                checkAnswer();
                questionNumber++;
                timeLeft = 15;
                fetchQuestion(questionNumber);
                showQuestionAndOptions();
                timer.restart();
            }
            if (questionNumber == 15) {
                next.setVisible(false); // Hide the next button after the last question
                submit.setVisible(true); // Show the submit button
            }
        } else if (e.getSource() == submit) {
            checkAnswer();
            JOptionPane.showMessageDialog(frame, "Answers submitted successfully!");
            frame.dispose();
            new LastFrame(correctAnswersCount);
           
        }
    }

    public void fetchQuestion(int qNumber) {
        try {
            String query = "SELECT * FROM QUESTIONSET WHERE QNO = " + qNumber;
            rs = smt.executeQuery(query);
            if (rs.next()) {
                setQuestion(rs.getString("QNO"), rs.getString("QUESTION"), rs.getString("OPTION1"),
                        rs.getString("OPTION2"), rs.getString("OPTION3"), rs.getString("OPTION4"));
                answer = rs.getString("ANSWER");
                next.setEnabled(false);
                submit.setEnabled(false);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e);
        }
    }

    public void setQuestion(String qno, String question, String opt1, String opt2, String opt3, String opt4) {
        qnoLabel.setText(qno + " .");
        questionLabel.setText(question);
        op1.setText(opt1);
        op2.setText(opt2);
        op3.setText(opt3);
        op4.setText(opt4);
    }

    public void hideQuestionAndOptions() {
        qnoLabel.setText("");
        questionLabel.setText("");
        op1.setVisible(false);
        op2.setVisible(false);
        op3.setVisible(false);
        op4.setVisible(false);
    }

    public void showQuestionAndOptions() {
        qnoLabel.setText("Q" + questionNumber + " .");
        questionLabel.setText(questionLabel.getText());
        op1.setVisible(true);
        op2.setVisible(true);
        op3.setVisible(true);
        op4.setVisible(true);
    }

    public void checkAnswer() {
        String selectedAnswer = null;
        if (op1.isSelected()) {
            selectedAnswer = op1.getText();
        } else if (op2.isSelected()) {
            selectedAnswer = op2.getText();
        } else if (op3.isSelected()) {
            selectedAnswer = op3.getText();
        } else if (op4.isSelected()) {
            selectedAnswer = op4.getText();
        }

        if (selectedAnswer != null && selectedAnswer.equals(answer)) {
            correctAnswersCount++;
        }
        bg.clearSelection(); // Clear the selection for the next question
    }
    

}