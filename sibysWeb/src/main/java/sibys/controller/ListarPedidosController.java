package sibys.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.SelectEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import sibys.model.entity.Dependencia;
import sibys.model.entity.DetallePedido;
import sibys.model.entity.Pedido;
import sibys.model.entity.Producto;
import sibys.service.PedidoService;
import sibys.service.DetallePedidoService;
import sibys.util.Message;

@Named
@ViewScoped
public class ListarPedidosController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoService pedidoService;
	
	@Inject
	private DetallePedidoService detallePedidoService;

	private Pedido pedido;
	private List<Pedido> pedidos;

	private List<DetallePedido> listDetallePedido = new ArrayList<DetallePedido>();

	public void loadPedidos() {
		try {
			this.pedidos = pedidoService.findAll();
		} catch (Exception e) {
			Message.messageError("Error: " + e.getMessage());
		}
	}

	@PostConstruct
	public void init() {
		this.pedido = new Pedido();
		this.loadPedidos();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public void exportarPDF(String numero) throws JRException, IOException, Exception {
		try {
			pedido = pedidoService.findByNumero(numero);

			if (pedido != null){
				File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/pedidos.jrxml"));			

				listDetallePedido = detallePedidoService.findByPedido(pedido.getId());

				String nombreArchivo = "Pedido " + pedido.getNumero() + ".pdf";

				Map<String, Object> parametros = new HashMap<String, Object>();

				parametros.put("pedido", pedido.getNumero());
				parametros.put("dependencia", pedido.getDependenciaId().getNombre());
				parametros.put("fecha", pedido.getFechaConfeccion());

				JasperReport jr = JasperCompileManager.compileReport(jasper.getAbsolutePath());
				JasperPrint jasperPrint = JasperFillManager.fillReport(jr, parametros, new JRBeanCollectionDataSource(listDetallePedido));
				HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
				response.addHeader("Content-disposition", "attachment; filename=" + nombreArchivo);
				ServletOutputStream stream = response.getOutputStream();

				JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
				stream.flush();
				stream.close();

				FacesContext.getCurrentInstance().responseComplete();
			}else{
				agregarMensajeError("Error:", "No existe el número de pedido");
			}
		} catch (IOException e) {
			agregarMensajeError("No se ha podido descargar el reporte", e.getLocalizedMessage());
		}
	}

	protected void redireccionarPagina(String pagina) {
		try {
			getExternalContext().redirect(getHttpRequest().getContextPath().concat(pagina));
		} catch (IOException e) {
			agregarMensajeError("No se puede redireccionar a " + pagina, e.getLocalizedMessage());
		}
	}

	protected void agregarMensajeError(String resumen, String detalle) {
		FacesMessage errorMessage = new FacesMessage();
		errorMessage.setSummary(resumen);
		errorMessage.setDetail(detalle == null ? detalle : detalle);
		errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		FacesContext.getCurrentInstance().validationFailed();
	}

	protected ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	public HttpServletRequest getHttpRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}
}
