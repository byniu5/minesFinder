import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class MinesFinder extends JFrame {

    private JPanel panel1;
    private JButton btnEasy;
    private JButton btnMedium;
    private JButton btnHard;
    private JButton btnExit;
    private JLabel lblScoreEasy;
    private JLabel lblScoreMedium;
    private JLabel lblScoreHard;
    private JLabel lblPlayerEasy;
    private JLabel lblPlayerMedium;
    private JLabel lblPlayerHard;


    public MinesFinder(String title) {

        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        pack();
        setVisible(true);

        lblPlayerEasy.setText("Carlos");
        lblScoreEasy.setText("100");

        btnExit.addActionListener(this::btnExitActionPerformed);
        btnEasy.addActionListener(this::btnEasyActionPerformed);
        btnMedium.addActionListener(this::btnMediumActionPerformed);
        btnHard.addActionListener(this::btnHardActionPerformed);
    }

    public static void main(String[] args) {

        new MinesFinder("MinesFinder");

    }

    private void btnExitActionPerformed(ActionEvent e) {
        System.out.println("Exit Clicked");
        System.exit(0);
    }

    private void btnEasyActionPerformed(ActionEvent e) {
        System.out.println("Easy Clicked");
        var window = new GameWindow(new MineField(9, 9, 10));
        window.setVisible(true);
    }

    private void btnMediumActionPerformed(ActionEvent e) {
        System.out.println("Medium Clicked");
        var window = new GameWindow(new MineField(16, 16, 20));
        window.setVisible(true);
    }

    private void btnHardActionPerformed(ActionEvent e) {
        System.out.println("Hard Clicked");
        var window = new GameWindow(new MineField(30, 16, 30));
        window.setVisible(true);
    }

}


