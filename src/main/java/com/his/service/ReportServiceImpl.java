package com.his.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.client.ApplicationApiClient;
import com.his.client.CitizenApiClient;
import com.his.client.EdApiClient;
import com.his.dto.ApplicationRegDTO;
import com.his.dto.CitizenDTO;
import com.his.dto.CitizenReports;
import com.his.dto.EligDetermineDTO;
import com.his.dto.SearchCriteriaDto;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	EdApiClient edClient;
	
	@Autowired
	CitizenApiClient citizenClient;
	
	@Autowired
	ApplicationApiClient arClient;
	
	@Override
	public List<String> getPlanNames() {
		return edClient.getPlanNames();
	}

	@Override
	public List<String> getPlanStatuses() {
		return edClient.getStatuses();
	}

	@Override
	public List<CitizenReports> getCitizens(SearchCriteriaDto criteria) {
		EligDetermineDTO edDto= new EligDetermineDTO();
		
		if(!criteria.getPlanName().equals("")) {
		edDto.setPlanName(criteria.getPlanName());
		}
		
		if(!criteria.getPlanStatus().equals("")) {
			edDto.setEligStatus(criteria.getPlanStatus());
			}
		
//		if(!criteria.getGender().equals("")) {
//			edDto.(criteria.getGender());
//			}
		
//		if(!criteria.getStartDate().equals(null)) {
//			edDto.setEligStartdate(criteria.getStartDate());
//			}
//		
//		if(!criteria.getEndDate().equals(null)) {
//			edDto.setEligStartdate(criteria.getEndDate());
//			}
//		
		List<EligDetermineDTO> searchEdDetails = edClient.searchEdDetails(edDto);
		List<CitizenReports> reportCitizenList= new ArrayList<CitizenReports>();
		searchEdDetails.forEach(edEntity->{
			ApplicationRegDTO application = arClient.getApplication(edEntity.getAppNumber());
			CitizenDTO citizen = citizenClient.getCitizen(application.getCitizenId());
			CitizenReports citizenRepo= new CitizenReports();
			BeanUtils.copyProperties(citizen, citizenRepo);
			reportCitizenList.add(citizenRepo);
		});
		
		return reportCitizenList;
	}

	@Override
	public void generateExcel(HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generatePdf(HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

}
