package ie.nco.ddd.lottery.infra.exception;

/**
 * Exception to be used in errors occurred in validation of input.
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
public class ValidationException extends BusinessException {

	private static final long serialVersionUID = 2333896849603700499L;

	public ValidationException(String message) {
		super(message);
	}	
}
