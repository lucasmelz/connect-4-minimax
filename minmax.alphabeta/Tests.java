import java.awt.Point;

public class Tests {

        public static void main(String[] args) throws CloneNotSupportedException {

                // Array of 10 different board configurations
                char[][][] boardConfigurations = {
                                { // Configuration 1
                                                { 'W', 'B', 'W', 'W', 'B', 'B', 'W' },
                                                { 'B', 'W', 'B', 'B', '.', 'W', 'B' },
                                                { 'W', '.', '.', 'W', 'B', 'W', 'W' },
                                                { '.', 'B', 'W', '.', 'B', 'W', '.' },
                                                { '.', 'B', '.', 'W', '.', 'B', '.' },
                                                { 'W', 'W', '.', 'B', '.', '.', '.' }
                                }, // Configuration 2
                                {
                                                { '.', '.', 'W', 'B', '.', '.', '.' },
                                                { '.', 'B', 'W', 'W', '.', '.', '.' },
                                                { 'B', 'W', 'W', '.', 'B', '.', '.' },
                                                { 'W', 'W', 'B', '.', '.', '.', '.' },
                                                { 'B', '.', '.', '.', '.', '.', '.' },
                                                { '.', '.', '.', '.', '.', '.', '.' }
                                },
                                // Configuration 3
                                {
                                                { 'W', '.', 'B', 'W', 'B', '.', 'W' },
                                                { 'B', 'W', '.', '.', 'B', 'W', 'B' },
                                                { 'W', 'B', '.', 'W', '.', 'W', '.' },
                                                { 'B', '.', 'W', '.', 'B', '.', 'W' },
                                                { '.', 'W', 'B', 'W', '.', 'B', '.' },
                                                { 'W', '.', 'W', 'B', 'W', '.', 'B' }
                                },
                                // Configuration 4
                                {
                                                { '.', 'B', 'W', '.', 'B', 'W', '.' },
                                                { 'W', '.', 'B', 'W', '.', 'B', 'W' },
                                                { '.', 'W', '.', 'B', 'W', '.', 'B' },
                                                { 'B', '.', 'W', '.', 'B', 'W', '.' },
                                                { '.', 'B', '.', 'W', '.', 'B', 'W' },
                                                { 'W', '.', 'B', 'W', '.', 'B', '.' }
                                },
                                // Configuration 5
                                {
                                                { 'B', 'W', 'B', 'W', 'B', 'W', 'B' },
                                                { 'W', 'B', 'W', 'B', 'W', 'B', 'W' },
                                                { '.', '.', '.', '.', '.', '.', '.' },
                                                { 'W', 'B', 'W', 'B', 'W', 'B', 'W' },
                                                { '.', '.', '.', '.', '.', '.', '.' },
                                                { 'W', 'B', 'W', 'B', 'W', 'B', 'W' }
                                },
                                // Configuration 6
                                {
                                                { 'W', 'W', 'W', 'W', '.', '.', '.' },
                                                { 'B', 'B', 'B', 'B', '.', '.', '.' },
                                                { 'W', 'W', 'W', 'W', '.', '.', '.' },
                                                { 'B', 'B', 'B', 'B', '.', '.', '.' },
                                                { 'W', 'W', 'W', 'W', '.', '.', '.' },
                                                { 'B', 'B', 'B', 'B', '.', '.', '.' }
                                },
                                // Configuration 7
                                {
                                                { '.', '.', '.', 'W', 'W', 'W', 'W' },
                                                { '.', '.', '.', 'B', 'B', 'B', 'B' },
                                                { '.', '.', '.', 'W', 'W', 'W', 'W' },
                                                { '.', '.', '.', 'B', 'B', 'B', 'B' },
                                                { '.', '.', '.', 'W', 'W', 'W', 'W' },
                                                { '.', '.', '.', 'B', 'B', 'B', 'B' }
                                },
                                // Configuration 8
                                {
                                                { 'B', 'W', '.', '.', '.', '.', '.' },
                                                { '.', 'B', 'W', '.', '.', '.', '.' },
                                                { '.', '.', 'B', 'W', '.', '.', '.' },
                                                { '.', '.', '.', 'B', 'W', '.', '.' },
                                                { '.', '.', '.', '.', 'B', 'W', '.' },
                                                { '.', '.', '.', '.', '.', 'B', 'W' }
                                },
                                // Configuration 9
                                {
                                                { '.', '.', '.', '.', '.', '.', '.' },
                                                { 'B', 'W', 'B', 'W', 'B', 'W', 'B' },
                                                { 'W', '.', '.', 'B', 'W', 'B', 'W' },
                                                { 'B', 'W', 'B', 'W', 'B', 'W', 'B' },
                                                { '.', '.', '.', '.', '.', '.', '.' },
                                                { 'B', 'W', 'B', 'W', 'B', 'W', 'B' }
                                },
                                // Configuration 10
                                {
                                                { '.', '.', '.', '.', '.', '.', '.' },
                                                { '.', '.', '.', '.', '.', '.', '.' },
                                                { '.', '.', 'W', 'W', '.', '.', '.' },
                                                { '.', '.', '.', 'W', 'B', '.', '.' },
                                                { '.', '.', '.', '.', 'B', '.', '.' },
                                                { '.', 'B', '.', '.', '.', '.', '.' }
                                }
                };

                for (int i = 0; i < boardConfigurations.length; i++) {
                        char[][] preFilledBoard = boardConfigurations[i];
                        State state = new State(6, 7);
                        state.board = preFilledBoard;

                        Minimax minimaxAgent = new Minimax(5);
                        long startTime = System.currentTimeMillis(); // Start time measurement

                        while (true) {
                                // White player's move
                                Point whiteMove = minimaxAgent.getMove(state, true);
                                state = state.generateSuccessor('W', whiteMove);

                                // Uncomment to see the game playing out
                                // System.out.printf("White inserts piece at coordinates (%.0f, %.0f)%n%n",
                                // whiteMove.getX(),
                                // whiteMove.getY());
                                // state.printBoard();

                                if (state.isGoal('W')) {
                                        break;
                                }

                                // Black player's move
                                Point blackMove = minimaxAgent.getMove(state, false);
                                state = state.generateSuccessor('B', blackMove);

                                // Uncomment to see the game playing out
                                // System.out.printf("Black inserts piece at coordinates (%.0f, %.0f)%n%n",
                                // blackMove.getX(),
                                // blackMove.getY());
                                // state.printBoard();

                                if (state.isGoal('B')) {
                                        break;
                                }

                                // Check for draw
                                if (state.getPossibleMoves().isEmpty()) {
                                        break;
                                }
                        }
                        long endTime = System.currentTimeMillis(); // End time measurement

                        System.out.println("Board Configuration " + (i + 1) + ":");
                        System.out.println("Time taken: " + (endTime - startTime) + " ms");
                        System.out.println("Expanded nodes: " + minimaxAgent.expandedNodes);
                        System.out.println();
                }
        }
}