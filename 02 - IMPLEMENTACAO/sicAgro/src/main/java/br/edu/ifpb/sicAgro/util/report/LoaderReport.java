package br.edu.ifpb.sicAgro.util.report;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import br.edu.ifpb.sicAgro.exceptions.SicAgroExceptionHandler;

/**
 * Classe utilitária para geração de relatório com o @see {@link JasperReport}
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 * @param <T> Tipo de objeto a ser gerado o relatório
 */
public class LoaderReport<T> {

	private String pathReport;
	private HttpServletResponse response;
	private FacesContext context;
	private Map<String, Object> paramets = new HashMap<String, Object>();
	private String namefileOut;
	private List<T> list;

	private boolean relatorioGerado;

	/**
	 * Construtor default.
	 * 
	 * @param pathReport caminho do arquivo .jasper a ser preenchido.
	 * @param list lista de objetos a serem adicionado no relatório.
	 * @param namefileOut no arquivo de saída.
	 */
	public LoaderReport(String pathReport, List<T> list, String namefileOut) {
		this.list = list;
		this.namefileOut = namefileOut;
		this.pathReport = pathReport;
		
		this.paramets.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));

		this.context = FacesContext.getCurrentInstance();
		this.response = (HttpServletResponse) this.context.getExternalContext().getResponse();
	}

	/**
	 * Gera um relatório em PDF.
	 * 
	 * @param dtaInit
	 * @param dtaEnd
	 */
	public void execute(Date dtaInit, Date dtaEnd) {
		try {
			String path = context.getExternalContext().getRealPath("/WEB-INF/reports/logo_pref.png");
			paramets.put("logo", path);
			paramets.put("dateInit", dtaInit);
			paramets.put("dateEnd", dtaEnd);
			
			InputStream relatorioStream = this.getClass().getResourceAsStream(this.pathReport);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JasperReport jasper = (JasperReport) JRLoader.loadObject(relatorioStream);
			JasperPrint print = JasperFillManager.fillReport(jasper,this.paramets, new JRBeanCollectionDataSource(list));
			
			this.relatorioGerado = print.getPages().size() > 0;

			if (this.relatorioGerado) {
				JasperExportManager.exportReportToPdfStream(print, baos);

				response.reset();
				response.setContentType("application/pdf");
				response.setContentLength(baos.size());
				response.setHeader("Content-disposition", "inline; filename="+ namefileOut);
				response.getOutputStream().write(baos.toByteArray());
				response.getOutputStream().flush();
				response.getOutputStream().close();
				context.responseComplete();
			}else{
				new SicAgroExceptionHandler("Nenhum dado encontrado.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
