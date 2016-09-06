package ie.nco.ddd.lottery.infra.logging;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/**
 * Interface to inject logging in case of exceptions.
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
@InterceptorBinding
@Retention(RUNTIME)
@Target({ METHOD, TYPE })
public @interface TicketLoggerExcepton {

}