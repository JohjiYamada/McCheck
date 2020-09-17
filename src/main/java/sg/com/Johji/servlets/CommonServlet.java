package sg.com.Johji.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

public abstract class CommonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected abstract void doProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
	}
	
	protected void fowardPage(String path, HttpServletRequest req, HttpServletResponse res) {
		try {
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/pages/error.jsp");
			try {
				dispatcher.forward(req, res);
			} catch (ServletException | IOException e1) {}
		}
	};
	
	protected String getParamIfExists(HttpServletRequest req) {
		try {
			String name = req.getAttribute("name").toString();
			String temp = req.getAttribute("temp").toString();
			if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(temp)) {
				return "?name="+URLEncoder.encode(name, "UTF-8") + "&temp=" +URLEncoder.encode(temp, "UTF-8");
			} else {
				return "";
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name = req.getParameter("name");
		String mc = req.getParameter("mc");
		String yourself = req.getParameter("yourself");
		String family = req.getParameter("family");
		String guest = req.getParameter("guest");
		String office = req.getParameter("office");
		
		req.setAttribute("name", StringUtils.defaultString(name));
		req.setAttribute("mc", StringUtils.defaultString(mc));
		req.setAttribute("yourself", StringUtils.defaultString(yourself));
		req.setAttribute("family", StringUtils.defaultString(family));
		req.setAttribute("guest", StringUtils.defaultString(guest));
		req.setAttribute("office", StringUtils.defaultString(office));

		doProcess(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
