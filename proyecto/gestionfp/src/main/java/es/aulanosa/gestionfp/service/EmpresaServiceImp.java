package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Empresa;
import es.aulanosa.gestionfp.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpresaServiceImp implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

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

}
