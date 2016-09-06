package ie.nco.ddd.lottery.domain.utils;

import java.util.Arrays;

import ie.nco.ddd.lottery.domain.entity.Line;

/**
 * Class useful for calculate the result of a line, based in rules applied in
 * its results. The code could be in {@link Line}, but was preferred create a
 * class to grouping all informations about resulting.  
 * 
 * <i>As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.</i>
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
public class ResultLine 
{	
	private static final int DEFAULT_VALUE_TO_VALIDATE_SUM_ALL_ELEMENTS = 2;
	
	/**
	 * Result is an enumeration of the possible results of a set of numbers
	 * (elements in the line). Created to facilitate tests and reference in
	 * other classes.
	 * 
	 * @author Danilo de Milano (danilo.milano@gmail.com)
	 */
	public enum Result
	{
		SUM_ALL_NUMBERS_IS_EQUALS_DEFAULT_VALUE (10),
		ALL_NUMBERS_WITH_SIMILAR_VALUE (5), 
		ELEMENTS_DIFFERENT_FIRST_ELEMENT (1),
		NO_RULE (0);
		
		private int value;
		
		private Result(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}	

	public static Result generateResultBasedInRules(int[] numbers) {
		
		if (isSumNumbersEqualsDefaultValue(numbers)) {
			return Result.SUM_ALL_NUMBERS_IS_EQUALS_DEFAULT_VALUE;
		} else if (isNumbersWithSimilarValue(numbers)) {
			return Result.ALL_NUMBERS_WITH_SIMILAR_VALUE;
		} else if (isNumbersDifferentFromFirstNumber(numbers)) {
			return Result.ELEMENTS_DIFFERENT_FIRST_ELEMENT;
		} else {
			return Result.NO_RULE;
		}
	}

	private static boolean isSumNumbersEqualsDefaultValue(int[] numbers) {
		return Arrays.stream(numbers).sum() == DEFAULT_VALUE_TO_VALIDATE_SUM_ALL_ELEMENTS;
	}

	private static boolean isNumbersWithSimilarValue(int[] numbers) {
    	return Arrays.stream(numbers).allMatch(s -> s == numbers[0]);       	
	}

	private static boolean isNumbersDifferentFromFirstNumber(int[] numbers) {
		return Arrays.stream(numbers, 1, numbers.length).allMatch(s -> s != numbers[0]);
	}
	
}
