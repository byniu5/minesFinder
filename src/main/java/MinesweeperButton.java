import javax.swing.*;
import java.awt.*;

public class MinesweeperButton extends JButton {

    private int state;

    public MinesweeperButton() {
        this.state = MineField.EMPTY;

    }

    public void setState(int state) {
        this.state = state;
        switch (state) {
            case MineField.EMPTY:
                setText("");
                setBackground(Color.LIGHT_GRAY);
                break;
            case MineField.MINE:
                setText("*");
                setBackground(Color.RED);
                break;
            case MineField.MARKED:
                setText("!");
                setBackground(Color.ORANGE);
                break;
            case MineField.QUESTION:
                setText("?");
                setBackground(Color.YELLOW);
                break;
            case MineField.COVERED:
                setText("x");
                setBackground(Color.GREEN);
                break;

            default:
                setText(String.valueOf(state));
                setBackground(Color.BLACK);
        }
    }



}
