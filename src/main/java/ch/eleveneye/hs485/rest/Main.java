package ch.eleveneye.hs485.rest;

import java.io.IOException;

import ch.eleveneye.hs485.rest.resources.Resource;

import com.sun.jersey.api.core.ClassNamesResourceConfig;
import com.sun.jersey.simple.container.SimpleServerFactory;

public class Main {

	public static void main(String[] args) throws IOException {

		SimpleServerFactory.create("http://localhost:8888");
	}
}