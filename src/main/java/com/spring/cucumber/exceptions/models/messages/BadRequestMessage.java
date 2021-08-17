package com.spring.cucumber.exceptions.models.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class BadRequestMessage extends Message implements Comparable<BadRequestMessage>{
	@Schema(title = "object",
			description = "object",
			example = "User")
	private String object;

	@Schema(title = "field",
			description = "field",
			example = "username")
	private String field;

	@Schema(title = "rejectedValue",
			description = "rejectedValue",
			example = "rahul")
	private Object rejectedValue;

	@Schema(title = "defaultMessage",
			description = "defaultMessage",
			example = "username must be a minimum of 6 and maximum of 12 Characters")
	private String defaultMessage;

	@Override
	public int compareTo(BadRequestMessage badRequestMessage) {
		return this.getField().compareTo(badRequestMessage.getField());
	}
}