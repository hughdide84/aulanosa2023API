package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Pago;

import java.util.List;
import java.util.Optional;

public interface PagoService {

    public Pago guardar(Pago pago);
    public List<Pago> buscarTodo();
    public Optional<Pago> buscarPorId(Integer id);
    public Pago modificar(Pago pago) throws NoSeHaEncontradoException, NoSuchFieldException;
    public void borrar(Integer id);

}
