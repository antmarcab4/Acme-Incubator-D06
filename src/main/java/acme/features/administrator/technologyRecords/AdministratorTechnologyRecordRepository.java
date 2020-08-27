
package acme.features.administrator.technologyRecords;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.technologyRecords.TechnologyRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorTechnologyRecordRepository extends AbstractRepository {

	@Query("select t from TechnologyRecord t where t.id = ?1")
	TechnologyRecord findOneById(int id);

	@Query("select t from TechnologyRecord t")
	Collection<TechnologyRecord> findManyAll();

}
