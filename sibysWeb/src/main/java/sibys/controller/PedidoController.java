package sibys.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

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
public class PedidoController implements Serializable {

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

	private String nombreProducto;

	private Dependencia dependencia;
	private List<Dependencia> dependencias;
	private Integer seleccionado;

	private DetallePedido detallePedido;
	private List<DetallePedido> detallesPedido;

	private Producto producto;
	private Producto productoFinal;
	private List<Producto> productos;
	private Integer productoSeleccionado;

	private ArrayList<Producto> productoLista = new ArrayList<Producto>();

	public Integer getProductoSeleccionado() {
		return productoSeleccionado;
	}

	public void setProductoSeleccionado(Integer productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	public Producto getProductoFinal() {
		return productoFinal;
	}

	public void setProductoFinal(Producto producto) {
		this.productoFinal = producto;
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

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	@PostConstruct
	public void init() {
		this.pedido = new Pedido();
		this.producto = new Producto();
		// this.nombreProducto ="";
		this.loadDependencia();
		this.detallePedido = new DetallePedido();
		detallesPedido = new ArrayList<>();
	}

	public void loadDependencia() {
		try {
			this.dependencias = dependenciaService.findAll();
		} catch (Exception e) {
			Message.messageError("Error: " + e.getMessage());
		}
	}

	// public void loadProductos() {
//		try {
//			this.productos = productoService.findByDescripcion(descripcion);
//		} catch (Exception e) {
//			Message.messageError("Error: " + e.getMessage());
//		}
//	}

	public List<Producto> completeText(String query) {

		List<Producto> results = new ArrayList<>();

		try {

			if (query != null && query.length() > 2) {
				productos = productoService.findByNombre(query);

				for (Producto producto : productos) {
					results.add(producto);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return results;
	}

	public Producto obtenerProductoSeleccionado(Integer id) {
		Producto prd = null;
		for (Producto producto : productos) {
			if (producto.getId().intValue() == id.intValue()) {
				prd = producto;
				break;
			}
		}
		return prd;
	}

	public void addDetail() {
		try {
			
				DetallePedido newDetail = new DetallePedido();
				newDetail.setObservacion(this.detallePedido.getObservacion());
				newDetail.setCantidad(this.detallePedido.getCantidad());
				newDetail.setProductoId(this.productoFinal);
				this.detallesPedido.add(newDetail);
				this.resetFormDetail();
		} catch (Exception e) {
			Message.messageError("Ingrese valores números en cantidad");
		}

	}

	public void selectItem(SelectEvent a) {
		Producto prod = (Producto) a.getObject();
		this.productoFinal = obtenerProductoSeleccionado(prod.getId());
	}

	public void resetFormDetail() {
		this.productoFinal = null;
		this.detallePedido = new DetallePedido();
	}

	public void resetForm() {
		this.pedido = new Pedido();
		this.detallesPedido = new ArrayList<>();
	}

	public ArrayList<Producto> getProductoLista() {
		return productoLista;
	}

	public void setProductoLista(ArrayList<Producto> productoLista) {
		this.productoLista = productoLista;
	}

	public void itemUnselectListener(UnselectEvent event) {
		this.productoFinal = new Producto();
	}

	public void grabarPedido() {
		try {
			this.pedido.setDependenciaId(this.dependencia);
			this.pedido.setFechaConfeccion(new Date());
			Integer id = this.pedidoService.insert(this.pedido);
			this.pedido.setId(id);
			for (DetallePedido det : detallesPedido) {
				det.setPedidoId(this.pedido);
				this.detallePedidoService.insert(det);
			}
			resetForm();
			resetFormDetail();
			Message.messageInfo("CREADO CORRECTAMENTE");
		} catch (Exception e) {
			Message.messageError("Error en guardar Pedido: " + e.getMessage());
		}
	}

	public void selDependencia(SelectEvent e) {
		this.dependencia = (Dependencia) e.getObject();
	}

	public Dependencia cargarDependencias() throws Exception {
		dependencia = dependenciaService.findByIds(this.seleccionado);
		return dependencia;
	}

}
