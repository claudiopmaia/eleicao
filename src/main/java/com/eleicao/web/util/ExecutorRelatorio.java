package com.eleicao.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.jdbc.Work;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class ExecutorRelatorio implements Work {
	
	private String caminhoRelatorio;
	private HttpServletResponse response;
	private Map<String, Object> parametros;
	private String nomeDoArquivo;
	
	private boolean relatorioGerado;
	
	

	public ExecutorRelatorio(String caminhoRelatorio, HttpServletResponse response,
			                  Map<String, Object> parametros, String nomeDoAquivo) {
		this.caminhoRelatorio = caminhoRelatorio;
		this.response = response;
		this.parametros = parametros;
		this.nomeDoArquivo = nomeDoAquivo;
		
		this.parametros.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
	}

	@Override
	public void execute(Connection connection) throws SQLException {
		try {
			InputStream relatorioStream = this.getClass().getResourceAsStream(this.caminhoRelatorio);
		
			JasperPrint print = JasperFillManager.fillReport(relatorioStream, this.parametros);
			this.relatorioGerado = print.getPages().size() > 0;
			
			if (relatorioGerado) {
						
					JRExporter exportador = new JRPdfExporter();
					exportador.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
					exportador.setParameter(JRExporterParameter.JASPER_PRINT, print);
					
					response.setContentType("application/pdf");
					
					// força o download do arquivo
					response.setHeader("Content-Disposition", "attachment; filename=\""
							            + this.nomeDoArquivo + "\"") ;
					
					exportador.exportReport();
			}
			
		} catch (JRException e) {
			throw new SQLException("Erro ao executar relatorio  " + this.caminhoRelatorio, e);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public boolean isRelatorioGerado() {
		return relatorioGerado;
	}

	
	
}
