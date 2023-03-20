package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Pago;
import es.aulanosa.gestionfp.repository.MatriculasRepository;
import es.aulanosa.gestionfp.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PagoServiceImp implements PagoService {

    @Autowired
    private PagoRepository repositorio;
    @Transactional
    @Override
    //guarda un pago nuevo en la BD
    public Pago guardarPago(Pago pago) {
        return repositorio.save(pago);
    }
    @Transactional(readOnly = true)
    @Override
    //devuelve una lista de todos los pagos
    public List<Pago> buscarTodosPagos() {
        return repositorio.findAll();
    }
    @Transactional(readOnly = true)
    @Override
    //devuelve el pago que coincida con el id introducido
    public Optional<Pago> buscarPorIdPago(Integer id) {
        return repositorio.findById(id);
    }
    @Transactional
    @Override
    //modifica un pago existente
    public Pago modificarPago(Pago pago) throws NoSeHaEncontradoException, NoSuchFieldException {
        if (repositorio.findById(pago.getId()).isPresent()) {
            return repositorio.save(pago);
        } else{
            throw new NoSuchFieldException("No existe el pago con id: " + pago.getId());
        }

    }
    @Transactional
    @Override
    //borra un pago existente a partir de el id proporcionado
    public void borrarPago(Integer id){
            repositorio.deleteById(id);
        }
    @Transactional(readOnly = true)
    @Override
    //devuelve una lista de pagos a partir de un idMatricula que se le pase
    public List<Pago> listarPorMatricula(int idMatricula) throws NoSeHaEncontradoException {


        List<Pago> pagos = new ArrayList<>();

        if(!repositorio.findAllByIdMatricula(idMatricula).isEmpty()){
            pagos = repositorio.findAllByIdMatricula(idMatricula);
            return pagos;
        }else{
            throw new NoSeHaEncontradoException("No existe el idMatricula");
        }

    }
}
