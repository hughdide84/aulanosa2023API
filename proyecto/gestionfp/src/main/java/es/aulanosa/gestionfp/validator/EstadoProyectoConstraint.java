package es.aulanosa.gestionfp.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EstadoProyectoValidator.class)
public @interface EstadoProyectoConstraint {

    String message() default "EstadoTutoria debe un de los siguientes valores (P|A|F)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
