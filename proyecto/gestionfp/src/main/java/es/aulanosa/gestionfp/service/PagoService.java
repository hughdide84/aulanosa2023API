package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Pago;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PagoService {
    /**
     * Guarda un pago en la base de datos.
     * @param pago el objeto Pago a guardar.
     * @return el pago guardado.
     */
    public Pago guardarPago(Pago pago);
    /**
     * Busca todos los pagos en la base de datos.
     * @return una lista con todos los pagos.
     */
    public List<Pago> buscarTodosPagos();
    /**
     * Busca un pago por su identificador en la base de datos.
     * @param id el identificador del pago a buscar.
     * @return el pago encontrado, o null si no existe.
     */
    public Optional<Pago> buscarPorIdPago(Integer id);
    /**
     * Modifica un pago existente en la base de datos.
     * @param pago el pago modificado.
     * @return el pago modificado.
     * @throws NoSeHaEncontradoException si el pago no se encuentra en la base de datos.
     * @throws NoSuchFieldException si algún campo del pago es incorrecto.
     */
    public Pago modificarPago(Pago pago) throws NoSeHaEncontradoException, NoSuchFieldException;
    /**
     * Borra un pago de la base de datos.
     * @param id el identificador del pago a borrar.
     */
    public void borrarPago(Integer id);
    /**
     * Lista todos los pagos de una matrícula en la base de datos.
     * @param idMatricula el identificador de la matrícula.
     * @return una lista con todos los pagos de la matrícula.
     * @throws NoSeHaEncontradoException si la matrícula no existe en la base de datos.
     */
    @Transactional(readOnly = true)
    List<Pago> listarPorMatricula(int idMatricula) throws NoSeHaEncontradoException;
}
