package br.com.ywassa.galaxy.dto;

import br.com.ywassa.galaxy.domain.Forecast;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO {

	private long day;

	private Forecast forecast;
}
