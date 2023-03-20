package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.AlumnoEmpresa;
import es.aulanosa.gestionfp.model.Empresa;
import es.aulanosa.gestionfp.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpresaServiceImp implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private AlumnoEmpresaServiceImp alumnoEmpresaServiceImp;

    //busca todas las empresas
    @Override
    @Transactional(readOnly = true)
    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    //busca una empresa por su id
    @Override
    @Transactional(readOnly = true)
    public Empresa findById(int id) {
        return empresaRepository.findById(id).orElse(null);
    }

    //busca todas las empresas por nombre
    @Override
    @Transactional(readOnly = true)
    public List<Empresa> findAllByNombre(String nombre) {
        return empresaRepository.findAllByNombre(nombre);
    }

    //guarda una empresa
    @Override
    @Transactional
    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    //borra una empresa por id
    @Override
    @Transactional
    public void deleteById(int id) {
        empresaRepository.deleteById(id);
    }

    //actualiza una empresa
    @Override
    @Transactional
    public Empresa update(Empresa empresa) throws NoSeHaEncontradoException {
        if (empresaRepository.findById(empresa.getId()) == null) {
            return empresaRepository.save(empresa);
        } else {
            throw new NoSeHaEncontradoException("No se ha encontrado la empresa");
        }
    }

    //busca las empresas que tienen alumnos en un curso de estudios
    @Override
    @Transactional(readOnly = true)
    public Map<Empresa, List<Alumno>> buscarEmpresaConAlumnosPorCursoEstudio(int idCurso, int idEstudios) {
        var a = empresaRepository.buscarEmpresaConAlumnosPorCursoEstudio(idCurso, idEstudios);
        Map<Empresa, List<Alumno>> map = new HashMap<>();

        for (Empresa empresa : a) {

            List<Alumno> alumnos = alumnoEmpresaServiceImp.buscarTodosAlumnosPorIdEmpresa(empresa.getId());

            map.put(empresa, alumnos);
        }
        return map;
    }

}
