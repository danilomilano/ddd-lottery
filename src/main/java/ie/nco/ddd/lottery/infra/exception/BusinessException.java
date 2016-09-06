package ie.nco.ddd.lottery.infra.exception;

/**
 * Exception to be used in business errors.
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -8619179964771607812L;

	public BusinessException(String message) {
		super(message);
	}
}
