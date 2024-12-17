package br.ufrn.imd.e_commerce.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BonusDTO {

	private int idUser;
	private int bonus;
	
	public BonusDTO(int idUser, int bonus) {
		this.idUser = idUser;
		this.bonus = bonus;
	}
}
