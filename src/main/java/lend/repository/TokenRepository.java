package lend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lend.entity.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer>{
	List<Token> findAllValidTokenByUserId(Integer id);
	Optional<Token>	findByToken(String token);
	 
}
