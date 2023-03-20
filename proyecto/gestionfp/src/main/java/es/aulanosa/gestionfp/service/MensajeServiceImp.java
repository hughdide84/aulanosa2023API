package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.Estudios;
import es.aulanosa.gestionfp.model.Mensaje;
import es.aulanosa.gestionfp.repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class MensajeServiceImp implements MensajeService {

    @Autowired
    private MensajeRepository repositorio;
    @Override
    @Transactional
    public Mensaje insertarMensaje(Mensaje mensaje) {
        return repositorio.save(mensaje);
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<Mensaje> consultarPorIdMensaje(Integer id) {return repositorio.findById(id);}

    @Override
    @Transactional(readOnly = true)
    public List<Mensaje> consultarTodosMensajes() {return repositorio.findAll();}

    @Override
    @Transactional
    public Mensaje modificarMensaje(Mensaje mensaje) throws NoSuchFieldException{
        if (repositorio.findById(mensaje.getId()).isPresent()){
            return repositorio.save(mensaje);
        }
        else{
            throw new NoSuchFieldException("No existe el mensaje con id: " + mensaje.getId());
        }
    }
    @Override
    @Transactional
    public void eliminarMensaje(Integer id) {repositorio.deleteById(id);}






}