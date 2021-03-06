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
 * NewOpenTradesEvent
 */

public class NewOpenTradesEvent
{
	@SerializedName("openTrades")
	private List<ManagerOpenTrades> openTrades = new ArrayList<ManagerOpenTrades>();

	public NewOpenTradesEvent openTrades(List<ManagerOpenTrades> openTrades) {
		this.openTrades = openTrades;
		return this;
	}

	public NewOpenTradesEvent addOpenTradesItem(ManagerOpenTrades openTradesItem) {
		this.openTrades.add(openTradesItem);
		return this;
	}

	/**
	 * Get openTrades
	 *
	 * @return openTrades
	 **/
	@ApiModelProperty(required = true, value = "")
	public List<ManagerOpenTrades> getOpenTrades() {
		return openTrades;
	}

	public void setOpenTrades(List<ManagerOpenTrades> openTrades) {
		this.openTrades = openTrades;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		NewOpenTradesEvent newOpenTradesEvent = (NewOpenTradesEvent) o;
		return Objects.equals(this.openTrades, newOpenTradesEvent.openTrades);
	}

	@Override
	public int hashCode() {
		return Objects.hash(openTrades);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class NewOpenTradesEvent {\n");

		sb.append("    openTrades: ").append(toIndentedString(openTrades)).append("\n");
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

