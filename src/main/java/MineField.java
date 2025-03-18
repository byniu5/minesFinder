public class MineField {

    private boolean[][] mines;
    public static final int EMPTY = 0;
    public static final int MINE = 9;
    public static final int MARKED = 10;
    public static final int QUESTION = 11;
    public static final int SAFE = 12;

    private int[][] state;

    private int nrLines;
    private int nrColumns;
    private int nrMines;

    public MineField(int nrLines, int nrColumns, int nrMines) {

        this.nrLines = nrLines;
        this.nrColumns = nrColumns;
        this.nrMines = nrMines;

        this.mines = new boolean[nrLines][nrColumns]; //If there is mine or not
        this.state = new int[nrLines][nrColumns];

    }

    public int getStateOfSquare(int x, int y)
    {
        return state[x][y];
    }

    public boolean hasMine(int x, int y)
    {
        return mines[x][y];
    }

    public int getNrLines() {
        return nrLines;
    }

    public int getNrColumns() {
        return nrColumns;
    }

    public int getNrMines(int nrMines) {
        return this.nrMines = nrMines;
    }

}
