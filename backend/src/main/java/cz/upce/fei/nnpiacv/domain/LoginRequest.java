package cz.upce.fei.nnpiacv.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginRequest(
    @JsonProperty("user") String username,
    @JsonProperty("pass") String password
) {
}