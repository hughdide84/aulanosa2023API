package es.aulanosa.gestionfp.excepciones;

import es.aulanosa.gestionfp.dto.ErrorDTO;
import es.aulanosa.gestionfp.util.Errores;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException constraintViolationException) {
        Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
        List<ErrorDTO> listaErrores = new ArrayList<>();
        if (!violations.isEmpty()) {
            for(ConstraintViolation violation : violations) {
                ErrorDTO errorDTO = new ErrorDTO(Errores.COD_ERROR_DATOS_ENTRADA,violation.getMessage());
                listaErrores.add(errorDTO);
            }
        } else {
            ErrorDTO errorDTO = new ErrorDTO(Errores.COD_ERROR_DATOS_ENTRADA,"Error indeterminado.");
            listaErrores.add(errorDTO);
        }
        return new ResponseEntity<>(listaErrores, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        List<ErrorDTO> listaErrores = new ArrayList<>();
        ErrorDTO errorDTO = new ErrorDTO(Errores.COD_ERROR_INTEGRIDAD,"Error de integridad.");
        listaErrores.add(errorDTO);
        return new ResponseEntity<>(listaErrores, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleDataIntegrityViolationException(Exception exception) {
        List<ErrorDTO> listaErrores = new ArrayList<>();
        ErrorDTO errorDTO = new ErrorDTO(Errores.COD_ERROR_INTERNO,"Error interno del sistema : "+exception.getMessage());
        listaErrores.add(errorDTO);
        return new ResponseEntity<>(listaErrores, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
