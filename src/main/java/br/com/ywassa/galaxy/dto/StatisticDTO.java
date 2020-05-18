package br.com.ywassa.galaxy.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class StatisticDTO {

	private long dryDays;

	private long rainDays;

	private long excellentDays;

	private long maxRainDay;

	private long regularDay;

}
