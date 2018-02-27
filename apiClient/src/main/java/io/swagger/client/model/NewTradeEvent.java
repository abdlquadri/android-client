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
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;

/**
 * NewTradeEvent
 */

public class NewTradeEvent
{
	@SerializedName("managerAccountId")
	private UUID managerAccountId = null;

	@SerializedName("trades")
	private List<OrderModel> trades = new ArrayList<OrderModel>();

	public NewTradeEvent managerAccountId(UUID managerAccountId) {
		this.managerAccountId = managerAccountId;
		return this;
	}

	/**
	 * Get managerAccountId
	 *
	 * @return managerAccountId
	 **/
	@ApiModelProperty(required = true, value = "")
	public UUID getManagerAccountId() {
		return managerAccountId;
	}

	public void setManagerAccountId(UUID managerAccountId) {
		this.managerAccountId = managerAccountId;
	}

	public NewTradeEvent trades(List<OrderModel> trades) {
		this.trades = trades;
		return this;
	}

	public NewTradeEvent addTradesItem(OrderModel tradesItem) {
		this.trades.add(tradesItem);
		return this;
	}

	/**
	 * Get trades
	 *
	 * @return trades
	 **/
	@ApiModelProperty(required = true, value = "")
	public List<OrderModel> getTrades() {
		return trades;
	}

	public void setTrades(List<OrderModel> trades) {
		this.trades = trades;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		NewTradeEvent newTradeEvent = (NewTradeEvent) o;
		return Objects.equals(this.managerAccountId, newTradeEvent.managerAccountId) &&
				Objects.equals(this.trades, newTradeEvent.trades);
	}

	@Override
	public int hashCode() {
		return Objects.hash(managerAccountId, trades);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class NewTradeEvent {\n");

		sb.append("    managerAccountId: ").append(toIndentedString(managerAccountId)).append("\n");
		sb.append("    trades: ").append(toIndentedString(trades)).append("\n");
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
