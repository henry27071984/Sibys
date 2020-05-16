package sibys.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import sibys.model.entity.Producto;
import sibys.model.entity.UnidadMedida;
import sibys.service.ProductoService;
import sibys.service.UnidadMedidaService;
import sibys.util.Message;

@Named
@ViewScoped
public class ProductoController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ProductoService productoService;
	@Inject
	private UnidadMedidaService unidadMedidaService;

	private Producto producto;
	private Producto productoSelect;
	private List<Producto> productos;

	private UnidadMedida unidadMedida;
	private List<UnidadMedida> unidadesMedida;
	private Integer seleccionado;

	

	public Integer getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(Integer seleccionado) {
		this.seleccionado = seleccionado;
	}

	public void loadUnidadMedida() {
		try {
			this.unidadesMedida = unidadMedidaService.findAll();
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

	public void resetForm() {
		this.producto = new Producto();
		this.productoSelect = null;
	}

	@PostConstruct
	public void init() {
		this.producto = new Producto();
		this.productoSelect = new Producto();
		this.loadProductos();
		this.loadUnidadMedida();
	}

	public void saveProducto() {
		try {
			if (this.producto.getId() != null) {
				this.producto.setUnidadMedida(this.unidadMedida);
				this.productoService.update(this.producto);
				Message.messageInfo("ACTUALIZADO CORRECTAMENTE");
			} else {
				this.producto.setUnidadMedida(this.unidadMedida);
				this.productoService.insert(this.producto);
				Message.messageInfo("CREADO CORRECTAMENTE");
			}
			this.loadProductos();
			this.resetForm();
		} catch (Exception e) {
			Message.messageError("Error en guardar Producto: " + e.getMessage());
		}
	}

	public void selectProducto(SelectEvent e) {
		this.productoSelect = (Producto)e.getObject();
	}
	
	public void selUnidadMedida(SelectEvent e){
	    this.unidadMedida = (UnidadMedida)e.getObject();
	}
	
	public UnidadMedida cargarUnidadesMedida() throws Exception {
		System.out.print(this.seleccionado);
		unidadMedida = unidadMedidaService.findByIds(this.seleccionado);
		System.out.print("HOLA");
		return unidadMedida;
	}

	public void editProducto() {
		try {
			if (productoSelect != null) {
				this.producto = this.productoSelect;
			} else {
				Message.messageError("Error Debe Seleccionar un Producto Para Editar");
			}
		} catch (Exception e) {
			Message.messageError("Error en editar Producto: " + e.getMessage());
		}
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Producto getProductoSelect() {
		return productoSelect;
	}

	public void setProductoSelect(Producto productoSelect) {
		this.productoSelect = productoSelect;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public List<UnidadMedida> getUnidadesMedida() {
		return unidadesMedida;
	}

	public void setUnidadesMedida(List<UnidadMedida> unidadesMedida) {
		this.unidadesMedida = unidadesMedida;
	}

}
