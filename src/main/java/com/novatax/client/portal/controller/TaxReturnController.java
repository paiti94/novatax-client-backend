package com.novatax.client.portal.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novatax.client.portal.entities.Clients;
import com.novatax.client.portal.entities.TaxReturn;
import com.novatax.client.portal.entities.Users;
import com.novatax.client.portal.repository.ClientRepository;
import com.novatax.client.portal.repository.TaxReturnRepository;
import com.novatax.client.portal.repository.UserRepository;

@RestController
@RequestMapping("/taxreturn")
public class TaxReturnController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	TaxReturnRepository taxReturnRepository;

	 @GetMapping("/listall")
	    public List<TaxReturn> index(){
	        return taxReturnRepository.findAll();
	    }

	    @GetMapping("/find/{id}")
	    public Optional<TaxReturn> show(@PathVariable String id){
	        int tax_return_id = Integer.parseInt(id);
	        return taxReturnRepository.findById(tax_return_id);
	    }


	    @PostMapping("/create")
	    public TaxReturn create(@RequestBody Map<String, String> body){
	    
	        int client_id = Integer.parseInt(body.get("client_id"));
	        Clients client = clientRepository.findById(client_id).orElseThrow(()-> new RuntimeException("Client not found with id: "+ client_id));
	        LocalDate tax_year = LocalDate.parse(body.get("tax_year"));
	        Date sql_tax_year = java.sql.Date.valueOf(tax_year);
	        String filing_status = body.get("filing_status");
	        double adjusted_gross_income = Double.parseDouble(body.get("adjusted_gross_income"));
	        double total_income = Double.parseDouble(body.get("total_income"));
	        double total_deductions = Double.parseDouble(body.get("total_deductions"));
	        double total_credits = Double.parseDouble(body.get("total_credits"));
	        double taxable_income = Double.parseDouble(body.get("taxable_income"));
	        LocalDate tax_due = LocalDate.parse(body.get("tax_due"));
	        Date sql_tax_due = java.sql.Date.valueOf(tax_due);
	        String payment_status = body.get("payment_status");
	        LocalDate filing_date = LocalDate.parse(body.get("filing_date"));
	        Date sql_filing_date = java.sql.Date.valueOf(filing_date);
	        LocalDate submission_date = LocalDate.parse(body.get("submission_date"));
	        Date sql_submission_date = java.sql.Date.valueOf(submission_date);
	        LocalDate approval_date = LocalDate.parse(body.get("approval_date"));
	        Date sql_approval_date = java.sql.Date.valueOf(approval_date);
	        int user_id = Integer.parseInt(body.get("reviewed_by"));
	        Users reviewer = userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User not found with id: "+ user_id));
	        String notes = body.get("notes");
	        String review_notes = body.get("review_notes");
	        String audit_status = body.get("audit_status");
	        String payment_method = body.get("payment_method");
	        String filing_location = body.get("filing_location");
	     
	        return taxReturnRepository.save(new TaxReturn(client, sql_tax_year, filing_status, adjusted_gross_income, total_deductions, total_income, total_credits, taxable_income, sql_tax_due, 
	        		payment_status, sql_filing_date, sql_submission_date, sql_approval_date, reviewer, notes, review_notes, audit_status, payment_method, filing_location));
	
	   }


	    @PutMapping("/update/{id}")
	    public TaxReturn update(@PathVariable String id, @RequestBody Map<String, String> body){
	    		int tax_return_id = Integer.parseInt(id);
	    	
	    	    TaxReturn taxReturn = taxReturnRepository.findById(tax_return_id).orElseThrow(() -> new RuntimeException("tax return not found with id: " + tax_return_id));
	    	    int client_id = Integer.parseInt(body.get("client_id"));
	    	    Clients client = clientRepository.findById(client_id).orElseThrow(()-> new RuntimeException("Client not found with id: "+ client_id));
	    	    LocalDate tax_year = LocalDate.parse(body.get("tax_year"));
		        Date sql_tax_year = java.sql.Date.valueOf(tax_year);
	    	    taxReturn.setClient(client);
	    	    taxReturn.setTax_year(sql_tax_year);
	    	    taxReturn.setFiling_status(body.get("filing_status"));
	    	    taxReturn.setAdjusted_gross_income(Double.parseDouble(body.get("adjusted_gross_income")));
	    	    taxReturn.setTotal_income(Double.parseDouble(body.get("total_income")));
	    	    taxReturn.setTotal_deductions(Double.parseDouble(body.get("total_deductions")));
	    	    taxReturn.setTotal_credits(Double.parseDouble(body.get("total_credits")));
	    	    taxReturn.setTaxable_income(Double.parseDouble(body.get("taxable_income")));
	    	    LocalDate tax_due = LocalDate.parse(body.get("tax_due"));
		        Date sql_tax_due = java.sql.Date.valueOf(tax_due);
	    	    taxReturn.setTax_due(sql_tax_due);
	    	    taxReturn.setPayment_status(body.get("payment_status"));
	    	    LocalDate filing_date = LocalDate.parse(body.get("filing_date"));
		        Date sql_filing_date = java.sql.Date.valueOf(filing_date);
		        taxReturn.setFiling_date(sql_filing_date);
		        LocalDate submission_date = LocalDate.parse(body.get("submission_date"));
		        Date sql_submission_date = java.sql.Date.valueOf(submission_date);
		        taxReturn.setSubmission_date(sql_submission_date);
		        LocalDate approval_date = LocalDate.parse(body.get("approval_date"));
		        Date sql_approval_date = java.sql.Date.valueOf(approval_date);
		        taxReturn.setApproval_date(sql_approval_date);
		        int user_id = Integer.parseInt(body.get("reviewed_by"));
		        Users reviewer = userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User not found with id: "+ user_id));
		        taxReturn.setReviewedBy(reviewer);
	    	    taxReturn.setNotes(body.get("notes"));
	    	    taxReturn.setReview_notes(body.get("review_notes"));
	    	    taxReturn.setAudit_status(body.get("audit_status"));
	    	    taxReturn.setPayment_method(body.get("payment_method"));
	    	    taxReturn.setFiling_location(body.get("filing_location"));
	    	   
	    	    return taxReturnRepository.save(taxReturn);
	    	  
	    }

	    @DeleteMapping("/delete/{id}")
	    public boolean delete(@PathVariable String id){
	        int tax_return_id = Integer.parseInt(id);
	        taxReturnRepository.deleteById(tax_return_id);
	        return true;
	    }
}
