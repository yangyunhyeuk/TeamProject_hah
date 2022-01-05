package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("SignPageAction ¿‘¿Â.");
		ActionForward forward = null;
		String mode = request.getParameter("mode");
		String loginCnt=request.getParameter("loginFail");
		System.out.println("mode : "+mode);
		request.setAttribute("mode", mode);
		request.setAttribute("loginCnt", loginCnt);

		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("login.jsp");

		return forward;
	}

}
