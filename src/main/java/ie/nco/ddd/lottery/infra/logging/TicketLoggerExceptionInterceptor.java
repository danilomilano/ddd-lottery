package ie.nco.ddd.lottery.infra.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Interceptor to inject log in case of exceptions using AOP. Any class or
 * method with the annotation {@link @TicketLoggerExcepton} will be logged though this
 * class.
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
@Interceptor
@TicketLoggerExcepton
public class TicketLoggerExceptionInterceptor {

	@Inject
	private Logger log;
	
	@AroundInvoke
	public Object loggingInvocationMethod(InvocationContext invocationCtx) throws Exception {

		if (0 < invocationCtx.getParameters().length && invocationCtx.getParameters()[0] instanceof Exception) {
			Exception exception = (Exception) invocationCtx.getParameters()[0];
			log.log(Level.SEVERE, "", exception);
		}

		return invocationCtx.proceed();
	}
}