package jsp_day8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ArticleDao {
	String url = "jdbc:mysql://127.0.0.1:3306/jsp?serverTimezone=UTC";
	String user = "sbsst";
	String password = "sbs123414";
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, user, password);		
		return conn;
	}
	
	public Article getArticleById(int articleId) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT a.id, a.title, a.body, a.regDate, m.name, a.hit, a.memberId\r\n" + 
				"FROM article a \r\n" + 
				"INNER JOIN `member` m\r\n" + 
				"ON a.memberId = m.id where a.id = " + articleId;
		
		ResultSet rs = stmt.executeQuery(sql);
		
		ArrayList<Article> articleList = new ArrayList<>();
		
		if(rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String body = rs.getString("body");
			String regDate = rs.getString("regDate");
			int hit = rs.getInt("hit");
			int memberId = rs.getInt("memberId");
			String name = rs.getString("name");
			
			Article a = new Article(id, regDate, title, body, name, hit, memberId);
			
			return a;
		}
		
		return null;
	}
	
	public ArrayList<Article> getArticleList() throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT a.id, a.title, a.body, a.regDate, m.name, a.hit, a.memberId\r\n" + 
				"FROM article a \r\n" + 
				"INNER JOIN `member` m\r\n" + 
				"ON a.memberId = m.id";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		ArrayList<Article> articleList = new ArrayList<>();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String body = rs.getString("body");
			String regDate = rs.getString("regDate");
			String name = rs.getString("name");
			int hit = rs.getInt("hit");
			int memberId = rs.getInt("memberId");
			
			Article a = new Article(id, title, body, regDate, name, hit, memberId);
			
			articleList.add(a);
		}
		
		return articleList;
	}
	
	public void insertArticle(String title, String body) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		String sql = "INSERT INTO article\r\n" + 
				"SET regDate = NOW(),\r\n" + 
				"title = '" + title + "',\r\n" + 
				"`body` = '" + body + "',\r\n" + 
				"boardId = 1, \r\n" + 
				"memberId = 1,\r\n" + 
				"writer = '',\r\n" + 
				"passwd = '',\r\n" + 
				"hit = 0";
		
		stmt.executeUpdate(sql);
	}

	public void updateArticle(int id, String title, String body) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		String sql = "UPDATE article SET title = '" + title + "', `body` = '" + body + "' WHERE id = " + id;
		
		stmt.executeUpdate(sql);
		
	}

	public void deleteArticle(int id) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		String sql = "DELETE FROM article WHERE id = " + id;
		
		stmt.executeUpdate(sql);
	}

	public void insertReply(int articleId, int memberId, String rbody) throws ClassNotFoundException, SQLException {		
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		String sql = "INSERT INTO articleReply\r\n" + 
				"SET regDate = NOW(),\r\n" + 
				"`body` = '" + rbody + "',\r\n" + 
				"boardId = 1,\r\n" + 
				"articleId = " + articleId + ",\r\n" + 
				"memberId = " + memberId;
		
		stmt.executeUpdate(sql);
	}
	
	public ArrayList<Reply> getReplyList(int paramId) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT *\r\n" + 
				"FROM articleReply ar\r\n" + 
				"INNER JOIN `member` m\r\n" + 
				"ON ar.memberId = m.id\r\n" + 
				"WHERE articleId = " + paramId;
		
		ResultSet rs = stmt.executeQuery(sql);
		
		ArrayList<Reply> replyList = new ArrayList<>();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			int articleId = rs.getInt("articleId");
			String body = rs.getString("body");
			String regDate = rs.getString("regDate");
			String name = rs.getString("name");
			int memberId = rs.getInt("memberId");
			
			//  String body, String regDate, int memberId, String name
			Reply r = new Reply(id, articleId, body, regDate, memberId, name);
			
			replyList.add(r);
		}
		
		return replyList;
	}
}

