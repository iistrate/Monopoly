public class Property {
	private String m_sname;
	private int m_ivalue;
	private boolean m_bavailable;
	private int m_iplayer; 
	private int m_igroup;
	
	public Property() {}
	
	public Property Available(boolean b) {
		m_bavailable = b;
		return this;
	}
	public Property Value(int value) {
		m_ivalue = value;
		return this;
	}
	public Property Name(String name) {
		m_sname = name;
		return this;
	}
	public Property Group(int group) {
		m_igroup = group;
		return this;
	}
	
	//setters
	public void setAvailable(boolean b) {
		m_bavailable = b;
	}
	public void setPlayer(int player) {
		m_iplayer = player;
	}
	public void setGroup(int group) {
		m_igroup = group;
	}
	
	//getters
	public String getName() {
		return m_sname;
	}
	public int getValue() {
		return m_ivalue;
	}
	public boolean isAvailable() {
		return m_bavailable;
	}
	public int getPlayer() {
		return m_iplayer;
	}
	public int getGroup() {
		return m_igroup;
	}
}
