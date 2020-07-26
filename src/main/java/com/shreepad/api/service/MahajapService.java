package com.shreepad.api.service;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shreepad.api.model.Participant;
import com.shreepad.api.repository.MahajapRepository;

@Service
public class MahajapService {

	@Autowired
	private MahajapRepository mahajapRepository;
	
	public List<Participant> findByParticipantIdentityMobileNumber(String mobileNumber) {
		return mahajapRepository.findByParticipantIdentityMobileNumber(mobileNumber);
	}

	public List<Participant> findAll() {
		return (List<Participant>) mahajapRepository.findAll();
	}
	public void addParticipant(Participant participant) {
		
		mahajapRepository.save(participant);
	}
	
	public void deleteByParticipantIdentityMobileNumber(String mobileNumber) {
		mahajapRepository.deleteByParticipantIdentityMobileNumber(mobileNumber);
	}
}
