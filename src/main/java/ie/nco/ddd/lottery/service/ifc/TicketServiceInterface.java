package ie.nco.ddd.lottery.service.ifc;

import java.util.List;

import ie.nco.ddd.lottery.domain.utils.LineOrder.SortOrderLine;
import ie.nco.ddd.lottery.interfaces.entity.TicketInterfaceDTO;

/**
 * Service interface to operate in a Ticket.
 * 
 * <i>As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.</i>
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
public interface TicketServiceInterface {

	public TicketInterfaceDTO generateTicketWithNLines(int numberLines);
	
	public TicketInterfaceDTO getStatusOfLinesOnATicket(Long ticketId, SortOrderLine sortOrder);
	
	public TicketInterfaceDTO amendTicketWithNAdditionalLines(Long ticketId, int numberLines);

	public List<Long> getAllTicketsId();

	public void deleteTicket(Long ticketId);
	
	public void deleteAllTickets();

}