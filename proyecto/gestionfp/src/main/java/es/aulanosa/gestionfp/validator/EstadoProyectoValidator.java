package es.aulanosa.gestionfp.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EstadoProyectoValidator  implements ConstraintValidator<EstadoProyectoConstraint, Character> {


    @Override
    public void initialize(EstadoProyectoConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(Character c, ConstraintValidatorContext constraintValidatorContext) {
        boolean resultado = false;

        if ((c == 'P') || (c == 'A') || (c == 'F')) {
            resultado = true;
        }

        return resultado;
    }
}

