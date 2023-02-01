package pi.de.diamondevents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pi.de.diamondevents.models.Festa;

@Repository
public interface FestaRepository extends JpaRepository<Festa, Long>{

}