package gui;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Button;
import bezier.Bezier;
import bezier.Ponto;
import javax.swing.UIManager;

public class Plano extends Frame {
	private ArrayList pontosControle;

	private Checkbox chkMostraIndices = null;
	private TextField txtValorIndiceT = null;
	private Label lblIndiceT = null;
	private Button btnLimpar = null;

	public Plano() {
		super();
		pontosControle = new ArrayList();
		initialize();
	}

	public void paint(Graphics  g) {
		HashSet resultado = new HashSet();
		Ponto pontoAux;
		double indiceT = 0.0;
		int i;
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//Desenhando os eixos
		g.setColor(Color.YELLOW);
		g.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight() );
		g.drawLine(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);

		// Desenhando os pontos de controle
		g.setColor(Color.BLUE);
		ligaPontosControle(pontosControle,g);

		try {
			indiceT = Double.parseDouble(txtValorIndiceT.getText());
		} catch (NumberFormatException ex) {
			indiceT = 0.0;
		}
		txtValorIndiceT.setText(Double.toString(indiceT));
		
		
		for (i=0 ; i < this.pontosControle.size() ; i++) {
			if (pontosControle.get(i) != null) {
				desenhaPonto((Ponto)pontosControle.get(i),g);
			}
		}
		
		if (pontosControle != null && pontosControle.size()>0 && indiceT > 0) {			
			Bezier bezier = new Bezier();
			bezier.curvaBezier(this.pontosControle, resultado, indiceT);
	    
		    Iterator t = resultado.iterator();
			Ponto[] pontos = new Ponto[resultado.size()];
		    g.setColor(Color.RED);
		    i = 0;
		    while (t.hasNext()) {
		    	pontoAux = (Ponto)t.next();
				desenhaPonto(pontoAux,g);
				if ( this.chkMostraIndices.getState()) {
					g.drawString(Integer.toString(i), (this.getWidth()/ 2) + pontoAux.x, (this.getHeight()/ 2) - pontoAux.y);	
				}
				pontos[i++] = new Ponto(pontoAux);				
			}
		}
	}
	
	private  void initialize() {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e){
            System.out.println("Erro na seleção do visual do sistema");
        }
		
		lblIndiceT = new Label();
		this.setLayout(null);  // Generated
		this.setSize(607, 433);
		this.setTitle("Curva de Bèzier - Leonardo Melo Santos - leonardomelosantos@gmail.com");

		lblIndiceT.setBounds(8, 375, 20, 23);  // Generated
		lblIndiceT.setText("t = ");  // Generated
		this.add(getChkMostraIndices(), null);  // Generated
		this.add(getTxtValorIndiceT(), null);  // Generated
		this.add(getBtnLimpar(), null);  // Generated
		this.add(lblIndiceT, null);  // Generated
		this.addWindowListener(new java.awt.event.WindowAdapter() { 
			public void windowClosing(java.awt.event.WindowEvent e) {    
				//System.out.println("windowClosing()"); // TODO Auto-generated Event stub windowClosing()
				System.exit(0);
			}
		});
		this.addMouseListener(new java.awt.event.MouseListener() { 
			public void mouseClicked(java.awt.event.MouseEvent e) {    
				//System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
				adicionaPonto(e.getX(), e.getY());
			}
			public void mouseEntered(java.awt.event.MouseEvent e) {} 
			public void mouseExited(java.awt.event.MouseEvent e) {} 
			public void mousePressed(java.awt.event.MouseEvent e) {} 
			public void mouseReleased(java.awt.event.MouseEvent e) {} 
		});
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			}
		});
		
	}
	
	private void adicionaPonto(int x, int y) {
		x = x - (this.getWidth()/2);
		y = (this.getHeight()/2) - y;
		Ponto ponto = new Ponto(x, y);
		this.pontosControle.add(ponto);
		ponto = null;
		paint(getGraphics());
	}
	
	private void limpaPontosControle() {
		this.pontosControle.clear();
		paint(getGraphics());
	}

	public void ligaPontosControle(ArrayList pontos, Graphics g) {
		int conversaoX = (this.getWidth() / 2); 
		int conversaoY = (this.getHeight() / 2);
		int i;
		
		for (i=0 ; i < pontos.size()-1 ; i++) {
			g.drawLine(conversaoX + ((Ponto)pontos.get(i)).x, conversaoY - ((Ponto)pontos.get(i)).y,
			conversaoX + ((Ponto)pontos.get(i+1)).x,conversaoY - ((Ponto)pontos.get(i+1)).y);
		}
	}

	public void desenhaPonto(Ponto meuponto, Graphics g) {
		int novoX;
		int novoY;

	    novoX = (this.getWidth()/ 2) + meuponto.x;
	    novoY = (this.getHeight() / 2) - meuponto.y;
	    
	    g.fillOval(novoX, novoY, 5, 5);
	}
	
	/**
	 * This method initializes checkbox	
	 * 	
	 * @return java.awt.Checkbox	
	 */    
	private Checkbox getChkMostraIndices() {
		if (chkMostraIndices == null) {
			try {
				chkMostraIndices = new Checkbox();
				chkMostraIndices.setLabel("Mostrar indices");  // Generated
				chkMostraIndices.setBounds(9, 348, 102, 23);  // Generated
				chkMostraIndices.setName("chkMostraIndices");  // Generated
			}
			catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return chkMostraIndices;
	}
	/**
	 * This method initializes textField	
	 * 	
	 * @return java.awt.TextField	
	 */    
	private TextField getTxtValorIndiceT() {
		if (txtValorIndiceT == null) {
			try {
				txtValorIndiceT = new TextField();
				txtValorIndiceT.setBounds(28, 377, 72, 21);  // Generated
				txtValorIndiceT.setText("0.0005");
				txtValorIndiceT.setEditable(false);
			}
			catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtValorIndiceT;
	}
	/**
	 * This method initializes button	
	 * 	
	 * @return java.awt.Button	
	 */    
	private Button getBtnLimpar() {
		if (btnLimpar == null) {
			try {
				btnLimpar = new Button();
				btnLimpar.setBounds(9, 402, 67, 23);  // Generated
				btnLimpar.setLabel("Limpar");  // Generated
				btnLimpar.addActionListener(new java.awt.event.ActionListener() { 
					public void actionPerformed(java.awt.event.ActionEvent e) {    
						limpaPontosControle();
					}
				});
			}
			catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnLimpar;
	}

   }  //  @jve:decl-index=0:visual-constraint="10,10"
