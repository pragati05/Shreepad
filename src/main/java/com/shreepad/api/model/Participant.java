package com.shreepad.api.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Participant {

	@EmbeddedId
	private ParticipantIdentity participantIdentity;
	private int jap;
	private int strotram;
	private String location;

	public Participant() {
		
	}

	public ParticipantIdentity getParticipantIdentity() {
		return participantIdentity;
	}

	public void setParticipantIdentity(ParticipantIdentity participantIdentity) {
		this.participantIdentity = participantIdentity;
	}

	public int getJap() {
		return jap;
	}

	public void setJap(int jap) {
		this.jap = jap;
	}

	public int getStrotram() {
		return strotram;
	}

	public void setStrotram(int strotram) {
		this.strotram = strotram;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + jap;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((participantIdentity == null) ? 0 : participantIdentity.hashCode());
		result = prime * result + strotram;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participant other = (Participant) obj;
		if (jap != other.jap)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (participantIdentity == null) {
			if (other.participantIdentity != null)
				return false;
		} else if (!participantIdentity.equals(other.participantIdentity))
			return false;
		if (strotram != other.strotram)
			return false;
		return true;
	}

	public Participant(ParticipantIdentity participantIdentity, int jap, int strotram, String location) {
		this.participantIdentity = participantIdentity;
		this.jap = jap;
		this.strotram = strotram;
		this.location = location;
	}

}
