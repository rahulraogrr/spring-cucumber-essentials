package com.spring.cucumber.exceptions.models.messages;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonSubTypes({
	@Type(name = "BadRequestMessage",value = BadRequestMessage.class),
	@Type(name = "NotFoundMessage",value = NotFoundMessage.class),
	@Type(name = "InternalServerMessage",value = InternalServerMessage.class),
})
public abstract class Message {
	
}