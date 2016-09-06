package ie.nco.ddd.lottery.interfaces;
import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ie.nco.ddd.lottery.domain.utils.LineOrder.SortOrderLine;

/**
 * Class to test the rest api service. Using {@link ResteasyClient} to
 * facilitate the invoking of http methods.
 * 
 * <i>As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.</i>
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */
public class TicketRestServiceTest {

	private static ResteasyClient clientToSendCommands = null;
	
	private static URI defaultClientLocation = null;
	
	private static String DEFAULT_CLIENT_LINK = "http://localhost:8080/ddd-lottery/rest/tickets";
	
	@Before
	public void createRestClient() throws URISyntaxException {		
		clientToSendCommands = new ResteasyClientBuilder().build();
		defaultClientLocation = new URI(DEFAULT_CLIENT_LINK);
	}
	
	@Test
	public void generateTicketSuccessful() {
		Response response = generateTicket();
		assertEquals(Status.CREATED.getStatusCode(), response.getStatus());
		deleteTicket(response.getLocation());
	}
	
	@Test
	public void generateTicketWithNumberLinesSuccessful() throws URISyntaxException {
		URI uri = new URI(defaultClientLocation + "?numberLines=2");
		TicketRestServiceTestEntity entityRest = new TicketRestServiceTestEntity.TicketRestEntityBuilder().location(uri).httpMethod(HttpMethod.POST)
				.createEntityRest();
		
		Response response = callRestService(entityRest);
		assertEquals(Status.CREATED.getStatusCode(), response.getStatus());
		deleteTicket(response.getLocation());
	}
	
	@Test
	public void getListTicketIdsSuccessful() {
		Response generateTicketResponse = generateTicket();
		
		TicketRestServiceTestEntity entityRest = new TicketRestServiceTestEntity.TicketRestEntityBuilder().location(defaultClientLocation).httpMethod(HttpMethod.GET)
				.createEntityRest();
		
		Response response = callRestService(entityRest);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		deleteTicket(generateTicketResponse.getLocation());
	}
	
	@Test
	public void getTicketSuccessful() {				
		Response generateTicketResponse = generateTicket();
		Response response = getTicketByLocation(generateTicketResponse.getLocation());

		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		deleteTicket(generateTicketResponse.getLocation());
	}

	@Test
	public void getTicketWithInvalidIdSuccessful() throws URISyntaxException {				
		Response response = getTicketByLocation(new URI(defaultClientLocation + "/0"));
		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}
	
	private Response getTicketByLocation(URI uri) {
		TicketRestServiceTestEntity entityRest = new TicketRestServiceTestEntity.TicketRestEntityBuilder().location(uri).httpMethod(HttpMethod.GET)
				.createEntityRest();
		return callRestService(entityRest);
	}
	
	@Test
	public void getTicketWithLinesInAscendingOrderSuccessful() throws URISyntaxException {
		getTicketWithLinesInOrder(SortOrderLine.ASCENDING);	
	}
	
	@Test
	public void getTicketWithLinesInDescendingOrderSuccessful() throws URISyntaxException {
		getTicketWithLinesInOrder(SortOrderLine.DESCENDING);	
	}

	@Test
	public void deleteTicketSuccessful () {
		Response response = deleteTicket(generateTicket().getLocation());
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void deleteTicketWithInvalidIdSuccessful() throws URISyntaxException {				
		Response response = deleteTicket(new URI(defaultClientLocation + "/0"));
		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void deleteAllsTicketsSuccessful() throws URISyntaxException {				
		Response response = deleteTicket(defaultClientLocation);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void amendTicketSuccessful() throws URISyntaxException {
		
		Response generateTicketResponse = generateTicket();
		
		URI uri = new URI(generateTicketResponse.getLocation().toString() + "?numberLines=6");
		TicketRestServiceTestEntity entityRest = new TicketRestServiceTestEntity.TicketRestEntityBuilder().location(uri).httpMethod(HttpMethod.PUT)
				.createEntityRest();
		
		Response response = callRestService(entityRest);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		
		deleteTicket(generateTicketResponse.getLocation());
	}
	
	@Test
	public void amendTicketWithoutNumberLinesParameterSuccessful() {
		
		Response generateTicketResponse = generateTicket();
		
		TicketRestServiceTestEntity entityRest = new TicketRestServiceTestEntity.TicketRestEntityBuilder().location(generateTicketResponse.getLocation()).httpMethod(HttpMethod.PUT)
				.createEntityRest();
		
		Response response = callRestService(entityRest);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		
		deleteTicket(generateTicketResponse.getLocation());
	}
	
	@Test
	public void amendTicketAfterReadStatusSuccessful() throws URISyntaxException {
		
		Response generateTicketResponse = generateTicket();
		getTicketByLocation(generateTicketResponse.getLocation());

		URI uri = new URI(generateTicketResponse.getLocation().toString());
		TicketRestServiceTestEntity entityRest = new TicketRestServiceTestEntity.TicketRestEntityBuilder().location(uri).httpMethod(HttpMethod.PUT)
				.createEntityRest();

		Response response = callRestService(entityRest);
		assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
		
		deleteTicket(generateTicketResponse.getLocation());
	}
	
	private Response generateTicket() {
		TicketRestServiceTestEntity entityRest = new TicketRestServiceTestEntity.TicketRestEntityBuilder().location(defaultClientLocation).httpMethod(HttpMethod.POST).createEntityRest();
		return callRestService(entityRest);
	}

	private Response deleteTicket(URI location) {
		TicketRestServiceTestEntity entityRest = new TicketRestServiceTestEntity.TicketRestEntityBuilder().location(location).httpMethod(HttpMethod.DELETE).createEntityRest();
		return callRestService(entityRest);
	}
	
	private void getTicketWithLinesInOrder(SortOrderLine sortOrder) throws URISyntaxException {		
		Response generateTicketResponse = generateTicket();
		
		URI uri = new URI(generateTicketResponse.getLocation().toString() + "?sortOrder=" + sortOrder.getOrder());
		Response response = getTicketByLocation(uri);		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		
		deleteTicket(generateTicketResponse.getLocation());
	}
	
	private Response callRestService(TicketRestServiceTestEntity ticketEntityRest) {
		ResteasyWebTarget resteasyWebTarget = clientToSendCommands.target(ticketEntityRest.getLocation());
		Response response = null;		
		try {
			if (ticketEntityRest.getHttpMethod().equals(HttpMethod.GET)) {
				response = resteasyWebTarget.request().get();		
			} else if (ticketEntityRest.getHttpMethod().equals(HttpMethod.POST)) {
				response = resteasyWebTarget.request().post(ticketEntityRest.getEntity());
			} else if (ticketEntityRest.getHttpMethod().equals(HttpMethod.PUT)) {
				response = resteasyWebTarget.request().put(ticketEntityRest.getEntity());
			} else if (ticketEntityRest.getHttpMethod().equals(HttpMethod.DELETE)) {
				response = resteasyWebTarget.request().delete();
			}
			return response;
		}
		finally {
			Optional.ofNullable(response).ifPresent(r -> r.close());
		}
	}
	
	@After
	public void finalizeRestClient() {
		clientToSendCommands.close();
	}
}