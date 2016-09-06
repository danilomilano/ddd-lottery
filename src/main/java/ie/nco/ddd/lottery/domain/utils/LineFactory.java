package ie.nco.ddd.lottery.domain.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ie.nco.ddd.lottery.domain.entity.Line;
import ie.nco.ddd.lottery.domain.ifc.LineInterface;

/**
 * Factory responsible for create Lines.
 * 
 * <i>As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.</i>
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
public class LineFactory {
	
	private static int QUANTITY_OF_NUMBERS_IN_LINE = 3;
	
	private static int LOWER_BOUND_TO_CREATE_A_RANDOM_NUMBER = 0;
	
	private static int UPPER_BOUND_TO_CREATE_A_RANDOM_NUMBER = 2;
	
	private static int DEFAULT_NUMBER_OF_LINES_TO_CREATE = 3;
	
	public static LineInterface createLineWithNumbers(int[] numbers) {
		return new Line(numbers, ResultFactory.createResultWithNumbers(numbers));
	}
	
	private static LineInterface createLineWithRandomNumbers() {
		// as the upper bound for the Random.ints(..) is exclusive, is necessary add 1
		return createLineWithNumbers(new Random().ints(QUANTITY_OF_NUMBERS_IN_LINE, LOWER_BOUND_TO_CREATE_A_RANDOM_NUMBER, UPPER_BOUND_TO_CREATE_A_RANDOM_NUMBER + 1).toArray());
	}

	public static List<LineInterface> createLineListWithRandomNumbers(int numberLinesToCreate) {
		if (numberLinesToCreate <= 0) {
			numberLinesToCreate = DEFAULT_NUMBER_OF_LINES_TO_CREATE;
		}
		
		List<LineInterface> lineList = new ArrayList<LineInterface>();
		
		for (int i = 0; i < numberLinesToCreate; i++) {
			lineList.add(LineFactory.createLineWithRandomNumbers());
		}
		return lineList;
	}
}
