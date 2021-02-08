package edu.cuny.csi.csc330.lab6;

import java.util.*;

public class QuickPickerException extends Exception 
{
	
	// static publicly defined error codes 
	  public static int	UNSET = 0; 
	  public static int	INVALID_GAME = 1; 
	  public static int PROPERTIES_MISSING = 2; 
	    
	  // static publicly defined exception messages  
	  public static String[]  MESSAGE = 
	  { 
		"Code Unspecified", 
		"Unknown Game - Can't locate specified game.", 
		"Properties Missing", 
		  };
	  protected int code;
		// Constructors ... 
		private  QuickPickerException() 
		{
		} 
		
		// with thrower  message 
		public QuickPickerException(String m) 
		{ 
			super(m); 
		} 
		
		// with thrower message and code 
		public QuickPickerException(String message, int code) 
		{ 
			super(message);
			this.code = code;
		} 
		
		public int getCode() 
		{ 
			return code;
		}
		
		@Override
		public String toString() 
		{
			return "QuickPickerException [code=" + code + ", toString()="
					+ super.toString() + "]\n" + MESSAGE[code] ;
		}
		
		public static void main(String [] args ) 
		{
			
		}
}