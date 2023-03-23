package es.aulanosa.gestionfp.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EstadoCursoValidator implements ConstraintValidator<EstadoCursoConstraint, Character> {

    @Override
    public void initialize(EstadoCursoConstraint estadoCursoConstraint) {
    }

    @Override
    public boolean isValid(Character s, ConstraintValidatorContext constraintValidatorContext) {
        boolean resultado = false;

        if ((s == 'B') || (s == 'A') || (s == 'F')) {
            resultado = true;
        }

        return resultado;
    }


}
