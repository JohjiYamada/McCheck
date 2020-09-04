package sg.com.Johji.servlets;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.com.Johji.SpreadSheetAccesser;

@WebServlet("/help")
public class HelpServlet extends CommonServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String path = "/WEB-INF/pages/help.jsp" + getParamIfExists(req);
		try {
			String html = SpreadSheetAccesser.getHelpHtml();
			req.setAttribute("html", html);
		} catch (IOException | GeneralSecurityException e) {
			e.printStackTrace();
		}
		fowardPage(path, req, res);
	}
	

}
