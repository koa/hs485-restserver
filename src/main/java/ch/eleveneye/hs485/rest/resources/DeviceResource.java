package ch.eleveneye.hs485.rest.resources;

import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.eleveneye.hs485.device.Registry;

import com.thoughtworks.xstream.XStream;

@Path("devices")
public class DeviceResource {
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
	@Produces(MediaType.APPLICATION_XML)
	public XmlDevice getDevice(@PathParam("id") final Integer address) throws IOException {
		return new XmlDevice(registry.getPhysicallyDevice(address));
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Devices listDevices() throws IOException {
		final Devices devices = new Devices(registry.listPhysicalDevices());
		System.out.println(new XStream().toXML(devices));
		return devices;
	}
}
