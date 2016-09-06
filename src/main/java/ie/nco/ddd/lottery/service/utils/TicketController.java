package ie.nco.ddd.lottery.service.utils;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ie.nco.ddd.lottery.domain.ifc.TicketInterface;
import ie.nco.ddd.lottery.domain.ifc.TicketRepositoryInterface;
import ie.nco.ddd.lottery.domain.ifc.TicketInterface.Status;
import ie.nco.ddd.lottery.domain.utils.LineFactory;
import ie.nco.ddd.lottery.domain.utils.LineOrder;
import ie.nco.ddd.lottery.domain.utils.TicketFactory;
import ie.nco.ddd.lottery.domain.utils.LineOrder.SortOrderLine;
import ie.nco.ddd.lottery.interfaces.entity.TicketInterfaceDTO;

/**
 * Controller to operate in a ticket, executing the business rules referred to
 * the domain. Created to abstracting repository and service
 * 
 * When necessary, this class reads {@link TicketInterface} from the repository
 * and returns {@link TicketInterfaceDTO} to the service.
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 * 
 * @see TicketInterface
 * @see TicketInterfaceDTO
 */
@Singleton
public class TicketController {

	/**
	 * Interface to ticket repository.
	 */
	@Inject
	TicketRepositoryInterface ticketRepository;
	
	/**
	 * Generate tickets with n lines, inserting it in the repository.
	 * 
	 * @param numberLines
	 *            number of lines to generate ticket
	 * @return ticket generated
	 */
    public TicketInterfaceDTO generateTicketWithNLines(int numberLines) {    	
    	TicketInterface ticketInterface = TicketFactory.generateTicketWithNLines(numberLines, ticketRepository.getNextId());
		ticketRepository.insert(ticketInterface);
		return new TicketInterfaceDTO(ticketInterface);
	}
	
    /**
     * Get status of line on a ticket, changing the status of a line ({@link Status}).
     *  
     * @param ticketId id from ticket to get data
     * @param sortOrderLine order to return lines
     * @return ticket found 
     * @see Status
     */
	public TicketInterfaceDTO getStatusOfLinesOnATicket(Long ticketId, SortOrderLine sortOrderLine) {
		TicketInterface ticketInterface = ticketRepository.findById(ticketId);
		ticketInterface.setStatus(Status.CHECKED);
		
		TicketInterfaceDTO ticketInterfaceDTO = new TicketInterfaceDTO(ticketInterface);		
		ticketInterfaceDTO.setLines(LineOrder.orderList(ticketInterfaceDTO.getLines(), sortOrderLine));
		
		return ticketInterfaceDTO;
	}
	
	/**
	 * Amend ticket with new lines, updating in repository
	 * 
	 * @param ticketId
	 *            id from ticket to amend
	 * @param numberLines
	 *            quantity of lines to created
	 * @return new ticket
	 */
	public TicketInterfaceDTO amendTicketWithNAdditionalLines(Long ticketId, int numberLines) {
		TicketInterface ticketInterface = ticketRepository.findById(ticketId);
		ticketInterface.addLines(LineFactory.createLineListWithRandomNumbers(numberLines));
		ticketRepository.update(ticketInterface.getId(), ticketInterface);
		return new TicketInterfaceDTO(ticketInterface);
	}
	
	/**
	 * Get all tickets id in repository
	 * 
	 * @return tickets id
	 */
	public List<Long> getAllTicketsId() {
		return ticketRepository.getAllIds();
	}

	/**
	 * Delete ticket by id
	 * 
	 * @param ticketId
	 *            ticket id to delete in repository
	 */
	public void deleteTicket(Long ticketId) {
		TicketInterface ticketInterface = ticketRepository.findById(ticketId);
		ticketRepository.delete(ticketInterface);
	}

	/**
	 * Delete all tickets
	 */
	public void deleteAllTickets() {
		ticketRepository.deleteAll();
	}
}
