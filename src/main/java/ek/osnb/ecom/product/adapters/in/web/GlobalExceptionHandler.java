package ek.osnb.ecom.product.adapters.in.web;

import ek.osnb.ecom.product.core.app.exceptions.NotFoundException;
import ek.osnb.ecom.product.core.domain.exceptions.BusinessLogicException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    public record ApiErrorResponse(String message, int statusCode, String status , String path) {}

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessLogicException(BusinessLogicException ex, HttpServletRequest request) {
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), 400, "BAD_REQUEST", request.getRequestURI());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), 404, "NOT_FOUND", request.getRequestURI());
        return ResponseEntity.status(404).body(response);
    }
}
