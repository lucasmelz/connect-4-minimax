import java.awt.Point;

class Minimax {
    int depth;
    Point bestMove = new Point();
    int expandedNodes = 0;

    public Minimax(int depth) {
        this.depth = depth;
    }

    public Point getMove(State state, boolean isMaxPlayer) throws CloneNotSupportedException {
        if (isMaxPlayer) {
            maxValue(state, depth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        } else {
            minValue(state, depth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        }
        return bestMove;
    }

    private double maxValue(State state, int depth, double alpha, double beta) throws CloneNotSupportedException {
        expandedNodes++;
        if (depth == 0) {
            return state.utilityFunction('W');
        }

        double value = Double.NEGATIVE_INFINITY;
        for (Point action : state.getPossibleMoves()) {
            double newValue = minValue(state.generateSuccessor('W', action), depth - 1, alpha, beta);
            if (newValue > value) {
                value = newValue;
                bestMove = action;
            }
            if (value >= beta) {
                return value;
            }
            alpha = Math.max(alpha, value);
        }
        return value;
    }

    private double minValue(State state, int depth, double alpha, double beta) throws CloneNotSupportedException {
        expandedNodes++;
        if (depth == 0) {
            return state.utilityFunction('B');
        }

        double value = Double.POSITIVE_INFINITY;
        for (Point action : state.getPossibleMoves()) {
            double newValue = maxValue(state.generateSuccessor('B', action), depth - 1, alpha, beta);
            if (newValue < value) {
                value = newValue;
                bestMove = action;
            }
            if (value <= alpha) {
                return value;
            }
            beta = Math.min(beta, value);
        }
        return value;
    }
}