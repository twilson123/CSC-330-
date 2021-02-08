
public class JulianCalendar {

	// Max number of Days in a month
	static private final int MAX_DAY = 31;

	// abbreviated Month names
	static private final String[] MONTH_NAMES = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
			"Nov", "Dec" };

	// day length of each month
	static private final int[] MONTH_SIZES = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	/**
	 * display The "DAY" Column Heading
	 */
	static private void displayDayHeading() {
		System.out.printf("%6s", "Day");

	}

	/**
	 * display Month names between Day .... Day
	 */
	static private void displayHeading() {
		displayDayHeading();

		for (int i = 0; i < MONTH_NAMES.length; ++i) {
			System.out.printf("%5s", MONTH_NAMES[i]);
		}

		displayDayHeading();
	}

	static public void display() {
		displayHeading(); // display heading

		/**
		 * Complete the logic to display a Julian cal given the pre-populated arrays
		 * defined by the Class
		 * 
		 * 
		 * 
		 * 
		 */
		System.out.println();

		int t = 0, w = 0;

		for (int i = 1; i < 32; i++) {
			System.out.printf("%7s", i);

			for (int j = 0; j < MONTH_SIZES.length; j++)// j = month
			{
				if (i > MONTH_SIZES[j]) {
					//System.out.printf("%3s", "");

					System.out.printf("  %03d", w);

					t += MONTH_SIZES[j];// add the month for the next row
				} else {
//					System.out.printf("%2s", "");

					System.out.printf("  %03d", t + i);// 0 fills in the empty digits

					t += MONTH_SIZES[j];// add the month in the row
				}
			}

			t = 0;// reset

			System.out.printf("%7s", i);

			System.out.println();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// invoke display method
		display();
	}
}