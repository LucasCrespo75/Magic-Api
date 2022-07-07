package br.com.magic.magicards.model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.magic.magicards.entidades.dtos.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@OneToMany(mappedBy="usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Carta> cartas;


	public UsuarioDto toDto() {
		return new UsuarioDto(this);
	}
	
	public Usuario(UsuarioDto dto) {
		this.username = dto.getUsername();
		this.password = dto.getPassword();
		if(null != dto.getCartas()) {
			this.cartas = dto.getCartas().stream().map(mapCarta -> new Carta(mapCarta))
					.collect(Collectors.toList());
		}
	}
}
