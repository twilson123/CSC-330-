/**LAB 3 - Lotto QuickPicker Game 
 */
package edu.cuny.csi.csc330.lab3;
import java.math.BigInteger;
import java.util.*;
import edu.cuny.csi.csc330.lib.*;

// call Randomizer and use it

public class MegaMillionGame
{
	public final static int DEF_GAME_COUNT = 1; 
	private final static int SEL_POOL_SIZE = 40; 
	private final static int SEL_COUNT = 6; 
	private int [][]lottoTicket ;
	
	public MegaMillionGame() 
	{
		init(DEF_GAME_COUNT); 
	}
	
	public MegaMillionGame(int games) 
	{
		init(games); 
	}
	
	private void init(int games)
	 {
		/** Now what ... START FROM HERE 
		 * What additional methods do you need?
		 * What additional data properties/members do you need? 
		 * Randomizer.generateInt(1, SELECTION_POOL_SIZE); 
		 * private int generateInt(int low, int high){}
		 */
		lottoTicket = new int[games][SEL_COUNT];
		int winNum;
		for(int i = 0; i < games; i++) 
		{
			for(int j = 0; j < SEL_COUNT; j++) 
			{
				winNum = Randomizer.generateInt(1, SEL_POOL_SIZE);
				for (int k=0; k<j; k++){//check is it has same number in each game. if have, re-get a Random number
					if (lottoTicket[i][k] == winNum)
						winNum = Randomizer.generateInt(1, SEL_POOL_SIZE);
				}
				lottoTicket[i][j]=winNum;// assign the number to the game row
			}
			Arrays.sort(lottoTicket[i]);
		}
	}

	public void displayTicket() 
	{
		/**
		 * display heading 
		 * for i in gameCount 
		 * 		generate selectionCount number of unique random selections in ascending order 
		 * display footer 
		 */
		displayHeading(); 
		/** Display selected numbers 
		 */
		for(int i = 0; i<lottoTicket.length; i++)
		{
			System.out.printf("(%2d) " , i+1);
			for(int j = 0; j < SEL_COUNT; j++)
			{
				System.out.printf(" %02d ",lottoTicket[i][j]);
			}
			System.out.println();
		}
		calculateOdds();
		displayOdds();
		displayFooter(); 
		return;
	}
	
	protected void displayHeading()
	{
		System.out.println("---------------------------------\n" + 
				"--------  Mega Million ---------");
		System.out.println(" "+Calendar.getInstance().getTime()+"\n" );
	}
	
	protected void displayFooter()
	{
		System.out.println("\n----- (c) S.I. Corner Deli ------\n" + 
		 		"---------------------------------");
	}
	/**
	 * It uses the BigInteger odds to store the value of the factorial because the number after factorial is too large.
	 * The formula of combinations is n!/(r!*(n-r)!)
	 * I send the SELECTION_POOL_SIZE (=n)and  SELECTION_COUNT(=r) to factorial function to get the factorial value.
	 * First I assign the n!/r! to the odds valuable.
	 * The I odds / (n-r)! because of odds contents n!/r!.
	 * The odds is n!/(r!*(n-r)!) after these two steps. 
	 */
	private BigInteger odds;
	private long calculateOdds() 
	{
		odds = factorial(SEL_POOL_SIZE).divide(factorial(SEL_COUNT));
		odds = odds.divide(factorial(SEL_POOL_SIZE-SEL_COUNT));
		return 0;
	}
	
	public static BigInteger factorial(int num)
    {
       BigInteger result = BigInteger.ONE;
       for (int i = 1; i <= num; i++)
    	   result = result.multiply(new BigInteger(i + ""));
       return result;
    }
	
	protected void displayOdds() 
	{
		 System.out.println();
		 System.out.printf("Odds of Winning: 1 in "+ "%,d", odds);
	}
	/**@param args 
	 */
	public static void main(String[] args) 
	{
		// takes an optional command line parameter specifying number of QP games to be generated
		//  By default, generate 1  
		int numberOfGames  = 1; 
		if(args.length > 0)
		{
			numberOfGames = Integer.parseInt(args[0]);
		}
		MegaMillionGame game = new MegaMillionGame(numberOfGames);
		 
		game.displayTicket(); 
		
	}
}
