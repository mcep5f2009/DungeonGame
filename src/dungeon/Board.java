package dungeon;

public class Board {

    private Position[][] positions;

    public Board(int length, int height) {
        positions = new Position[length][height];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                positions[i][j] = new Position(i, j);
            }
        }
    }

    public Position getPosition(int x, int y) {
        return positions[x][y];
    }

    public Position getAdjacentPosition(Position position, int xDiff, int yDiff) {
        int x = position.getX() + xDiff;
        int y = position.getY() + yDiff;
        if (positionExists(x, y)) {
            return positions[x][y];
        }
        return null;
    }

    public int getLength() {
        return positions.length;
    }

    public int getHeight() {
        return positions[0].length;
    }

    public boolean positionExists(int x, int y) {
        try {
            getPosition(x, y);
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

}
