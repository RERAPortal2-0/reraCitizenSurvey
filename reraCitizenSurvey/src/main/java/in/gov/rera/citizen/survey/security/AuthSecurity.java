package in.gov.rera.citizen.survey.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@WebFilter(urlPatterns = "/form_five/secure/*", dispatcherTypes = { DispatcherType.REQUEST })
public class AuthSecurity extends HttpFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 135325423L;
	public final static String HEADER_AUTH_TKN = "RERA_USER_SIGNATURE";
	public final static String AUTH_USER_ATTR = "RERA_AUTH_USER_ATTR";

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Implementation details...
		System.out.println("token ::" + request.getHeader(HEADER_AUTH_TKN));
		AuthUser user = authenticate(request);
		if (user != null) {
			chain.doFilter(request, response);
		} else {
			response.setHeader("access-control-allow-origin", "*");
			response.setContentType("application/json");
			response.getWriter().println("{\"Error\":\"Invalid Request\"}");
		}
		response.setHeader("access-control-allow-origin", "*");
		response.setHeader("Access-Control-Allow-Headers","*");
		response.setHeader("Access-Control-Allow-Methods","*");
	}

	private AuthUser authenticate(HttpServletRequest request) {
		AuthUser user = null;
		try {
			System.out.println("...." + request.getHeader(HEADER_AUTH_TKN));
			if (request.getHeader(HEADER_AUTH_TKN) != null && request.getHeader(HEADER_AUTH_TKN).startsWith("")) {
				String autTkn = request.getHeader(HEADER_AUTH_TKN);
				String[] tkns = StrEncUtill.decrypt(autTkn).split("~");
				if (tkns.length >8) {
					if (isValid(tkns[tkns.length-1])) {
						user = new AuthUser(tkns);
						request.setAttribute(AUTH_USER_ATTR, user);
					} else {

					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return user;
	}

	private boolean isValid(String dt) {
		return (new Date().getTime() < Long.parseLong(dt));
	}
}