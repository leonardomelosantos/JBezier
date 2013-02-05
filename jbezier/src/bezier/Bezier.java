package bezier;
import java.util.ArrayList;
import java.util.HashSet;


public class Bezier {

	public Bezier() {
	}
	/**
	 * 
	 * @param pontosControle
	 * @param pontosCurva
	 */
	public void curvaBezier(ArrayList pontosControle, HashSet pontosCurva, double indiceT) {
		Ponto ponto = new Ponto();
		int i, k, x, y, N;	//Pontos de controle menos 1 (um)
		double t;
		
		N = pontosControle.size()-1;
		t = 0;
		for (i=1 ; t<=1 ; i++) {						
			x = 0; 
			y = 0;
			for (k=0 ; k <= N  ; k++) {
					x = x + (int)(C(N, k) * Math.pow((double)t, (double)k) * Math.pow((double)1-t,(double)N-k) * ((Ponto)pontosControle.get(k)).x);
					y = y + (int)(C(N, k) * Math.pow((double)t, (double)k) * Math.pow((double)1-t,(double)N-k) * ((Ponto)pontosControle.get(k)).y);
			}

			ponto = new Ponto(x,y);
			pontosCurva.add(ponto);
			ponto = null;
							
			if (t == 1) {
				t = 1.1;
			} else if (indiceT * i > 1) {
				t = 1;
			} else {
				t = indiceT * i;
			}
		}				 
	}
	
	/**
	 * 
	 * @param N
	 * @param i
	 * @return
	 */
	private double C(int N, int i) {
		double dblRetorno = 0.0;

		dblRetorno = fatorial(N) / (fatorial(i) * fatorial(N - i));		
		
		return dblRetorno;
	}
	
	/**
	 * Método que calcula o fatorial de um número.
	 * 
	 * @param n Número para calcular o fatorial
	 * @return Resultado do fatorial de n
	 */
	private long fatorial(int n) {
		if (n == 1 || n == 0) {
			return 1;
		} else {
			return n * fatorial(n-1);
		}
		
	}



}
