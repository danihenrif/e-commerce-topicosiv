package br.ufrn.imd.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class SellDTO {
	
	private int id;

	public SellDTO(int id) {
		super();
		this.id = id;
	}

	public SellDTO() {
	}
}

