package sibys.model.repository;
import sibys.model.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido>{
	Pedido findByNumero(String numero) throws Exception;
}