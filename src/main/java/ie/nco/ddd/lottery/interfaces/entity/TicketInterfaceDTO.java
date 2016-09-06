package ie.nco.ddd.lottery.interfaces.entity;

import java.util.List;

import ie.nco.ddd.lottery.domain.ifc.LineInterface;
import ie.nco.ddd.lottery.domain.ifc.TicketInterface;

/**
 * DTO to transform a Ticket in a external representation, created in the
 * service layer.
 * 
 * <i>As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.</i>
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
public class TicketInterfaceDTO {

	private List<LineInterface> linesInTicket;
	
	private Long ticketId;
	
	public TicketInterfaceDTO(TicketInterface ticketInterface) {
		linesInTicket = ticketInterface.getLines();
		ticketId = ticketInterface.getId();
	}

	public List<LineInterface> getLines() {
		return linesInTicket;
	}
	
	public void setLines(List<LineInterface> lines) {
		this.linesInTicket = lines;
	}

	public Long getId() {
		return ticketId;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getId());
		sb.append(" = ");
		sb.append(linesInTicket);
		return sb.toString();
	}
}
