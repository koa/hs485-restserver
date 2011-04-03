package ch.eleveneye.hs485.rest.resources;

import java.io.IOException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ch.eleveneye.hs485.device.physically.PhysicallyDevice;

@XmlRootElement(name = "device")
public class XmlDevice {
	public static enum ActorCapability {
		DIMM, SWITCH, TIMER
	}

	private XmlActorList	actors;

	private int						address;

	public XmlDevice() {
		address = -1;
	}

	public XmlDevice(final PhysicallyDevice device) throws IOException {
		this(device, false);
	}

	public XmlDevice(final PhysicallyDevice device, final boolean onlyId) throws IOException {
		address = device.getAddress();
		if (!onlyId)
			actors = new XmlActorList(device.listActors(), true);
	}

	@XmlElement
	public XmlActorList getActors() {
		return actors;
	}

	@XmlElement(name = "id")
	public int getAddress() {
		return address;
	}

	public void setActors(final XmlActorList actors) {
		this.actors = actors;
	}

	// public void setActors(final XmlActorList actors) {
	// actors.setActors(actors.getActors());
	// }

	public void setAddress(final int address) {
		this.address = address;
	}
}
