package AD.AD06;

import com.mongodb.*;
import com.mongodb.client.model.*;
import org.bson.BsonDocument;
import org.bson.conversions.Bson;
import java.util.Arrays;
import java.util.List;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class minitwitter extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					minitwitter frame = new minitwitter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public minitwitter() {
		//Conexión con MongoDB
        String host = new String("192.168.56.102");
        String port = new String("27017");
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://"+host+":"+port));
        
        //Collemos a base de datos que queremos. Hai que creala fora
        String dbName = new String("minitwitter");
        final DB database = mongoClient.getDB(dbName);
        
       DBCollection colUsuario= database.getCollection("usuario");
        
        DBCollection colMensaxe= database.getCollection("mensaxe");
        
    ////////////////////////////////////////////////////////////////////    
		setTitle("MiniTwitter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Rexistro");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rexistro rexistro = new Rexistro(database);
				rexistro.setVisible(true);
			}
		});
		btnNewButton.setBounds(152, 75, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login=new Login(database);
				login.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(152, 123, 89, 23);
		panel.add(btnNewButton_1);
	}
}
