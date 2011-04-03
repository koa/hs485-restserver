package ch.eleveneye.hs485.rest;

import java.io.Closeable;
import java.io.IOException;

import com.sun.jersey.simple.container.SimpleServerFactory;

public class Main {

	public static void main(final String[] args) throws IOException {
		final Closeable server = SimpleServerFactory.create("http://localhost:8888");
		System.out.println(server);
	}
}