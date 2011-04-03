package ch.eleveneye.hs485.rest.resources;

import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;

import ch.eleveneye.hs485.device.Registry;

public class RegistrySingleton {
	private static Registry	registry;

	public static synchronized Registry getRegistry() throws UnsupportedCommOperationException, IOException {
		if (registry == null)
			registry = new Registry();
		return registry;
	}
}
