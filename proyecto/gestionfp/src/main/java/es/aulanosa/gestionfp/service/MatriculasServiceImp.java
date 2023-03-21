package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Curso;
import es.aulanosa.gestionfp.model.Matricula;
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
    public Matricula insertarMatricula(Matricula matricula) {
        return matriculasRepository.save(matricula);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Matricula> consultarPorIdMatricula(Integer id) {
        return matriculasRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Matricula> consultarTodasMatriculas() {
        return matriculasRepository.findAll();
    }

    @Override
    @Transactional
    public Matricula modificarMatricula(Matricula matricula) throws NoSuchFieldException {
        if (matriculasRepository.findById(matricula.getId()).isPresent()){
            return matriculasRepository.save(matricula);
        }
        else{
            throw new NoSuchFieldException("No existe la matricula con id: " + matricula.getId());
        }
    }

    @Override
    @Transactional
    public void eliminarMatricula(Integer id) {
        matriculasRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Matricula> buscarPorNombreDeMatricula(String nombre) {
        return matriculasRepository.buscarPorNombre(nombre);
    }

    @Override
    public List<Matricula> buscarPorMesDeMatricula(Integer numMes) {
        return null;
    }

    @Override
    public List<Matricula> buscarPorMesDeMatricula(String nombre) {
        return null;
    }

    @Override
    @Transactional
    public List<Curso> buscarTodosCursosPorId(Integer idCurso) {
        return matriculasRepository.buscarTodosCursosPorId(idCurso);
    }

}
