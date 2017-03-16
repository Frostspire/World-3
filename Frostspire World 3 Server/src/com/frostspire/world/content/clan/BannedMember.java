package com.frostspire.world.content.clan;

import com.frostspire.world.model.SecondsTimer;

public class BannedMember {

	private SecondsTimer timer;
	private String name;
	
	public BannedMember(String name, int seconds) {
		this.setName(name);
		this.setTimer(new SecondsTimer(seconds));
	}

	public SecondsTimer getTimer() {
		return timer;
	}

	public void setTimer(SecondsTimer timer) {
		this.timer = timer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
