package com.regularExpression;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {
	 private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z][a-z]*");
//	    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
	    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^[6-9]\\d{9}$");
	    private static final Pattern CARD_NUMBER_PATTERN = Pattern.compile("^\\d{16}$");
	    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
	    private static final Pattern BALANCE_PATTERN = Pattern.compile("[0-9]+");
	    private static final Pattern PIN_PATTERN = Pattern.compile("^\\d{4}$");
	    private static final Pattern ADDRESS_PATTERN=Pattern.compile("^[\\w\\d/,.\\s-]+$");
	public boolean isValidName(String name) {
		Matcher matcher = NAME_PATTERN.matcher(name);
		return matcher.matches();
	}
	public boolean isValidNumber(long phoneNumbe) {
		Matcher matcher=PHONE_NUMBER_PATTERN.matcher( String.valueOf( phoneNumbe ));
		return matcher.matches();
	}
	public boolean isValidEmail(String mail) {
		Matcher matcher=EMAIL_PATTERN.matcher(mail);
		return matcher.matches();
	}
	public boolean isValidAmount(String amount) {
		Matcher matcher=BALANCE_PATTERN.matcher(amount);
		return matcher.matches();
	}
	public boolean isValidaccNumber(long number) {
		Matcher matcher=CARD_NUMBER_PATTERN.matcher( String.valueOf( number ));
		return matcher.matches();
	}
	public boolean isValidPin(String pin) {
		Matcher matcher=PIN_PATTERN.matcher(pin);
		return matcher.matches();
	}
	public boolean isValidAddress(String address) {
		Matcher matcher=ADDRESS_PATTERN.matcher(address);
		return matcher.matches();
	}
}
