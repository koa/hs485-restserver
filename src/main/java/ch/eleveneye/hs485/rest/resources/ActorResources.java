package ch.eleveneye.hs485.rest.resources;

import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import ch.eleveneye.hs485.device.Registry;
import ch.eleveneye.hs485.device.physically.Actor;

@Path("devices/{device}/actors")
public class ActorResources {
	private static Registry	registry;
	static {
		try {
			registry = RegistrySingleton.getRegistry();
		} catch (final UnsupportedCommOperationException e) {
			throw new RuntimeException(e);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	@GET
	@Path("{id}")
	public XmlActor getActor(@PathParam("device") final String device, @PathParam("id") final String id) throws IOException {
		final Actor actor = registry.getActor(Integer.parseInt(device), Integer.parseInt(id));
		return new XmlActor(actor);

	}

	@GET
	public XmlActorList getList(@PathParam("device") final String device) throws NumberFormatException, IOException {
		final Collection<Actor> foundActors = registry.getPhysicallyDevice(Integer.parseInt(device)).listActors();
		return new XmlActorList(foundActors);

	}
}
