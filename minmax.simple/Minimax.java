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
            maxValue(state, depth);
        } else {
            minValue(state, depth);
        }
        return bestMove;
    }

    private double maxValue(State state, int depth) throws CloneNotSupportedException {
        expandedNodes++;
        if (depth == 0) {
            return state.utilityFunction();
        }

        double value = Double.NEGATIVE_INFINITY;
        for (Point action : state.getPossibleMoves()) {
            double newValue = minValue(state.generateSuccessor('W', action), depth - 1);
            if (newValue > value) {
                value = newValue;
                bestMove = action;
            }
        }
        return value;
    }

    private double minValue(State state, int depth) throws CloneNotSupportedException {
        expandedNodes++;
        if (depth == 0) {
            return state.utilityFunction();
        }

        double value = Double.POSITIVE_INFINITY;
        for (Point action : state.getPossibleMoves()) {
            double newValue = maxValue(state.generateSuccessor('B', action), depth - 1);
            if (newValue < value) {
                value = newValue;
            }
        }
        return value;
    }
}