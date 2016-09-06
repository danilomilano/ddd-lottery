package ie.nco.ddd.lottery.domain.entity;

import java.util.Collections;
import java.util.List;

import ie.nco.ddd.lottery.domain.ifc.LineInterface;
import ie.nco.ddd.lottery.domain.ifc.TicketInterface;
import ie.nco.ddd.lottery.infra.exception.BusinessException;

/**
 * Class representing a concrete Ticket. To enrich the model based in DDD, the
 * control of permit addLines based in {@link Status} is inside the class.
 * 
 * <i>As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.</i>
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 *
 */
public class Ticket implements TicketInterface {	

	private Status status = Status.NON_CHECKED;

	private Long id;

	private List<LineInterface> lines;	
	
	public Ticket(Long id, List<LineInterface> lines) {
		
		// avoid null in lines
		if (null == lines)
		{
			lines = Collections.emptyList();
		}
		
		this.id = id;		
		this.lines = lines;
	}

	@Override
	public void addLines(List<LineInterface> lines) {
		if (this.status.equals(Status.CHECKED)) {
			throw new BusinessException("Invalid Operation. Status already checked!");
		}
		
		this.lines.addAll(lines);
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public List<LineInterface> getLines() {
		return lines;
	}
	
	@Override
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getId());
		sb.append(" = ");
		sb.append(lines);
		return sb.toString();
	}
	
}
