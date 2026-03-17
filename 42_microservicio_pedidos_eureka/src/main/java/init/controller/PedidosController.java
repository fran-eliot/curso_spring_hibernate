package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import init.dtos.PedidoDto;
import init.service.PedidosService;

@RestController
public class PedidosController {
    
    @Autowired
    PedidosService pedidosService;
    
    @PostMapping(value = "pedido", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> nuevoPedido(@RequestBody PedidoDto pedido, 
                                            @RequestHeader("Authorization") String token) {
        try {
            pedidosService.altaPedido(pedido, token);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    
    @GetMapping("pedidos")
    public ResponseEntity<List<PedidoDto>> getPedidos() {
        return new ResponseEntity<>(pedidosService.pedidos(), HttpStatus.OK);
    }
}