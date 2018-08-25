package dungeon;

import java.util.Random;
import java.util.List;

public class Vampire extends Piece {

    private boolean canMove;
    private Random random;

    public Vampire(boolean canMove) {
        this.canMove = canMove;
        random = new Random();
    }

    public void move(Board board, List<Vampire> vampiresDestroyed) {
        if (!canMove) {
            return;
        }

        int[] change = movement();
        int xChange = change[0];
        int yChange = change[1];

        Position currentPosition = getPosition();
        Position newPosition = board.getAdjacentPosition(currentPosition, xChange, yChange);

        if (newPosition == null || newPosition.containsVampire()) {
            return;
        }

        if (newPosition.containsSlayer()) {
            vampiresDestroyed.add(this);
            currentPosition.setPiece(null);
            return;
        }

        setPosition(newPosition);
        currentPosition.setPiece(null);
        newPosition.setPiece(this);
    }

    @Override
    public void addToBoard(Board board) {

        while (true) {
            int x = random.nextInt(board.getLength());
            int y = random.nextInt(board.getHeight());

            Position position = board.getPosition(x, y);

            if (!position.containsPiece()) {
                setPosition(position);
                position.setPiece(this);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "v";
    }

    private int[] movement() {
        int[] movement = new int[2];
        movement[0] = 0;
        movement[1] = 0;

        int randomNumber = random.nextInt(4);
        switch (randomNumber) {
            case 0:
                movement[0] = -1;
                break;
            case 1:
                movement[0] = 1;
                break;
            case 2:
                movement[1] = -1;
                break;
            case 3:
                movement[1] = 1;
                break;
            default:
                break;
        }

        return movement;
    }

}
