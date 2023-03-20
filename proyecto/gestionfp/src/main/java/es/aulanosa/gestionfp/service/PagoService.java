package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Pago;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PagoService {

    public Pago guardarPago(Pago pago);
    public List<Pago> buscarTodosPagos();
    public Optional<Pago> buscarPorIdPago(Integer id);
    public Pago modificarPago(Pago pago) throws NoSeHaEncontradoException, NoSuchFieldException;
    public void borrarPago(Integer id);

    @Transactional(readOnly = true)
    List<Pago> listarPorMatricula(int idMatricula) throws NoSeHaEncontradoException;
}
