package com.shreepad.api.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shreepad.api.model.Participant;
import com.shreepad.api.service.MahajapService;

@RestController
@RequestMapping("/admin")
@Transactional
public class AdminController {

	@Autowired
	private MahajapService mahajapService;

	@GetMapping("/participant")
	public List<Participant> getAllParticipant() {
		
		return mahajapService.findAll();
	}

	@GetMapping("/participant/{mobileNumber}")
	public List<Participant> getAllParticipantByMobileNumber(@PathVariable String mobileNumber) {
		return mahajapService.findByParticipantIdentityMobileNumber(mobileNumber);
	}

	@PostMapping("/participant")
	public void addParticipant(@RequestBody Participant  participant) {
		mahajapService.addParticipant(participant);
	}
	
	@DeleteMapping("/participant/{mobileNumber}")
	public void deleteParticipantByMobileNumber(@PathVariable String mobileNumber) {
		mahajapService.deleteByParticipantIdentityMobileNumber(mobileNumber);
	}
	
	
}
