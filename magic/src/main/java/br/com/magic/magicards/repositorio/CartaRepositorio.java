package br.com.magic.magicards.repositorio;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.magic.magicards.model.Carta;


	@Repository
	public interface CartaRepositorio extends JpaRepository<Carta, String>{
		
		

		@Query(value = "SELECT * FROM CARTA c ORDER BY Nome_carta ASC",nativeQuery = true)
		List<Carta> findAlfabeto();

		@Query(value = "SELECT * FROM CARTA c", nativeQuery = true)
		List<Carta> findAll();
		
		@Query(value = "SELECT * FROM CARTA c ORDER BY Preco ASC",nativeQuery = true)
		List<Carta> findPreco();
		
		@Query(value = "SELECT * FROM CARTA c WHERE u.carta =: raridade",nativeQuery = true)
		Optional<Carta> findId(@Param("raridade")String raridade);
		
		
		@Modifying
		@Transactional
		default void insert(Carta carta, EntityManager entityManager) {
			entityManager.createNativeQuery("INSERT INTO CARTAS (nome_carta, edicao, idioma,preco, usuario_id) VALUES (?,?)")
			.setParameter(1, carta.getNome_carta())
			.setParameter(2, carta.getEdicao())
			.setParameter(3, carta.getIdioma())
			.setParameter(4, carta.getPreco())
			.setParameter(5, carta.getUsuario().getUsername())
			.executeUpdate();
		}

		@Transactional
		default void update(Carta carta, EntityManager entityManager) {
			entityManager.createNativeQuery("UPDATE carta c SET nome_carta=?, edicao=?, idioma=?,preco=?, usuario_id=? WHERE raridade=?")
			.setParameter(1, carta.getNome_carta())
			.setParameter(2, carta.getEdicao())
			.setParameter(3, carta.getIdioma())
			.setParameter(4, carta.getPreco())
			.setParameter(5, carta.getUsuario().getUsername())
			.setParameter(6, carta.getRaridade())
			.executeUpdate();
		}
		
		@Transactional
		default void delete(String raridade, EntityManager entityManager) {
			entityManager.createNativeQuery("DELETE from carta WHERE raridade=?")
			.setParameter(1, "raridade")
			.executeUpdate();
		}


		@Query(value= "INSERT INTO CARTAS (raridade, nome_carta, edicao, idioma,preco) VALUES (:raridade,:nome_carta, :edicao, :idioma,:preco)", nativeQuery = true)
		void insert(String raridade, String nome_carta, String edicao, String idioma, Long preco);


	}
	
	
	




