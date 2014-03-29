public class Property {
	private String m_sname;
	private int m_ivalue;
	private boolean m_bavailable;
	private int m_iplayer; 
	
	final int BROWN = 1;
	final int LIGHT_BLUE = 2;
	final int PURPLE = 3;
	final int ORANGE = 4;
	final int RED = 5;
	final int YELLOW = 6;
	final int GREEN = 7;
	final int BLUE = 8;
	final int UTILITIES = 9;
	final int TRAIN_STATIONS = 10;
	
	
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
