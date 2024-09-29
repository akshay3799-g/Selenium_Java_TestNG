package utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomGenerator {

	public static Random rand = new Random();

	private RandomGenerator(){

	}

	/**
	 * To Generate Random Capitalized Letters of the Entered Length
	 * @param length
	 * @return String
	 */
	public static String GenerateRandomCapitalizedString(int length) {
		StringBuilder output = new StringBuilder(1000);
		output.append((char) (rand.nextInt(26) + 'A'));

		for (int i = 1; i < length; i++) {
			char c = (char) (rand.nextInt(26) + 'a');
			output.append(c);
		}
		return output.toString();
	}

	
	/**
	 * To Generate Random Numbers of the Entered Length
	 * @param length
	 * @return
	 */
	public static String GenerateRandomNumber(int length) {
		String string = "123456789";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(string.length());
			char randomChar = string.charAt(index);
			sb.append(randomChar);
		}
		return sb.toString();
	}

	
	/**
	 * To Generate Random Small Letters of the Entered Length
	 * @param length
	 * @return
	 */
	public static String GenerateRandomSmallLetters(int length) {
		String string = "abcdefghijklmopqrstuvwxyz";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(string.length());
			char randomChar = string.charAt(index);
			sb.append(randomChar);
		}
		return sb.toString();
	}

	/**
	 * To Generate Random Alpha-Numeric Characters of the Entered Length
	 * 
	 * @param length
	 * @return String
	 */
	public static String GenerateRandomAlphaNumericCharacters(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

	/**
	 * To Generate Random Capital Letters of the Entered Length
	 * 
	 * @param length
	 * @return String
	 */
	public static String GenerateRandomCapitalLetters(int length) {
		StringBuilder output = new StringBuilder(1000);
		output.append((char) (rand.nextInt(26) + 'A'));

		for (int i = 1; i < length; i++) {
			char c = (char) (rand.nextInt(26) + 'A');
			output.append(c);
		}
		return output.toString();
	}

	/**
	 * To Generate Random Alpha-Numeric Characters of the Entered Length
	 * 
	 * @param length
	 * @return String
	 */
	public static String GenerateRandomASCIICharacters(int length) {
		return RandomStringUtils.randomAscii(length);
	}

	/**
	 * To Generate Random E-Mail IDs(Auto Generate Domain Names
	 * 
	 * @return String
	 */
	public static String GenerateRandomEMAILIDs() {
		String EmailID = RandomStringUtils.randomAlphabetic(15);
		String Domain = RandomStringUtils.randomAlphabetic(7).toLowerCase();
		return EmailID + "@" + Domain + ".com";
	}

	/**
	 * To Generate Random E-Mail IDs
	 * 
	 * @param DomainName
	 * @return String
	 */
	public static String GenerateRandomEMAILIDs(String DomainName) {
		String EmailID = RandomStringUtils.randomAlphabetic(5).toLowerCase() + GenerateRandomNumber(4);
		return EmailID + "@" + DomainName;
	}
	
	/**
	 * This method generate random number in between
	 * 
	 * @param start
	 * @param end
	 * @return String
	 */
	public static String createRandomIntBetween(int start, int end) {
		int year = start + (int) Math.round(Math.random() * (end - start));
		return Integer.toString(year);
	}
	
	/**
	 * Generates a random number within the given range (inclusive of max)
	 * 
	 * @param min
	 * @param max
	 * @return int
	 */
	public static int getRandomNumberInRange(int min, int max) {
		max++;
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}
	
	/**
	 * Generates any random number wih given length other than number excluded
	 * 
	 * @param length
	 * @param exclude
	 * @return String
	 */
	public static String getRandomNumberWithExclusion(int length, String exclude) {
		String string = "0134";
		StringBuilder sb;
		Random random = new Random();
		do {
			sb = new StringBuilder();
			for (int i = 0; i < length; i++) {
				int index = random.nextInt(string.length());
				char randomChar = string.charAt(index);
				sb.append(randomChar);
			}
		} while (sb.toString().contains(exclude));
		return sb.toString();
	}

}
