import java.awt.Point;

public class Teste {

    public static void main(String[] args) throws CloneNotSupportedException {
        Minimax minimaxAgent = new Minimax(3);
        State state = new State(6, 7);
        char[][] preFilledBoard = {
                { 'W', 'B', 'W', 'W', 'B', 'B', 'W' },
                { 'B', 'W', 'B', 'B', '.', 'W', 'B' },
                { 'W', '.', '.', 'W', 'B', 'W', 'W' },
                { '.', 'B', 'W', '.', 'B', 'W', '.' },
                { '.', 'B', '.', 'W', '.', 'B', '.' },
                { 'W', 'W', '.', 'B', '.', '.', '.' }
        };

        state.board = preFilledBoard;

        while (true) {
            // White player's move
            Point whiteMove = minimaxAgent.getMove(state, true);
            state = state.generateSuccessor('W', whiteMove);
            System.out.printf("White inserts piece at coordinates (%.0f, %.0f)%n%n", whiteMove.getX(),
                    whiteMove.getY());
            state.printBoard();

            if (state.isGoal('W')) {
                System.out.println("White wins!");
                break;
            }

            // Black player's move
            Point blackMove = minimaxAgent.getMove(state, false);
            state = state.generateSuccessor('B', blackMove);
            System.out.printf("Black inserts piece at coordinates (%.0f, %.0f)%n%n", blackMove.getX(),
                    blackMove.getY());
            state.printBoard();

            if (state.isGoal('B')) {
                System.out.println("Black wins!");
                break;
            }

            // Check for draw
            if (state.getPossibleMoves().isEmpty()) {
                System.out.println("It's a draw!");
                break;
            }
        }
    }

}
