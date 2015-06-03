package edu.toronto.cs.se.ci.random;
import java.util.Random;


public class Randomization {

	protected static int MIN_EMAIL_ADDRESS_LENGTH = 5;
	protected static int MAX_EMAIL_ADDRESS_LENGTH = 20;
	protected static int MIN_DOMAIN_LENGTH = 5;
	protected static int MAX_DOMAIN_LENGTH = 20;
	
	protected static String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
	protected static String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	protected static String PUNCTUATION = ",.;:'\"()!?";
	
	protected Random random;
	
	public Randomization(){
		random = new Random();
	}
	
	public Randomization(long seed){
		random = new Random(seed);
	}
	
	/**
	 * A random character in the ASCII range 32-126 inclusive.
	 * @return
	 */
	public char character(){
		int randInt = random.nextInt();
		if (randInt < 0) randInt *= -1;
		int normalized = 32 + (randInt % (126-32));
		if (normalized < 0) normalized *= -1;
		char toRet = (char) normalized;
		return toRet;
	}
	
	/**
	 * Returns a random string from the given alphabet
	 * @param alphabet
	 * @return
	 */
	public char character(String alphabet){
		return alphabet.charAt(integer(alphabet.length()));
	}
	
	/**
	 * Returns a random string.
	 * @param minLength - non negative
	 * @param maxLength - greater than zero
	 * @param alphabet - the set of characters to draw characters from
	 * @return
	 */
	public String string(int minLength, int maxLength, String alphabet){
		
		int length;
		if (minLength == maxLength){
			length = minLength;
		}
		else{
			length = minLength + random.nextInt(maxLength - minLength);
		}
		
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++){
			sb.append(character(alphabet));
		}
		return sb.toString();
		
	}
	
	/**
	 * Returns a random string, drawing from lowercase, uppercase, and punctuation.
	 * The distribution of characters is (approximately) 90% lower case, 5% spaces, 3% upper case, and 2% punctuation
	 * @param minLength - must be non-negative
	 * @param maxLength - must be greater than 0
	 * @return
	 */
	public String string(int minLength, int maxLength){

		//generate a string of only lowercase letter
		StringBuffer s = new StringBuffer(string(minLength, maxLength, LOWER_CASE));
		
		//sprinkle in upper case
		int upperCaseToAdd = (int) Math.ceil(s.length() * 0.03d);
		while (upperCaseToAdd > 0){
			int index = integer(s.length());
			s.setCharAt(index, character(UPPER_CASE));
			upperCaseToAdd--;
		}
		
		//sprinkle in punctuation
		int puncToAdd = (int) Math.ceil(s.length() * 0.02d);
		while (puncToAdd > 0){
			int index = integer(s.length());
			s.setCharAt(index, character(PUNCTUATION));
			puncToAdd--;
		}
		
		//sprinkle in spaces
		int spacesToAdd = (int) Math.ceil(s.length() * 0.02d);
		while (spacesToAdd > 0){
			int index = integer(s.length());
			s.setCharAt(index, ' ');
			spacesToAdd--;
		}
		
		return s.toString();
	}
	
	/**
	 * A random integer.
	 * @param bound - maximum value (inclusive bound)
	 * @return
	 */
	public int integer(int bound){
		if (bound == 0) return 0;
		return random.nextInt(bound);
	}
	
	/**
	 * A random integer.
	 * @param min - inclusive minimum value
	 * @param max - inclusive maximum value
	 * @return
	 */
	public int integer(int min, int max){
		return min + integer(max - min);
	}
	
	public long longNumber(){
		return random.nextLong();
	}
	
	/**
	 * Returns a randomly generated phone number in the format <areaCode>-xxx-xxxx
	 */
	public String phoneNumber(String areaCode){
		return String.format("%s-%s-%s",
				areaCode,
				String.valueOf(integer(100, 999)),
				String.valueOf(integer(1000, 9999)));
	}
	
	/**
	 * Returns a randomly generated phone number in the format xxx-xxx-xxxx
	 */
	public String phoneNumber(){
		//randomly create the area code
		return phoneNumber(
				String.valueOf(integer(100, 999)));
	}
	
	/**
	 * Returns a random email address in the format xxx@<domain>
	 * @param domain - A domain like "gmail.com" or "yahoo.co.uk"
	 */
	public String emailAddress(String domain){
		String peronsal = string(MIN_EMAIL_ADDRESS_LENGTH, MAX_EMAIL_ADDRESS_LENGTH);
		return peronsal + "@" + domain;
	}
	
	/**
	 * Returns a totally random email address in the format xxxx@xxxx.xxxx
	 */
	public String emailAddress(){
		String domain = domain();
		return emailAddress(domain);
	}
	
	/**
	 * Returns a random domain of the format xxx.yyy
	 * The first part of the domain will have a length bounded by MIN_DOMAIN_LENGTH, MAX_DOMAIN_LENGTH.
	 * The last part will have length 2 or 3.
	 * @return
	 */
	public String domain(){
		return string(MIN_DOMAIN_LENGTH, MAX_DOMAIN_LENGTH) + 
				string(2, 3);
	}
	
	/**
	 * Returns a random website URL
	 * @return
	 */
	public String website(){
		return String.format("http://%s/", domain());
	}
	
	/**
	 * Returns a random string of the format xxx xxx
	 * @return
	 */
	public String postalCode(){
		return string(3,3) + " " + string(3,3);
	}
	
	public static int getMIN_EMAIL_ADDRESS_LENGTH() {
		return MIN_EMAIL_ADDRESS_LENGTH;
	}

	public static void setMIN_EMAIL_ADDRESS_LENGTH(int mIN_EMAIL_ADDRESS_LENGTH) {
		MIN_EMAIL_ADDRESS_LENGTH = mIN_EMAIL_ADDRESS_LENGTH;
	}

	public static int getMAX_EMAIL_ADDRESS_LENGTH() {
		return MAX_EMAIL_ADDRESS_LENGTH;
	}

	public static void setMAX_EMAIL_ADDRESS_LENGTH(int mAX_EMAIL_ADDRESS_LENGTH) {
		MAX_EMAIL_ADDRESS_LENGTH = mAX_EMAIL_ADDRESS_LENGTH;
	}

	public static int getMIN_DOMAIN_LENGTH() {
		return MIN_DOMAIN_LENGTH;
	}

	public static void setMIN_DOMAIN_LENGTH(int mIN_DOMAIN_LENGTH) {
		MIN_DOMAIN_LENGTH = mIN_DOMAIN_LENGTH;
	}

	public static int getMAX_DOMAIN_LENGTH() {
		return MAX_DOMAIN_LENGTH;
	}

	public static void setMAX_DOMAIN_LENGTH(int mAX_DOMAIN_LENGTH) {
		MAX_DOMAIN_LENGTH = mAX_DOMAIN_LENGTH;
	}

}
