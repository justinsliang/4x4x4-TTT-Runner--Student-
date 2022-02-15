import java.util.ArrayList;
import java.util.Scanner;

public class VisuallyBattle
{
    public static final int NUM_GAMES=2, MOVE_SLEEP=100, END_SLEEP=5000, START_SLEEP=0;
    public static Board board = new Board();
    public static DisplayScreenV_AI display;
    public static void main(String[] args)
    {

        Scanner keyboard = new Scanner(System.in);
        ArrayList<PlayerInt> aisAsX = new ArrayList<>();
        ArrayList<PlayerInt> aisAsO = new ArrayList<>();


        aisAsX.add(new Test_AI_1('x'));
        aisAsO.add(new Test_AI_1('o'));
        aisAsX.add(new PoopyAI1845('x'));
        aisAsO.add(new PoopyAI1845('o'));


        double[] maxPoints = {0,0,0,0};

        System.out.println("Select an AI: ");
        for(int x=0; x<aisAsX.size(); x++)
        {
            System.out.println((x+1) + ". - "+ aisAsX.get(x).getName());
        }
        System.out.println("0. Exit Program");
        int ai1 = keyboard.nextInt();
        if(ai1==0)
        {
            System.out.println("\nGood Bye.");
            System.exit(0);
        }

        System.out.println("Select a second AI: ");
        for(int x=0; x<aisAsX.size(); x++)
        {
            System.out.println((x+1) + ". - "+ aisAsX.get(x).getName());
        }
        System.out.println("0. Exit Program");
        int ai2 = keyboard.nextInt();
        if(ai2==0)
        {
            System.out.println("\nGood Bye.");
            System.exit(0);
        }

        display = new DisplayScreenV_AI(board);

        PlayerInt ai1AsX = aisAsX.get(ai1-1);
        PlayerInt ai1AsO = aisAsO.get(ai1-1);

        PlayerInt ai2AsX = aisAsX.get(ai2-1);
        PlayerInt ai2AsO = aisAsO.get(ai2-1);

        try { Thread.sleep(START_SLEEP);}
        catch(Exception e){}

        System.out.println(ai1AsX.getName()+" as X vs " +ai2AsO.getName() + " as O");

        for(int games=0; games<NUM_GAMES; games++)
        {
            ai1AsX.refresh();
            ai2AsO.refresh();
            runGame(ai1AsX,ai2AsO);
        }

        System.out.println(ai2AsX.getName()+" as X vs " +ai1AsO.getName() + " as O");
        for(int games=0; games<NUM_GAMES; games++)
        {
            ai1AsO.refresh();
            ai2AsX.refresh();
            runGame(ai2AsX,ai1AsO);
        }
    }




    public static void runGame(PlayerInt xPlayer, PlayerInt oPlayer)
    {
        board.reset();
        boolean xTurn = true;
        while(true)
        {
            if (!board.isWinner('X') && !board.isWinner('O') && !board.isCat()) {
                if (xTurn) {
                    Location l = xPlayer.getMove(board.getDataCopy());

                    if (l != null && board.isEmpty(l))
                        board.setLocation(l, 'x');
                    else {
                        System.out.println(xPlayer.getName() + " failed to move.");
                        System.out.println("The move was "+ l);
                        try { Thread.sleep(1000000);}
                        catch(Exception e){}
                    }

                    xTurn = false;
                } else {
                    Location l = oPlayer.getMove(board.getDataCopy());

                    if (l != null && board.isEmpty(l))
                        board.setLocation(l, 'o');
                    else
                    {
                        System.out.println(oPlayer.getName() + " failed to move.");
                        System.out.println("The move was "+ l);
                        try { Thread.sleep(1000000);}
                        catch(Exception e){}
                    }

                    xTurn = true;
                }
                try { Thread.sleep(MOVE_SLEEP);}
                catch(Exception e){}
            }
            if (board.isWinner('x')) {
                System.out.println("\t"+xPlayer.getName() + " wins as X.");
                try { Thread.sleep(END_SLEEP);}
                catch(Exception e){}
                return;
            } else if (board.isWinner('o')) {
                System.out.println("\t"+oPlayer.getName() + " wins as O.");
                try { Thread.sleep(END_SLEEP);}
                catch(Exception e){}
                return;
            } else if (board.isCat()) {
                System.out.println("\t"+"Tie Game.");
                try { Thread.sleep(END_SLEEP);}
                catch(Exception e){}
                return;
            }
        }
    }
}
