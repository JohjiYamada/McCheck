package sg.com.Johji.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import sg.com.Johji.GeneralUtils;
import sg.com.Johji.JavaMail;

@WebServlet("/input")
public class DataInputServlet extends CommonServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		String path = "/WEB-INF/pages/success.jsp";
		String name = req.getAttribute("name").toString();
		String yourself = req.getAttribute("yourself").toString();
		String family = req.getAttribute("family").toString();
		String guest = req.getAttribute("guest").toString();
		if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(yourself) 
			&& StringUtils.isNotBlank(yourself) && StringUtils.isNotBlank(guest) 
				&& GeneralUtils.isOpen()) {
			TimeZone tzn = TimeZone.getTimeZone("Asia/Singapore");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");

			Date date = new Date();
			sdf.setTimeZone(tzn);
			sdf2.setTimeZone(tzn);
			String dateTime = sdf.format(date);
			req.setAttribute("time", dateTime);
			JavaMail mailSend = new JavaMail();
			mailSend.send("attendance", name +", " + yourself +", " + family +", " + guest +", "+sdf2.format(date));
		} else {
			path = "/error";
		}
		fowardPage(path, req, res);
	}
}
