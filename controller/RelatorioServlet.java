package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import Dao.UsuarioDAO;
/**
 * Servlet implementation class RelatorioServlet
 */
@WebServlet("/RelatorioServlet")
public class RelatorioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RelatorioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("iniciado");
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario n = new Usuario();	
		UsuarioDAO dao = new UsuarioDAO();
		
		
		usuarios = dao.todosUsuarios();
		
		
		// instancia objetos para compilação report
		JasperReport report = null;
		InputStream reportStream = null;
		
		// tenta compilar
		try{
			System.out.println("compilando");
			reportStream = getServletConfig().getServletContext().getResourceAsStream("/relatorio/projeto.jrxml");
			report = JasperCompileManager.compileReport(reportStream);
		}
		catch (JRException e) {
			System.out.println("erro");// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("compilado");
		
		// preenche relatorios
		 // preenche os dados do relatório
		
		System.out.println("Gerando relatorio");
	    JasperPrint print = null;
		try {
			print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(usuarios, false));
		} catch (JRException e) {
			System.out.println("erro");// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("fim de geracao");
		// exportação do relatório para outro formato, no caso PDF
		byte[] bytes = null;
		try {
				bytes = JasperExportManager.exportReportToPdf(print);
		} catch (JRException e) {
			e.printStackTrace();
		}     
		
		response.setContentType("application/pdf");     
		response.setContentLength(bytes.length);     
		ServletOutputStream ouputStream = response.getOutputStream();     
		ouputStream.write(bytes, 0, bytes.length);     
		ouputStream.flush();     
		ouputStream.close();  

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
