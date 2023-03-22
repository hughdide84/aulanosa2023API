package es.aulanosa.gestionfp.repository;
/**
 Interfaz que extiende de JpaRepository y define los métodos para acceder a la entidad Pago en la base de datos.
 */
import es.aulanosa.gestionfp.model.Matricula;
import es.aulanosa.gestionfp.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
@Repository
public interface PagoRepository extends JpaRepository <Pago,Integer> {
    /**
     * Método que permite buscar todos los pagos asociados a una matrícula específica.
     * @param id el identificador de la matrícula
     * @return una lista de objetos Pago que corresponden a la matrícula especificada.
     */
    public List<Pago> findAllByIdMatricula (Integer id);

}
