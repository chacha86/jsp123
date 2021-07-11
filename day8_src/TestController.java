package jsp_day8;

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

@WebServlet("/article8/*")
public class TestController extends HttpServlet {

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
				list(request, response);
			}
			else if(action.equals("addForm")) {
				forwarding(request, response, "addForm");
			}
			else if(action.equals("add")) {
				add(request, response);
			}
			else if(action.equals("detail")) {
				
				HttpSession session = request.getSession();
				
				if(session.getAttribute("loginedUser") == null) {
					forwarding(request, response, "needToLogin");
				}
				else {
					detail(request, response);					
				}				
				
			}
			else if(action.equals("showUpdate")) {
				// 게시물 하나 같이 가져가야 함.
				showUpdate(request, response);
				
			}
			else if(action.equals("update")) {
				update(request, response);
			}
			else if(action.equals("delete")) {
				delete(request, response);
			}
			else if(action.equals("addReply")) {
				addReply(request, response);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}

	private void addReply(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		int memberId = Integer.parseInt(request.getParameter("memberId")); 
		String rbody =request.getParameter("rbody");
		
		adao.insertReply(articleId, memberId, rbody);
		
		response.sendRedirect("/JSP/article8/detail?articleId=" + articleId);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		adao.deleteArticle(id);
		response.sendRedirect("/JSP/article8/list");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		
		adao.updateArticle(id, title, body);
		
		response.sendRedirect("/JSP/article8/detail?articleId=" + id);
	}

	private void showUpdate(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		int articleId = Integer.parseInt(request.getParameter("id"));
		Article article = adao.getArticleById(articleId);
		request.setAttribute("article", article);
		forwarding(request, response, "updateForm");
		
	}

	private void detail(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		// DB 접속
		String strId = request.getParameter("articleId");
		int id = Integer.parseInt(strId);
		Article article = adao.getArticleById(id);
		ArrayList<Reply> replies = adao.getReplyList(id);
		
		request.setAttribute("article", article);
		request.setAttribute("replies", replies);
		
		forwarding(request, response, "detail");
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		
		adao.insertArticle(title, body);
		response.sendRedirect("/JSP/article8/list");	
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		int pageNo = 1;
		
		if(request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));			
		}
		
		
		
		// 1블럭 : 1 ~ 5 / 5 -> 0.xxx ~ 1
		// 2블럭 : 6 ~ 10 / 5 -> 1.xxxx ~ 2
		// 3블럭 : 11 ~ 15 / 5 -> 2.xxxx ~ 3
		// (현재페이지 / 한블럭당 페이지 수) 올림
		
		int currentBlock = (int)Math.ceil((double)pageNo / 5);
		
		// 블럭의 시작 페이지, 끝 페이지
		// 1블럭 (5 * 현재블럭 - 1) + 1 ~ 시작페이지 + 4
		// 2블럭 (5 * 현재블럭 - 1) + 1 ~ 시작페이지 + 4
		// 3블럭 (5 * 현재블럭 - 1) + 1 ~ 시작페이지 + 4
		int startPageNo = 5 * (currentBlock - 1) + 1;
		int endPageNo = startPageNo + 4;
		
		ArrayList<Article> articles = adao.getArticleList(pageNo);
		request.setAttribute("articles", articles);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("startPageNo", startPageNo);
		request.setAttribute("endPageNo", endPageNo);
		
		forwarding(request, response, "list");		
	}

	public void forwarding(HttpServletRequest request, HttpServletResponse response, String fileName) throws ServletException, IOException {
		String prefix = "/jsp/day8/";
		String suffix = ".jsp";
		String path = prefix + fileName + suffix;
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
		
	}
}
