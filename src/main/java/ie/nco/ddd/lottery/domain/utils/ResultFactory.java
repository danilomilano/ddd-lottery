package ie.nco.ddd.lottery.domain.utils;

import ie.nco.ddd.lottery.domain.entity.Result;
import ie.nco.ddd.lottery.domain.ifc.ResultInterface;

/**
 * Factory responsible for create Result.
 * 
 * <i>As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.</i>
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
public class ResultFactory {
	
	public static ResultInterface createResultWithNumbers(int[] numbers) {
		return new Result(numbers);
	}	
}
