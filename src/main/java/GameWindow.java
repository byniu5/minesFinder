import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GameWindow extends JFrame
{

    private JPanel panelGame;
    private MinesweeperButton[][] buttons;
    private MineField mineField;

    /**
    * Get access to the size of board.
     * Set grid layout for this window.
     * Initialise every button on board.
    * */
    public GameWindow(MineField mineField)
    {
        this.mineField = mineField;
        var nrLines = mineField.getNrLines();
        var nrColumns = mineField.getNrColumns();

        this.buttons = new MinesweeperButton[nrLines][nrColumns];

        panelGame.setLayout(new GridLayout(nrLines, nrColumns));

        for (int row = 0; row < nrLines; ++row) {
            for (int column = 0; column < nrColumns; ++column) {
                buttons[row][column] = new MinesweeperButton(row, column);

                /**
                * Implementation of MouseListener interface and add it to the button.
                 * Interface defines mousePressed() and other method updateButtonsState().
                 * Add mouse listener for right-click functionality.
                * */
                MouseListener mouseListener = new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {}

                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() != MouseEvent.BUTTON3) {
                            return;
                        }

                        var button = (MinesweeperButton) e.getSource();

                        var rowClick = button.getRow();
                        var columnClick = button.getColumn();

                        markSquareAsMine(rowClick, columnClick);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {}

                    @Override
                    public void mouseEntered(MouseEvent e) {}

                    @Override
                    public void mouseExited(MouseEvent e) {}
                };
                /**
                * Implementation of KeyListener interface and add it to the buttons.
                 * There is possibility to navigate by arrows and marks squares by 'C' button.
                 * If player is on the edge of the board its start focusing on the other side of opposite side.
                 */
                KeyListener keyListener = new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            System.out.println("Escape pressed");
                            System.exit(0);
                        }

                        var button = (MinesweeperButton) e.getSource();
                        var rowKey = button.getRow();
                        var columnKey = button.getColumn();

                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_W -> buttons[--rowKey < 0 ? nrLines - 1 : rowKey][columnKey].requestFocus();
                            case KeyEvent.VK_S -> buttons[(rowKey + 1) % nrLines][columnKey].requestFocus();
                            case KeyEvent.VK_A -> buttons[rowKey][--columnKey < 0 ? nrColumns -1 : columnKey].requestFocus();
                            case KeyEvent.VK_D -> buttons[rowKey][(columnKey + 1) % nrColumns].requestFocus();
                            case KeyEvent.VK_C -> {markSquareAsMine(rowKey, columnKey); System.out.println("C");}
                        }
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {}
                };

                buttons[row][column].addMouseListener(mouseListener);
                buttons[row][column].addKeyListener(keyListener);

                buttons[row][column].addActionListener(this::btnMinesweeperButtonActionPerformed);

                buttons[row][column].setState(mineField.getStateOfSquare(row, column));
                panelGame.add(buttons[row][column]);
            }
        }

            setContentPane(panelGame);
            pack();
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setVisible(true);

    }

    /**
    * Helper function to mark the square.
     * Few options in one button.
     */
    private void markSquareAsMine(int x, int y) {
        switch (mineField.getStateOfSquare(x, y)) {
            case MineField.COVERED -> mineField.markAsHavingMine(x, y);
            case MineField.MARKED -> mineField.markAsSuspect(x, y);
            case MineField.QUESTION -> mineField.unmark(x, y);
        }
        updateButtonsState();
    }

    /**
    * When button clicked start those actions.
     * Get access to position of button.
     * Reveal square and check all rules.
     * After that get the final state of the square.
    * */
    public void btnMinesweeperButtonActionPerformed(ActionEvent e) {
        var button = (MinesweeperButton) e.getSource();
        var x = button.getRow();
        var y = button.getColumn();

        System.out.println("Clicked on (" + x + "," + y + ")");
        mineField.revealSquare(x, y);

        updateButtonsState();
    }

    /**
    * To see any changes on board it's needed to update all squares.
     * It takes the state of square that has been defined in revealSquare(x, y).
     */
    private void updateButtonsState() {
        for (int x = 0; x < mineField.getNrLines(); x++) {
            for (int y = 0; y < mineField.getNrColumns(); y++) {
                buttons[x][y].setState(mineField.getStateOfSquare(x, y));
            }
        }
    }

        public static void main(String[] args) {

            new GameWindow(new MineField(1, 3, 1));

        }
}
