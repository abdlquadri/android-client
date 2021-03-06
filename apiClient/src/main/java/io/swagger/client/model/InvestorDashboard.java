/*
 * Core API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v1
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * InvestorDashboard
 */

public class InvestorDashboard
{
	@SerializedName("investmentPrograms")
	private List<InvestmentProgramDashboardInvestor> investmentPrograms = null;

	@SerializedName("profitFromPrograms")
	private Double profitFromPrograms = null;

	@SerializedName("investedAmount")
	private Double investedAmount = null;

	@SerializedName("totalPortfolioAmount")
	private Double totalPortfolioAmount = null;

	public InvestorDashboard investmentPrograms(List<InvestmentProgramDashboardInvestor> investmentPrograms) {
		this.investmentPrograms = investmentPrograms;
		return this;
	}

	public InvestorDashboard addInvestmentProgramsItem(InvestmentProgramDashboardInvestor investmentProgramsItem) {
		if (this.investmentPrograms == null) {
			this.investmentPrograms = new ArrayList<InvestmentProgramDashboardInvestor>();
		}
		this.investmentPrograms.add(investmentProgramsItem);
		return this;
	}

	/**
	 * Get investmentPrograms
	 *
	 * @return investmentPrograms
	 **/
	@ApiModelProperty(value = "")
	public List<InvestmentProgramDashboardInvestor> getInvestmentPrograms() {
		return investmentPrograms;
	}

	public void setInvestmentPrograms(List<InvestmentProgramDashboardInvestor> investmentPrograms) {
		this.investmentPrograms = investmentPrograms;
	}

	public InvestorDashboard profitFromPrograms(Double profitFromPrograms) {
		this.profitFromPrograms = profitFromPrograms;
		return this;
	}

	/**
	 * Get profitFromPrograms
	 *
	 * @return profitFromPrograms
	 **/
	@ApiModelProperty(value = "")
	public Double getProfitFromPrograms() {
		return profitFromPrograms;
	}

	public void setProfitFromPrograms(Double profitFromPrograms) {
		this.profitFromPrograms = profitFromPrograms;
	}

	public InvestorDashboard investedAmount(Double investedAmount) {
		this.investedAmount = investedAmount;
		return this;
	}

	/**
	 * Get investedAmount
	 *
	 * @return investedAmount
	 **/
	@ApiModelProperty(value = "")
	public Double getInvestedAmount() {
		return investedAmount;
	}

	public void setInvestedAmount(Double investedAmount) {
		this.investedAmount = investedAmount;
	}

	public InvestorDashboard totalPortfolioAmount(Double totalPortfolioAmount) {
		this.totalPortfolioAmount = totalPortfolioAmount;
		return this;
	}

	/**
	 * Get totalPortfolioAmount
	 *
	 * @return totalPortfolioAmount
	 **/
	@ApiModelProperty(value = "")
	public Double getTotalPortfolioAmount() {
		return totalPortfolioAmount;
	}

	public void setTotalPortfolioAmount(Double totalPortfolioAmount) {
		this.totalPortfolioAmount = totalPortfolioAmount;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		InvestorDashboard investorDashboard = (InvestorDashboard) o;
		return Objects.equals(this.investmentPrograms, investorDashboard.investmentPrograms) &&
				Objects.equals(this.profitFromPrograms, investorDashboard.profitFromPrograms) &&
				Objects.equals(this.investedAmount, investorDashboard.investedAmount) &&
				Objects.equals(this.totalPortfolioAmount, investorDashboard.totalPortfolioAmount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(investmentPrograms, profitFromPrograms, investedAmount, totalPortfolioAmount);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class InvestorDashboard {\n");

		sb.append("    investmentPrograms: ").append(toIndentedString(investmentPrograms)).append("\n");
		sb.append("    profitFromPrograms: ").append(toIndentedString(profitFromPrograms)).append("\n");
		sb.append("    investedAmount: ").append(toIndentedString(investedAmount)).append("\n");
		sb.append("    totalPortfolioAmount: ").append(toIndentedString(totalPortfolioAmount)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

}

