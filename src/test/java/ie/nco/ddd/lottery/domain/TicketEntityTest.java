package ie.nco.ddd.lottery.domain;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import ie.nco.ddd.lottery.domain.ifc.TicketInterface;
import ie.nco.ddd.lottery.domain.ifc.TicketInterface.Status;
import ie.nco.ddd.lottery.domain.utils.LineFactory;
import ie.nco.ddd.lottery.domain.utils.TicketFactory;
import ie.nco.ddd.lottery.infra.exception.BusinessException;

/**
 * Class to test functionalities related to the entity {@link TicketInterface}.
 * 
 * <i>As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.</i>
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
public class TicketEntityTest 
{
    @Test
    public void validateAmmendLines() {
    	TicketInterface ticket = TicketFactory.generateTicketWithNLines(3, 1L);
    	ticket.addLines(LineFactory.createLineListWithRandomNumbers(3));
    	assertEquals(ticket.getLines().size(), 6);
    }
    
    @Test
    public void validateAmmendLinesAfterChangeStatus() {
    	TicketInterface ticket = TicketFactory.generateTicketWithNLines(3, 1L);
		ticket.setStatus(Status.CHECKED);
    	
		boolean isAmendAfterReadStatus = true;
		try {
			ticket.addLines(LineFactory.createLineListWithRandomNumbers(1));
		} 
		catch (BusinessException ex) {
			isAmendAfterReadStatus = false;
		}
		assertFalse(isAmendAfterReadStatus);
    }
}