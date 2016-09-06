package ie.nco.ddd.lottery.interfaces;

import java.net.URI;

import javax.ws.rs.client.Entity;

/**
 * Class to facilitate the test of rest service. Created with a builder.
 * 
 * <i>As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.</i>
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
public final class TicketRestServiceTestEntity {
	
	private Entity<Object> entity;
	private URI location; 
	private String httpMethod;
	
	private TicketRestServiceTestEntity(Entity<Object> entity, URI location, String httpMethod) {
		this.entity = entity;
		this.location = location;
		this.httpMethod = httpMethod;
	}
	
	public URI getLocation() {
		return location;
	}
	
	public Entity<Object> getEntity() {
		return entity;
	}
	
	public String getHttpMethod() {
		return httpMethod;
	}
	
	public static class TicketRestEntityBuilder {
		
		private Entity<Object> entity;
		private URI location;
		private String httpMethod;

		public TicketRestEntityBuilder entity(Entity<Object> entity) {
			this.entity = entity;
			return this;
		}
		
		public TicketRestEntityBuilder location(URI location) {
			this.location = location;
			return this;
		}
		
		public TicketRestEntityBuilder httpMethod(String httpMethod) {
			this.httpMethod = httpMethod;
			return this;
		}

		public TicketRestServiceTestEntity createEntityRest() {
			return new TicketRestServiceTestEntity(entity, location, httpMethod);
		}
	}	
}