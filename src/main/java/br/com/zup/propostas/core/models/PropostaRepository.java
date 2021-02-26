package br.com.zup.propostas.core.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    Optional<Proposta> findByUuid(String identificador);

    Optional<Proposta> findBySolicitanteDocumento(String documento);

    Boolean existsBySolicitanteDocumento(String documento);
}
