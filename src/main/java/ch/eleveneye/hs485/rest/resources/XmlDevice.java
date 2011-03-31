package ch.eleveneye.hs485.rest.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ch.eleveneye.hs485.device.Dimmer;
import ch.eleveneye.hs485.device.SwitchingActor;
import ch.eleveneye.hs485.device.TimedActor;
import ch.eleveneye.hs485.device.config.DimmerMode;
import ch.eleveneye.hs485.device.config.TimeMode;
import ch.eleveneye.hs485.device.physically.Actor;
import ch.eleveneye.hs485.device.physically.HS485D;
import ch.eleveneye.hs485.device.physically.HS485S;
import ch.eleveneye.hs485.device.physically.PhysicallyDevice;

@XmlRootElement(name = "device")
public class XmlDevice {
	public static enum ActorCapability {
		DIMM, SWITCH, TIMER
	}

	public static class XmlActor {
		private Integer											address;
		private final Set<ActorCapability>	capibilities	= new HashSet<XmlDevice.ActorCapability>();
		private DimmerMode									dimmerMode;
		private Integer											dimmValue;
		private Integer											maxValue;
		private Boolean											on;
		private TimeMode										timeMode;
		private Integer											timeValue;
		private Boolean											toggleBit;

		@XmlElement(name = "actor_id")
		public Integer getAddress() {
			return address;
		}

		@XmlElement(name = "capability")
		public Set<ActorCapability> getCapibilities() {
			return capibilities;
		}

		public DimmerMode getDimmerMode() {
			return dimmerMode;
		}

		public Integer getDimmValue() {
			return dimmValue;
		}

		public Integer getMaxValue() {
			return maxValue;
		}

		public Boolean getOn() {
			return on;
		}

		public TimeMode getTimeMode() {
			return timeMode;
		}

		public Integer getTimeValue() {
			return timeValue;
		}

		public Boolean getToggleBit() {
			return toggleBit;
		}

		public void setAddress(final Integer address) {
			this.address = address;
		}

		public void setDimmerMode(final DimmerMode dimmerMode) {
			this.dimmerMode = dimmerMode;
		}

		public void setDimmValue(final Integer dimmValue) {
			this.dimmValue = dimmValue;
		}

		public void setMaxValue(final Integer maxValue) {
			this.maxValue = maxValue;
		}

		public void setOn(final Boolean on) {
			this.on = on;
		}

		public void setTimeMode(final TimeMode timeMode) {
			this.timeMode = timeMode;

		}

		public void setTimeValue(final Integer timeValue) {
			this.timeValue = timeValue;
		}

		public void setToggleBit(final Boolean toggleBit) {
			this.toggleBit = toggleBit;
		}
	}

	public static XmlDevice buildDevice(final PhysicallyDevice physicallyDevice) throws IOException {
		if (physicallyDevice instanceof HS485D)
			return new XmlDevice(physicallyDevice);
		if (physicallyDevice instanceof HS485S)
			return new XmlDevice(physicallyDevice);
		return null;
	}

	private final Collection<XmlActor>	actors	= new ArrayList<XmlDevice.XmlActor>();

	private int													address;

	public XmlDevice() {
		address = -1;
	}

	protected XmlDevice(final PhysicallyDevice device) throws IOException {
		address = device.getAddress();
		final Collection<Actor> listActors = device.listActors();
		for (final Actor actor : listActors) {
			final XmlActor xmlActor = new XmlActor();
			xmlActor.setAddress(actor.getActorNr());
			if (actor instanceof Dimmer) {
				final Dimmer dimmer = (Dimmer) actor;
				xmlActor.getCapibilities().add(ActorCapability.DIMM);
				xmlActor.setDimmerMode(dimmer.getDimmerMode());
				xmlActor.setDimmValue(dimmer.getDimmValue());
				xmlActor.setMaxValue(dimmer.getMaxValue());
			}
			if (actor instanceof SwitchingActor) {
				final SwitchingActor sw = (SwitchingActor) actor;
				xmlActor.getCapibilities().add(ActorCapability.SWITCH);
				xmlActor.setOn(sw.isOn());
				xmlActor.setToggleBit(sw.getToggleBit());
			}
			if (actor instanceof TimedActor) {
				final TimedActor timedActor = (TimedActor) actor;
				xmlActor.setTimeMode(timedActor.getTimeMode());
				xmlActor.setTimeValue(timedActor.getTimeValue());
			}
			getActors().add(xmlActor);
		}
	}

	@XmlElement(name = "actor")
	public Collection<XmlActor> getActors() {
		return actors;
	}

	@XmlElement(name = "device_id")
	public int getAddress() {
		return address;
	}

	public void setAddress(final int address) {
		this.address = address;
	}
}
