package br.com.magic.magicards.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import br.com.magic.magicards.model.Carta;
import br.com.magic.magicards.model.Usuario;
import br.com.magic.magicards.repositorio.CartaRepositorio;
import br.com.magic.magicards.repositorio.UserRepositorio;

@Service
public class CartaoService {
	
	
	private CartaRepositorio repositorioCarta;

	private UserRepositorio repositorioUser;

	private CartaoService cartaoService;
	
	@PersistenceContext
	private EntityManager entity; 
	
	

	
	public List<Carta> findAll(String username){
		Optional<Usuario> objUsuario = this.repositorioUser.findById(username);
		if(objUsuario.isEmpty()) {
			return this.repositorioCarta.findAll();

		}else {
			return null;

		}
	}
	
	public List<Carta> findAlfabeto(String username){
		Optional<Usuario> objUsuario = this.repositorioUser.findById(username);
		if(objUsuario.isEmpty()) {
			List<Carta> ListacartasAlfa = new ArrayList<>();
			
			Collections.sort(ListacartasAlfa);
			return ListacartasAlfa;
		}else {
			return null;


		}
	}
	
	public List<Carta> findPreco(String username){
		Optional<Usuario> objUsuario = this.repositorioUser.findById(username);
		if(objUsuario.isEmpty()) {
			List<Carta> ListacartasPreco = new ArrayList<>();
			
			Collections.sort(ListacartasPreco);
			return ListacartasPreco;
	}
	else {
		return null;

	}
	

	}
	public Optional<Carta> findById(String raridade){
		return this.repositorioCarta.findById(raridade);
	}
	
	
	public void insert(Carta cartao) {
		this.repositorioCarta.insert(cartao, this.entity);
	}
	
	//public void update(Carta carta) {
	//	this.repositorioCarta.update(carta, entity);
	//}
	public void delete(String username) {
		this.repositorioCarta.delete(username, this.entity);
	}
	
	public void deleteByUsuario(Usuario usuario) {
		if(null != usuario.getCartas()) {
			for(Carta carta : usuario.getCartas()) {
				this.delete(carta.getRaridade());
			}
		}
	}
}

	//public void update(Usuario usuario) {
		//vai ver se tem alguem cadastrado na parte dos usuarios, pelo id(username)
	//	Optional<Usuario> objUsuario = this.repositorioUser.findById(usuario.getUsername());
		//if(objUsuario.isEmpty()) {
		//	System.out.println("Colaborador ja cadastrado");
		//}
		
		//if(!objUsuario.get().getUsername().equals(usuario.getUsername())) {
			//this.repositorioUser.update(usuario.getUsername(), usuario.getPassword());
		//}
		//for(Carta carta : usuario.getCartas()) {
		//	carta.setUsuario(usuario);
		//	this.cartaoService.insert(carta);
	//	}

	//}
//	public void delete(String username) {
		//Optional<Usuario> objUsuario = this.repositorioUser.findById(username);
		//if(objUsuario.isEmpty()) {
		//	System.out.println("COLABORADOR JA CADASTRADO");
		//}
		
		//this.cartaoService.deleteByUsuario(objUsuario.get());
	//}
	
		//public void save(Carta carta) {
		//Optional<Usuario> usuarioObj = this.repositorioUser.findById(usuario.getUsername());
	//	if(usuarioObj.isPresent()) {
	//		System.out.println("Colaborador ja cadastrado");
			
	//	}
		
		//this.repositorioUser.insert(usuario.getUsername(), usuario.getPassword());	 
		
	//	for(Carta carta : usuario.getCartas()) {
		//	carta.setUsuario(usuario);
		//	this.cartaoService.insert(carta);
		//}
	
	
	//}

	

		
	

