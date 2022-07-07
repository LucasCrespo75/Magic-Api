package br.com.magic.magicards.entidades.dtos;

import java.util.List;
import java.util.stream.Collectors;

import br.com.magic.magicards.model.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UsuarioDto {
	
	private String username;
	
	private String password;
	
	private List<CartaDto> cartas;

	
	
	public UsuarioDto(Usuario usuario) {
		this.username = usuario.getUsername();
		this.password = usuario.getPassword();
		this.cartas = usuario.getCartas().stream()
				.map(mapCarta -> mapCarta.toDto())
				.collect(Collectors.toList());
		
	}

}
