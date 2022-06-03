package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {

	/**  Módulo de conexão *. */
	// Parâmetros de conexao
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://192.168.1.196:3306/dbagenda?useTimeZone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "adm";
	
	/** The password. */
	private String password = "mysql123";
	
	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	//Método de conexão
	private Connection conectar() {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println(e);
			return null;			
		}
	}
	
	/**
	 *  CRUD CREATE *.
	 *
	 * @param contato the contato
	 */
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome, fone, email) values (?, ?, ?)";
		
		try {
			//abrir a conexao
			Connection con = conectar();
			
			//Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			
			//Substituir os parâmetros (?) pelo conteúdo das variáveis JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			
			//Executar a query
			pst.executeUpdate();
			
			//Encerrar a conexão com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 *  CRUD READ *.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarContatos() {
		String read = "select * from contatos order by nome";
		
		//Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		
		try {
			//abrir a conexao
			Connection con = conectar();
			
			//Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(read);
			
			//Executar a query
			ResultSet rs = pst.executeQuery();
			
			//o laço abaixo será executado enquanto houver contatos
			while (rs.next()) {
				
				//Variáveis de apoio que recebe os dados do banco
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				
				//popular o ArrayList
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			//Encerrar a conexão com o banco
			con.close();
			
			//retornando a lista
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * * CRUD UPDATE *.
	 *
	 * @param contato the contato
	 */
	//Selecionar o contato
	public void selecionarContato(JavaBeans contato) {
		String readById = "select * from contatos where idcon = ?";
		
		try {
			//abrir a conexao
			Connection con = conectar();
			
			//Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(readById);
			
			//Substituir os parâmetros (?) pelo conteúdo das variáveis JavaBeans
			pst.setString(1, contato.getIdcon());
			
			//Executar a query
			ResultSet rs = pst.executeQuery();
			
			//o laço abaixo será executado enquanto houver contatos
			while (rs.next()) {
				
				//Variáveis de apoio que recebe os dados do banco
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			//Encerrar a conexão com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Alterar contato.
	 *
	 * @param contato the contato
	 */
	//editar o contato
	public void alterarContato(JavaBeans contato) {
		String update = "update contatos set nome=?, fone=?, email=? where idcon=?";
		
		try {
			//abrir a conexao
			Connection con = conectar();
			
			//Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(update);
			
			//Substituir os parâmetros (?) pelo conteúdo das variáveis JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());
			
			//Executar a query
			pst.executeUpdate();
			
			//Encerrar a conexão com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 *  CRUD DELETE *.
	 *
	 * @param contato the contato
	 */
	public void deletarContato(JavaBeans contato) {
		String delete = "delete from contatos where idcon=?";
		
		try {
			//abrir a conexao
			Connection con = conectar();
			
			//Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(delete);
			
			//Substituir os parâmetros (?) pelo conteúdo das variáveis JavaBeans
			pst.setString(1, contato.getIdcon());
			
			//Executar a query
			pst.executeUpdate();
			
			//Encerrar a conexão com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
