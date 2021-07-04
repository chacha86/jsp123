package jsp_day6;

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
	
	public ArrayList<Article> getArticleList() throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT a.id, a.title, a.body, a.regDate, m.name, a.hit\r\n" + 
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
			
			Article a = new Article(id, title, body, regDate, name, hit);
			
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
	
}

