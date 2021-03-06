package com.tg.mymember;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/mymember/add")
public class MyMemberAdd extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		res.sendRedirect("./myMemberForm.jsp");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		
System.out.println("MemberAddServlet의 doPost를 탄다");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "jsp";
		String password ="jsp";
		
//		req.setCharacterEncoding("UTF-8"); //한글 깨짐 없게 하기
		
		String emailStr = req.getParameter("email");
		String pwdStr = req.getParameter("password");
		String nameStr = req.getParameter("name");
		
		String sql = "";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			
			sql = "INSERT INTO MEMBERS";
			sql += " (MNO, EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE)";
			sql += " VALUES(MEMBERS_MNO_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, SYSDATE)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, emailStr);
			pstmt.setString(2, pwdStr);
			pstmt.setString(3, nameStr);
			
			pstmt.executeUpdate();
			
			res.sendRedirect("./mylist");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			
//			예외처리 페이지 실행
			req.setAttribute("error", e);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
			
			dispatcher.forward(req, res);
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} // finally end
		
	}
	
}

