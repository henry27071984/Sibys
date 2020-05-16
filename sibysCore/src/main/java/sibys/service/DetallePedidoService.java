package sibys.service;
import java.util.List;
import sibys.model.entity.DetallePedido;

public interface DetallePedidoService extends CrudService<DetallePedido>{
	List<DetallePedido> findByProducto(String codigo) throws Exception;
}
