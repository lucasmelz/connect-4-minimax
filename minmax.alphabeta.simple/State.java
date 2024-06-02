import java.util.*;
import java.awt.Point;

class State implements Cloneable {
    int rows, cols;
    char[][] board;

    public State(int n_rows, int n_cols) {
        this.rows = n_rows;
        this.cols = n_cols;
        this.board = new char[n_rows][n_cols];

        // Fill the board with blanks
        for (int i = 0; i < n_rows; i++) {
            Arrays.fill(this.board[i], '.');
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        State state = (State) obj;
        return Arrays.deepEquals(board, state.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        State newState = new State(this.rows, this.cols);
        for (int i = 0; i < this.rows; i++) {
            newState.board[i] = this.board[i].clone();
        }
        return newState;
    }

    public List<Point> getPossibleMoves() {
        List<Point> actions = new ArrayList<>();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (this.board[i][j] == '.') {
                    actions.add(new Point(i, j));
                }
            }
        }
        return actions;
    }

    public State generateSuccessor(char agent, Point action) throws CloneNotSupportedException {
        State newState = (State) this.clone();
        newState.board[action.x][action.y] = agent;
        return newState;
    }

    public void printBoard() {
        // Print column indices
        System.out.print("   ");
        for (int j = 0; j < this.cols; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        // Print top border
        System.out.println("  ---------------");

        // Print each row with row indices
        for (int i = 0; i < this.rows; i++) {
            System.out.print(i + "| "); // Print row index
            for (int j = 0; j < this.cols; j++) {
                System.out.print(this.board[i][j] + " ");
            }
            System.out.println("|");
        }

        // Print bottom border
        System.out.println("  ---------------\n");
    }

    public boolean isGoal(char agent) {
        String find = String.valueOf(agent).repeat(4);

        // Check rows
        for (char[] row : this.board) {
            if (new String(row).contains(find)) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < this.cols; j++) {
            StringBuilder col = new StringBuilder();
            for (int i = 0; i < this.rows; i++) {
                col.append(this.board[i][j]);
            }
            if (col.toString().contains(find)) {
                return true;
            }
        }

        // Check diagonals
        return checkDiagonals(find);
    }

    private boolean checkDiagonals(String find) {
        for (int i = 0; i <= this.rows - 4; i++) {
            for (int j = 0; j <= this.cols - 4; j++) {
                if (checkDiagonal(find, i, j, 1, 1) || checkDiagonal(find, i, j + 3, 1, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonal(String find, int startX, int startY, int dx, int dy) {
        StringBuilder diag = new StringBuilder();
        for (int x = startX, y = startY; x < this.rows && y < this.cols && y >= 0; x += dx, y += dy) {
            diag.append(this.board[x][y]);
        }
        return diag.toString().contains(find);
    }

    public double utilityFunction() {
        if (this.isGoal('W')) {
            return 1000.0;
        }
        if (this.isGoal('B')) {
            return -1000.0;
        }
        return 0.0;
    }
}