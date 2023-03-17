package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Matriculas;
import es.aulanosa.gestionfp.repository.MatriculasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculasServiceImp implements MatriculasService{

    @Autowired
    MatriculasRepository matriculasRepository;

    @Override
    @Transactional
    public Matriculas insertar(Matriculas matriculas) {
        return matriculasRepository.save(matriculas);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Matriculas> consultarPorId(Integer id) {
        return matriculasRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Matriculas> consultarTodos() {
        return matriculasRepository.findAll();
    }

    @Override
    @Transactional
    public Matriculas modificar(Matriculas matriculas) throws NoSuchFieldException {
        if (matriculasRepository.findById(matriculas.getId()).isPresent()){
            return matriculasRepository.save(matriculas);
        }
        else{
            throw new NoSuchFieldException("No existe la matricula con id: " + matriculas.getId());
        }
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        matriculasRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Matriculas> buscarPorNombre(String nombre) {
        return matriculasRepository.buscarPorNombre(nombre);
    }
}
