package dungeon;

public class Position {

    private int x;
    private int y;
    private Piece piece;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean containsPiece() {
        return (piece != null);
    }

    public boolean containsSlayer() {
        return (getPiece() instanceof Slayer);
    }

    public boolean containsVampire() {
        return (getPiece() instanceof Vampire);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
