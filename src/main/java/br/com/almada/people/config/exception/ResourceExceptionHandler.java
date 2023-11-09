package br.com.almada.people.config.exception;

import br.com.almada.people.config.exception.standardError.beanValidationStandardError.StandardErrorFieldsNotValid;
import br.com.almada.people.config.exception.standardError.beanValidationStandardError.ValidationErrorCollection;
import br.com.almada.people.config.exception.standardError.commomStandardError.StandardError;
import br.com.almada.people.service.customException.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class ResourceExceptionHandler {

    private final MessageSource messageSource;

    private final Logger logger = LoggerFactory.getLogger(ResourceExceptionHandler.class);

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorCollection> handleValidations(MethodArgumentNotValidException exception, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        String error = "There is one or more invalid parameters";

        List<StandardErrorFieldsNotValid> errorFieldsNotValids = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String field = e.getField();
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            errorFieldsNotValids.add(new StandardErrorFieldsNotValid(field, message));
        });
        log(exception);
        return ResponseEntity.status(status).body(new ValidationErrorCollection(Instant.now(), status.value(), error, path, errorFieldsNotValids));

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> methodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Information being passed wrong to the server", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<StandardError> httpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException exception, HttpServletRequest request) {
        return handlingException(exception, request, "This media type is not acceptable", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<StandardError> missingServletRequestParameterException(MissingServletRequestParameterException exception, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        String message = exception.getMessage().split("'")[1] + " cannot be null";

        StandardError err = new StandardError(Instant.now(), status.value(), "Missing parameter value", message, request.getRequestURI());
        log(exception);
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<StandardError> methodNotSupportedException(HttpRequestMethodNotSupportedException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Method not allowed", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = PropertyReferenceException.class)
    public ResponseEntity<StandardError> propertyReference(PropertyReferenceException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Property Reference error", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidEmailFormatException.class)
    public ResponseEntity<StandardError> invalidEmailFormat(InvalidEmailFormatException exception, HttpServletRequest request) {
        return handlingException(exception, request, "invalid e-mail", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidNameFormatException.class)
    public ResponseEntity<StandardError> invalidNameFormat(InvalidNameFormatException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Invalid name", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmailAlreadyRegisteredException.class)
    public ResponseEntity<StandardError> emailAlreadyRegistered(EmailAlreadyRegisteredException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Registering user error", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = InvalidEnumValueException.class)
    public ResponseEntity<StandardError> invalidEnumValue(InvalidEnumValueException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Invalid value", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Resource not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<StandardError> invalidPassword(InvalidPasswordException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Invalid Password", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardError> genericException(RuntimeException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<StandardError> handlingException(Exception exception, HttpServletRequest request, String error, HttpStatus status) {
        StandardError err = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
        log(exception);
        return ResponseEntity.status(status).body(err);
    }

    private void log(Throwable exception) {
        logger.error("error message {}. Details:", exception.getMessage(), exception);
    }

}
