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
        double utilityValue = utilityFunction(agent);
        return Math.abs(utilityValue) == 4.0;
    }

    public double utilityFunction(char agent) {

        double rowsEval = evaluateRows(agent);
        double colsEval = evaluateColumns(agent);
        double diagEval = evaluateDiagonals(agent);

        return agent == 'W' ? Math.max(Math.max(rowsEval, colsEval), diagEval)
                : Math.min(Math.min(rowsEval, colsEval), diagEval);

    }

    public double evaluateRows(char agent) {

        double maximumEvaluation = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= cols - 4; j++) {
                char[] sequence = Arrays.copyOfRange(board[i], j, j + 4);
                int positionsCurrentPlayer = countOccurrences(sequence, agent);
                int positionsNextPlayer = countOccurrences(sequence, agent == 'W' ? 'B' : 'W');

                if (positionsNextPlayer == 0) {
                    maximumEvaluation = Math.max(maximumEvaluation, positionsCurrentPlayer);
                }
            }
        }

        return agent == 'W' ? maximumEvaluation : -maximumEvaluation;
    }

    public double evaluateColumns(char agent) {
        double maximumEvaluation = 0;

        for (int j = 0; j < cols; j++) {
            for (int i = 0; i <= rows - 4; i++) {
                char[] sequence = new char[4];
                for (int k = 0; k < 4; k++) {
                    sequence[k] = board[i + k][j];
                }
                int positionsCurrentPlayer = countOccurrences(sequence, agent);
                int positionsNextPlayer = countOccurrences(sequence, agent == 'W' ? 'B' : 'W');

                if (positionsNextPlayer == 0) {
                    maximumEvaluation = Math.max(maximumEvaluation, positionsCurrentPlayer);
                }
            }
        }

        return agent == 'W' ? maximumEvaluation : -maximumEvaluation;
    }

    public double evaluateDiagonals(char agent) {
        double maximumEvaluation = 0;

        // Evaluate diagonals from top-left to bottom-right
        for (int i = 0; i <= rows - 4; i++) {
            for (int j = 0; j <= cols - 4; j++) {
                char[] sequence = new char[4];
                for (int k = 0; k < 4; k++) {
                    sequence[k] = board[i + k][j + k];
                }
                int positionsCurrentPlayer = countOccurrences(sequence, agent);
                int positionsNextPlayer = countOccurrences(sequence, agent == 'W' ? 'B' : 'W');

                if (positionsNextPlayer == 0) {
                    maximumEvaluation = Math.max(maximumEvaluation, positionsCurrentPlayer);
                }
            }
        }

        // Evaluate diagonals from top-right to bottom-left
        for (int i = 0; i <= rows - 4; i++) {
            for (int j = 3; j < cols; j++) {
                char[] sequence = new char[4];
                for (int k = 0; k < 4; k++) {
                    sequence[k] = board[i + k][j - k];
                }
                int positionsCurrentPlayer = countOccurrences(sequence, agent);
                int positionsNextPlayer = countOccurrences(sequence, agent == 'W' ? 'B' : 'W');

                if (positionsNextPlayer == 0) {
                    maximumEvaluation = Math.max(maximumEvaluation, positionsCurrentPlayer);
                }
            }
        }

        return agent == 'W' ? maximumEvaluation : -maximumEvaluation;
    }

    public int countOccurrences(char[] row, char target) {
        int count = 0;
        for (int i = 0; i < row.length; i++) {
            if (row[i] == target) {
                count++;
            }
        }
        return count;
    }
}