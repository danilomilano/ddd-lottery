package ie.nco.ddd.lottery.service.ejb;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import ie.nco.ddd.lottery.service.ifc.TicketServiceInterface;

/**
 * Qualifier to ticket services. A new implementation of
 * {@link TicketServiceInterface} needs be annotated with this.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE })
@Qualifier
public @interface TicketServiceQualifier
{
}
