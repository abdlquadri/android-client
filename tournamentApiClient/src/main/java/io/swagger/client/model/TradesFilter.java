/*
 * Tournament Core API
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

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;

/**
 * TradesFilter
 */

public class TradesFilter
{
	@SerializedName("participantId")
	private UUID participantId = null;

	@SerializedName("symbol")
	private String symbol = null;

	@SerializedName("direction")
	private DirectionEnum direction = null;

	@SerializedName("skip")
	private Integer skip = null;

	@SerializedName("take")
	private Integer take = null;

	public TradesFilter participantId(UUID participantId) {
		this.participantId = participantId;
		return this;
	}

	/**
	 * Get participantId
	 *
	 * @return participantId
	 **/
	@ApiModelProperty(required = true, value = "")
	public UUID getParticipantId() {
		return participantId;
	}

	public void setParticipantId(UUID participantId) {
		this.participantId = participantId;
	}

	public TradesFilter symbol(String symbol) {
		this.symbol = symbol;
		return this;
	}

	/**
	 * Get symbol
	 *
	 * @return symbol
	 **/
	@ApiModelProperty(value = "")
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public TradesFilter direction(DirectionEnum direction) {
		this.direction = direction;
		return this;
	}

	/**
	 * Get direction
	 *
	 * @return direction
	 **/
	@ApiModelProperty(value = "")
	public DirectionEnum getDirection() {
		return direction;
	}

	public void setDirection(DirectionEnum direction) {
		this.direction = direction;
	}

	public TradesFilter skip(Integer skip) {
		this.skip = skip;
		return this;
	}

	/**
	 * Get skip
	 *
	 * @return skip
	 **/
	@ApiModelProperty(value = "")
	public Integer getSkip() {
		return skip;
	}

	public void setSkip(Integer skip) {
		this.skip = skip;
	}

	public TradesFilter take(Integer take) {
		this.take = take;
		return this;
	}

	/**
	 * Get take
	 *
	 * @return take
	 **/
	@ApiModelProperty(value = "")
	public Integer getTake() {
		return take;
	}

	public void setTake(Integer take) {
		this.take = take;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TradesFilter tradesFilter = (TradesFilter) o;
		return Objects.equals(this.participantId, tradesFilter.participantId) &&
				Objects.equals(this.symbol, tradesFilter.symbol) &&
				Objects.equals(this.direction, tradesFilter.direction) &&
				Objects.equals(this.skip, tradesFilter.skip) &&
				Objects.equals(this.take, tradesFilter.take);
	}

	@Override
	public int hashCode() {
		return Objects.hash(participantId, symbol, direction, skip, take);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TradesFilter {\n");

		sb.append("    participantId: ").append(toIndentedString(participantId)).append("\n");
		sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n");
		sb.append("    direction: ").append(toIndentedString(direction)).append("\n");
		sb.append("    skip: ").append(toIndentedString(skip)).append("\n");
		sb.append("    take: ").append(toIndentedString(take)).append("\n");
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

	/**
	 * Gets or Sets direction
	 */
	@JsonAdapter(DirectionEnum.Adapter.class)
	public enum DirectionEnum
	{
		BUY("Buy"),

		SELL("Sell");

		public static DirectionEnum fromValue(String text) {
			for (DirectionEnum b : DirectionEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		DirectionEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<DirectionEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final DirectionEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public DirectionEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return DirectionEnum.fromValue(String.valueOf(value));
			}
		}
	}

}

