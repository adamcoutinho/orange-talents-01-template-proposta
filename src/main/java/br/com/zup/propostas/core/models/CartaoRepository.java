package br.com.zup.propostas.core.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Cartao,Long> {

    Optional<Cartao> findByIdentificadorCartao(String identificadorCartao);

}
