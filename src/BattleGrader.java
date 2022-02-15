import java.util.ArrayList;
import java.util.Scanner;

public class BattleGrader
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        ArrayList<PlayerInt> studentAIsAsX = new ArrayList<>();
        ArrayList<PlayerInt> studentAIsAsO = new ArrayList<>();
        studentAIsAsX.add(new Test_AI_1('x'));
        studentAIsAsO.add(new Test_AI_1('o'));
        studentAIsAsX.add(new PoopyAI1845('x'));
        studentAIsAsO.add(new PoopyAI1845('o'));

        ArrayList<PlayerInt> testerAIsAsX = new ArrayList<>();
        ArrayList<PlayerInt> testerAIsAsO = new ArrayList<>();

        testerAIsAsX.add(new Test_AI_1('x'));
        testerAIsAsO.add(new Test_AI_1('o'));


        double[] maxPoints = {0,0,0,0};

        System.out.println("Select a Student AI: ");
        for(int x=0; x<studentAIsAsX.size(); x++)
        {
            System.out.println((x+1) + ". - "+ studentAIsAsX.get(x).getName());
        }
        System.out.println("0. Exit Program");
        int selection = keyboard.nextInt();
        if(selection==0)
        {
            System.out.println("\nGood Bye.");
            System.exit(0);
        }
        else {
            for (int testRound = 0; testRound < 4; testRound++) {
                System.out.println("\nRound "+(testRound+1)+" results: ");
                for(int ai=0; ai<testerAIsAsX.size(); ai++)
                {
                    PlayerInt studentX = studentAIsAsX.get(selection-1);
                    PlayerInt testerO = testerAIsAsO.get(ai);

                    PlayerInt studentO = studentAIsAsO.get(selection-1);
                    PlayerInt testerX = testerAIsAsX.get(ai);
                    System.out.print(studentX.getName()+" vs " +testerO.getName());
                    PlayerScores playerScores = new PlayerScores();
                    for(int games=0; games<20; games++)
                    {
                        studentX.refresh();
                        testerO.refresh();
                        runGame(playerScores,studentX,testerO,true);
                    }

                    for(int games=0; games<20; games++)
                    {
                        studentO.refresh();
                        testerX.refresh();
                        runGame(playerScores,testerX,studentO,false);
                    }

                    double points = playerScores.getWins()+playerScores.getCats()/2.0;
                    System.out.println(" (W-"+playerScores.getWins()+" T-"+playerScores.getCats()+" L-"+playerScores.getLosses()
                            +") Points = "+points);

                    if(points > maxPoints[ai])
                        maxPoints[ai] = points;
                }
            }

            System.out.println("\nMax Points");
            for(int ai=0; ai<testerAIsAsX.size(); ai++)
                System.out.println(testerAIsAsX.get(ai).getName() + " - " + maxPoints[ai]);
        }
    }

    public static void runGame(PlayerScores playerScores, PlayerInt xPlayer, PlayerInt oPlayer, boolean playerIsX)
    {
        Board board = new Board();
        boolean xTurn = true;
        while(true)
        {
            if (!board.isWinner('X') && !board.isWinner('O') && !board.isCat()) {
                if (xTurn) {
                    Location l = xPlayer.getMove(board.getDataCopy());

                    if (l != null && board.isEmpty(l))
                        board.setLocation(l, 'x');
                    else
                        System.out.println(xPlayer.getName() + " failed to move.");
                    xTurn = false;
                } else {
                    Location l = oPlayer.getMove(board.getDataCopy());

                    if (l != null && board.isEmpty(l))
                        board.setLocation(l, 'o');
                    else
                        System.out.println(oPlayer.getName() + " failed to move.");

                    xTurn = true;
                }

            }
            if (board.isWinner('x')) {
                if (playerIsX)
                    playerScores.addWin();
                else
                    playerScores.addLoss();
                return;
            } else if (board.isWinner('o')) {
                if (playerIsX)
                    playerScores.addLoss();
                else
                    playerScores.addWin();
                return;
            } else if (board.isCat()) {
                playerScores.addCat();
                return;
            }
        }
    }
}
