package com.investmentapp.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.investmentapp.model.Investment;
import com.investmentapp.service.IInvestmentService;

@RestController
public class IvestmentResponseController {

	IInvestmentService investmentService;

	@Autowired
	public void setInvestmentService(IInvestmentService investmentService) {
		this.investmentService = investmentService;
	}

	@PostMapping("/investments")
	public ResponseEntity<Void> addInvestment(@RequestBody Investment investment) {
		investmentService.addInvestment(investment);
		HttpHeaders headers = new HttpHeaders();
		headers.add("description", "investment is added");
		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
	}

	@PutMapping("/investments")
	public ResponseEntity<String> updateInvestment(@RequestBody Investment investment) {
		investmentService.updateInvestment(investment);
		HttpHeaders headers = new HttpHeaders();
		headers.add("description", "investment is updated");
		return ResponseEntity.accepted().body("Updated");

	}

	@DeleteMapping("/investments/{planId}")
	public ResponseEntity<String> deleteInvestment(@PathVariable("planId") int planId) {
		investmentService.deleteInvestment(planId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("description", "investment is deleted");
		return ResponseEntity.accepted().body("deleted");

	}

	// Risk and Term
	@GetMapping("/investments/risk/{risk}/term/{term}")
	public ResponseEntity<List<Investment>> getByRiskAndTerm(@PathVariable("risk") String risk,
			@PathVariable("term") int term) {
		List<Investment> investments = investmentService.getByRiskAndTerm(risk, term);
		HttpHeaders headers = new HttpHeaders();
		headers.add("description", "All investments by Risk and Term");
		headers.add("info", "Getting all investments by Risk and Term from springDb");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(investments);
	}

//Getting all
	@GetMapping("/investments")
	public ResponseEntity<List<Investment>> getAll() {
		// Response entity
		List<Investment> investments = investmentService.getAll();
		HttpHeaders headers = new HttpHeaders();
		headers.add("description", "Getting all investments");
		headers.add("info", "Getting all investments from springDb");
		ResponseEntity<List<Investment>> responseEntity = new ResponseEntity<>(investments, headers, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/investments/type/{type}")
	public ResponseEntity<List<Investment>> getByType(@PathVariable("type") String type) {

		List<Investment> investments = investmentService.getByType(type);
		HttpHeaders headers = new HttpHeaders();
		headers.add("description", "All investments by Type");
		headers.add("info", "Getting all investments Type from springDb");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(investments);

	}

	@GetMapping("/investments/purpose/{purpose}")
	public ResponseEntity<List<Investment>> getByPurpose(@PathVariable("purpose") String purpose) {

		List<Investment> investments = investmentService.getByPurpose(purpose);
		HttpHeaders headers = new HttpHeaders();
		headers.add("description", "All investments by Purpose");
		headers.add("info", "Getting all investments Purpose from springDb");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(investments);

	}

	@GetMapping("/investments/planId/{planId}")
	public ResponseEntity<Investment> getById(@PathVariable("planId") int planId) {
		Investment investment = investmentService.getById(planId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("description", "investment by Id");
		headers.add("info", "Getting the investment Id from springDb");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(investment);
	}

	@GetMapping("/investments/planId/{planId}/amount/{amount}")
	public ResponseEntity<Void> updateInvestment(@PathVariable("planId") int planId,
			@PathVariable("amount") double amount) {
		investmentService.updateInvestmentAmount(planId, amount);
		HttpHeaders headers = new HttpHeaders();
		headers.add("description", "investment by Id updated");
		headers.add("info", "Getting the investment Id from springdb");
		return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers).build();
	}
}
