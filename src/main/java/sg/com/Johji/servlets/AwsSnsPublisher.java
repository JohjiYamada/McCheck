package sg.com.Johji.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;
import software.amazon.awssdk.utils.StringUtils;

@WebServlet("/remind")
public class AwsSnsPublisher extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String target = req.getParameter("target");
		String message = req.getParameter("message");
		String name = req.getParameter("name");
		publishSns(message, target, name);
	}

	public void publishSns(String message, String target, String name) throws UnsupportedEncodingException {
		if (StringUtils.isNotBlank(target)) {
			if (StringUtils.isBlank(message)) {
				message = "please submit tempareture. https://temp-check.herokuapp.com/top?name="+name+" [From MIS]";
			}
			String phoneNo = getPhoneNo(target);
			SnsClient snsClient = SnsClient.builder().region(Region.AP_SOUTHEAST_1).build();
			try {
				PublishRequest request = PublishRequest.builder().message(message).subject("Temperature Notification").phoneNumber(phoneNo).build();
				PublishResponse result = snsClient.publish(request);
				System.out.println(result.messageId() + " Message sent. Status was " + result.sdkHttpResponse().statusCode());
			} catch (SnsException e) {
				System.err.println(e.awsErrorDetails().errorMessage());
			}
		}
	}

	public String getPhoneNo(String target) {
//		if(GeneralUtils.isHeroku()) {
		return "+"+target;
//		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	
}
