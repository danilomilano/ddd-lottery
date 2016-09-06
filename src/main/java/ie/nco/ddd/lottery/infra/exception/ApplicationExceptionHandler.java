package ie.nco.ddd.lottery.infra.exception;

import javax.ejb.EJBException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ie.nco.ddd.lottery.infra.logging.TicketLoggerExcepton;

/**
 * Handler to transform system exceptions in http exceptions, with the corrected
 * status
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
@Provider
@TicketLoggerExcepton
public class ApplicationExceptionHandler implements ExceptionMapper<EJBException>
{
	@Override
	public Response toResponse(EJBException ex) {
		
		Status status = Status.INTERNAL_SERVER_ERROR;
		
		String message = ex.getMessage(); 
		Exception causedByException = ((EJBException) ex).getCausedByException();
		
		if (null != causedByException) {
			message = causedByException.getMessage();

			if (causedByException instanceof ValidationException) {
				status = Status.BAD_REQUEST;				
			}
		}

		return Response.status(status).entity(message).build();
	}
}