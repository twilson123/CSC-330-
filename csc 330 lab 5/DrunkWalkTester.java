package edu.cuny.csi.csc330.lab5;

import java.util.*;

public class DrunkWalkTester 
{
	
	private Scanner input;

	public DrunkWalkTester() 
	{
		init();  
	}
	
	private void init() 
	{
		input = new Scanner(System.in);
	}
	
	public void runTest(int steps )
	{ 
		
		
		System.out.print("Enter the starting avenue value: ");
		int avenue = input.nextInt();
		System.out.print("Enter the starting street value: ");
		int street = input.nextInt();
		
		//////////////////////// start test 
		// make the Drunkard with initial position
		DrunkWalker billy = new DrunkWalker(avenue,street);

		// have him move/step 200 time 
		billy.fastForward(steps);
		
		// get his current location
		String location = billy.getLocation();
		
		// get distance from start
		int distance = billy.howFar();

		System.out.println("Billy's " + location);
		System.out.println("That's " + distance + " blocks from start.");
		
		billy.displayWalkDetails();
		
		//////////////////// end test 
		/** 
		 * Expand the test above to include the following ... 
		 * Create a 2nd instances of DrunkWalker - Harvey  
		 * Have then race each - which instance (billy or harvey)  
		 * manages to walk a greater distance with 200 steps?  
		 * 
		 * Also invoke the displayWalkDetails() on both instances.
 */		
		
		DrunkWalker harvey = new DrunkWalker(avenue,street);
		harvey.fastForward(steps);
		String harveyLocation = harvey.getLocation();
		int harveyDistance = harvey.howFar();
		System.out.println("\nHarvey's " + harveyLocation);
		System.out.println("That's " + distance + " blocks from start.");//????
		harvey.displayWalkDetails();
		
		if(distance > harveyDistance)
		{
			System.out.println("\nBilly walked a greater distance!");
		}
		else if(harveyDistance > distance) 
		{
			System.out.println("\nHarvey walked the greater distance!");
		}
		else {
			System.out.println("\nThey walked the same distance!");
		}
	}

	/**
	 * @param args 
	 * 
	 */
	public static void main(String[] args)
	{
		DrunkWalkTester tester = new DrunkWalkTester();
		
		int steps = 150; 
		if(args.length == 1)
		{
			steps = Integer.parseInt(args[0]);
		}
		
		tester.runTest(steps); 
		
		System.exit(0);

	}

}
