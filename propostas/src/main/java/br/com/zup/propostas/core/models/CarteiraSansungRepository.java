package br.com.zup.propostas.core.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarteiraSansungRepository extends JpaRepository<CarteiraSansung,Long> {
    Optional<CarteiraSansung> findByCartaoIdentificadorCartao(String identificadorCartao);

}
