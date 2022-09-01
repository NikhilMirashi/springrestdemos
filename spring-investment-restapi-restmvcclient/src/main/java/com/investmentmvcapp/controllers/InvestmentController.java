package com.investmentmvcapp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.investmentmvcapp.model.Investment;

@Controller
public class InvestmentController {

	@Autowired
	private RestTemplate restTemplate;

	public static final String BASEURL = "http://localhost:8080/investment-api/investments";

	@RequestMapping("/")
	public String homePage(Model model) {

		ResponseEntity<List> responseEntity = restTemplate.getForEntity(BASEURL, List.class); // getting
		List<Investment> investments = responseEntity.getBody();
		System.out.println(responseEntity.getStatusCode() + " " + responseEntity.getStatusCodeValue()); // getting
																										// status code
		HttpHeaders headers = responseEntity.getHeaders(); // getting response headersfrom other api
		System.out.println(headers.get("info"));
		model.addAttribute("investments", investments); // adding attributes
		return "index";
	}

	@RequestMapping("/admin")
	public String adminPage() {
		return "admin";
	}

	@RequestMapping("/addForm")
	public String addFormPage() {
		return "addformpage";
	}

	// Add Investment
	@RequestMapping(value = "/addInvestment", method = RequestMethod.POST)
	public String addInvestment(Investment investment, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println(result.getErrorCount());
			System.out.println(result.getAllErrors());
			return "redirect:/";
		}
		// get the investment object
		System.out.println(investment);

		// create multivalue
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("planName", investment.getPlanName());
		map.add("planId", investment.getPlanId());
		map.add("entryAge", investment.getEntryAge());
		map.add("type", investment.getType());
		map.add("purpose", investment.getPurpose());
		map.add("risk", investment.getRisk());
		map.add("nominee", investment.getNominee());
		map.add("term", investment.getTerm());

		// send the
		restTemplate.postForEntity(BASEURL, map, Void.class);
		return "admin";
	}

	@RequestMapping("/updateForm")
	public String updateFormPage() {
		return "editformpage";
	}

	// http://localhost:8080/investments-api/investments/planId/1
	@RequestMapping("/getOne")
	public String getById(@RequestParam("planId") int planId, Model model) {

		String url = BASEURL + "/planId/" + planId;
		ResponseEntity<Investment> responseEntity = restTemplate.getForEntity(url, Investment.class); // getting
		System.out.println(responseEntity.getStatusCode() + " " + responseEntity.getStatusCodeValue()); // getting
																										// status code
		HttpHeaders headers = responseEntity.getHeaders(); // getting response headersfrom other api
		System.out.println(headers.get("info"));
		Investment investment = responseEntity.getBody();
		model.addAttribute("investment", investment);
		return "updateformpage";
	}

	@RequestMapping("/updateInvestment")
	public String updateInvestment(Investment investment, Model model) {
		restTemplate.put(BASEURL, investment);
		return "admin";
	}

//	@ExceptionHandler(Exception.class)
//	public String handlerException(Exception e, Model model) {
//		System.out.println("Exception...");
//		model.addAttribute("error", "error occured");
//		return "admin";
//	}

	@RequestMapping("/deleteForm")
	public String deleteFormPage() {
		return "deleteformpage";
	}

	@RequestMapping("/deleteInvestment")
	public String deleteInvestment(@RequestParam("planId") int planId) {
		String url = BASEURL + "/" + planId;
		restTemplate.delete(url, planId);
		return "admin";
	}

}
