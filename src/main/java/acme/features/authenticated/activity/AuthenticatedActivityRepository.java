
package acme.features.authenticated.activity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activities.Activity;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedActivityRepository extends AbstractRepository {

	@Query("select a from Activity a where a.id = ?1")
	Activity findOneById(int id);

	@Query("select a from Activity a where a.investmentRound.id = ?1")
	Collection<Activity> findManyActivitiesfromInvestment(int investmentId);

	@Query("select a from Activity a")
	Collection<Activity> findManyActivities();

}
