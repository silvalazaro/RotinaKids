package controller;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

public interface Logica {
	public String executa(HttpServletRequest request, HttpServletResponse response);
}
