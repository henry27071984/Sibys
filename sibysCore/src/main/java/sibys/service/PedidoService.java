package sibys.service;

import sibys.model.entity.Pedido;

public interface PedidoService extends CrudService<Pedido>{
	Pedido findByNumero(String numero) throws Exception;
}
