package com.ml.iptrace.domain;

import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.Positive;
import java.io.Serializable;

@Value
public class ExchangeRate implements Serializable {

	@NonNull
	@Positive
	private final Double value;

	public static ExchangeRate of(double value) {
		return new ExchangeRate(Double.valueOf(value));
	}

	public double toDouble() { return this.getValue(); }


}
