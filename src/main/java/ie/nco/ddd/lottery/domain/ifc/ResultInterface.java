package ie.nco.ddd.lottery.domain.ifc;

import ie.nco.ddd.lottery.domain.utils.ResultLine.Result;

/**
 * Interface for a Result, encapsulating the implementation in all the project.
 * Similar with {@link LineInterface}, is treated as VO (Value Object) not
 * permitting to change values with set* methods.
 * 
 * <i>As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.</i>
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
public interface ResultInterface
{
	Result getValue();
}
