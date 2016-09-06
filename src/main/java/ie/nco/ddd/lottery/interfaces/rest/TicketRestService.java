package ie.nco.ddd.lottery.interfaces.rest;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import ie.nco.ddd.lottery.domain.utils.LineOrder.SortOrderLine;
import ie.nco.ddd.lottery.infra.logging.TicketLogger;
import ie.nco.ddd.lottery.interfaces.entity.TicketInterfaceDTO;
import ie.nco.ddd.lottery.service.ejb.TicketServiceQualifier;
import ie.nco.ddd.lottery.service.ifc.TicketServiceInterface;

/**
 * Class REST to be a system interface to the client.
 * 
 * Using HATEOAS to facilitate the interaction with the API, without need a
 * specified documentation.
 * 
 * <i>As the methods are clear and easy to understand, was preferred not obscure
 * the code with Javadoc.</i>
 * 
 * @author Danilo de Milano (danilo.milano@gmail.com)
 */

@Path("/tickets")
@TicketLogger
public class TicketRestService  {

	@Inject
	@TicketServiceQualifier
    private TicketServiceInterface ticketService;
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateTicketWithNLines(@QueryParam("numberLines") int numberLines) { 
		TicketInterfaceDTO ticket = ticketService.generateTicketWithNLines(numberLines);			
		return Response.created(getCreatedLinkToAccessTicket(ticket.getId())).entity(ticket.getId()).build();
    }
	
	@GET
    @Path("{ticketId}")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TicketInterfaceDTO getStatusOfLinesOnATicket(@PathParam("ticketId") long ticketId, @QueryParam("sortOrder") int sortOrder) {
		TicketInterfaceDTO ticket = ticketService.getStatusOfLinesOnATicket(ticketId, SortOrderLine.getSortOrderByType(sortOrder));
		return ticket;
    }

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Long> getAllTicketsId() {
		return ticketService.getAllTicketsId();		
    }
	
    @PUT
    @Path("{ticketId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response amendTicketWithNAdditionalLines(@PathParam("ticketId") long ticketId, @QueryParam("numberLines") int numberLines) {
    	TicketInterfaceDTO ticket = ticketService.amendTicketWithNAdditionalLines(ticketId, numberLines);
		return Response.ok().contentLocation(getCreatedLinkToAccessTicket(ticket.getId())).build();
    }
	
    @DELETE
    @Path("{ticketId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTicket(@PathParam("ticketId") long ticketId) {
    	ticketService.deleteTicket(ticketId);
		return Response.ok().build();
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAllTickets() {
    	ticketService.deleteAllTickets();
		return Response.ok().build();
    }

    private URI getCreatedLinkToAccessTicket(Long ticketId) {
		return UriBuilder.fromPath("tickets/{ticketId}").build(ticketId);
	}
}
