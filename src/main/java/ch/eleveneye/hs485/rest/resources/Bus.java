package ch.eleveneye.hs485.rest.resources;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bus {
	private int address;

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}
}
