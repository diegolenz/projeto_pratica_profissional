

import gui.janelaPrincipal;
import gui.operador.login;
import lib.dao.AbstractDao;

import javax.swing.*;
import java.awt.*;
//import static sun.plugin.javascript.navig.JSType.Window;

public class Main {

	public Main() {

	}

	@SuppressWarnings("deprecation")
	
	public static void main(String[] args) throws Exception {

	   	//AbstractDao.estanciaem();

	     AbstractDao abstractDao=new AbstractDao();
	     abstractDao.Conexão();
		  janelaPrincipal janelaprincipal=new janelaPrincipal();
	        try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

				SwingUtilities.updateComponentTreeUI(janelaprincipal);

	        } catch (Throwable ex) {
	        }
		login login=new login(null, true);
		janelaprincipal.getContentPane().setBackground(Color.white);
		janelaprincipal.show();
		janelaprincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
	//	login.setVisible(true);
	//	janelaprincipal.setOperador(login.getOperadorLogado());
		
	}

}
