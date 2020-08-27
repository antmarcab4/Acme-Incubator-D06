
package acme.features.entrepreneur.activity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activities.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.parameters.Parameter;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurActivityRepository extends AbstractRepository {

	@Query("select a from Activity a where a.id = ?1")
	Activity findOneById(int id);

	@Query("select a from Activity a where a.investmentRound.id = ?1")
	Collection<Activity> findManyActivitiesfromInvestment(int investmentId);

	@Query("select a from Activity a")
	Collection<Activity> findManyActivities();

	@Query("select p from Parameter p")
	Parameter findParameters();

	@Query("select i from InvestmentRound i where i.id = ?1")
	InvestmentRound findOneInvestmentRoundById(int id);

	@Query("select sum(a.budget.amount) from Activity a where a.investmentRound.id = ?1")
	Double sumBudgetsByInvestmentRoundId(int investmentRoundId);
}
