package ch.eleveneye.hs485.rest.resources;

import java.io.IOException;

import ch.eleveneye.hs485.device.physically.HS485D;

public class XmlHs485D extends XmlDevice {

	public XmlHs485D(final HS485D physHS485) throws IOException {
		super(physHS485);
	}
}
