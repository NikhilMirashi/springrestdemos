package com.investmentapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.investmentapp.exceptions.PlanNotFoundException;
import com.investmentapp.model.Investment;
import com.investmentapp.repository.IIvestmentRepository;

@Service
public class InvestmentServiceImpl implements IInvestmentService {

	private IIvestmentRepository investmentRepository;

	@Autowired
	public void setInvestmentRepository(IIvestmentRepository investmentRepository) {
		this.investmentRepository = investmentRepository;
	}

	@Override
	public void addInvestment(Investment investment) {

		investmentRepository.save(investment);
	}

	@Override
	public void updateInvestment(Investment investment) {
		investmentRepository.save(investment);
	}

	@Override
	public void deleteInvestment(int planId) {
		investmentRepository.deleteById(planId);
	}

	// custom
	@Override
	public List<Investment> getByRiskAndTerm(String risk, int term) {

		return investmentRepository.findByRiskAndTerm(risk, term);
	}

	// custom
	@Override
	public List<Investment> getByType(String type) {
		List<Investment> investments = investmentRepository.findByType(type);
		if (investments.isEmpty()) {
			throw new PlanNotFoundException("plan type is not found");
		}
		return investments;
	}

	// custom
	@Override
	public List<Investment> getByPurpose(String purpose) {
		List<Investment> investments = investmentRepository.findByPurpose(purpose);
		if (investments.isEmpty()) {
			throw new PlanNotFoundException("plan purpose is not found");
		}
		return investments;
	}

	@Override
	public List<Investment> getAll() {
		return investmentRepository.findAll();
	}

	@Override
	public Investment getById(int planId) {
		return investmentRepository.findById(planId).orElseThrow(() -> new PlanNotFoundException("Invalid Id"));
	}

	@Override
	public double calculateMaturity(Investment investment) {
		return 0;
	}

	@Override
	@Transactional
	public void updateInvestmentAmount(int planId, double amount) {
		investmentRepository.updateInvestmentAmount(planId, amount);
	}

}
