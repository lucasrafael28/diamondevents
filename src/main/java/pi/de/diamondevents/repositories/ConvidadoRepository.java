package pi.de.diamondevents.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pi.de.diamondevents.models.Convidado;
import pi.de.diamondevents.models.Festa;

public interface ConvidadoRepository extends JpaRepository<Convidado, Long>{

	List<Convidado> findByFesta(Festa festa);
}