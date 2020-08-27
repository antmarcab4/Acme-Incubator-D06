package acme.entities.parameters;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Parameter extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@PositiveOrZero
	@NotNull
	private Double				spamthreshold;

	@NotBlank
	private String				spamwords;
}

