package ch.eleveneye.hs485.rest.resources;

import java.io.IOException;

import ch.eleveneye.hs485.device.physically.HS485S;

public class XmlHs485S extends XmlDevice {
	public XmlHs485S(final HS485S phyDev) throws IOException {
		super(phyDev);
	}
}
