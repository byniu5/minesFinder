import java.util.Random;

public class MineField {

    private boolean[][] mines;
    public static final int EMPTY = 0;
    public static final int MINE = 9;
    public static final int MARKED = 10;
    public static final int QUESTION = 11;
    public static final int COVERED = 12;

    private int[][] state;

    private int nrLines;
    private int nrColumns;
    private int nrMines;

    private boolean firstMove;

    /**
    * Initialization of board based on chosen difficulty
     * Assign 'covered' state to all squares
     */
    public MineField(int nrLines, int nrColumns, int nrMines) {

        this.nrLines = nrLines;
        this.nrColumns = nrColumns;
        this.nrMines = nrMines;

        this.mines = new boolean[nrLines][nrColumns]; //If there is mine true; otherwise false
        this.state = new int[nrLines][nrColumns];   // State marked by player
        firstMove = true;

        for (var x = 0; x < nrLines; ++x) {
            for (var y = 0; y < nrColumns; ++y) {
                state[x][y] = COVERED;
            }
        }

    }

    /**
    * Getters
     *
     */
    public int getStateOfSquare(int x, int y)
    {
        return state[x][y];
    }

    public boolean hasMine(int x, int y)
    {
        return mines[x][y];     // Here will be generator of mines
    }

    public int getNrLines() {
        return nrLines;
    }

    public int getNrColumns() {
        return nrColumns;
    }

    public int getNrMines() {
        return nrMines;     // Enough
    }

    /**
    * Generate mines on all randomly on all board
     * Except the square that was clicked
     */
    private void setMines(int exceptX, int exceptY) {
        var random = new Random();
        var x = 0;
        var y = 0;

        for (int i = 0; i < nrMines; ++i) {
            do {
                x = random.nextInt(nrLines);
                y = random.nextInt(nrColumns);
            } while (x == exceptX && y == exceptY || mines[x][y]);

        mines[x][y] = true;
        state[x][y] = MINE;
        }
    }

    /**
    * Directly after clicking button
     * Only covered buttons can make any action
     * Generate mines after first move
     *
     */
    public void revealSquare(int x, int y) {
        if (state[x][y] < COVERED)
            return;
        if (firstMove) {
            firstMove = false;
            setMines(x, y);
        }
        state[x][y] = EMPTY;

    }
}
