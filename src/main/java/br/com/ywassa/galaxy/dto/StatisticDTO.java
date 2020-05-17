package br.com.ywassa.galaxy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class StatisticDTO {

	private long dryDays;

	private long rainDays;

	private long excellentDays;

	private long maxRainDay;

	private long regularDay;

}
