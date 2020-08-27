
package acme.features.anonymous.technologyRecords;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.technologyRecords.TechnologyRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousTechnologyRecordRepository extends AbstractRepository {

	@Query("select t from TechnologyRecord t where t.id = ?1")
	TechnologyRecord findOneById(int id);

	@Query("select t from TechnologyRecord t")
	Collection<TechnologyRecord> findManyAll();

	@Query("select t from TechnologyRecord t where t.activitySector = ?1")
	Collection<TechnologyRecord> findByActivitySector(String sector);

	@Query("select t from TechnologyRecord t where t.stars = ?1")
	Collection<TechnologyRecord> findByStars(Integer stars);

}
