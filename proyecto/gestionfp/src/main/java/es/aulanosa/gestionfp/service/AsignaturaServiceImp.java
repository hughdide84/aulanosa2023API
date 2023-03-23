package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Asignatura;
import es.aulanosa.gestionfp.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * Esta interfaz define los métodos que se pueden utilizar para interactuar con la entidad de asignatura.
 */
@Service
public class AsignaturaServiceImp implements AsignaturaService{

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)

    public List<Asignatura> buscarTodoAsignatura() {
        return asignaturaRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Asignatura buscarPorIdAsignatura(int id) {
        return asignaturaRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Asignatura> buscarTodoPorNombreAsignaturaConteniendoNombreAsignatura(String nombre) {
        return asignaturaRepository.findAllByNombreContaining(nombre);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Asignatura guardarAsignatura(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void borrarPorIdAsignatura(int id) {
        asignaturaRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Asignatura modificarAsignatura(Asignatura asignatura) {
        var asignaturaEncontrada = asignaturaRepository.findById(asignatura.getId());
        if (!asignaturaEncontrada.isEmpty()) {
            return asignaturaRepository.save(asignatura);
        } else {
            return null;
        }
    }
}
