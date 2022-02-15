public class LocationScore1845 implements Comparable<LocationScore1845> {
    private char[][][] board;
    private int s, r, c;
    private char piece;
    private int[] myScore, opponentScore;
    private int positionScore;

    public LocationScore1845(char[][][] board, char piece, int c, int r, int s) {
        this.board = board;
        this.piece = piece;
        myScore = new int[5];
        opponentScore = new int[5];
        this.s = s;
        this.r = r;
        this.c = c;
        computeScore(myScore, piece);
        if(piece == 'x') computeScore(opponentScore, 'o');
        else computeScore(myScore, 'x');
        positionScore = myScore[4] * 1000 + myScore[3] * 100 + myScore[2] * 10 + myScore[1];
    }

    public void computeScore(int[] score, char piece) {
        // r, 0-4
        int sheetCount = 0, rowCount = 0, colCount = 0;
        for(int i = 0; i < 4; i++) {
            if(board[i][r][c] != '-' && board[i][r][c] != piece) sheetCount = Integer.MIN_VALUE;
            else if(board[i][r][c] == piece) sheetCount++;
//            else System.out.println("i: " + i + " r: " + r + " c: " + c + " " + board[s][i][c]);
            if(board[s][r][i] != '-' && board[s][r][i] != piece) rowCount = Integer.MIN_VALUE;
            else if(board[s][r][i] == piece)  rowCount++;
//            else System.out.println("s: " + s + " r: " + r + " i: " + i + " " + board[s][r][i]);
            if(board[s][i][c] != '-' && board[s][i][c] != piece) colCount = Integer.MIN_VALUE;
            else if(board[s][i][c] == piece) colCount++;
//            else System.out.println("s: " + s + " i: " + i + " c: " + c + " " +board[s][i][c]);
        }

//        System.out.println("sheetCount: " + sheetCount + " rowCount: " + rowCount + " colCount: " + colCount);
        myScore[Math.max(sheetCount + 1, 0)]++;
        myScore[Math.max(rowCount + 1, 0)]++;
        myScore[Math.max(colCount + 1, 0)]++;

        int count1 = r == c ? 0 : Integer.MIN_VALUE, count2 = r == 3 - c ? 0 : Integer.MIN_VALUE; // same sheet
        int count3 = s == c ? 0 : Integer.MIN_VALUE, count4 = s == 3 - c ? 0 : Integer.MIN_VALUE; // same row
        int count5 = s == r ? 0 : Integer.MIN_VALUE, count6 = s == 3 - r ? 0 : Integer.MIN_VALUE; // same col
        int count7 = s == r && r == c ? 0 : Integer.MIN_VALUE, count8 = r == 3 - c && r == s ? 0 : Integer.MIN_VALUE, count9 = r == 3 - c && s == c ? 0: Integer.MIN_VALUE, count10 = r == c && s == 3 - r? 0 : Integer.MIN_VALUE;
//        System.out.println(count1 + " " + count2 + " " + count3 + " " + count4 + " " + count5 + " " + count6);
        for(int i = 0; i < 4; i++) {
            // same sheet
            if(board[s][i][i] != '-' && board[s][i][i] != piece) count1 = Integer.MIN_VALUE;
            else if(board[s][i][i] == piece) count1++;
            if(board[s][3-i][i] != '-' && board[s][3-i][i] != piece) count2 = Integer.MIN_VALUE;
            else if(board[s][3-i][i] == piece) count2++;
            // same row
            if(board[i][r][i] != '-' && board[i][r][i] != piece) count3 = Integer.MIN_VALUE;
            else if(board[i][r][i] == piece) count3++;
            if(board[3-i][r][i] != '-' && board[3-i][r][i] != piece) count4 = Integer.MIN_VALUE;
            else if(board[3-i][r][i] == piece) count4++;
            // same col
            if(board[i][i][c] != '-' && board[i][i][c] != piece) count5 = Integer.MIN_VALUE;
            else if(board[i][i][c] == piece) count5++;
            if(board[3-i][i][c] != '-' && board[3-i][i][c] != piece) count6 = Integer.MIN_VALUE;
            else if(board[3-i][i][c] == piece) count6++;
            // others
            if(board[i][i][i] != '-' && board[i][i][i] != piece) count7 = Integer.MIN_VALUE;
            else if(board[i][i][i] == piece) count7++;
            if(board[i][3-i][i] != '-' && board[i][3-i][i] != piece) count8 = Integer.MIN_VALUE;
            else if(board[i][i][i] == piece) count8++;
            if(board[3-i][i][3-i] != '-' && board[3-i][i][3-i] != piece) count9 = Integer.MIN_VALUE;
            else if(board[3-i][i][3-i] == piece) count9++;
            if(board[i][3-i][3-i] != '-' && board[i][3-i][3-i] != piece) count10 = Integer.MIN_VALUE;
            else if(board[i][3-i][3-i] == piece) count10++;
        }
//        System.out.println(count1 + " " + count2 + " " + count3 + " " + count4 + " " + count5 + " " + count6);
        myScore[Math.max(count1 + 1, 0)]++;
        myScore[Math.max(count2 + 1, 0)]++;
        myScore[Math.max(count3 + 1, 0)]++;
        myScore[Math.max(count4 + 1, 0)]++;
        myScore[Math.max(count5 + 1, 0)]++;
        myScore[Math.max(count6 + 1, 0)]++;
        myScore[Math.max(count7 + 1, 0)]++;
        myScore[Math.max(count8 + 1, 0)]++;
        myScore[Math.max(count9 + 1, 0)]++;
        myScore[Math.max(count10 + 1, 0)]++;
    }
    public int[] getMyScore() { return myScore; }
    public int[] getOpponentScore() { return opponentScore; }
    public int getPositionScore() { return positionScore; }
    public int getSheet() { return s; }
    public int getRow() { return r; }
    public int getCol() { return c; }

    public int compareTo(LocationScore1845 other) {
        if(positionScore != other.getPositionScore()) return other.getPositionScore() - positionScore;
        if ((s == 1 || s == 2) && (r == 1 || r == 2) && (c == 1 || c == 2) && !((other.getSheet() == 1 || other.getSheet() == 2) && (other.getRow() == 1 || other.getRow() == 2) && (other.getCol() == 1 || other.getCol() == 2))) return -1;
        if ((other.getSheet() == 1 || other.getSheet() == 2) && (other.getRow() == 1 || other.getRow() == 2) && (other.getCol() == 1 || other.getCol() == 2) && !((s == 1 || s == 2) && (r == 1 || r == 2) && (c == 1 || c == 2))) return 1;
        return 0;
    }
}
