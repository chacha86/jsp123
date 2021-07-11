package jsp_day8;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberDao {
	String url = "jdbc:mysql://127.0.0.1:3306/board2?serverTimezone=UTC";
	String user = "sbsst";
	String password = "sbs123414";
	
	Connection conn = null;
	
	public void startTransaction() throws ClassNotFoundException, SQLException {
		conn = getConnection();
		conn.setAutoCommit(false);
	}
	
	public void endTransaction() throws SQLException {
		conn.commit();
		conn.close();
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {	
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		if(conn == null) {
			conn = DriverManager.getConnection(url, user, password);					
		}
		
		return conn;
	}
	
	public Member login(String loginId, String loginPw) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		String sql = "select * from member where loginId='" + loginId + "'AND loginPw='" + loginPw + "'";
		ResultSet rs = stmt.executeQuery(sql);
		
		Member m = null;
		
		if(rs.next()) {
			int resultId = rs.getInt("id");
			String resultLoginId = rs.getString("loginId");
			String resultLoginPw = rs.getString("loginPw");
			String resultName = rs.getString("name");
			
			m = new Member(resultId, resultLoginId, resultLoginPw, resultName);
		}
		
		return m;
	}
	
	
}
