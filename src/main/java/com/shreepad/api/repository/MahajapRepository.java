package com.shreepad.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shreepad.api.model.Participant;
import com.shreepad.api.model.ParticipantIdentity;

@Repository
public interface MahajapRepository extends JpaRepository<Participant, ParticipantIdentity> {

	List<Participant> findByParticipantIdentityMobileNumber(String mobileNumber);

	void deleteByParticipantIdentityMobileNumber(String mobileNumber);

}
