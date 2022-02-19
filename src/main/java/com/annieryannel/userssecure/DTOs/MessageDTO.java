package com.annieryannel.userssecure.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    @JsonProperty(namespace = "fromUsername")
    private String fromUsername;

    @JsonProperty(namespace = "message")
    private String message;
}
