package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AsignaturaHorario;
import es.aulanosa.gestionfp.repository.AsignaturaHorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaHorarioServiceImp implements AsignaturaHorarioService{

    @Autowired
    AsignaturaHorarioRepository repository;

    @Override
    @Transactional(readOnly = true)

    //busca asignaturaHorario en la BD por el id que se le pasa
    public Optional<AsignaturaHorario> buscarPorId(Integer id) throws NoSeHaEncontradoException {
        Optional<AsignaturaHorario> asignaturaHorario = repository.findById(id);

        if(asignaturaHorario.isPresent()){
            return asignaturaHorario;
        }else{
            throw new NoSeHaEncontradoException("El id proporcionado no consta en la base de datos");
        }
    }

    @Override
    @Transactional
    //inserta una asignaturahorario en la base de datos, no controla que este en la base de datos porque el id es autoIncremental y los demás campos se pueden repetir
    public AsignaturaHorario insertar(AsignaturaHorario asignaturaHorario) {
        return repository.save(asignaturaHorario);
    }

    @Override
    @Transactional
    //busca con el id del objeto que se le pasa si existe en la base de datos, en caso de que lo haga se modifica
    public AsignaturaHorario modificar(AsignaturaHorario asignaturaHorario) throws NoSeHaEncontradoException {
        Optional<AsignaturaHorario> asignaturaHorarioConsultada = repository.findById(asignaturaHorario.getId());

        if(asignaturaHorarioConsultada.isPresent()){
            return repository.save(asignaturaHorarioConsultada.get());
        }else{
            throw new NoSeHaEncontradoException("El id proporcionado no consta en la base de datos");
        }

    }

    @Override
    @Transactional(readOnly = true)

    //busca los horarios que se relacionen con los cursos a traves de idAsignatura, que es un campo de asignaturaHorario
    public List<AsignaturaHorario> buscarPorCurso(int idAsignatura) throws NoSeHaEncontradoException {
        List<AsignaturaHorario> asignaturaHorarioConsultada = repository.findByIdAsignatura(idAsignatura);

        if(!asignaturaHorarioConsultada.isEmpty()){
            return repository.findByIdAsignatura(idAsignatura);
        }else{
            throw new NoSeHaEncontradoException("El id de la asignatura no consta en la base de datos");
        }

    }

    @Override
    @Transactional
    //elimina de la base de datos la asignatura horario con el id que se le pase
    public void eliminar(int id) throws NoSeHaEncontradoException {
        Optional<AsignaturaHorario> asignaturaHorarioConsultada = repository.findById(id);

        if(asignaturaHorarioConsultada.isPresent()){
            repository.deleteById(id);
        }else{
            throw new NoSeHaEncontradoException("En id proporcionado no existe en la base de datos");
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<AsignaturaHorario> listarTodo(){
        List<AsignaturaHorario> lista = repository.findAll();
        return lista;
    }
}
