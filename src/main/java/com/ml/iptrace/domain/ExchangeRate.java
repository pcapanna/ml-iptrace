package com.ml.iptrace.domain;

import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.Positive;

@Value
public class ExchangeRate {

	@NonNull
	@Positive
	private final Double value;

	public static ExchangeRate of(double value) {
		return new ExchangeRate(Double.valueOf(value));
	}

	public double toDouble() { return this.getValue(); }


}
