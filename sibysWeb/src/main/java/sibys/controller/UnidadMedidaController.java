package sibys.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import sibys.model.entity.UnidadMedida;
import sibys.service.UnidadMedidaService;
import sibys.util.Message;

@Named
@ViewScoped
public class UnidadMedidaController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UnidadMedidaService unidadMedidaService;
	
	private UnidadMedida unidadMedida;
	private UnidadMedida unidadMedidaSelect;
	private List<UnidadMedida> unidadesMedida;
	
	public void loadUnidadesMedida() {
		try {
			this.unidadesMedida = unidadMedidaService.findAll();
		}catch (Exception e) {
			Message.messageError("Error: "+ e.getMessage());
		}
	}
	
	public void resetForm () {
		this.unidadMedida = new UnidadMedida();
		this.unidadMedidaSelect = null;
	}
	
	@PostConstruct
	public void init() {
		this.unidadMedida = new UnidadMedida();
		this.unidadMedidaSelect = new UnidadMedida();
		this.loadUnidadesMedida();
	}
	
	public void saveUnidadMedida() {
		try {
			if(unidadMedida.getId() != null) {
				unidadMedidaService.update(unidadMedida);
				Message.messageInfo("ACTUALIZADO CORRECTAMENTE");
			}
			else {
				unidadMedidaService.insert(unidadMedida);
				Message.messageInfo("CREADO CORRECTAMENTE");
			}
			this.loadUnidadesMedida();
			this.resetForm();
		} catch (Exception e) {
			Message.messageError("Error en guardar Producto: "+ e.getMessage());
		}
	}
	
	public void selectUnidadMedida(SelectEvent e) {
		this.unidadMedidaSelect = (UnidadMedida)e.getObject();
	}
	
	public void editUnidadMedida() {
		try {
			if(unidadMedidaSelect != null) {
				this.unidadMedida = this.unidadMedidaSelect;
			}
			else {
				Message.messageError("Error Debe Seleccionar un Producto Para Editar");
			}
		} catch (Exception e) {
			Message.messageError("Error en editar Producto: "+ e.getMessage());
		}
	}

	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public UnidadMedida getUnidadMedidaSelect() {
		return unidadMedidaSelect;
	}

	public void setUnidadMedidaSelect(UnidadMedida unidadMedidaSelect) {
		this.unidadMedidaSelect = unidadMedidaSelect;
	}

	public List<UnidadMedida> getUnidadesMedida() {
		return unidadesMedida;
	}

	public void setUnidadesMedida(List<UnidadMedida> unidadesMedida) {
		this.unidadesMedida = unidadesMedida;
	}
	
	
}
