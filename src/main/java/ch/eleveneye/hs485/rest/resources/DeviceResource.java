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

@Path("")
public class DeviceResource {
	private static Registry	registry;
	static {
		try {
			registry = new Registry();
		} catch (final UnsupportedCommOperationException e) {
			throw new RuntimeException(e);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	@GET
	@Path("devices/{deviceid}.xml")
	@Produces(MediaType.APPLICATION_XML)
	public XmlDevice getDevice(@PathParam("deviceid") final Integer address) throws IOException {
		return XmlDevice.buildDevice(registry.getPhysicallyDevice(address));
	}

	@GET
	@Path("devices.xml")
	@Produces(MediaType.APPLICATION_XML)
	public Devices listDevices() throws IOException {
		final Devices devices = new Devices(registry.listPhysicalDevices());
		System.out.println(new XStream().toXML(devices));
		return devices;
	}
}
