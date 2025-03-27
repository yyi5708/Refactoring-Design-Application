package bowling;

public class Bowler {

	private String fullName;
	private String nickName;
	private String email;

	public Bowler(String nick, String full, String mail) {
		nickName = nick;
		fullName = full;
		email = mail;
	}

	public String getNickName() {
		return nickName;
	}

	public String getFullName() {
		return fullName;
	}

	public String getNick() {
		return nickName;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Bowler bowler = (Bowler) obj;
		return (nickName != null && nickName.equals(bowler.nickName))
				&& (fullName != null && fullName.equals(bowler.fullName))
				&& (email != null && email.equals(bowler.email));
	}

	@Override
	public int hashCode() {
		int result = nickName != null ? nickName.hashCode() : 0;
		result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		return result;
	}
}