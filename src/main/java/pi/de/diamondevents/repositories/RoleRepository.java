package pi.de.diamondevents.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pi.de.diamondevents.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	Role findByNome(String nome);

	Iterable<Role> findAllByOrderByNomeAsc();
}