package com.novatax.client.portal.entities;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.novatax.client.portal.repository.UserRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
public class TaxReturn {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tax_return_id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Clients client;
    
	@Column(nullable = false)
	private Date tax_year;
	
	@Column(nullable = false)
	private String filing_status;
	
	@Column(nullable = false)
	private Double adjusted_gross_income;
	
	@Column(nullable = false)
	private Double total_income;
	
	@Column(nullable = false)
	private Double total_deductions;
	
	@Column(nullable = false)
	private Double total_credits;
	
	@Column(nullable = false)
	private Double taxable_income;
	
	@Column(nullable = false)
	private Date tax_due;
	
	@Column(nullable = false)
	private String payment_status;
	
	@Column(nullable = false)
	private Date filing_date;
	
	@Column(nullable = false)
	private Date submission_date;
	
	@Column(nullable = false)
	private Date approval_date;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reviewed_by", referencedColumnName = "id")
    private Users reviewedBy;
	
    @Column(nullable = false)
	private String notes;

    @Column(nullable = false)
	private String review_notes;

	@Column(nullable = false)
   	private String audit_status;
    
    @Column(nullable = false)
   	private String payment_method;
    
    @Column(nullable = false)
   	private String filing_location;

    
	public TaxReturn(Clients client, Date tax_year, String filing_status,
			Double adjusted_gross_income, Double total_deductions, Double total_income, Double total_credits, Double taxable_income,
			Date tax_due, String payment_status, Date filing_date, Date submission_date, Date approval_date,
			Users reviewedBy, String notes, String review_notes, String audit_status, String payment_method, String filing_location) {
		super();
		this.client = client;
		this.tax_year = tax_year;
		this.filing_status = filing_status;
		this.adjusted_gross_income = adjusted_gross_income;
		this.total_deductions = total_deductions;
		this.total_income = total_income;
		this.total_credits = total_credits;
		this.taxable_income = taxable_income;
		this.tax_due = tax_due;
		this.payment_status = payment_status;
		this.filing_date = filing_date;
		this.submission_date = submission_date;
		this.approval_date = approval_date;
		this.reviewedBy = reviewedBy;
		this.notes = notes;
		this.review_notes = review_notes;
		this.audit_status = audit_status;
		this.payment_method = payment_method;
		this.filing_location = filing_location;
	}

	
	public TaxReturn() {}


	public Integer getTax_return_id() {
		return tax_return_id;
	}

	public void setTax_return_id(Integer tax_return_id) {
		this.tax_return_id = tax_return_id;
	}
	  public String getReview_notes() {
			return review_notes;
		}


		public void setReview_notes(String review_notes) {
			this.review_notes = review_notes;
		}
	public Clients getClient() {
		return client;
	}

	public void setClient(Clients client) {
		this.client = client;
	}

	public Date getTax_year() {
		return tax_year;
	}

	public void setTax_year(Date tax_year) {
		this.tax_year = tax_year;
	}

	public String getFiling_status() {
		return filing_status;
	}

	public void setFiling_status(String filing_status) {
		this.filing_status = filing_status;
	}

	public Double getAdjusted_gross_income() {
		return adjusted_gross_income;
	}

	public void setAdjusted_gross_income(Double adjusted_gross_income) {
		this.adjusted_gross_income = adjusted_gross_income;
	}

	public Double getTotal_deductions() {
		return total_deductions;
	}
	
	public Double getTotal_income() {
		return total_income;
	}

	public void setTotal_deductions(Double total_deductions) {
		this.total_deductions = total_deductions;
	}

	public void setTotal_income(Double total_income) {
		this.total_income = total_income;
	}
	
	public Double getTotal_credits() {
		return total_credits;
	}

	public void setTotal_credits(Double total_credits) {
		this.total_credits = total_credits;
	}

	public Double getTaxable_income() {
		return taxable_income;
	}

	public void setTaxable_income(Double taxable_income) {
		this.taxable_income = taxable_income;
	}

	public Date getTax_due() {
		return tax_due;
	}

	public void setTax_due(Date tax_due) {
		this.tax_due = tax_due;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public Date getFiling_date() {
		return filing_date;
	}

	public void setFiling_date(Date filing_date) {
		this.filing_date = filing_date;
	}

	public Date getSubmission_date() {
		return submission_date;
	}

	public void setSubmission_date(Date submission_date) {
		this.submission_date = submission_date;
	}

	public Date getApproval_date() {
		return approval_date;
	}

	public void setApproval_date(Date approval_date) {
		this.approval_date = approval_date;
	}

	public Users getReviewedBy() {
		return reviewedBy;
	}

	public void setReviewedBy(Users reviewedBy) {
		this.reviewedBy = reviewedBy;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getAudit_status() {
		return audit_status;
	}

	public void setAudit_status(String audit_status) {
		this.audit_status = audit_status;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getFiling_location() {
		return filing_location;
	}

	public void setFiling_location(String filing_location) {
		this.filing_location = filing_location;
	}
	
	
	
}
