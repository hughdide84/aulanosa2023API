package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Curso;
import es.aulanosa.gestionfp.model.Matricula;
import es.aulanosa.gestionfp.repository.MatriculasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatriculasServiceImp implements MatriculasService{

    @Autowired
    MatriculasRepository matriculasRepository;

    @Override
    @Transactional
    //inserta una nueva matricula en la BD
    public Matricula insertarMatricula(Matricula matricula) {
        return matriculasRepository.save(matricula);
    }

    @Override
    @Transactional(readOnly = true)
    //devuelve una matricula a partir de un id especificado
    public Optional<Matricula> consultarPorIdMatricula(Integer id) {
        return matriculasRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    //devuelve una lista de todas las matriculas
    public List<Matricula> consultarTodasMatriculas() {
        return matriculasRepository.findAll();
    }

    @Override
    @Transactional
    //modifica una matricula existente en la BD
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
    //elimina una matricula existente a partir de un id especificado
    public void eliminarMatricula(Integer id) {
        matriculasRepository.deleteById(id);
    }

    @Override
    @Transactional (readOnly = true)
    //devuelve una lista de matriculas a partir de un nombre especificado
    public List<Matricula> buscarPorNombreDeMatricula(String nombre) {
        return matriculasRepository.buscarPorNombre(nombre);
    }

    @Override
    @Transactional (readOnly = true)
    //devuelve una lista de matriculas a partir de un idCurso existente
    public List<Matricula> buscarTodosCursosPorId(Integer idCurso) {
        return matriculasRepository.findAllByidCurso(idCurso);
    }

    //devuelve unal ista con las matriculas que tienen saldo menor que 0, o un error diciendo que no hay ninguna
    @Override
    public List<Matricula> buscaridCursoSaldoNegativo(Integer idCurso) throws NoSeHaEncontradoException {
        if(!matriculasRepository.buscaridCursoSaldoNegativo(idCurso).isEmpty()){
            return matriculasRepository.buscaridCursoSaldoNegativo(idCurso);
        }else{
            throw new NoSeHaEncontradoException("No hay ninguna matrícula con salgo negativo en el idCurso " + idCurso);
        }
    }

    @Override
    @Transactional (readOnly = true)
    //devuelve una lista de las matriculas a partir de un mes especificado
    public List<Matricula> buscarPorMesDeMatricula(Integer mes) {

        List<Matricula> matriculaFecha = matriculasRepository.buscarPorMes(mes);

        return matriculaFecha;




    }
}
