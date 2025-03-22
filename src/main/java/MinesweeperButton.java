import javax.swing.*;
import java.awt.*;

public class MinesweeperButton extends JButton {

    private int state;

    private int row;
    private int column;

    public MinesweeperButton(int row, int column) {

        this.state = MineField.EMPTY;
        this.row = row;
        this.column = column;

    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    /**
    * Setting state for one button.
     * Depend on the number that is given to parameter 'state'.
     *
    * */
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
