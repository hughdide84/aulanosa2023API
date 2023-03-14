package es.aulanosa.gestionfp.excepciones;

public class NoSeHaEncontradoException extends Exception {
    public NoSeHaEncontradoException(String mensaje){
        super(mensaje);
    }
}
