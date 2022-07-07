package br.com.magic.magicards.entidades.dtos;

import java.util.List;

import br.com.magic.magicards.model.Carta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartaDto {
	
	
	private String raridade;
	
	private String nome_carta;
	
	private String edicao;
	
	private String idioma;
	
	private Long preco;
		
	public CartaDto(Carta carta) {
		this.raridade = carta.getRaridade();
		this.nome_carta = carta.getNome_carta();
		this.edicao = carta.getEdicao();
		this.idioma = carta.getIdioma();
		this.preco = carta.getPreco();
	}


}
