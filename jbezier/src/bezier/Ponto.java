package bezier;
/*
 * Created on 13/02/2003
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Administrador
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Ponto {
	public int x;
	public int y;
	
	public Ponto() {
		this.x = 0;
		this.y = 0;
	}
	public Ponto(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Ponto(Ponto ponto) {
		this.x = ponto.x;
		this.y = ponto.y;
	}
	public String toString() {
		
		return Integer.toString(x) + "," + Integer.toString(y); 
		
	}

}
