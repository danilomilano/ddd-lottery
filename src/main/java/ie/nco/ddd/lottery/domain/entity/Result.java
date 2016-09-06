package ie.nco.ddd.lottery.domain.entity;

import ie.nco.ddd.lottery.domain.ifc.ResultInterface;
import ie.nco.ddd.lottery.domain.utils.ResultLine;

/**
 * Class representing a concrete Result.
 * 
 * <i>As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.</i>
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
public class Result implements ResultInterface {

	private ie.nco.ddd.lottery.domain.utils.ResultLine.Result result;
	
	public Result(int[] numbers) {
		this.result = ResultLine.generateResultBasedInRules(numbers);
	}

	@Override
	public ie.nco.ddd.lottery.domain.utils.ResultLine.Result getValue() {
		return result;
	}

	@Override
	public String toString() {
		return String.valueOf(getValue());
	}
}
