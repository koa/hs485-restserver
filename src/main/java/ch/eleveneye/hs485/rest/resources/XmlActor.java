package ch.eleveneye.hs485.rest.resources;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

import ch.eleveneye.hs485.device.Dimmer;
import ch.eleveneye.hs485.device.SwitchingActor;
import ch.eleveneye.hs485.device.TimedActor;
import ch.eleveneye.hs485.device.config.DimmerMode;
import ch.eleveneye.hs485.device.config.TimeMode;
import ch.eleveneye.hs485.device.physically.Actor;
import ch.eleveneye.hs485.rest.resources.XmlDevice.ActorCapability;

@XmlRootElement(name = "actor")
public class XmlActor {
	private Integer								address;
	private Set<ActorCapability>	capibilities;
	private DimmerMode						dimmerMode;
	private Integer								dimmValue;
	private Integer								maxValue;
	private Boolean								on;
	private TimeMode							timeMode;
	private Integer								timeValue;
	private Boolean								toggleBit;

	public XmlActor() {
	}

	public XmlActor(final Actor actor) throws IOException {
		this(actor, false);
	}

	public XmlActor(final Actor actor, final boolean onlyId) throws IOException {
		setAddress(actor.getActorNr());
		if (!onlyId) {
			capibilities = new HashSet<XmlDevice.ActorCapability>();
			if (actor instanceof Dimmer) {
				final Dimmer dimmer = (Dimmer) actor;
				getCapibilities().add(ActorCapability.DIMM);
				setDimmerMode(dimmer.getDimmerMode());
				setDimmValue(dimmer.getDimmValue());
				setMaxValue(dimmer.getMaxValue());
			}
			if (actor instanceof SwitchingActor) {
				final SwitchingActor sw = (SwitchingActor) actor;
				getCapibilities().add(ActorCapability.SWITCH);
				setOn(sw.isOn());
				setToggleBit(sw.getToggleBit());
			}
			if (actor instanceof TimedActor) {
				final TimedActor timedActor = (TimedActor) actor;
				setTimeMode(timedActor.getTimeMode());
				setTimeValue(timedActor.getTimeValue());
			}
		}
	}

	@XmlElement(name = "id")
	public Integer getAddress() {
		return address;
	}

	@XmlList
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

	public void setCapibilities(final Set<ActorCapability> capibilities) {
		this.capibilities = capibilities;
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