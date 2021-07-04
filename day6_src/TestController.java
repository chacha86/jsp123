package jsp_day6;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/article6/*")
public class TestController extends HttpServlet {
	
	MemberDao mdao = new MemberDao();
	ArticleDao adao = new ArticleDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		System.out.println("공통코드 수행");
		
		// Controller 요청 -> 응답, 요청 분석.
		// url 분석
		String uri = request.getRequestURI();
		
		String[] uriBits = uri.split("/");
		
		if(uriBits.length < 4) {
			return;
		}
		
		String action = uriBits[3];
		
		
		try {
			
			if(action.equals("list")) {
				ArrayList<Article> articles = adao.getArticleList();
				request.setAttribute("articles", articles);
				
				forwarding(request, response, "list");		
				
			}
			else if(action.equals("add")) {
				
				String title = request.getParameter("title");
				String body = request.getParameter("body");
				
				adao.insertArticle(title, body);
				forwarding(request, response, "addForm");
				
			}
			else if(action.equals("login")) {
				forwarding(request, response, "login");
			}
			else if(action.equals("detail")) {
				
				// DB 접속
				String strId = request.getParameter("articleId");
				int id = Integer.parseInt(strId);
				Article article = adao.getArticleById(id);
				
				request.setAttribute("article", article);
				forwarding(request, response, "detail");
				
			}
			else if(action.equals("doLogin")) {
				
				String loginId = request.getParameter("loginId");
				String loginPw = request.getParameter("loginPw");
				
				Member target = mdao.login(loginId, loginPw);
							
				//성공 -> list
				if(target != null) {
					
					HttpSession session = request.getSession();
					session.setAttribute("loginedUser", target);
					
					//request.setAttribute("loginUser", target);
					
					// 리다이렉트
					
//					ArrayList<Article> articles = adao.getArticleList();
//					request.setAttribute("articles", articles);

					response.sendRedirect("/JSP/article6/list");
//					forwarding(request, response, "list");
					
				}
				else {
					// 포워딩
					forwarding(request, response, "loginError");
				}
			}
			else if(action.equals("doLogout")) {
				// 세션에 저장되어 있는 로그인 유저 정보를 삭제.
				HttpSession session = request.getSession();
				session.invalidate(); // 세션 저장소 파기
				
				// 리스트 -> 포워딩, 리다이렉트
				response.sendRedirect("/JSP/article6/list");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	public void forwarding(HttpServletRequest request, HttpServletResponse response, String fileName) throws ServletException, IOException {
		String prefix = "/jsp/day6/";
		String suffix = ".jsp";
		String path = prefix + fileName + suffix;
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
		
	}
}
