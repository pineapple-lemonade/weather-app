package ru.itis.ruzavin.web.helper;

import java.util.Map;

public class TextHelper {
	public static String makeResult(StringBuilder content){
		Map<String, String> json1 = JsonHelper.parseJson(content.toString());

		StringBuilder result = new StringBuilder();

		result.append("description:" ).append(json1.get("description")).append("\n");
		result.append("temperature: ").append(json1.get("temp")).append("\n");
		result.append("feels like: ").append(json1.get("feels_like")).append("\n");
		result.append("min temperature: ").append(json1.get("temp_min")).append("\n");
		result.append("max temperature: ").append(json1.get("temp_max")).append("\n");
		result.append("pressure: ").append(json1.get("pressure")).append("\n");
		result.append("humidity: ").append(json1.get("humidity")).append("\n");
		result.append("speed of wind: ").append(json1.get("wind_speed")).append("\n");
		result.append("name: ").append(json1.get("name")).append("\n");

		return result.toString();
	}
}
