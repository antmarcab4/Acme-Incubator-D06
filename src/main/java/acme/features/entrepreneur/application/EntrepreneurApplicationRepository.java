
package acme.features.entrepreneur.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.parameters.Parameter;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurApplicationRepository extends AbstractRepository {

	@Query("select a from Application a Where a.id = ?1")
	Application findOneApplicationById(int id);

	@Query("select a from Application a join a.iRound.entrepreneur e where e.id= ?1")
	Collection<Application> findManyByEntrepreneurId(int entrepreneurId);

	@Query("select p from Parameter p")
	Parameter findParameters();

	@Query("select a from Application a join a.iRound ir where ir.id=?1")
	Collection<Application> findManyByInvestmentRound(int iRoundId);
}
