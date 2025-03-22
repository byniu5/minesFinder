import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class GameWindow extends JFrame
{

    private JPanel panelGame;
    private MinesweeperButton[][] buttons;
    private MineField mineField;

    public GameWindow(MineField mineField)
    {
        this.mineField = mineField;
        var nrLines = mineField.getNrLines();
        var nrColumns = mineField.getNrColumns();

        this.buttons = new MinesweeperButton[nrLines][nrColumns];

        panelGame.setLayout(new GridLayout(nrLines, nrColumns));

        for (int i = 0; i < nrLines; ++i) {
            for (int j = 0; j < nrColumns; ++j) {
                buttons[i][j] = new MinesweeperButton();    // ??
                buttons[i][j].addActionListener(this::btnClick);
                //buttons[i][j].setState(mineField.getStateOfSquare(i, j)); // ??
                panelGame.add(buttons[i][j]);
            }
        }

            setContentPane(panelGame);
            pack();
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setVisible(true);

    }

    public void btnClick(ActionEvent e)
    {
        var button = (MinesweeperButton) e.getSource();

        //mineField.revealSquare(x, y);
    }

        public static void main(String[] args) {

            new GameWindow(new MineField(1, 3, 1));


        }
}
