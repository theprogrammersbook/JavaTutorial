/**
 * 
 */

import java.text.DecimalFormat;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author
 *
 */
public class RockyCateringMenu {
	final int ENTER_BOOKING = 1;
	final int DISPLAY_BOOKINGS = 2;
	final int DISPLAY_STATISTICS = 3;
	final int SEARCH_BOOKINGS = 4;
	final int SORT_BOOKINGS = 5;
	final int EXIT = 6;

	JFrame jFrame = new JFrame();
	DecimalFormat decimalFormat = new DecimalFormat("0.00");
	//   array of booking objects
	Object[] bookings = new Object[10];
	//   variable for the current booking entered (integer)
	private int bookingCounter = 0;

	Scanner inputMenuChoice = new Scanner(System.in);

	private int getMenuItem() {
		System.out.println("\nPlease select from the following");
		System.out.println(ENTER_BOOKING + ". Enter booking name and number of guests");
		System.out.println(DISPLAY_BOOKINGS + ". Display all booking names, number of guests and charges");
		System.out.println(DISPLAY_STATISTICS + ". Display Statistics");
		System.out.println(SEARCH_BOOKINGS + ". Search for booking");
		System.out.println(SORT_BOOKINGS + ". Sort the bookings");
		System.out.println(EXIT + ". Exit the application");
		System.out.print("Enter choice==> ");

		String choice = inputMenuChoice.nextLine();
		while (choice.equals("") || !isStringNumeric(choice)) {
			errorDailogMessage("Error - Menu selection name cannot be blank and must be numeric", "Input Menu Choice");

			System.out.print("Enter choice==> ");

			choice = inputMenuChoice.nextLine();
		}

		return Integer.parseInt(choice);
	}

	private boolean isStringNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i)))
				return false;
		}

		return true;
	}

	private void processBookings() {
		System.out.println("Welcome to the Rocky Catering Service Management System\n");
		int choice = getMenuItem();
		while (choice != EXIT) {
			switch (choice) {
			case ENTER_BOOKING:
				enterBooking();
				break;
			case DISPLAY_BOOKINGS:
				displayAllBookings();
				break;
			case DISPLAY_STATISTICS:
				displayStatistics();
				break;
			case SEARCH_BOOKINGS:
				searchBookings();
				break;
			case SORT_BOOKINGS:
				sortBookings();
				break;
			default:
				System.out.println("ERROR choice not recognised");
			}
			choice = getMenuItem();
		}
		System.out.println("\nThank you for using Rocky Catering Service Management System");
		System.out.println("Program Written by 12345678");
		System.exit(0);
			
	}

	private void enterBooking() {
		if (bookingCounter >= 10) {
			errorDailogMessage("Error - maximum bookings to be entered has been reached", "Enter Booking");
		} else {
			Booking booking = new Booking();

			getBookingName(booking);
			getGuests(booking);

			bookings[bookingCounter] = booking;
			bookingCounter++;
			System.out.println("Details for booking " + bookingCounter + " entered");
			displayAllBookings();
		}
	}

	private void getGuests(Booking booking) {
		String guest = guestDailogMessage();
		while (guest == null || guest.trim().length() == 0) {
			errorDailogMessage("Error - Number of guests cannot be blank", "Input Number of Guests");
			guest = guestDailogMessage();
		}
		int guestInt = Integer.parseInt(guest);
		while (guestInt < 10) {
			errorDailogMessage("Error - Number of guests must be at least 10", "Input Number of Guests");
			guest = guestDailogMessage();
			guestInt = Integer.parseInt(guest);
		}
		booking.setGuests(guestInt);
	}

	private String guestDailogMessage() {
		return JOptionPane.showInputDialog(jFrame, "Enter  the number of guests", "Input Number of Guests",
				JOptionPane.DEFAULT_OPTION);
	}

	private void getBookingName(Booking booking) {
		String name = bookingDailogMessage();

		while (name == null || name.trim().length() == 0) {
			errorDailogMessage("Error - Booking name cannot be blank", "Input Booking Name");
			name = bookingDailogMessage();
		}
		booking.setBookingName(name);
	}

	private String bookingDailogMessage() {
		return JOptionPane.showInputDialog(jFrame, "Please enter the booking name", "Input Booking Name",
				JOptionPane.DEFAULT_OPTION);
	}

	private void displayHeading() {
		System.out.printf("%-30s%-17s%-6s\n", "Booking Name", "Guests", "Charge");
	}


	private void displayAllBookings() {
		// check if there has been a booking entered
		if (bookingCounter == 0) {
			errorDailogMessage("Error - You must enter at least one booking", "Display All Bookings");
		} else {
			// display all of the entries entered
			displayHeading();
			for (Object booking : bookings) {
				if (booking != null) {
					displayBooking((Booking) booking);
				}

			}
		}

	}
	
	private void displayBooking(Booking booking) {

		System.out.printf("%-30s%-17s%-6s\n", booking.getBookingName(), booking.getGuests(),
				"$" + decimalFormat.format(booking.calculateCharge()));
	}

	private void displayStatistics() {
		// check if there has been a booking entered
		if (bookingCounter == 0) {
			errorDailogMessage("Error - You must enter at least one booking", "Display Statistics");
		} else {
			// display all of the entries entered
			System.out.println("Statistics for Rocky Catering Service");
			sortBookingOnGuests();
			int guestSum = 0;
			float chargeSum = 0;
			for (Object booking : bookings) {
				if (booking != null) {
					Booking bookingObj = (Booking) booking;
					guestSum += bookingObj.getGuests();
					chargeSum += bookingObj.calculateCharge();
					
				}

			}
			statistics((Booking)bookings[bookingCounter-1],"maximum");
			statistics((Booking)bookings[0],"minimum");
			System.out.println("Average number of guest is " + decimalFormat.format(guestSum / bookingCounter));
			System.out.println("The total charges are: $" + decimalFormat.format(chargeSum));
		}

	}
	
	private void sortBookingOnGuests(){
		Booking temp = null;
			// Sorting the bookings
		for (int i = 0; i < bookingCounter; i++) {
				for (int j = i + 1; j < bookingCounter; j++) {
					Booking bookingI = (Booking) bookings[i];
					Booking bookingJ = (Booking) bookings[j];
					if (bookingI.getGuests()>bookingJ.getGuests()) {
						temp = bookingI;
						bookings[i] = bookingJ;
						bookings[j] = temp;
					}
				}
			}
	}

	private void statistics(Booking booking,String minMax) {
		System.out.println(booking.getBookingName() + " has the "+minMax+" number of " + booking.getGuests() + " guests");
	}

	private void searchBookings() {
		// check if there has been a booking entered
		if (bookingCounter == 0) {
			errorDailogMessage("Error - You must enter at least one booking", "Search Bookings");
		} else {
			String searhKey = bookingDailogMessage();
			for (Object booking : bookings) {
				if (booking != null) {
					Booking bookingObj = (Booking) booking;
					if (bookingObj.getBookingName().equalsIgnoreCase(searhKey)) {
						System.out.println("Booking found");
						displayHeading();
						displayBooking(bookingObj);
						return;
					} else {
						String errorMessage = searhKey + " not found";
						JOptionPane.showMessageDialog(null, errorMessage, "Search Bookings",
								JOptionPane.WARNING_MESSAGE);
					}
				}

			}
		}
	}

	private void sortBookings() {
		if (bookingCounter < 2) {
			errorDailogMessage("Error - You must enter at least two booking to sort","Sort Bookings");
		} else {
			Booking temp = null;
			// Sorting the bookings
			for (int i = 0; i < bookingCounter; i++) {
				for (int j = i + 1; j < bookingCounter; j++) {
					Booking bookingI = (Booking) bookings[i];
					Booking bookingJ = (Booking) bookings[j];
					if (bookingI.getBookingName().compareTo(bookingJ.getBookingName()) > 0) {
						temp = bookingI;
						bookings[i] = bookingJ;
						bookings[j] = temp;
					}
				}
			}
			// displaying booking.
			System.out.println("Booking sorted");
			displayAllBookings();
		}

	}

	private void errorDailogMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	}

	public static void main(String[] args) {
		RockyCateringMenu app = new RockyCateringMenu();
		app.processBookings();
	}
}
