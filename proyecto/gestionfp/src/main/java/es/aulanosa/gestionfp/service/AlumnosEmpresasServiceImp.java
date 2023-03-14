package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AlumnosEmpresas;
import es.aulanosa.gestionfp.repository.AlumnosEmpresasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlumnosEmpresasServiceImp implements AlumnosEmpresasService{

    @Autowired
    private AlumnosEmpresasRepository alumnosEmpresasRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AlumnosEmpresas> findAll() {
        return alumnosEmpresasRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public AlumnosEmpresas findById(int id) {
        return alumnosEmpresasRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public AlumnosEmpresas save(AlumnosEmpresas alumnosEmpresas) {
        return alumnosEmpresasRepository.save(alumnosEmpresas);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        alumnosEmpresasRepository.deleteById(id);
    }

    @Override
    @Transactional
    public AlumnosEmpresas update(AlumnosEmpresas alumnosEmpresas) throws NoSeHaEncontradoException {
        var a = alumnosEmpresasRepository.findById(alumnosEmpresas.getId());
        if (!a.isEmpty()) {
            return alumnosEmpresasRepository.save(alumnosEmpresas);
        } else {
            throw new NoSeHaEncontradoException("No se ha encontrado el alumnosEmpresas");
        }
    }


}
