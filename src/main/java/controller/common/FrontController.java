package controller.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.ActionForward;
import controller.action.AddFavAction;
import controller.action.CDeleteAction;
import controller.action.CInsertAction;
import controller.action.CheckMnameAction;
import controller.action.CheckUserAction;
import controller.action.DeleteFavAction;
import controller.action.EditUserAction;
import controller.action.JoinAction;
import controller.action.LoginAction;
import controller.action.LogoutAction;
import controller.action.MailAction;
import controller.action.MainAction;
import controller.action.MyFavAction;
import controller.action.MyPageAction;
import controller.action.PCntAction;
import controller.action.PContentAction;
import controller.action.PDeleteAction;
import controller.action.PEditAction;
import controller.action.PInsertAction;
import controller.action.PListsAction;
import controller.action.PUpdateAction;
import controller.action.QAMailSendAction;
import controller.action.QuitUserAction;
import controller.action.SearchAction;
import controller.action.SignPageAction;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	private void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String cp = request.getContextPath();
		String action = uri.substring(cp.length());

		ActionForward forward = null;

		System.out.println("action: " + action);
		if (action.equals("/main.do")) {
			forward = new MainAction().execute(request, response);
		} else if (action.equals("/login.do")) {
			forward = new LoginAction().execute(request, response);
		} else if (action.equals("/join.do")) {
			forward = new JoinAction().execute(request, response);
		} else if (action.equals("/logout.do")) {
			forward = new LogoutAction().execute(request, response);
		} else if (action.equals("/pLists.do")) { // 게시판 이동 ✔
			forward = new PListsAction().execute(request, response);
		} else if (action.equals("/pContent.do")) { // 게시글 보기 ✔✔
			forward = new PContentAction().execute(request, response);
		} else if (action.equals("/pEdit.do")) { // 게시글 수정 페이지 이동 ✔✔
			forward = new PEditAction().execute(request, response);
		} else if (action.equals("/pCnt.do")) { // 조회수 증가 ✔✔
			forward = new PCntAction().execute(request, response);
		} else if (action.equals("/search.do")) { // 게시판 내 검색
			forward = new SearchAction().execute(request, response);
		} else if (action.equals("/pInsert.do")) { // 게시글 작성 ✔✔
			forward = new PInsertAction().execute(request, response);
		} else if (action.equals("/pDelete.do")) { // 게시글 삭제 ✔✔ - 세션 아이디 값 받아서 다시 해봐야함
			forward = new PDeleteAction().execute(request, response);
		} else if (action.equals("/pUpdate.do")) { // 게시글 수정 ✔✔
			forward = new PUpdateAction().execute(request, response);
		} else if (action.equals("/myPage.do")) {
			forward = new MyPageAction().execute(request, response);
		} else if (action.equals("/editUser.do")) {
			forward = new EditUserAction().execute(request, response);
		} else if (action.equals("/cInsert.do")) { // 댓글 작성 ✔✔
			forward = new CInsertAction().execute(request, response);
		} else if (action.equals("/cDelete.do")) { // 댓글 삭제 ✔✔
			forward = new CDeleteAction().execute(request, response);
		} else if (action.equals("/quitUser.do")) {
			forward = new QuitUserAction().execute(request, response);
		} else if (action.equals("/myFav.do")) {
			forward = new MyFavAction().execute(request, response);
		} else if (action.equals("/addFav.do")) {
			forward = new AddFavAction().execute(request, response);
		} else if (action.equals("/mail.do")) { // 임시비밀번호 발급 액션
			forward = new MailAction().execute(request, response);
		} else if (action.equals("/qamail.do")) { // 임시비밀번호 발급 액션
			forward = new QAMailSendAction().execute(request, response);
		} else if (action.equals("/deleteFav.do")) { // 임시비밀번호 발급 액션
			forward = new DeleteFavAction().execute(request, response);
		} else if (action.equals("/checkId.do")) { 
			forward = new CheckUserAction().execute(request, response);
		} else if (action.equals("/signPage.do")) {
			forward = new SignPageAction().execute(request, response);
		} else if (action.equals("/checkMname.do")) {
			forward = new CheckMnameAction().execute(request, response);
		}

		else {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/error/error404.jsp");
		}

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		else if (!action.equals("/checkId.do") && !action.equals("/checkMname.do")) {
			PrintWriter out = response.getWriter();
			out.println("<script>window.close();</script>");
		}
	}
}
