package ie.nco.ddd.lottery.service.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ie.nco.ddd.lottery.domain.utils.LineOrder.SortOrderLine;
import ie.nco.ddd.lottery.interfaces.entity.TicketInterfaceDTO;
import ie.nco.ddd.lottery.service.ifc.TicketServiceInterface;
import ie.nco.ddd.lottery.service.utils.TicketController;

/**
 * EJB responsible to operate in a ticket, calling {@link TicketController}.
 * 
 * Marked as stateless to not maintain a conversational state.  
 * 
 * As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.
 * 
 * @see TicketServiceInterface
 * @see TicketController
 */
@Stateless
@TicketServiceQualifier
public class TicketEJBService implements TicketServiceInterface {
	
    @Inject
    TicketController ticketController; 
    
    @Override
	public TicketInterfaceDTO generateTicketWithNLines(int numberLines) {
		return ticketController.generateTicketWithNLines(numberLines);
	}
	
	@Override
	public TicketInterfaceDTO getStatusOfLinesOnATicket(Long ticketId, SortOrderLine sortOrder) {
		return ticketController.getStatusOfLinesOnATicket(ticketId, sortOrder);
	}
	
	@Override
	public TicketInterfaceDTO amendTicketWithNAdditionalLines(Long ticketId, int numberLines) {
		return ticketController.amendTicketWithNAdditionalLines(ticketId, numberLines);
	}

	@Override
	public void deleteTicket(Long ticketId) {
		ticketController.deleteTicket(ticketId);
	}
	
	@Override
	public List<Long> getAllTicketsId() {
		return ticketController.getAllTicketsId();
	}

	@Override
	public void deleteAllTickets() {
		ticketController.deleteAllTickets();
	}
}
