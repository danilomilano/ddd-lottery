package ie.nco.ddd.lottery.infra.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.enterprise.inject.Alternative;

import ie.nco.ddd.lottery.domain.ifc.TicketInterface;
import ie.nco.ddd.lottery.domain.ifc.TicketRepositoryInterface;
import ie.nco.ddd.lottery.infra.exception.ValidationException;

/**
 * Repository that represents tickets in memory. The repository is unique (
 * {@link Singleton}) and was created with {@link Alternative}.
 * 
 * The repository control concurrency using the {@link ConcurrencyManagement}
 * and the methods that change the set of tickets lock the entity (annotated
 * with {@link LockType.WRITE)}
 * 
 * <i>As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.</i>
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
@Singleton
@Alternative
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.READ)
public class TicketRepositoryInMemory implements TicketRepositoryInterface {

	private Map<Long, TicketInterface> ticketsInMemory; 
	
	public TicketRepositoryInMemory() {
		ticketsInMemory = new HashMap<Long, TicketInterface>();		
	}

	@Lock(LockType.WRITE)
	@Override
	public Long getNextId() {
		return new Long(ticketsInMemory.size()+1);
	}

	@Override
	public List<Long> getAllIds() {
		return new ArrayList<Long>(ticketsInMemory.keySet());
	}

	@Lock(LockType.WRITE)
	@Override
	public void insert(TicketInterface ticketInterface) {
		ticketsInMemory.put(ticketInterface.getId(), ticketInterface);
	}
	
	@Lock(LockType.WRITE)
	@Override
	public void update(Long id, TicketInterface ticketInterface) {
		ticketsInMemory.put(id, ticketInterface);
	}	

	@Override
	public void delete(TicketInterface ticketInterface) {
		ticketsInMemory.remove(ticketInterface.getId());
	}
	
	@Override
	public void deleteAll() {
		ticketsInMemory.clear();
	}
	
	@Override
	public TicketInterface findById(Long id) {
		TicketInterface ticket = ticketsInMemory.get(id);
		
		if (null == ticket) {
			throw new ValidationException("Object Not Found. Invalid identifier: " + id);
		}
		
		return ticket;
	}
}
