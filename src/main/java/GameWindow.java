import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class GameWindow extends JFrame
{

    private JPanel panelGame;
    private MinesweeperButton[][] buttons;
    private MineField mineField;

    /**
    * Get access to the size of board
     * Set grid layout for this window
     * Initialise every button on board
    * */
    public GameWindow(MineField mineField)
    {
        this.mineField = mineField;
        var nrLines = mineField.getNrLines();
        var nrColumns = mineField.getNrColumns();

        this.buttons = new MinesweeperButton[nrLines][nrColumns];

        panelGame.setLayout(new GridLayout(nrLines, nrColumns));

        for (int i = 0; i < nrLines; ++i) {
            for (int j = 0; j < nrColumns; ++j) {
                buttons[i][j] = new MinesweeperButton(i, j);    // ??
                buttons[i][j].addActionListener(this::btnMinesweeperButtonActionPerformed); // ??
                //buttons[i][j].setState(mineField.getStateOfSquare(i, j));

                panelGame.add(buttons[i][j]);
            }
        }

            setContentPane(panelGame);
            pack();
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setVisible(true);

    }

    /**
    * When button clicked start those actions
     * Get access to position of button
     * Reveal square and check all rules
     * After that get the final state of the square
    * */
    public void btnMinesweeperButtonActionPerformed(ActionEvent e) {
        var button = (MinesweeperButton) e.getSource();
        var x = button.getRow();
        var y = button.getColumn();

        System.out.println("Clicked on (" + x + "," + y + ")");

        button.setState(mineField.getStateOfSquare(x, y));
        mineField.revealSquare(x, y);
    }

        public static void main(String[] args) {

            new GameWindow(new MineField(1, 3, 1));


        }
}
