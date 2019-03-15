package com.tg.mymember;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/mymember/update")

public class MyMemberUpdate extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "jsp";
		String password = "jsp";
		
		
		int mNo = Integer.parseInt(req.getParameter("no"));
		
		String sql = "";
		
		try {
			Class.forName(driver);
			System.out.println("오라클 드라이버 로드");
			
			conn = DriverManager.getConnection(url, user, password);
			
			sql = "SELECT MNO, MNAME, EMAIL, CRE_DATE, PWD";
			sql += " FROM MEMBERS";
			sql += " WHERE MNO = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, mNo);
			
			rs = pstmt.executeQuery();
			
			int mno = 0;
			String mName = "";
			String email = "";
			String pwd = "";
			Date creDate = null;
			
//			ArrayList<MemberDto> memberList = new ArrayList<MemberDto>();
//			업데이트트는 하나의 정보만 불러와서 수정하기 때문에 어레이리스트가 필요없음
			MyMemberDto myMemberDto = null;
			
			while(rs.next()) {
				mno = rs.getInt("MNO");
				mName = rs.getString("MNAME");
				email = rs.getString("email");
				creDate = rs.getDate("cre_date");
				pwd = rs.getString("pwd");
				
				myMemberDto = new MyMemberDto();
				myMemberDto.setNo(mno);
				myMemberDto.setName(mName);
				myMemberDto.setEmail(email);
				myMemberDto.setCreateDate(creDate);
				
//				memberList.add(memberDto);
			}
			
			
			req.setAttribute("myMemberDto", myMemberDto);
//			req.setAttribute("memberList", memberList);
			
			res.setContentType("text/html");
			res.setCharacterEncoding("UTF-8");
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("./myMemberDetailView.jsp");
			
			dispatcher.include(req, res);
			
		
			
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			//나중에 예외처리
			
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} // if(rs != null) end
			
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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("MemberAddServlet의 doPost를 탄다");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "jsp";
		String password = "jsp";
		
		req.setCharacterEncoding("UTF-8");
		
		String email = req.getParameter("email");
		String name = req.getParameter("name");
//		String pwd = req.getParameter("password");
		
		//추가및 수정부분
		int mno = Integer.parseInt(req.getParameter("no"));
	
		String sql = "";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			
			//추가 및 수정부분
			sql = "UPDATE MEMBERS";
			sql += " SET EMAIL = ?, MNAME = ?, MOD_DATE = SYSDATE";
			sql += " WHERE MNO = ?";
			
			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, name);
			pstmt.setInt(3, mno);
			
			pstmt.executeUpdate();
			
			res.sendRedirect("./mylist");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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