
package acme.features.anonymous.toolRecords;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolRecords.ToolRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousToolRecordRepository extends AbstractRepository {

	@Query("select t from ToolRecord t where t.id = ?1")
	ToolRecord findOneById(int id);

	@Query("select t from ToolRecord t")
	Collection<ToolRecord> findManyAll();

	@Query("select t from ToolRecord t where t.activitySector = ?1")
	Collection<ToolRecord> findByActivitySector(String sector);

	@Query("select t from ToolRecord t where t.stars = ?1")
	Collection<ToolRecord> findByStars(Integer stars);

}
