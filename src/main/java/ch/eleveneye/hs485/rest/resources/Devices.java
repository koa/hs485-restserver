package ch.eleveneye.hs485.rest.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ch.eleveneye.hs485.device.physically.PhysicallyDevice;

@XmlRootElement
public class Devices {
	private final Collection<XmlDevice>	devices	= new ArrayList<XmlDevice>();

	public Devices() {
	}

	public Devices(final Collection<PhysicallyDevice> devices) throws IOException {
		for (final PhysicallyDevice physicallyDevice : devices)
			getDevices().add(XmlDevice.buildDevice(physicallyDevice));
	}

	@XmlElement(name = "device")
	public Collection<XmlDevice> getDevices() {
		return devices;
	}

}
