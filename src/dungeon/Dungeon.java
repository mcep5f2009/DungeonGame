package dungeon;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Dungeon {

    private Scanner reader;
    private Board board;
    private Slayer slayer;
    private List<Vampire> vampires;
    private List<Vampire> vampiresDestroyed;
    private int moves;

    public Dungeon(int length, int height, int totalVampires, int moves, boolean vampiresMove) {
        reader = new Scanner(System.in);
        board = new Board(length, height);
        slayer = new Slayer();
        slayer.addToBoard(board);

        vampires = new ArrayList<Vampire>();
        for (int i = 0; i < totalVampires; i++) {
            Vampire vampire = new Vampire(vampiresMove);
            vampire.addToBoard(board);
            vampires.add(vampire);
        }

        this.vampiresDestroyed = new ArrayList<Vampire>();
        this.moves = moves;
    }

    public void run() {

        while (true) {
            printGameStatus();
            System.out.print("");
            String command = reader.nextLine().trim();

            for (int i = 0; i < command.length(); i++) {
                char c = command.charAt(i);
                switch (c) {
                    case 'w':
                        movePieces(0, -1);
                        break;
                    case 's':
                        movePieces(0, 1);
                        break;
                    case 'a':
                        movePieces(-1, 0);
                        break;
                    case 'd':
                        movePieces(1, 0);
                        break;
                    default:
                        break;
                }
            }

            if (vampires.isEmpty()) {
                System.out.println("YOU WIN");
                break;
            }

            moves--;
            
            if ((moves) == 0) {
                System.out.println("YOU LOSE");
                break;
            }
        }
    }

    private void printGameStatus() {
        System.out.println(moves + "\n");
        System.out.println(slayer + " " + slayer.getPosition());
        for (Piece vampire : vampires) {
            System.out.println(vampire + " " + vampire.getPosition());
        }
        printDungeon();
    }

    private void printDungeon() {
        System.out.println();
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getLength(); j++) {
                Piece piece = board.getPosition(j, i).getPiece();
                System.out.print(getPositionSymbol(piece));
            }
            System.out.println();
        }
        System.out.println();
    }

    private String getPositionSymbol(Piece piece) {
        if (piece == null) {
            return ".";
        }
        return piece.toString();
    }

    private void movePieces(int xChange, int yChange) {
        slayer.move(board, xChange, yChange, vampiresDestroyed);
        killVampires();
        for (Vampire vampire : vampires) {
            vampire.move(board, vampiresDestroyed);
        }
        killVampires();;
    }
    
    private void killVampires() {
        vampires.removeAll(vampiresDestroyed);
        vampiresDestroyed.clear();
    }

}
