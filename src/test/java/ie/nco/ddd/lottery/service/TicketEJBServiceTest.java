package ie.nco.ddd.lottery.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.Before;
import org.junit.Test;

import ie.nco.ddd.lottery.domain.utils.LineOrder.SortOrderLine;
import ie.nco.ddd.lottery.interfaces.entity.TicketInterfaceDTO;
import ie.nco.ddd.lottery.service.ifc.TicketServiceInterface;

/**
 * Class to test the rest ejb service, starting a container using openejb. As
 * the container is finished after all tests, isn't necessary to delete the
 * tickets generated.
 * 
 * <i>As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.</i>
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
public class TicketEJBServiceTest {
	
    private TicketServiceInterface ticketEJBService;

    @Before
    public void createEJBContainer() throws Exception {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
        Context context = new InitialContext(props);
        ticketEJBService = (TicketServiceInterface) context.lookup("java:global/classpath.ear/ddd-lottery/TicketEJBService!ie.nco.ddd.lottery.service.ifc.TicketServiceInterface");
    }
    
    @Test
	public void containerEjbCreatedSuccessful() {
        assertNotNull(ticketEJBService);
    }

    @Test
    public void generateTicketWithNLinesSuccessful() {
        assertNotNull(generateTicket());
    }
    
    @Test
    public void getTicketsIdsSuccessful() {
    	generateTicket();
        assertFalse(ticketEJBService.getAllTicketsId().isEmpty());
    }
    
    @Test
    public void getStatusOfLinesOnATicketSuccessful() {
    	TicketInterfaceDTO ticket = generateTicket();
        assertNotNull(ticketEJBService.getStatusOfLinesOnATicket(ticket.getId(), SortOrderLine.ASCENDING));
    }
    
    @Test
    public void amendTicketWithNAdditionalLinesSuccessful() {
    	
    	int numberOfLinesToAdd = 3;
    	TicketInterfaceDTO ticket = generateTicket();
    	int originalLength = ticket.getLines().size();
    	
    	ticketEJBService.amendTicketWithNAdditionalLines(ticket.getId(), numberOfLinesToAdd);
    	assertTrue(originalLength + numberOfLinesToAdd == ticket.getLines().size());
    }
    
    @Test
    public void deleteTicketSuccessful() {
    	TicketInterfaceDTO ticket = generateTicket();
    	ticketEJBService.deleteTicket(ticket.getId());
    	List<Long> ticketIds = ticketEJBService.getAllTicketsId();
    	assertFalse(ticketIds.contains(ticket.getId()));
    }
    
    private TicketInterfaceDTO generateTicket() {
    	return ticketEJBService.generateTicketWithNLines(3);
    }
}