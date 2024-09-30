package io.gtd.be.errorHandling;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ApiError(HttpStatus status, String message, List<String> errors) {
}
