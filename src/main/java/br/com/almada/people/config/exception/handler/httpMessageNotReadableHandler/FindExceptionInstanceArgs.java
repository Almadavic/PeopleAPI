package br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler;

import jakarta.servlet.http.HttpServletRequest;

public record FindExceptionInstanceArgs(Throwable rootCause, HttpServletRequest request, Exception exception) {

}
