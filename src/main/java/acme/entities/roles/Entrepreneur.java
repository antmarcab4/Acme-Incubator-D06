
package acme.entities.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.technologyRecords.TipoSector;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Entrepreneur extends UserRole {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				startupName;

	@NotNull
	private TipoSector			sector;

	@NotBlank
	private String				qualificationRecord;

	@NotBlank
	private String				skillsRecord;

}
