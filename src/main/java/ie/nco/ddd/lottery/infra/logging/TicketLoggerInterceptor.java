package ie.nco.ddd.lottery.infra.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Interceptor to inject log using AOP. Any class or method with
 * the annotation {@link @TicketLogger} will be logged through this class.
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
@Interceptor
@TicketLogger
public class TicketLoggerInterceptor {

	@Inject
	private Logger log;
	
	@AroundInvoke
	public Object loggingInvocationMethod(InvocationContext invocationCtx) throws Exception {

		StringBuilder parametersFromMethodInvocated = new StringBuilder("(");
		for (Object obj : invocationCtx.getParameters()) {
			parametersFromMethodInvocated.append(", ");
			parametersFromMethodInvocated.append(obj.toString());
		}
		parametersFromMethodInvocated.append(")");

		// remove the first comma of list, avoiding a if inside the last for 
		String parametersFormatted = parametersFromMethodInvocated.toString().replaceFirst(", ", "");		

		log.log(Level.INFO, "{0}.{1}{2} invocated.",
				new Object[] { invocationCtx.getMethod().getDeclaringClass().getName(),
						invocationCtx.getMethod().getName(), parametersFormatted });
		
		return invocationCtx.proceed();
	}
}