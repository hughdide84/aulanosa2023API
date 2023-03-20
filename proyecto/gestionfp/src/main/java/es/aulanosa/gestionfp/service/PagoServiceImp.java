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
    public Pago guardar(Pago pago) {
        return repositorio.save(pago);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Pago> buscarTodo() {
        return repositorio.findAll();
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<Pago> buscarPorId(Integer id) {
        return repositorio.findById(id);
    }
    @Transactional
    @Override
    public Pago modificar(Pago pago) throws NoSeHaEncontradoException, NoSuchFieldException {
        if (repositorio.findById(pago.getId()).isPresent()) {
            return repositorio.save(pago);
        } else{
            throw new NoSuchFieldException("No existe el pago con id: " + pago.getId());
        }

    }
    @Transactional
    @Override
    public void borrar (Integer id){
            repositorio.deleteById(id);
        }
    @Transactional(readOnly = true)
    @Override
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

