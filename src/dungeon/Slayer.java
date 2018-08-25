package dungeon;

import java.util.List;

public class Slayer extends Piece {

    public void move(Board board, int xChange, int yChange, List<Vampire> vampiresDestroyed) {
        Position currentPosition = getPosition();
        Position newPosition = board.getAdjacentPosition(currentPosition, xChange, yChange);

        if (newPosition == null) {
            return;
        }

        if (newPosition.containsVampire()) {
            Vampire vampire = (Vampire) newPosition.getPiece();
            vampiresDestroyed.add(vampire);
            newPosition.setPiece(null);
        }

        setPosition(newPosition);
        currentPosition.setPiece(null);
        newPosition.setPiece(this);
    }

    @Override
    public void addToBoard(Board board) {
        Position position = board.getPosition(0, 0);
        setPosition(position);
        position.setPiece(this);
    }

    @Override
    public String toString() {
        return "@";
    }

}
