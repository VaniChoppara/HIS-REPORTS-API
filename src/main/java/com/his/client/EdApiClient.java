package com.his.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.his.dto.EligDetermineDTO;

@FeignClient(name="ED-API")
public interface EdApiClient {
//	@GetMapping("/eddetails/{appNumber}")
//	public EligDetermineDTO getEdDetalilByAppNumber(@PathVariable("appNumber") Integer appNumber);
//	@GetMapping("/eddetails")
//	public List<EligDetermineDTO> getAllEdDetalil();
//	
	@GetMapping("/eddetails/plannames")
	public List<String> getPlanNames();
	
	@GetMapping("/eddetails/satuses")
	public List<String> getStatuses();

	@PostMapping("/eddetails/search")
	public List<EligDetermineDTO> searchEdDetails(@RequestBody EligDetermineDTO edDto);
	
	
	
	
}
