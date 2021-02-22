package br.com.zup.propostas.api.cartoes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "cartao-conta-resource",url = "http://localhost:8888/" )
public interface CartoesFeign {

    @GetMapping("/api/cartoes/{id}")
    CartaoDetailResponse consultarCartao(@PathVariable("id") String id);

    @PostMapping("/api/cartoes")
    CartaoDetailResponse gerarCartao(CartaoFormRequest request);

    @PostMapping("/api/cartoes/{id}/bloqueios")
    ResultadoBloqueioDetailResponse solicitarBloqueioCartao(@PathVariable("id") SolicitacaoBloqueioFormRequest solicitacaoBloqueio);



}
