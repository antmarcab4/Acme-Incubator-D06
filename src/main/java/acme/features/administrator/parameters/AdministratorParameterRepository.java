
package acme.features.administrator.parameters;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.parameters.Parameter;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorParameterRepository extends AbstractRepository {

	@Query("select p from Parameter p")
	Collection<Parameter> findManyAll();

}
