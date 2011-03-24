package ch.eleveneye.hs485.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("bus")
public class Resource {
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Bus getBus(){
		Bus b = new Bus();
		b.setAddress(3);
		return b;
	}

}
