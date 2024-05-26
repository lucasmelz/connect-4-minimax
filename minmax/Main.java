import java.util.Scanner;
import java.awt.Point;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the depth:");
        int depth = scanner.nextInt();

        Minimax minimaxAgent = new Minimax(depth);
        State state = new State(6, 7);

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
        scanner.close();
    }
}
