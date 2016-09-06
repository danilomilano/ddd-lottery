package ie.nco.ddd.lottery.domain.utils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import ie.nco.ddd.lottery.domain.ifc.LineInterface;

/**
 * Class useful for order lines for results. The method to sort could be direct
 * in Line (Line implementing {@link Comparable}), but was preferred create a
 * class to grouping all informations about ordering.
 * 
 * <i>As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.</i>
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
public class LineOrder {

	/**
	 * Result is an enumeration of the possible type of ordering. Created to
	 * facilitate tests and reference in other classes.
	 * 
	 * @author Danilo de Milano (danilo.milano@gmail.com)
	 */
	public enum SortOrderLine {
	    ASCENDING(0), DESCENDING(1);
		
		private int order;
		
		private SortOrderLine(int order) {
			this.order =  order;
		}
		
		public int getOrder() {
			return order;
		}
		
		public static SortOrderLine getSortOrderByType(int type) {
			for (SortOrderLine sortOrder : SortOrderLine.values()) {
				if (type == sortOrder.getOrder()) {
					return sortOrder;	            	
				}
			}				

			// send exception as sortOrder was not found
			throw new ValidationException("Invalid parameter.");
		}
	}
	
	private static List<LineInterface> orderLineListByComparator(List<LineInterface> lines, Comparator<LineInterface> comparator) {
		return lines.stream().sorted(comparator).collect(Collectors.toList());
	}
	
	public static List<LineInterface> orderList(List<LineInterface> lines, SortOrderLine sortOrder) {		
		Comparator<LineInterface> lineComparator = (p1, p2) -> Integer.compare(p1.getResult().getValue().getValue(), p2.getResult().getValue().getValue());
		
		List<LineInterface> ordenedList = lines; 
				
		if (null != sortOrder) {
			if (sortOrder.equals(SortOrderLine.ASCENDING)) {
				ordenedList = LineOrder.orderLineListByComparator(lines, lineComparator);
			}
			else if (sortOrder.equals(SortOrderLine.DESCENDING)) {
				ordenedList = LineOrder.orderLineListByComparator(lines, lineComparator.reversed());
			} 
		}
        
		return ordenedList;		
	}
	
}
