/**
 * 
 */


/**
 * @author 
 *
 */
public class Booking {
	private String bookingName;
	private int guests;

	final float TEN_TO_TWENTY_GUEST_CHARGE = 29.50f;
	final float TWENTY_TO_FOURTY_GUEST_CHARGE = 24.50f;
	final float ABOVE_FOURTY_GUEST_CHARGE = 19.50f;

	public Booking() {

	}

	public Booking(String bookingName, int guests) {
		super();
		this.bookingName = bookingName;
		this.guests = guests;
	}

	public String getBookingName() {
		return bookingName;
	}

	public int getGuests() {
		return guests;
	}

	public void setBookingName(String bookingName) {
		this.bookingName = bookingName;
	}

	public void setGuests(int guests) {
		this.guests = guests;
	}

	public float calculateCharge() {
		// checking the guests if between 10-20
		if (guests > 10 && guests <= 20) {
			return guestsCharge(guests, TEN_TO_TWENTY_GUEST_CHARGE);
		}
		// checking the guest if between 20-40
		if (guests > 20 && guests <= 40) {
			int remainingGuest = guests - 20;
			float twentyGuestCharge = guestsCharge(20, TEN_TO_TWENTY_GUEST_CHARGE);
			float remainingGuestCharge = guestsCharge(remainingGuest, TWENTY_TO_FOURTY_GUEST_CHARGE);
			return twentyGuestCharge + remainingGuestCharge;
		} else {
			//above 40 
			int remainingGuest = guests - 40;
			float twentyGuestCharge = guestsCharge(20, TEN_TO_TWENTY_GUEST_CHARGE);
			float twentyToFourtyGuestCharge = guestsCharge(20, TWENTY_TO_FOURTY_GUEST_CHARGE);
			float remainingGuesetCharge = guestsCharge(remainingGuest, ABOVE_FOURTY_GUEST_CHARGE);
			return twentyGuestCharge + twentyToFourtyGuestCharge + remainingGuesetCharge;
		}
	}

	private float guestsCharge(int guests, float charge) {
		return guests * charge;
	}

}
