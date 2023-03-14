package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Asignatura;
import es.aulanosa.gestionfp.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AsignaturaServiceImp implements AsignaturaService{

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Asignatura> findAll() {
        return asignaturaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Asignatura findById(int id) {
        return asignaturaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Asignatura> findAllByNombre(String nombre) {
        return asignaturaRepository.findAllByNombre(nombre);
    }

    @Override
    @Transactional
    public Asignatura save(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        asignaturaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Asignatura update(Asignatura asignatura) {
        var asignaturaEncontrada = asignaturaRepository.findById(asignatura.getId());
        if (!asignaturaEncontrada.isEmpty()) {
            return asignaturaRepository.save(asignatura);
        } else {
            return null;
        }
    }
}
