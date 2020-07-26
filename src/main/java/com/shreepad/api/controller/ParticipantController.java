package com.shreepad.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shreepad.api.model.Participant;
import com.shreepad.api.service.MahajapService;

@RestController
@RequestMapping("/participant")
public class ParticipantController {
	
	@Autowired
	private MahajapService mahajapService;
	
	@GetMapping("/{mobileNumber}")
	public List<Participant> getUserDataByMobileNumber(@PathVariable String mobileNumber){
		return  mahajapService.findByParticipantIdentityMobileNumber(mobileNumber);
	}

}
