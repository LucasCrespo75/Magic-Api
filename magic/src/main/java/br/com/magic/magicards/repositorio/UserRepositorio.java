package br.com.magic.magicards.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.magic.magicards.model.Usuario;

@Repository
public interface UserRepositorio extends JpaRepository<Usuario, String>  {
	
	//Usuario findByEmailePassword(String email, String password);
	
	//Usuario findByUsernameandPassword(String email, String password);
	
	
	//(value= "SELECT * FROM USUARIO u WHERE u.username, u.password = :username, :password", nativeQuery = true)
	//boolean findByUsernameAndPassword(String username, String password);

	@Query(value= "SELECT * FROM USUARIO u WHERE u.username = :username", nativeQuery = true)
	Optional<Usuario> findById(@Param("username")String username);
	
	@Query(value= "INSERT INTO USUARIO (username, password) VALUES(:username, :password)", nativeQuery = true)
	void insert(@Param("username") String username, @Param("password") String password);



	//@Query(value= "SELECT * FROM USUARIO u WHERE u.username= :username", nativeQuery = true)
	//Optional<Usuario> findByUsername(@Param ("username")String username);


	

}
