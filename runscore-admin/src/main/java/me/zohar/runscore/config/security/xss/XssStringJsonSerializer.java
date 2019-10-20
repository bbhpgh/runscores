package me.zohar.runscore.config.security.xss;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import cn.hutool.core.util.EscapeUtil;

public class XssStringJsonSerializer extends JsonSerializer<String> {
	
	@Override
	public Class<String> handledType() {
		return String.class;
	}

	@Override
	public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException {
		if (value != null) {
			String encodedValue = EscapeUtil.escapeHtml4(value);
			jsonGenerator.writeString(encodedValue);
		}
	}
}
