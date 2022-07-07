package br.com.magic.magicards.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.magic.magicards.entidades.dtos.CartaDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity 
@Table(name="CARTA")
public class Carta implements  Comparable<Object> {
	
	//private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//private Long id;
	@Column(name = "RARIDADE")
	private String raridade;
	
	@Column(name = "NOME_CARTA")
	private String nome_carta;
	
	@Column(name = "EDICAO")
	private String edicao;
	
	@Column(name = "IDIOMA")
	private String idioma;
	
	@Column(name = "PRECO")
	private Long preco;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="usuario_id", nullable= false)
	private Usuario usuario;

	@Override
	public int compareTo(Object o) {
		Carta c = (Carta) o;
		return getNome_carta().compareTo(c.getNome_carta());
	}
	
	public int compareTo(Carta p) {
		Carta c = (Carta) p;
		return getPreco().compareTo(c.getPreco());
	}
	
	public Carta(CartaDto cartaDto) {
		this.raridade = cartaDto.getRaridade();
		this.nome_carta = cartaDto.getNome_carta();
		this.edicao = cartaDto.getEdicao();
		this.idioma = cartaDto.getIdioma();
		this.preco = cartaDto.getPreco();
	}
	
	public CartaDto toDto() {
		return new CartaDto(this);
	}












}
