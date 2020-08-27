
package acme.features.entrepreneur.investmentRounds;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activities.Activity;
import acme.entities.applications.Application;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.parameters.Parameter;
import acme.entities.roles.Entrepreneur;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurInvestmentRoundRepository extends AbstractRepository {

	@Query("select i from InvestmentRound i where i.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select i from InvestmentRound i where i.entrepreneur.id = ?1")
	Collection<InvestmentRound> findManyByEntrepreneur(int entrepreneurId);

	@Query("select a from Application a where a.id = ?1")
	Application findOneApplicationById(int id);

	@Query("select p from Parameter p")
	Parameter findParameters();

	@Query("select sum(a.budget.amount) from Activity a where a.investmentRound.id = ?1")
	Double sumBudgetsByInvestmentRoundId(int investmentRoundId);

	@Query("select a from Activity a where a.investmentRound.id = ?1")
	Collection<Activity> findManyActivitiesByInvestmentRoundId(int investmentRoundId);

	@Query("select a from Application a where a.iRound.id = ?1")
	Collection<Application> applicationsByInvestmentRoundId(int investmentRoundId);

	@Query("select e from Entrepreneur e where e.userAccount.id = ?1")
	Entrepreneur findEntrepreneurByUserAccountId(int userAccountId);

	@Query("select i from InvestmentRound i")
	Collection<InvestmentRound> findAllInvestmentRounds();
}
