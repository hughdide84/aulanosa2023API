package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.AlumnoEmpresa;
import es.aulanosa.gestionfp.model.Empresa;
import es.aulanosa.gestionfp.repository.AlumnoEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlumnoEmpresaServiceImp implements AlumnoEmpresaService {

    @Autowired
    private AlumnoEmpresaRepository alumnoEmpresaRepository;

    //busca todo
    @Override
    @Transactional(readOnly = true)
    public List<AlumnoEmpresa> buscarTodoAlumnoEmpresa() {
        return alumnoEmpresaRepository.findAll();
    }

    //busca por id
    @Override
    @Transactional(readOnly = true)
    public AlumnoEmpresa buscarPorIdAlumnoEmpresa(int id) {
        return alumnoEmpresaRepository.findById(id).orElse(null);
    }

    //guarda
    @Override
    @Transactional
    public AlumnoEmpresa guardarAlumnoEmpresa(AlumnoEmpresa alumnosEmpresas) {
        return alumnoEmpresaRepository.save(alumnosEmpresas);
    }

    //borra por id
    @Override
    @Transactional
    public void borrarPorIdAlumnoEmpresa(int id) {
        alumnoEmpresaRepository.deleteById(id);
    }

    //modifica
    @Override
    @Transactional
    public AlumnoEmpresa modificarAlumnoEmpresa(AlumnoEmpresa alumnosEmpresas) throws NoSeHaEncontradoException {
        var a = alumnoEmpresaRepository.findById(alumnosEmpresas.getId());
        if (!a.isEmpty()) {
            return alumnoEmpresaRepository.save(alumnosEmpresas);
        } else {
            throw new NoSeHaEncontradoException("No se ha encontrado el alumnosEmpresas");
        }
    }

    //busca todos los alumnos por id de empresa
    @Override
    @Transactional(readOnly = true)
    public List<Alumno> buscarTodosAlumnosPorIdEmpresa(int empresaId) {
        return alumnoEmpresaRepository.findAllAlumnoByEmpresaId(empresaId);
    }

    //busca todas las empresas por id de alumno
    @Override
    @Transactional(readOnly = true)
    public List<Empresa> buscarTodasEmpresasPorIdAlumno(int alumnoId) {
        return alumnoEmpresaRepository.findAllEmpresaByAlumnoId(alumnoId);
    }



}
