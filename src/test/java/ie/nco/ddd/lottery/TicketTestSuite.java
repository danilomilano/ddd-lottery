package ie.nco.ddd.lottery;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ie.nco.ddd.lottery.domain.LineOrderingTest;
import ie.nco.ddd.lottery.domain.LineResultTest;
import ie.nco.ddd.lottery.domain.TicketEntityTest;
import ie.nco.ddd.lottery.interfaces.TicketRestServiceTest;
import ie.nco.ddd.lottery.service.TicketEJBServiceTest;

/**
 * Execute suite with all tests generated to the project.
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ LineResultTest.class, LineOrderingTest.class, TicketEntityTest.class, TicketEJBServiceTest.class,
		TicketRestServiceTest.class })
public class TicketTestSuite {
}
