package br.com.magic.magicards.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.magic.magicards.model.Carta;
import br.com.magic.magicards.model.Usuario;
import br.com.magic.magicards.repositorio.CartaRepositorio;
import br.com.magic.magicards.repositorio.UserRepositorio;

@Service
public class UsuarioService {
		
	//private CartaRepositorio repositorioCarta;
	
	private UserRepositorio repositorioUser;
	
	private CartaoService cartaoService;

	private CartaRepositorio repositorioCarta;

	public void update(Usuario usuario) {
		//vai ver se tem alguem cadastrado na parte dos usuarios, pelo id(username)
		Optional<Usuario> objUsuario = this.repositorioUser.findById(usuario.getUsername());
		if(objUsuario.isEmpty()) {
			System.out.println("Colaborador achado");
			for(Carta carta : usuario.getCartas()) {
				carta.setUsuario(usuario);
				this.cartaoService.insert(carta);
			}
		}

	}
	public void delete(String username) {
		Optional<Usuario> objUsuario = this.repositorioUser.findById(username);
		if(objUsuario.isEmpty()) {
			System.out.println("Colaborador achado");
			this.cartaoService.deleteByUsuario(objUsuario.get());

		}else{
			System.out.println("Colaborador nao cadastrado");

		}
		
	}
	
	public void save(Usuario usuario) {
		//verificar se ja existe colaborador caastrado
		Optional<Usuario> usuarioObj = this.repositorioUser.findById(usuario.getUsername());
		if(usuarioObj.isPresent()) {
			System.out.println("Colaborador ja cadastrado");
		}
		//Cadastro de colaborador 
			this.repositorioUser.insert(usuario.getUsername(), usuario.getPassword());	 

			//cadastro de cartas
			for(Carta carta : usuario.getCartas()) {
				carta.setUsuario(usuario);
				this.cartaoService.insert(carta);
			}
		}
	
	public Optional<Usuario> findById(String username){
		return this.repositorioUser.findById(username);
	}
	

	
}
	



	
	