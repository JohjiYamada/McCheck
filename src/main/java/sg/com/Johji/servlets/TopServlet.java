package sg.com.Johji.servlets;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.com.Johji.SpreadSheetAccesser;

@WebServlet(urlPatterns = {"/Index", "/", "/top"})
public class TopServlet extends CommonServlet {
	public static String version = System.getenv("HEROKU_RELEASE_VERSION");
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String path = "/WEB-INF/pages/top.jsp";
		try {
			List<String> members = SpreadSheetAccesser.getMembers();
			req.setAttribute("members", members);
		} catch (IOException | GeneralSecurityException e) {
			e.printStackTrace();
		}
		fowardPage(path, req, res);
	}

}
