package com.investmentapp.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.investmentapp.model.Investment;
import com.investmentapp.service.IInvestmentService;

//@RestController
public class InvestmentController {

	IInvestmentService investmentService;

	@Autowired
	public void setInvestmentService(IInvestmentService investmentService) {
		this.investmentService = investmentService;
	}

	//data comes from frontend and it will take all the values and reflect it back in databse
	@PostMapping("/investments")
	public void addInvestment(@RequestBody Investment investment) {
		investmentService.addInvestment(investment);
	}

	@PutMapping("/investments")
	public void updateInvestment(@RequestBody Investment investment) {
		investmentService.updateInvestment(investment);
	}
	@DeleteMapping("/investments/{planId}")
	public void deleteInvestment(@PathVariable("planId") int planId) {
		investmentService.deleteInvestment(planId);
	}

	// custom
	@GetMapping("/investments/risk/{risk}/term/{term}")
	public List<Investment> getByRiskAndTerm(@PathVariable("risk")  String risk,@PathVariable("term") int term) {
		return investmentService.getByRiskAndTerm(risk, term);
	}

	// custom
	@GetMapping("/investments/type/{type}")
	public List<Investment> getByType(@RequestParam("type") String type) {
		return investmentService.getByType(type);
	}

	// custom
	@GetMapping("/investments/purpose/{purpose}")
	public List<Investment> getByPurpose(@PathVariable("purpose") String purpose) {
		return investmentService.getByPurpose(purpose);
	}
	
	
	@GetMapping("/investments")
	public List<Investment> getAll() {
		return investmentService.getAll();
	}

	@GetMapping("/investments/planId/{planId}")
	public Investment getById(@PathVariable("planId") int planId) {
		return investmentService.getById(planId);
	}


}
