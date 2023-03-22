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

/**
 * Clase para controlar las validaciondes de las clases MODEL, en las que se especifica un requisito con Jakarta,--
 * p.e: @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")--
 * el anterior ejemplo produce una excepción controlada en uno de los métodos de esta clase, que devuelveautomáticamente al controller un body en el que especifica --
 * el error más en concreto.
 * Cada error es independiente y se añade a un LIST de errores, lo que genera más de uno en caso de que los haya
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Método para controlar el error producido por las validaciones del MODEL
     * @param constraintViolationException En este parámetro se especifica qué tipo de error controla, en el que se recoge en este caso un ConstraintViolationException
     * @return Devuelve un body preparado para el Controller en el que se especifica el error/errores
     */
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

    /**
     * Método para controlar fallos en las ejecuciones del Repository, usualmente se debe a la inserción erronea de datos cuando en la BD se necesitan otros--
     * p.e: Se le inserta una clave foránea que no existe
     * @param exception En este parámetro se especifica qué tipo de error controla, en el que se recoge en este caso un DataIntegrityViolationException
     * @return Devuelve un body preparado para el Controller en el que se especifica el error/errores
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        List<ErrorDTO> listaErrores = new ArrayList<>();
        ErrorDTO errorDTO = new ErrorDTO(Errores.COD_ERROR_INTEGRIDAD,"Error de integridad.");
        listaErrores.add(errorDTO);
        return new ResponseEntity<>(listaErrores, HttpStatus.BAD_REQUEST);
    }

    /**
     * Método para controlar fallos en las ejecuciones no controlada anteriormente
     * @param exception En este parámetro se especifica qué tipo de error controla, en el que se recoge en este caso un Exception
     * @return Devuelve un body preparado para el Controller en el que se especifica el error/errores
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleDataIntegrityViolationException(Exception exception) {
        List<ErrorDTO> listaErrores = new ArrayList<>();
        ErrorDTO errorDTO = new ErrorDTO(Errores.COD_ERROR_INTERNO,"Error interno del sistema : "+exception.getMessage());
        listaErrores.add(errorDTO);
        return new ResponseEntity<>(listaErrores, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
