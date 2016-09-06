package ie.nco.ddd.lottery.domain.ifc;

import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Alternative;

/**
 * Interface to a repository, representing collection of Tickets.
 * 
 * The repository might be created with the annotation {@link Alternative}. It
 * permits to create other representations of repositories, as a
 * TicketRepositoryInDataBase (tickets in a database) or a TicketRepositoryFile
 * (tickets in a file). To resolve what implementation will be used is necessary
 * put a reference on file beans.xml.
 * 
 * As the repository hidden the storage mode, the interface help to hidden other
 * boundaries (as a set of tickets in a ({@link Map} per example))
 * 
 * <i>As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.</i>
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
public interface TicketRepositoryInterface {
	
	TicketInterface findById(Long id);
	
	List<Long> getAllIds();
	
	Long getNextId();
	
	void insert(TicketInterface ticketInterface);
	
	void delete(TicketInterface ticketInterface);
	
	void update(Long id, TicketInterface ticketInterface);

	void deleteAll();	
}