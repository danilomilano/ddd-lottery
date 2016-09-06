package ie.nco.ddd.lottery.domain.ifc;

/**
 * Interface for a Line, encapsulating the implementation in all the project. As
 * Line is a VO (Value Object) it doesn't permit to change the values with set*
 * methods.
 * 
 * <i>As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.</i>
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
public interface LineInterface
{
	int[] getNumbers();
	
	ResultInterface getResult();
}
