package fr.jypast.parisjanitorapi.domain;

public record ApplicationError(String context, String message, Object value, Throwable cause) {}
