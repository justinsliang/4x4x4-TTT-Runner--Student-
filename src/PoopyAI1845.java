import java.util.*;

public class PoopyAI1845 implements PlayerInt {
    private String name;
    private char letter;

    public PoopyAI1845(char letter) {
        name = "Poopy AI";
        this.letter = letter;
    }

    /*Returns the letter the player is playing as*/
    public char getLetter() {
        return letter;
    }

    /*Returns the location that the player would like to move to.
    This method must receive a copy on the game board, to prevent players from cheating!*/
    public Location getMove(char[][][] board) {
        ArrayList<LocationScore1845> scores = new ArrayList<>();
        for(int s = 0; s < 4; s++)
            for(int r = 0; r < 4; r++)
                for(int c = 0; c < 4; c++)
                    if(board[s][r][c] == '-')
                        scores.add(new LocationScore1845(board, letter, c, r, s));
        Collections.sort(scores);
        LocationScore1845 best = scores.get(0);
        return new Location(best.getCol(), best.getRow(), best.getSheet());
    }

    /*Returns the player's name */
    public String getName() {
        return name;
    }

    public void refresh() {
        // TODO Auto-generated method stub

    }
}
