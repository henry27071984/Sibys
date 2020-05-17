package sibys.controller;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import sibys.model.entity.Dependencia;
import sibys.model.entity.DetallePedido;
import sibys.model.entity.Pedido;
import sibys.model.entity.Producto;
import sibys.service.DependenciaService;
import sibys.service.DetallePedidoService;
import sibys.service.PedidoService;
import sibys.service.ProductoService;
import sibys.util.Message;

@Named
@ViewScoped
public class PedidoController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PedidoService pedidoService;
	@Inject
	private DependenciaService dependenciaService;
	@Inject
	private DetallePedidoService detallePedidoService;
	@Inject
	private ProductoService productoService;
	
	private Pedido pedido;
	
	private Dependencia dependencia;
	private List<Dependencia> dependencias;
	private Integer seleccionado;
	
	private DetallePedido detallePedido;
	private List<DetallePedido> detallesPedido;
	
	private Producto producto;
	private List<Producto> productos;
	private Integer productoSeleccionado;
	
	public Integer getProductoSeleccionado() {
		return productoSeleccionado;
	}
	public void setProductoSeleccionado(Integer productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	public DetallePedido getDetallePedido() {
		return detallePedido;
	}
	public void setDetallePedido(DetallePedido detallePedido) {
		this.detallePedido = detallePedido;
	}
	public List<DetallePedido> getDetallesPedido() {
		return detallesPedido;
	}
	public void setDetallesPedido(List<DetallePedido> detallesPedido) {
		this.detallesPedido = detallesPedido;
	}
	public Dependencia getDependencia() {
		return dependencia;
	}
	public void setDependencia(Dependencia dependencia) {
		this.dependencia = dependencia;
	}
	public List<Dependencia> getDependencias() {
		return dependencias;
	}
	public void setDependencias(List<Dependencia> dependencias) {
		this.dependencias = dependencias;
	}
	public Integer getSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(Integer seleccionado) {
		this.seleccionado = seleccionado;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	@PostConstruct
	public void init() {
		this.pedido = new Pedido();
		this.loadDependencia();
		this.loadProductos();
	}
	
	public void loadDependencia() {
		try {
			this.dependencias = dependenciaService.findAll();
		} catch (Exception e) {
			Message.messageError("Error: " + e.getMessage());
		}
	}
	
	public void loadProductos() {
		try {
			this.productos = productoService.findAll();
		} catch (Exception e) {
			Message.messageError("Error: " + e.getMessage());
		}
	}
}
