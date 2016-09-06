package ie.nco.ddd.lottery.domain.utils;

import java.util.List;

import ie.nco.ddd.lottery.domain.entity.Ticket;
import ie.nco.ddd.lottery.domain.ifc.LineInterface;
import ie.nco.ddd.lottery.domain.ifc.TicketInterface;

/**
 * Factory responsible for create Tickets.
 * 
 * <i>As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.</i>
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
public class TicketFactory {
	
	public static TicketInterface generateTicketWithNLines(int numberLines, Long ticketId) {		
		return generateTicketWithCreatedLines(LineFactory.createLineListWithRandomNumbers(numberLines), ticketId);		
	}
	
	public static TicketInterface generateTicketWithCreatedLines(List<LineInterface> lines, Long ticketId) {		
		return new Ticket(ticketId, lines);
	}
}
