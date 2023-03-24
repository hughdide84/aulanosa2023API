package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.dto.EventoDTO;
import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.Comentario;
import es.aulanosa.gestionfp.repository.AlumnoEmpresaRepository;
import es.aulanosa.gestionfp.repository.AlumnoRepository;
import es.aulanosa.gestionfp.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz de comentario, en la que se especifica qué hará cada cosa
 */
@Service
public class ComentarioServiceImp implements ComentarioService {
    @Autowired
    ComentarioRepository comentarioRepository;
    @Autowired
    AlumnoServiceImp alumnoServiceImp;
    @Autowired
    UsuarioServiceImp usuarioServiceImp;
    @Autowired
    PagoServiceImp pagoServiceImp;
    @Autowired
    private AlumnoEmpresaRepository alumnoEmpresaRepository;

    // Guarda un comentario

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Comentario crear(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    /**
     * {@inheritDoc}
     */
    // Lista un comentario por ID
    @Override
    @Transactional(readOnly = true)
    public Optional<Comentario> listarPorId(Integer id) {
        return comentarioRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    // Lista todos los comentarios
    @Override
    @Transactional(readOnly = true)
    public List<Comentario> listarTodo() {
        return comentarioRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    // Lista todos los comentarios con un sistema y referencia concretos
    @Override
    @Transactional(readOnly = true)
    public List<Comentario> listarPorSistemaYReferencia(char sistema, int referencia) {
        return comentarioRepository.findBySistemaAndReferencia(sistema, referencia);
    }

    /**
     * {@inheritDoc}
     */
    // Actualiza un comentario
    @Override
    @Transactional
    public Comentario actualizar(Comentario comentario) throws NoSeHaEncontradoException {
        Optional<Comentario> comentarioConsultado = comentarioRepository.findById(comentario.getId());

        if (comentarioConsultado.isPresent()) {
            return comentarioRepository.save(comentario);
        } else {
            throw new NoSeHaEncontradoException("No se ha encontrado el comentario");
        }
    }

    /**
     * {@inheritDoc}
     */
    // Borra un comentario por ID
    @Override
    @Transactional
    public void borrarPorId(Integer id) {
        comentarioRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    //devuelve una lista con los campos de la BD que coincidan con los parametros de entrada
    @Override
    @Transactional(readOnly = true)
    public List<Comentario> listarPorSistemaEIdUsuarioComentario(char sistema, int idUC) throws NoSeHaEncontradoException {
        return comentarioRepository.findBySistemaAndIdUsuarioComentario(sistema, idUC);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventoDTO> listarEventosPorUsuario(int idUsuario) {
        var comentarios = comentarioRepository.buscarPorSistemaYUsuario(idUsuario);

        List<EventoDTO> eventos = new ArrayList<>();

        for (var comentario : comentarios) {
            eventos.add(new EventoDTO(comentario.getTexto(), comentario.getFecha()));
        }
        var usuarioOptional = usuarioServiceImp.listarPorId(idUsuario);
        if (usuarioOptional.isPresent()) {
            var alumno = alumnoServiceImp.buscarPorUsuario(usuarioOptional.get().getNombre());
            if (alumno != null) {
                String textoAlumnoInicio = "El alumno " + alumno.getNombre() + " comienza las prácticas el día " + alumno.getInicioPr();
                eventos.add(new EventoDTO(textoAlumnoInicio, alumno.getInicioPr()));
                String textoAlumnoFin = "El alumno " + alumno.getNombre() + " finaliza las prácticas el día " + alumno.getFinPr();
                eventos.add(new EventoDTO(textoAlumnoFin, alumno.getFinPr()));
            }
            var pagos = pagoServiceImp.buscarPorIdUsuario(idUsuario);
            for (var pago : pagos) {
                String estado;
                switch (pago.getEstado()) {
                    case 'C':
                        estado = "confirmado";
                        break;
                    case 'D':
                        estado = "devuelto";
                        break;
                    default:
                        estado = "(estado no reconocido)";
                }
                String textoPago = "El usuario " + usuarioOptional.get().getNombre() + " ha " + estado + " el pago de " + pago.getPago() + "€";
                eventos.add(new EventoDTO(textoPago, pago.getFecha()));
            }
        }

        return eventos;
    }

}