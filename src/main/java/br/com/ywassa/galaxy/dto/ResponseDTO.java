package br.com.ywassa.galaxy.dto;

import br.com.ywassa.galaxy.domain.Forecast;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ResponseDTO {

	private long day;

	private Forecast forecast;
}
