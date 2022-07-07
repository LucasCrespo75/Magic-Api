package br.com.magic.magicards.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.magic.magicards.config.SwaggerConfig;
import br.com.magic.magicards.entidades.dtos.CartaDto;
import br.com.magic.magicards.entidades.dtos.UsuarioDto;
import br.com.magic.magicards.model.Carta;
import br.com.magic.magicards.model.Usuario;
import br.com.magic.magicards.service.CartaoService;
import br.com.magic.magicards.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {SwaggerConfig.MAGIC_TAG})
@RestController
@RequestMapping({"/api/magic"})

public class MagicController {
		
	
	private UsuarioService service;
	
	private CartaoService serviceCartao;

	
	//private Usuario usuario;
	@ApiOperation(value="Cadastrando um usuario")
	@PostMapping(value = "/usuario-carta", produces = MediaType.APPLICATION_JSON_VALUE)
	public@ResponseBody ResponseEntity<UsuarioDto> salvar (@RequestBody UsuarioDto dto){
				Usuario usu = new Usuario(dto);
					this.service.save(usu);
					Optional<Usuario> usuarioBanco = this.service.findById(dto.getUsername());
					UsuarioDto usuarioDto = usuarioBanco.get().toDto();
					dto = usuarioDto;
					
				System.out.println("Cadastrado com sucesso!");
			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
				
	}
	 
	//Listall
	@ApiOperation(value="Listando todas as cartas")
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Carta> findAll(@PathVariable String username){
		return serviceCartao.findAll(username);
		
	}
	
	//Listar pelo alfabeto
	@ApiOperation(value="Listando por ordem alfabetica")
	@GetMapping(value = "/lista-alfabeto", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Carta> findAlfabeto(@PathVariable String username){
		return this.serviceCartao.findAlfabeto(username);

	}
	
	//Listagem preco
	@ApiOperation(value="Listando por preço do maior ao menor")
	@GetMapping(value = "/lista-preco", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Carta> findPreco(@PathVariable String username){
		///List<Carta> cartas = repositorioCarta.findAlfabeto();
		//carta.addAtribute(cartas);
		return serviceCartao.findPreco(username);
				
		
	}
	
	//Find
	@ApiOperation(value="Procurando pelo ID(raridade)")
	@GetMapping(value= "/{raridade}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findById(@PathVariable String raridade){
		this.service.findById(raridade);
		return ResponseEntity.status(HttpStatus.OK).body(raridade);
		
	}
	
	@ApiOperation(value="Fazendo update a partir da raridade")
	@PutMapping(value = "/repost/{raridade}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDto> update(@RequestBody UsuarioDto dto){
		Usuario usuario = new Usuario(dto);
		List<Carta> cartas = dto.getCartas().stream()
				.map(carta -> new Carta(carta)).collect(Collectors.toList());
		usuario.setCartas(cartas);
			System.out.println("Cadastrado com sucesso!");
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
		
		
	}
	
	@ApiOperation(value="Deletando a partir da raridade")
	@DeleteMapping(value= "/deletar/{rariade}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CartaDto> delete(@PathVariable String raridade){
		
		CartaDto dto = new CartaDto();
		dto.setRaridade(raridade);
		
		this.service.delete(raridade);
		System.out.println("Carta excluida");
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	
}

	


