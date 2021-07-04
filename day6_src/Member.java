package jsp_day6;

public class Member {
	private String loginId;
	private String loginPw;
	private String name;
	
	public Member(String loginId, String loginPw, String name) {
		super();
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginPw() {
		return loginPw;
	}
	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}
}
