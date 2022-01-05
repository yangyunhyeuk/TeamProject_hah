package controller.action;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemDAO;
import model.member.MemVO;

public class JoinAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      ActionForward forward = null;

      MemDAO mDAO = new MemDAO();
      MemVO mVO = new MemVO();

      mVO.setMid(request.getParameter("uid"));
      mVO.setMpw(request.getParameter("pwd"));
      mVO.setMname(request.getParameter("mname"));
      mVO.setMemail(request.getParameter("str_email01"));

      if (!mDAO.CheckUser(mVO)) {

         mDAO.InsertDB(mVO);

         response.setContentType("text/html; charset=UTF-8");
         PrintWriter out = response.getWriter();
         out.println("<script>alert('회원가입 완료');</script>");

         forward = new ActionForward();
         forward.setRedirect(true);
         forward.setPath("signPage.do?mode=login");
      } else {
         response.setContentType("text/html; charset=UTF-8");
         PrintWriter out = response.getWriter();
         out.println("<script>alert('존재하는 아이디입니다.');history.go(-1);</script>");

      }
      return forward;

   }
}