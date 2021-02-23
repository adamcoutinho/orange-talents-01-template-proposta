package br.com.zup.propostas.core.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarteiraPaypalRepository extends JpaRepository<CarteiraPaypal,Long> {

    Optional<CarteiraPaypal> findByCartaoIdentificadorCartao(String identificadorCartao);


}
