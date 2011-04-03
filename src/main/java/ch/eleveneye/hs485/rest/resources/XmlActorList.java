package ch.eleveneye.hs485.rest.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ch.eleveneye.hs485.device.physically.Actor;

@XmlRootElement(name = "actors")
public class XmlActorList {
	private final Collection<XmlActor>	actors;

	public XmlActorList() {
		actors = new ArrayList<XmlActor>();
	}

	public XmlActorList(final Collection<Actor> actors) throws IOException {
		this(actors, false);
	}

	public XmlActorList(final Collection<Actor> actors, final boolean onlyId) throws IOException {
		this.actors = new ArrayList<XmlActor>(actors.size());
		for (final Actor actor : actors)
			this.actors.add(new XmlActor(actor, onlyId));
	}

	@XmlElement(name = "actor")
	public Collection<XmlActor> getActors() {
		return actors;
	}

	@XmlAttribute
	public String getType() {
		return "array";
	}

}