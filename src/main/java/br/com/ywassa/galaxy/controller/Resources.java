package br.com.ywassa.galaxy.controller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Resources {
	public static final String TOKEN_VERSION = "1";
	private static final String VERSION_APP = "/v" + TOKEN_VERSION;
	private static final String API = "/api";
	private static final String APP = API + VERSION_APP;

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static final class WeatherResource {
		public static final String ROOT = APP + "/weather";

		public static final String TOKEN_DAY = "day";
	}
}
