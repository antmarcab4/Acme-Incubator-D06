
package acme.entities.applications;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Application extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	//Atributos------------------------------------------------

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}\\-\\d{2}\\-\\d{6}$")
	private String				ticker;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@PastOrPresent
	private Date				creationMoment;

	@NotNull
	private String				status;

	@NotBlank
	private String				statement;

	private String				justification;

	@Valid
	@NotNull
	private Money				moneyOffer;

	//Relaciones------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = true)
	private Investor			investor;

	@NotNull
	@Valid
	@ManyToOne(optional = true)
	private InvestmentRound		iRound;

}
