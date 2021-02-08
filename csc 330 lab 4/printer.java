package edu.cuny.csi.csc330.lab4;

import java.util.*;
import edu.cuny.csi.csc330.lib.Randomizer;

public class Printer
{
	
	protected static final int MIN_INK_CHOICE = 0;
   	protected static final int MAX_INK_CHOICE = 2;
   	
   	//capacity
   	protected static final int MIN_PRINTER_CAP = 50;
   	protected static final int MAX_PRINTER_CAP = 1000;
   	
   	protected static final int MIN_SPEED = 10;
   	protected static final int MAX_SPEED = 25;
   	protected static final int DEFAULT_SPEED = 15;
   	
   	protected static final int MIN_PRINTING_AMT = 20;
   	protected static final int MAX_PRINTING_AMT = 50;
   	
   	private String serialNum;
   	
    //how much the printer can hold
   	private int printerCap;
  
   	private boolean power;
   	private boolean landscape;
   	private String layout;
   
	private int inkColorLevel;
   	private String[] selectInkCol = new String[]{"Black & Write", "Grey", "Color"};
   	
   	// amount of paper inside 
	private int paperAmt;
   	
   
   	private int printSpeed;
   	
   	//max of paper that can be printed 
   	private int printingLtd;//max paper can print each time
   	
   	private Date firstTimeOn;
   	private Date lastTimeOn;
   	
	public Printer() 
	{
   		init();
   	}
	private void init() 
	{
   		Integer numberRange = Randomizer.generateInt(11111111, 99999999);
   		Integer capacity = Randomizer.generateInt(MIN_PRINTER_CAP, MAX_PRINTER_CAP);
   		this.serialNumber = new Date().getTime() + ":" + numberRange.toString();
   		this.printerCap = cap;
   	}
   	   	
	   private void on()
	   {
   		Date now = new Date();
		   if(firstTimeOn == null) 
		   {
   			landscape = false;//Portrait, not Landscape
   			layout = "Portrait";
   			firstTimeOn = now;
   			inkColorLevel = MIN_INK_CHOICE;
   			paperAmt = 0;
   			printSpeed = DEFAULT_SPEED;
   			printingLmt = MIN_PRINTING_AMT;
   	   }
   		power = true; 
   		lastTimeOn = now;
   	}
   	public void off()
    {
		power = false; 
	}
   	
   	public Date getFirstTimeOn() 
   	{
   		return firstTimeOn;
   	} 
   	
	// paper layout 
		
   	public boolean landscape()
   	 {
		return landscape;
	}
	
	public void setLandscape(boolean landscape) 
	{
		this.landscape = landscape;
		if (landscape == true)
			layout = "Landscape";
		else 
			layout = "Portrait";	
	}
	
	public boolean changeLandscape() 
	{
		landscape = !landscape;
		if (landscape == true)
			layout = "Landscape";
		else 
			layout = "Portrait";	
		return landscape;
	}
	
	public String getLayout() 
	{
		return layout;
	}
	
	public void setLayout(String Layout) 
	{
		this.layout = layout;
	}
   	// End of paper layout
   	
   	
   	//INK 
   	public int getInkColorLevel() 
   	{
		return inkColorLevel;
	}
	
   	public void setInkColorLevel(int inkColorLevel) 
   	{
		this.inkColorLevel = inkColorLevel;
	}
	
   	public int changInkColorLevel()
    {
   		return inkColorLevel = Randomizer.generateInt(MIN_INK_CHOICE, MAX_INK_CHOICE);
   	}
   	
	public String getSelectInkCol() 
	{
		return selectInkCol[inkColorLevel];
	}
	
	
	//Printer contains paper
	public int addPaperTray()
	{
		if(paperAmt==0)
			paperAmt = Randomizer.generateInt(MIN_PRINTER_CAP, printerCap);
		return paperAmt;
	}
	
	public int getPaperAmt() 
	{
		return paperAmt;
	}
	
	public void setPaperAmt(int paperAmt ) 
	{
		this.paperAmt  = paperAmt;
	}
	
	
	// Printing Speed
	public int getPrintSpeed() 
	{
		return printSpeed;
	}
	
	public void setPrintSpeed(int printSpeed) 
	{
		this.printSpeed = printSpeed;
	}
	
	public void decreasePrintSpeed(int Speed) 
	{
		if((printSpeed-Speed) >= MIN_SPEED)
		{
			this.printSpeed -= Speed;
		}
		else
			System.err.println("It's over the minimun printing speed.\n");
	}
	
	public void increasePrintSpeed(int Speed) 
	{
		if(printSpeed < (MAX_PRINTING_AMT-Speed))
		{
			this.printSpeed += Speed;
		}
		else
			System.err.println("It's over the maximun printing speed.\n");
	}
	// End of Printing Speed
	
	
	
 	public int getPrintingLtd() 
 	{
		return printingLtd;
	}
	
	public void setPrintingLtd(int printingLtd) 
	{
		this.printingLtd = printingLtd;
	}
	
	public void decreasePrintingLtd(int page)
	 {
		if (printingLtd > 5 && printingLtd > page)
		{
			this.printingLtd -= page;
		}
		else 
		{
			System.err.println("It's over the minimun printing limit.\n");
		}
	}
	
	public void increasePrintingLtd(int page) 
	{
		if((printingLtd + page)< MAX_PRINTING_AMT)
		{
			this.printingLtd += page;
		}
		else
		{
			System.err.println("It's over the maximun printing limit.\n");
		}
	}

	
    @Override
	public String toString() 
	{
    	"Printer Instance: " + "[SerialNumber = " + serialNum + ", Printer Capacity = "+printerCap 
    			+ ", powerState = " + power +",\n isLandscape = "+ landscape
    			+ ", paperLayoutForm = "+ layout + ", selectedInkColor = "+ selectInkColor[inkColorLevel]
    			+", inkColor = " + inkColorLevel + ",\n containedPaperAmount = " + paperAmt
    			+  ", selectedPrintSpeed = " + printSpeed + ", selectedPrintingLimit = "+ printingLtd
    			+ "\n firstTimeOn = " + firstTimeOn + ", lastTimeOn = " + lastTimeOn + "]\n";
   	}
      
   public static void main(String[] args) 
   {
   	   
   		Printer printer = new Printer();
   		System.out.println("New Instance\n" + printer + "\n");
   	   
   		
   		printer.on();
   		System.out.println("Turned On\n" + printer + "\n");
   		
   		
   		printer.decreasePrintSpeed(5);
   		System.out.println("Changed the PPM\n" + printer + "\n");
   	   
   		//paper layout 
   		printer.changeLandscape();
   		System.out.println("Changed to Landscape\n" + printer + "\n");
   		
   		//ink color change
   		printer.changeInkColorLevel();
   		System.out.println("Changed the ink color\n" + printer + "\n");
   		
   		// adding paper to tray
   		printer.addPaperTray();
   		System.out.println("Added paper to printer\n" + printer + "\n");
   		
   		// printing limit
   		printer.increasePrintingLtd(10);
   		System.out.println("Changed the Printer printing limit amount\n" + printer + "\n");
   		
   		printer.off();
   		System.out.println("Turned Off\n" + printer + "\n");
   	}
}