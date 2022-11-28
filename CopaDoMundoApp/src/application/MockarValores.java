package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import application.model.Jogador;
import application.model.Selecao;
import application.model.Tecnico;
import application.model.dao.SelecaoDAO;


/**
 * Classe Responsável por preencher as selecoes, jogadores e tecnicos
 * @author Joao Victor e José Gabriel
 *
 */
public class MockarValores {
	public static void MockSelecoes(SelecaoDAO selecDao) {
			for (int i = 0;i<32;i++) {
				try {
					Selecao selec = new Selecao("Time"+(i+1));
					selecDao.create(selec);					
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
	}
	
	public static void MockJogadores(SelecaoDAO selecDao) {
		String[] nomes = {"Tyrell","Shola","Garin","Ajay","River","Rehan","Edan","Roland","Jaye","Lochlan","Antoni"};
		String[] sobreNomes = {"Dickerson","Leonard","Hewitt","Bassett","Suarez","Suarez","Jackson","Britt","Garner","Coles","Klein"};
		Random rand = new Random();
		try {
			List<Selecao> selecoes = new ArrayList<Selecao>(selecDao.findAll().values());
			for(Selecao selec:selecoes) {
				for (int i = 0;i<11;i++) {
					int numAleatorio1 = rand.nextInt(11);
					int numAleatorio2 = rand.nextInt(11);
					int numPosicaoAleatorio = rand.nextInt(4);
					try {
						selec.getJogadoresDao().create(new Jogador(nomes[numAleatorio1]+ " " + sobreNomes[numAleatorio2],numPosicaoAleatorio));					
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void MockTecnicos(SelecaoDAO selecDao) {
		String[] nomes = {"Tyrell","Shola","Garin","Ajay","River","Rehan","Edan","Roland","Jaye","Lochlan","Antoni"};
		String[] sobreNomes = {"Dickerson","Leonard","Hewitt","Bassett","Suarez","Suarez","Jackson","Britt","Garner","Coles","Klein"};
		Random rand = new Random();
		try {
		List<Selecao> selecoes = new ArrayList<Selecao>(selecDao.findAll().values());
		for(Selecao selec:selecoes) {
			int numAleatorio1 = rand.nextInt(11);
			int numAleatorio2 = rand.nextInt(11);
			selec.getTecnicoDao().create(new Tecnico(nomes[numAleatorio1]+ " " + sobreNomes[numAleatorio2]));
		}
		} catch (Exception e){}
			
	}
}
