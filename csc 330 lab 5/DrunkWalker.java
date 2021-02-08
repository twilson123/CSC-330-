package edu.cuny.csi.csc330.lab5;

import java.math.*;
import java.util.*;

public class DrunkWalker 
{
	
	private Intersection startIntersection;
	private Intersection currentIntersection;

	private Integer totalSteps = 0;
	
	private Map<Intersection, Integer> intersectionCounter = new HashMap<Intersection, Integer>();
	
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  
	// add other data members here including Collection instances that you will use to 
	//  to track history and statistics ... 

	private DrunkWalker() 
	{
		init();
	}
	
	/**
	 * 
	 * @param avenue
	 * @param street
	 */
	public DrunkWalker(int avenue, int street) 
	{	
		this.startIntersection = new Intersection(avenue, street);
		init();
	}
	
	private void init() 
	{
		// What should be do here to initialize an instance?? 
		if(this.startIntersection == null) 
		{
			this.startIntersection = new Intersection();
		}
		currentIntersection = new Intersection(startIntersection.getAvenue(), startIntersection.getStreet());
	}
	
	/**
	 * step in a random direction 
	 */
	public void step() 
	{
		
		takeAStep(); 
		
		/**  !!!!!!!!!!!!!!!!!!!!!!!!!!!
		 * Now, update the Collections that manage the following:
		 *  1) add this next step/intersection to a "history" List that will contain the sequence of all 
		 *  steps taken by this DrunkWalker instance 
		 *  
		 *  2) add this next step to a Intersection -> Counter Map ... The Map 
		 *  Collection can and should be of Generics "Type" <Intersection, Integer>   ???
		 *  key = Intersection 
		 *  value = Count Value  
		 *  Need to do something like this: 
		 *  if intersection is not saved in Map yet as a key yet, add a key->value pair of Intersection->1 
		 *  else if intersection value is there, the existing key->value pair need to be replaced as 
		 *   Intersection->existing_count+1 
		 */
		totalSteps++;
		int stepCount;
		if(intersectionCounter.containsKey(currentIntersection))
		{
			stepCount = intersectionCounter.get(currentIntersection);
			intersectionCounter.put(currentIntersection, stepCount+1);
		}
		else
		{
			intersectionCounter.put(currentIntersection,1);	
		}
	}
	
	
	private void takeAStep() 
	{
		Direction dir = Direction.NONE.getNextRandom(); 
		/** !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 * now what do we do based on the random Direction created? 
		 * How do we go about updating the "currentIntersection" instance to reflect the 
		 * direction/step that was just selected? 
		 */
		Intersection nextIntersection = new Intersection(currentIntersection.getAvenue(), currentIntersection.getStreet());
		switch(dir) 
		{
		case SOUTH:
			nextIntersection.setStreet(nextIntersection.getStreet() - 1);
			break;
		case WEST:
			nextIntersection.setAvenue(nextIntersection.getAvenue() + 1);
			break;
		case NORTH:
			nextIntersection.setStreet(nextIntersection.getStreet() + 1);
			break;
		case EAST:
			nextIntersection.setAvenue(nextIntersection.getAvenue() - 1);
			break;	
		case NORTHEAST:
			nextIntersection.setStreet(nextIntersection.getStreet() + 1);
			nextIntersection.setAvenue(nextIntersection.getAvenue() - 1);
			break;
		case NORTHWEST:
			nextIntersection.setStreet(nextIntersection.getStreet() + 1);
			nextIntersection.setAvenue(nextIntersection.getAvenue() + 1);
			break;
		case SOUTHWEST:
			nextIntersection.setStreet(nextIntersection.getStreet() - 1);
			nextIntersection.setAvenue(nextIntersection.getAvenue() + 1);
			break;	
		case SOUTHEAST:
			nextIntersection.setStreet(nextIntersection.getStreet() - 1);
			nextIntersection.setAvenue(nextIntersection.getAvenue() - 1);
			break;
		default:
			System.out.println("The drunk died to alcohol poisoning.");
			System.exit(0);
		}
		currentIntersection = nextIntersection;
	
	}


	/** !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * toString() 
	 * @return
	 */
	@Override
	public String toString() 
	{
	/*	return "DrunkWalker [startIntersection=" + startIntersection + ", currentIntersection=" + currentIntersection
				+ ", intersectionCount=" + intersectionCount + ", totalSteps=" + totalSteps + "]";
	*/
		return null;
	}

	/**
	 * generate string that contains current intersection/location info 
	 */
	public String getLocation() 
	{
		// !!!!!!!!!!!!!!!!!  
		return String.format("Current location: DrunkWalker [avenue=%d, street=%d]", 
			currentIntersection.getAvenue(), currentIntersection.getStreet() );
	}

	/**
	 * Take N number of steps 
	 * @param steps
	 */
	public void fastForward(int steps ) 
	{
		// Considering that we already have a step() method, how would we 
		//  implement this method?  Uhh, think reuse!  
		for(int i = 0; i < steps; i++) 
		{
			step();
		}
	}
	
	/**
	 * Display information about this current walker instance 
	 */
	public void displayWalkDetails()
	{
		/**
		 * This method needs to display the following information in a neat, readable format 
		 * using calls to System.out.println() or System.out.printf()
		 * 
		 * 1 - starting location 
		 * 2 - current/ending location 
		 * 3 - distance (value returned by howFar() ) 
		 * 4 - number of steps taken - which collection would be able to provide that information, and how?  
		 * 
		 * 5 - number of unique intersections visited - 
		 * 		which collection would be able to provide that information, and how? 
		 * 6 - Intersections visited more than once 
		 */
		System.out.println("\nStarting Location: " + startIntersection);
		System.out.println("Current/Ending Location: " + currentIntersection);
		System.out.println("Distance (blocks): " + howFar());
		System.out.println("Number of steps taken: " + totalSteps);
		System.out.println("Number of unique intersections visited: " + intersectionCounter.size() + "\n");// size in hashmap
		for(Intersection intersection : intersectionCounter.keySet()) ////just check all line, not look like C++ that i++, but similar to for loop
		{
			Integer visitTimes = intersectionCounter.get(intersection);
			if(visitTimes > 1) 
			{
				System.out.println("Visited " + intersection + " " + visitTimes + " times!");
			}
		}		
	}
	
	/**
	 * X Y Coordinate distance formula 
	 *  |x1 - x2| + |y1 - y2|   
	 * @return
	 */
	public int howFar() 
	{
		/** |x1 - x2| + |y1 - y2|.
		startAvenue = x1 
		avenue = x2 
		startStreet = y1 
		street = y2 
		return (Math.abs(startAvenue - avenue) ) + (Math.abs(startStreet - street)); 
		 * 
		 */
		
		return (Math.abs(startIntersection.getAvenue() - currentIntersection.getAvenue()))
				+ (Math.abs(startIntersection.getStreet() - currentIntersection.getStreet())); 
	}


	public static void main(String[] args) 
	{
		
		// create Drunkard with initial position (ave,str)
		DrunkWalker billy = new DrunkWalker(6,23);
		
		for(int i = 1 ; i <= 3 ; ++i ) 
		{
			billy.step(); 
			System.out.printf("Billy's location after %d steps: %s\n",
					i, billy.getLocation() );
		}
			
		// get his current location
		String location = billy.getLocation();
		// get distance from start
		int distance = billy.howFar();
		System.out.println("Current location after fastForward(): " + location);
		System.out.println("That's " + distance + " blocks from start.");
		
		/*
		 * billy = new DrunkWalker(8, 50);
		 * int steps = 25;
		 * if(args.length>0)
		 * 	steps=Inter.pareInt(args[0]);
		 * 
		 * */
		
		System.out.println("\nStart Time: " + new Date());
		
		billy.fastForward(97);//billy.fastForward(steps)   -  have him move 100  random intersections
		System.out.println("End Time: " +  new Date());
		
		billy.displayWalkDetails();
		
	}
}
