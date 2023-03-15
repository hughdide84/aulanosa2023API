package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AlumnoEmpresa;
import es.aulanosa.gestionfp.repository.AlumnoEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlumnoEmpresaServiceImp implements AlumnoEmpresaService {

    @Autowired
    private AlumnoEmpresaRepository alumnoEmpresaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AlumnoEmpresa> findAll() {
        return alumnoEmpresaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public AlumnoEmpresa findById(int id) {
        return alumnoEmpresaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public AlumnoEmpresa save(AlumnoEmpresa alumnosEmpresas) {
        return alumnoEmpresaRepository.save(alumnosEmpresas);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        alumnoEmpresaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public AlumnoEmpresa update(AlumnoEmpresa alumnosEmpresas) throws NoSeHaEncontradoException {
        var a = alumnoEmpresaRepository.findById(alumnosEmpresas.getId());
        if (!a.isEmpty()) {
            return alumnoEmpresaRepository.save(alumnosEmpresas);
        } else {
            throw new NoSeHaEncontradoException("No se ha encontrado el alumnosEmpresas");
        }
    }


}
