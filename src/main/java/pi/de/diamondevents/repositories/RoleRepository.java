package pi.de.diamondevents.repositories;

import javax.management.relation.Role;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	Role findByNome(String nome);
}