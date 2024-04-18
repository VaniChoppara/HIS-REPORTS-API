package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.his.dto.CitizenReports;
import com.his.dto.SearchCriteriaDto;
import com.his.service.ReportService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class HisReportsRestController {
	@Autowired
	ReportService reportService;

	@GetMapping("/reports/plans")
	public ResponseEntity<List<String>> getPlanNames(){
		List<String> planNames = reportService.getPlanNames();
		return new ResponseEntity<>(planNames, HttpStatus.OK);
	}
	@GetMapping("/reports/statuses")
	public ResponseEntity<List<String>> getPlanStatuses(){
		List<String> planStatuses = reportService.getPlanStatuses();
		return new ResponseEntity<>(planStatuses, HttpStatus.OK);
		
	}
	
	@PostMapping("/reports/citizens")
	public ResponseEntity<List<CitizenReports>> getCitizens(@RequestBody SearchCriteriaDto criteria){
		List<CitizenReports> reports = reportService.getCitizens(criteria);
		return new ResponseEntity<>(reports, HttpStatus.OK);
		
	}
	
	@GetMapping("/reports/excel")
	public void generateExcel(HttpServletResponse response) {
		
	}
	@GetMapping("/reports/pdf")
	public void generatePdf(HttpServletResponse response) {
		
	}
}
