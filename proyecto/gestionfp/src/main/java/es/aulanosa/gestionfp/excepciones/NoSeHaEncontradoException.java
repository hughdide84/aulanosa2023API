package es.aulanosa.gestionfp.excepciones;

/**
 * Clase para la excepción en caso de que no se ecuentre algun dato, se utiliza manualmente y se especifica generalmente en la clase Service,--
 * aunque se usa en los test o en el controller para controlal un fallo en los datos proporcionados y devolver un error acorde
 */
public class NoSeHaEncontradoException extends Exception{

    public NoSeHaEncontradoException(String mensaje){
        super(mensaje);
    }

    public NoSeHaEncontradoException(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
}
