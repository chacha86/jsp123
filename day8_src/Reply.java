package jsp_day8;

public class Reply {
	private int id;
	private int articleId;
	private String body;
	private String regDate;
	private int memberId;
	private String name;
	public Reply(int id, int articleId, String body, String regDate, int memberId, String name) {
		super();
		this.id = id;
		this.articleId = articleId;
		this.body = body;
		this.regDate = regDate;
		this.memberId = memberId;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
