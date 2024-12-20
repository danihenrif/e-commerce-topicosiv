package br.ufrn.imd.e_commerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class BonusDTO {

	private int idUser;
	private int bonus;
	
	public BonusDTO(int idUser, int bonus) {
		super();
		this.idUser = idUser;
		this.bonus = bonus;
	}

	public BonusDTO() {
		super();
	}
}
