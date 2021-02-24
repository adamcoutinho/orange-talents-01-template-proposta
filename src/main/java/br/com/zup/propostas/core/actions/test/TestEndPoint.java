package br.com.zup.propostas.core.actions.test;

import br.com.zup.propostas.api.cartoes.CartoesFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEndPoint {

    @Autowired
    private CartoesFeign cartoesFeign;
        @GetMapping("/index")
        public ResponseEntity<String> testOK(){
            return  ResponseEntity.ok("SERVICE OK");
        }

    @PostMapping("/{documento}")
    public ResponseEntity<?> consultarCartao(@PathVariable("documento") String documento) {

            cartoesFeign.consultarCartao(documento);

        return ResponseEntity.ok().body("");
    }

}
