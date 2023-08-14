/**
 * 
 */
package com.k1rard.tiendamusicalweb.utils.filters;

import java.io.IOException;

import com.k1rard.tiendamusicalentities.entities.Persona;
import com.k1rard.tiendamusicalweb.session.SessionBean;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * @author k1rard
 * Clase que implementa el filtro para verificar la sesion del usuario
 */
@WebFilter
public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession httpSession = httpServletRequest.getSession();
		
		if(httpSession == null || httpSession.getAttribute("sessionBean") == null) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.xhtml");
		} else {
			
			if(httpSession.getAttribute("sessionBean") != null) {
				SessionBean sessionBean = (SessionBean) httpSession.getAttribute("sessionBean");
				
				Persona personaEnSesion = sessionBean.getPersona();
				
				if(personaEnSesion == null) {
					httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.xhtml");
					return;
				}
			}
			
			chain.doFilter(httpServletRequest, httpServletResponse);
		}
	}

}
