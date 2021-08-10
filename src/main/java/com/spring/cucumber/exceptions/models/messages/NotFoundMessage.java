package com.spring.cucumber.exceptions.models.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class NotFoundMessage {

	@Schema(title = "path",
			description = "path",
			example = "/api/v1/users/"
	)
	public String path;

	@Schema(title = "defaultMessage",
			description = "defaultMessage",
			example = "User was not found for parameters rahulraogrr"
	)
	public String defaultMessage;
}
