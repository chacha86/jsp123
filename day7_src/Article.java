package jsp_day7;

public class Article {

	private int id;
	private String regDate;
	private String title;
	private String body;
	private int boardId;
	private int memberId;
	private String writer;
	private String passwd;
	private int hit;
	private String name;
	
	// id, title, body, regDate, name, hit
	
	
	public Article(int id, String regDate, String title, String body, String name, int hit, int memberId) {
		super();
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.name = name;
		this.hit = hit;
		this.memberId = memberId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
}
