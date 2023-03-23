package es.aulanosa.gestionfp.validator;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.sql.Time;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EstadoPagoValidator implements ConstraintValidator<EstadoPagoConstraint, Time> {
    @Override
    public void initialize(EstadoPagoConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Time time, ConstraintValidatorContext constraintValidatorContext) {

        Pattern p = java.util.regex.Pattern.compile("MM/dd/yyyy HH:mm:ss");
        Matcher m = p.matcher(time.toString());
        if(m.matches()){
            return true;
        }else{
            return false;
        }
    }
}
