public class Property {
	private String m_sname;
	private int m_ivalue;
	private boolean m_bavailable;
	private int m_iplayer; 
	
	Property() {}
	
	//setters
	public void setAvailable(boolean b) {
		m_bavailable = b;
	}
	public void setPlayer(int player) {
		m_iplayer = player;
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
}
