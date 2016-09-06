package ie.nco.ddd.lottery.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ie.nco.ddd.lottery.domain.ifc.LineInterface;
import ie.nco.ddd.lottery.domain.ifc.TicketInterface;
import ie.nco.ddd.lottery.domain.utils.LineFactory;
import ie.nco.ddd.lottery.domain.utils.LineOrder;
import ie.nco.ddd.lottery.domain.utils.TicketFactory;
import ie.nco.ddd.lottery.domain.utils.LineOrder.SortOrderLine;
import ie.nco.ddd.lottery.domain.utils.ResultLine.Result;

/**
 * Each instance of Line contains a specified Result, according to certain rules
 * applied in it series of numbers. A list of Lines can be ordered, according
 * the Result. This class test if the order returned by the TicketUtils class is
 * correct.
 * 
 * The ascending order, based in the results, is:
 * 
 * First: {@link Result#SUM_ALL_NUMBERS_IS_EQUALS_DEFAULT_VALUE}. 
 * Second: {@link Result#ALL_NUMBERS_WITH_SIMILAR_VALUE}. 
 * Third: {@link Result#ELEMENTS_DIFFERENT_FIRST_ELEMENT}. 
 * Fouth: {@link Result#NO_RULE}.
 * 
 * {@see Line} 
 * {@see LineUtils}
 */
public class LineOrderingTest 
{
	private TicketInterface ticketInterfaceToTestSorter;
	
	/**
	 * Line to test with the result = 10
	 * 
	 * {@link Result#SUM_ALL_NUMBERS_IS_EQUALS_DEFAULT_VALUE}. 
	 */
	private LineInterface lineSumAllElementsEqualsDefaultValue = LineFactory.createLineWithNumbers(new int[] { 0, 1, 1 });
	
	/**
	 * Line to test with the result = 5
	 * 
	 * {@link Result#ALL_NUMBERS_WITH_SIMILAR_VALUE}. 
	 */
	private LineInterface lineAllElementsSimilarValue = LineFactory.createLineWithNumbers(new int[] { 0, 0, 0 });
	
	/**
	 * Line to test with the result = 1
	 * 
	 * {@link Result#ELEMENTS_DIFFERENT_FIRST_ELEMENT 
	 */
	private LineInterface lineElementsDifferentFirstElement = LineFactory.createLineWithNumbers(new int[] { 0, 1, 2 });

	/**
	 * Line to test with the result = 0
	 * 
	 * {@link Result#NO_RULE}. 
	 */
	private LineInterface lineNoRule = LineFactory.createLineWithNumbers(new int[] { 1, 1, 2 });
	
	@Before
	public void createNoOrderListToTest() 
	{		
		// list created with result order = 1, 0, 10, 5
		List<LineInterface> noOrderlistToTest = new ArrayList<LineInterface>();
		noOrderlistToTest.add(lineElementsDifferentFirstElement);
		noOrderlistToTest.add(lineNoRule);
		noOrderlistToTest.add(lineSumAllElementsEqualsDefaultValue);
		noOrderlistToTest.add(lineAllElementsSimilarValue);
		
		ticketInterfaceToTestSorter = TicketFactory.generateTicketWithCreatedLines(noOrderlistToTest, 1L);
	}

	@Test
	public void verifyIfDescendingOrderIsCorrect()
	{
		// list created with default order = 10, 5, 1, 0
		List<LineInterface> orderlistExpected = new ArrayList<LineInterface>();
		orderlistExpected.add(lineSumAllElementsEqualsDefaultValue);	
		orderlistExpected.add(lineAllElementsSimilarValue);	
		orderlistExpected.add(lineElementsDifferentFirstElement);
		orderlistExpected.add(lineNoRule);
		
		assertEquals(orderlistExpected, LineOrder.orderList(ticketInterfaceToTestSorter.getLines(), SortOrderLine.DESCENDING));
	}
	
	@Test
	public void verifyIfAscendingOrderIsCorrect()
	{		
		// list created with default order = 0, 1, 5, 10
		List<LineInterface> orderlistExpected = new ArrayList<LineInterface>();
		orderlistExpected.add(lineNoRule);
		orderlistExpected.add(lineElementsDifferentFirstElement);
		orderlistExpected.add(lineAllElementsSimilarValue);
		orderlistExpected.add(lineSumAllElementsEqualsDefaultValue);
		
		assertEquals(orderlistExpected, LineOrder.orderList(ticketInterfaceToTestSorter.getLines(), SortOrderLine.ASCENDING));
	}
}
