public class Image {
	//filename
	private String m_filename;
	//sprite x and y
	private int m_spriteX;
	private int m_spriteY;
	//where to draw them x and y
	private int m_x;
	private int m_y;	
	//width and height
	private int m_width;
	private int m_height;
	//animated
	boolean m_animated;
	
	Image() { 
		m_animated = false;
	}
	public void load(String fname, int sX, int sY, int x, int y, int w, int h) {
		m_filename = fname;
		m_spriteX = sX;
		m_spriteY = sY;
		m_x = x;
		m_y = y;
		m_width = w;
		m_height = h;
	}
	public String getFilename() {
		return m_filename;
	}
	public int getSpriteX() {
		return m_spriteX;
	}
	public int getSpriteY() {
		return m_spriteY;
	}
	public int getX() {
		return m_x;
	}
	public int getY() {
		return m_y;
	}
	public int getWidth() {
		return m_width;
	}
	public int getHeight() {
		return m_height;
	}
}