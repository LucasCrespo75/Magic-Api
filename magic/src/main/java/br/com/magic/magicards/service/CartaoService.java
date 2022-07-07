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
	
	public void update(Carta carta) {
		this.repositorioCarta.update(carta, entity);
	}
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


	

		
	

