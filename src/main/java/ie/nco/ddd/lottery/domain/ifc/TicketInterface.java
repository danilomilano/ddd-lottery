package ie.nco.ddd.lottery.domain.ifc;

import java.util.List;

/**
 * Interface for a ticket, encapsulating the instance in all the project.
 * 
 * <i>As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.</i>
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
public interface TicketInterface {

	/**
	 * Status is an enumeration of the different status of a ticket. A ticket
	 * start as {@link #NON_CHECKED} and change to {@link #CHECKED} when is
	 * readed.
	 * 
	 * (Other possible implementation is using either Status or Observing
	 * Pattern, recommended if the system change to send new events from
	 * different places)
	 * 
	 * @author Danilo de Milano (danilo.milano@gmail.com)
	 */
	enum Status {
		CHECKED, NON_CHECKED;
	}
	
	Long getId();
	
	void addLines(List<LineInterface> lines);	

	List<LineInterface> getLines();

	void setStatus(Status checked);	
}
