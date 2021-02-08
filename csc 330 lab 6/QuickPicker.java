package edu.cuny.csi.csc330.lab6;

import java.io.File;
import java.util.*;
import edu.cuny.csi.csc330.lib.Randomizer;
import edu.cuny.csi.csc330.newradio.RadioException;

public class QuickPicker 
{
	public final static int DEFAULT_GAME_COUNT = 1; 
	private static int genpick = 0;//selection in pool1
	private static int range1 = 0;// pool1size
	private static int specpick = 0;//selection in pool2
	private static int range2 = 0;//pool21size
	
	//data from properties file
	private static String gameName;
	private static String pool1;
	private static String pool2;
	private static String vendor;
	
	private int gameCount;
	private String gameTypeName;
	
	public QuickPicker(int games, String gameName) throws QuickPickerException
	{
		init(games, gameName); 
	}
	
	private void init(int games, String gameName) throws QuickPickerException
	{
		this.gameCount=games;
		gameDetail(gameName);
	}

	private void gameDetail(String gameName) throws QuickPickerException
	{
	
		File propertyFile= new File("C:\\Users\\yncha\\eclipse-workspace\\CSI-CSC330\\src", gameName + ".properties");
		ResourceBundle bundle;
		
		if (propertyFile.exists()) 
		{
			bundle = ResourceBundle.getBundle(gameName);//call the PropertyBundleDemo.properties
		} 
		else 
		{
			throw new QuickPickerException("Can't find: "+ gameName + ".properties", QuickPickerException.INVALID_GAME);
		}
	
		if (bundle.containsKey("GameName") ) 
		{
			gameName = bundle.getString("GameName");
			gameTypeName=bundle.getString("GameName");
		}
		else 
		{
			throw new QuickPickerException(gameName + " do not match the game name in file.", QuickPickerException.PROPERTIES_MISSING);
		}
			
		if ( bundle.containsKey("Pool1") ) 
		{
			pool1 = bundle.getString("Pool1");
			String []part = pool1.split("/");
			genpick = genpick + Integer.parseInt(part[0]);//pick size
		    range1 = range1 + Integer.parseInt(part[1]); //pool1 size
		}
		else 
		{
			throw new QuickPickerException("Can't find Pool 1.", QuickPickerException.PROPERTIES_MISSING);
		}
		if ( bundle.containsKey("Pool2") )
		 {
			pool2 = bundle.getString("Pool2");
			String []part2 = pool2.split("/");
			specpick = specpick + Integer.parseInt(part2[0]);//pick size
		    range2 = range2 + Integer.parseInt(part2[1]);//pool2 size
		}
		else 
		{
			throw new QuickPickerException("Can't find Pool 2.", QuickPickerException.PROPERTIES_MISSING);
		}
		if ( bundle.containsKey("Vendor") ) {
			vendor = bundle.getString("Vendor");
		}
		else 
		{
			throw new QuickPickerException("Can't find vendor ", QuickPickerException.PROPERTIES_MISSING);
		}
	}
	
	public void displayTicket() 
	{
		displayHeading(); 
		for(int i = 0; i<gameCount; ++i) 
		{
			int selection[]=gamerateGameselection();
			displayGame(i+1, selection);
		}
		displayFooter(); // display ticket footer 
		return;
	}

	private int[] gamerateGameselection() 
	{
		int selection[] = new int [genpick+specpick];
		int i=0, j=genpick;
		while(i<genpick) 
		{
			selection[i]=Randomizer.generateInt(1, range1);
			if(i>0) 
			{
				if(alreadyExists(selection, selection[i], i)==true) 
				{
					continue;
				}
			}
			++i;
		}
		while (j<genpick+specpick)	
		{
			selection[j]=Randomizer.generateInt(1, range2);	
			if(j>0) 
			{
				if(alreadyExists(selection, selection[j], j)==true) 
				{
					continue;
				}
			}
			++j;
		}
		Arrays.sort(selection, 0, genpick);
		Arrays.sort(selection, genpick,genpick+specpick);
		return selection;
	}
	protected boolean alreadyExists(int[] selection, int value, int endIndex)
	 {
		// TODO Auto-generated method stub
		for(int i= 0; i < endIndex; ++i) 
		{
			if(value==selection[i]) return true;
		}				
		return false;
	}
	
	protected void displayGame(int index, int[] selection) 
	{
		if(index!=0)
			System.out.printf(" (%2d) ", index);
		for(int i=0;i<selection.length;i++) 
		{
			if (i>=genpick)
			{
				System.out.printf(" (( %02d ))", selection[i]);
				}
			else
				System.out.printf(" %02d ", selection[i]);
		}
		System.out.printf("\n");
	}
	protected void displayHeading() 
	{
		
		System.out.printf("------------------------------------\n");
		System.out.printf("---------- << %s >> ----------\n", gameTypeName);
		System.out.println(" "+new Date()+"\n" );
	}
	
	protected void displayFooter() 
	{
		System.out.printf("\nOdds of Winning: %d in << %,d >>", 1, calculateOdds());
		System.out.printf("\n----- (c) %s------\n", vendor );
		System.out.printf("------------------------------------\n");
	}
	
	private long calculateOdds() 
	{
		long sequencesProduct=1;
		long poolsProduct=1;
		long sequencesProduct2=1;
		long poolsProduct2=1;
		if(range2>0) 
		{
			for(int sequence2=1;sequence2<=specpick;sequence2++)
			 {
				sequencesProduct2*=sequence2;
			}
			for(int pool2=range2;pool2>(range2-specpick);pool2--) 
			{
				poolsProduct2*=pool2;
			}
		}
		for(int sequence=1;sequence<=genpick;sequence++) 
		{
			sequencesProduct*=sequence;
		}
		for(int pool=range1;pool>(range1-genpick);pool--) 
		{
			poolsProduct*=pool;
		}
		return (poolsProduct*poolsProduct2)/(sequencesProduct*sequencesProduct2);
	}
	
	public static void main(String[] args) throws QuickPickerException 
	{
		String gameType = args[0];
		try {
			int numberOfGames = DEFAULT_GAME_COUNT;
			if(args.length > 1) 
			{
				gameType = args[0];
				numberOfGames = Integer.parseInt(args[1]);
			}
			else if(args.length > 0) 
			{
				gameType = args[0];
			}
			QuickPicker games = new QuickPicker(numberOfGames, gameType);
			games.displayTicket();
		}
		
		catch(QuickPickerException qpe) 
		{
			System.err.println(qpe);
		}
	}
}