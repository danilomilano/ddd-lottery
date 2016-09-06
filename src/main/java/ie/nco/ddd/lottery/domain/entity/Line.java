package ie.nco.ddd.lottery.domain.entity;

import java.util.Arrays;

import ie.nco.ddd.lottery.domain.ifc.LineInterface;
import ie.nco.ddd.lottery.domain.ifc.ResultInterface;

/**
 * Class representing a concrete Line. Line is a value object.
 * 
 * <i>As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.</i>
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
public class Line implements LineInterface {

	private int[] numbers;
	
	private ResultInterface result;
	
	public Line(int[] numbers, ResultInterface result) {
		this.numbers = numbers;		
		this.result = result;
	}
	
	@Override
	public int[] getNumbers() {
		return numbers;
	}
	
	@Override
	public ResultInterface getResult() {
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Arrays.toString(getNumbers()));
		sb.append(" = ");
		sb.append(getResult());
		return sb.toString();
	}
}
