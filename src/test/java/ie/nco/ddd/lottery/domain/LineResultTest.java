package ie.nco.ddd.lottery.domain;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ie.nco.ddd.lottery.domain.ifc.LineInterface;
import ie.nco.ddd.lottery.domain.utils.LineFactory;
import ie.nco.ddd.lottery.domain.utils.ResultLine.Result;

/**
 * Class to test if the Line object is being created with the Result correctly.
 * The Result is applied according certain rules.
 *
 * <i>As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.</i>
 * 
 * @author danilo (danilo.milano@gmail.com)
 * 
 * {@see Line}
 * {@see Result}
 */
@RunWith(Parameterized.class)
public class LineResultTest {

    private int[] numbers;
    private Result resultExpected;

    public LineResultTest(int[] numbers, Result expectedResult) {
        this.numbers = numbers;
        this.resultExpected = expectedResult;
    }

    @Parameters
    public static Collection<Object[]> allPossibleValuesToBeTested() 
    {
		// testing all possible values considering a matrix 3 x 3 = 27
		// combinations
		Object[][] data = new Object[][] { 
			{ new int[] { 0, 0, 2 }, Result.SUM_ALL_NUMBERS_IS_EQUALS_DEFAULT_VALUE }, 
			{ new int[] { 0, 1, 1 }, Result.SUM_ALL_NUMBERS_IS_EQUALS_DEFAULT_VALUE },
			{ new int[] { 0, 2, 0 }, Result.SUM_ALL_NUMBERS_IS_EQUALS_DEFAULT_VALUE },
			{ new int[] { 1, 0, 1 }, Result.SUM_ALL_NUMBERS_IS_EQUALS_DEFAULT_VALUE },	
			{ new int[] { 1, 1, 0 }, Result.SUM_ALL_NUMBERS_IS_EQUALS_DEFAULT_VALUE },
			{ new int[] { 2, 0, 0 }, Result.SUM_ALL_NUMBERS_IS_EQUALS_DEFAULT_VALUE },
			{ new int[] { 0, 0, 0 }, Result.ALL_NUMBERS_WITH_SIMILAR_VALUE },
			{ new int[] { 1, 1, 1 }, Result.ALL_NUMBERS_WITH_SIMILAR_VALUE },
			{ new int[] { 2, 2, 2 }, Result.ALL_NUMBERS_WITH_SIMILAR_VALUE },
			{ new int[] { 0, 1, 2 }, Result.ELEMENTS_DIFFERENT_FIRST_ELEMENT },
			{ new int[] { 0, 2, 1 }, Result.ELEMENTS_DIFFERENT_FIRST_ELEMENT },
			{ new int[] { 0, 2, 2 }, Result.ELEMENTS_DIFFERENT_FIRST_ELEMENT },
			{ new int[] { 1, 0, 0 }, Result.ELEMENTS_DIFFERENT_FIRST_ELEMENT },
			{ new int[] { 1, 0, 2 }, Result.ELEMENTS_DIFFERENT_FIRST_ELEMENT },
			{ new int[] { 1, 2, 0 }, Result.ELEMENTS_DIFFERENT_FIRST_ELEMENT },
			{ new int[] { 1, 2, 2 }, Result.ELEMENTS_DIFFERENT_FIRST_ELEMENT },
			{ new int[] { 2, 0, 1 }, Result.ELEMENTS_DIFFERENT_FIRST_ELEMENT },
			{ new int[] { 2, 1, 0 }, Result.ELEMENTS_DIFFERENT_FIRST_ELEMENT },
			{ new int[] { 2, 1, 1 }, Result.ELEMENTS_DIFFERENT_FIRST_ELEMENT },
			{ new int[] { 1, 1, 2 }, Result.NO_RULE },
			{ new int[] { 1, 2, 1 }, Result.NO_RULE },
			{ new int[] { 2, 0, 2 }, Result.NO_RULE },
			{ new int[] { 2, 1, 2 }, Result.NO_RULE },
			{ new int[] { 2, 2, 0 }, Result.NO_RULE },
			{ new int[] { 2, 2, 1 }, Result.NO_RULE },
			{ new int[] { 0, 0, 1 }, Result.NO_RULE },
			{ new int[] { 0, 1, 0 }, Result.NO_RULE }			
		};
        return Arrays.asList(data);
    }

    @Test
    public void verifyIfResultIsAccordingRule() 
    {
        LineInterface line = LineFactory.createLineWithNumbers(numbers);
        assertEquals(resultExpected.getValue(), line.getResult().getValue().getValue());
    }
}