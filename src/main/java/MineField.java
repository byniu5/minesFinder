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
    private boolean gameFinished;
    private boolean playerDefeated;



    public MineField(int nrLines, int nrColumns, int nrMines) {

        this.nrLines = nrLines;
        this.nrColumns = nrColumns;
        this.nrMines = nrMines;

        this.mines = new boolean[nrLines][nrColumns]; //If there is mine or not
        this.state = new int[nrLines][nrColumns];   // State marked by player

        firstMove = true;
        gameFinished = false;
        playerDefeated = false;

        for (var x = 0; x < nrLines; ++x) {
            for (var y = 0; y < nrColumns; ++y) {
                state[x][y] = COVERED;
            }
        }

    }

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

    public boolean isPlayerDefeated() {
        return playerDefeated;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    private void setMine(int exceptX, int exceptY) {
        var random = new Random();
        var x = 0;
        var y = 0;

        for (int i = 0; i < nrMines; ++i) {
            do {
                x = random.nextInt(nrLines);
                y = random.nextInt(nrColumns);
            } while (x == exceptX && y == exceptY || mines[x][y]);

        mines[x][y] = true;
        }
    }

    public void revealSquare(int x, int y) {
        if (gameFinished || state[x][y] < COVERED)
            return;
        if (firstMove) {
            firstMove = false;
            setMine(x, y);
        }
        if (mines[x][y]) {
            playerDefeated = true;
            gameFinished = true;
            state[x][y] = MINE;
            return;
        }
        int numNeighborMines = countNeighborMines(x, y);
        if (numNeighborMines == 0) {
            state[x][y] = EMPTY;
            // Reveal other squares that no have mines
            revealNeighborGrids(x, y);
        } else {
            state[x][y] = numNeighborMines;
        }
    }

    private int countNeighborMines(int x, int y) {
        var numNeighborMines = 0;

        for (var i = Math.max(0, x-1); i < Math.min(nrLines, x+2); ++i) {
            for (var j = Math.max(0, y-1); j < Math.min(nrColumns, y+2); ++j) {
                if (mines[i][j]) {
                    ++numNeighborMines;
                }
            }
        }
        return numNeighborMines;
    }

    private void revealNeighborGrids(int x, int y) {
        for (var i = Math.max(0, x-1); i < Math.min(nrLines, x+2); ++i) {
            for (var j = Math.max(0, y-1); j < Math.min(nrColumns, y+2); ++j) {
                revealSquare(i, j);
            }
        }
    }
}
