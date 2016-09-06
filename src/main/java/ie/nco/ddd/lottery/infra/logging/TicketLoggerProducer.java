 package ie.nco.ddd.lottery.infra.logging;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;


/**
 * Class created to use log by CDI.
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
public class TicketLoggerProducer {
    @Produces
    private Logger createLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
