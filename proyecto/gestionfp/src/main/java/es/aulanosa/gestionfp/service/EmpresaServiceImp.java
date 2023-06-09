package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Empresa;
import es.aulanosa.gestionfp.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaServiceImp implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    //busca todas las empresas

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    //busca una empresa por su id
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Empresa findById(int id) {
        return empresaRepository.findById(id).orElse(null);
    }

    //busca todas las empresas por nombre
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Empresa> findAllByNombre(String nombre) {
        return empresaRepository.findAllByNombre(nombre);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Empresa> buscarEmpresasPorCursoYEstudios(Integer idCurso, Integer idEstudios) throws NoSeHaEncontradoException {
        if(empresaRepository.findAllByIdCursoAndIdEstudios(idCurso, idEstudios).isEmpty()){
            throw new NoSeHaEncontradoException("No hay coincidencias en la BD con los datos proporcionados");
        }else{
            return empresaRepository.findAllByIdCursoAndIdEstudios(idCurso, idEstudios);
        }
    }

    //guarda una empresa
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    //borra una empresa por id
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteById(int id) {
        empresaRepository.deleteById(id);
    }

    //actualiza una empresa
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Empresa update(Empresa empresa) throws NoSeHaEncontradoException {
        if (empresaRepository.findById(empresa.getId()) == null) {
            return empresaRepository.save(empresa);
        } else {
            throw new NoSeHaEncontradoException("No se ha encontrado la empresa");
        }
    }

}
