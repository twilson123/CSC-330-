package edu.cuny.csi.csc330.lab2;

import java.util.Arrays;
import java.lang.Math;

public class NumericAnalyzer 
{
	private static void calculate(int a[]) 
	{
	   
		int median;
		double sum=0, t=0;
		double range, mean, var, standardDev;

		//puts them in order from least to greatest 
		System.out.printf("%-25s%5d", "Minimum:",a[0]);
		System.out.println();
		
		System.out.printf("%-25s%5d", "Maximum:", a[a.length-1]);
		System.out.println();

		//range
		range = a[a.length-1] - a[0];
		System.out.printf("%-25s%5.0f", "Range:", range);
		System.out.println();

		//total
		for (int i = 0; i < a.length; i++) 
		{
			sum += a[i];
		}		

		System.out.printf("%-25s%5.0f", "Sum:",sum);
		System.out.println();

		//mean/average
		mean=sum/a.length;
		System.out.printf("%-25s%5.0f", "Mean:", mean);
		System.out.println();

		//median
		if (a.length%2==0)
		{
			median=(a[a.length/2] + a[(a.length / 2) - 1]) / 2;
		}
		else
			median=a[a.length/2];
		System.out.printf("%-25s%5d", "Median:", median);
		System.out.println();

		//variance
		for(int j=0; j<a.length;j++)
			t+=(a[j] - mean) * (a[j] - mean);
		 var = t / a.length;

		System.out.printf("%-25s%5.0f", "Variance:", var);
		System.out.println();

		//standardDev
		standardDev= Math.sqrt(var);
		System.out.printf("%-25s%5.0f", "Standard Deviation:", standardDev);
	}
	public static void sort(int c[]) 
	{

	}
	 
	public static void display(int b[])
	 {
		for(int i = 0 ; i < b.length ; i++ )
	    {
			System.out.printf("%-5s", b[i]);	
		}
		System.out.println();
		System.out.println();
		System.out.printf("%-25s%5d", "Size:", b.length);
	}
	
	public static void main(String[] args) 
	{	
		if (args.length==0) 
		{   
			//error message
			System.err.printf("Please pass in 1 or more positive integer number.");
			System.exit(1); 
		}
		    // set array
		  int[] number = new int[args.length];
		
		for (int i=0; i<args.length; i++) 
		{   
			//printout array  
			number[i]=Integer.parseInt(args[i]);
		}
		Arrays.sort(number); 
		display(number); 
		System.out.println();
		calculate(number);
		System.out.println();
	}

}
