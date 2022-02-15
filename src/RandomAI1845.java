public class RandomAI1845 implements PlayerInt {
    private String name;
    private char letter;

    public RandomAI1845(String name, char letter) {
        this.name = name;
        this.letter = letter;
    }

    /*Returns the letter the player is playing as*/
    public char getLetter() {
        return letter;
    }

    /*Returns the location that the player would like to move to.
    This method must receive a copy on the game board, to prevent players from cheating!*/
    public Location getMove(char[][][] board) {
        int x = (int)(Math.random()*4);
        int y = (int)(Math.random()*4);
        int z = (int)(Math.random()*4);
        while (board[z][y][x] != '-') {
            x = (int)(Math.random()*4);
            y = (int)(Math.random()*4);
            z = (int)(Math.random()*4);
        }
        return new Location(x, y, z);
    }
	
    /*Returns the player's name */
    public String getName() {
        return name;
    }

    public void refresh() {
        // TODO Auto-generated method stub
        
    }
}
