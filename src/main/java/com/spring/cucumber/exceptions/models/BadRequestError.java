package com.spring.cucumber.exceptions.models;

import com.spring.cucumber.exceptions.models.messages.BadRequestMessage;
import lombok.*;

import java.util.List;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BadRequestError extends ApiErrorResponse {
    List<BadRequestMessage> messages;
}
