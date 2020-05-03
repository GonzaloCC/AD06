package AD.AD06;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.bson.BsonDocument;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.model.DBCollectionFindOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;

public class BuscarUsuarios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldUsername;
	DefaultListModel model;
	DB database;
	DBObject user;
	DBCollection colUsuario;
	String username;
	private List<String> follows;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			BuscarUsuarios dialog = new BuscarUsuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/**
	 * Create the dialog.
	 */
	public BuscarUsuarios(DB database,final DBObject user ) {
		this.database=database;
		this.user=user;
		follows=(List<String>) user.get("follows");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel LabelUsername = new JLabel("Username");
			LabelUsername.setBounds(10, 26, 72, 14);
			contentPanel.add(LabelUsername);
		}
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(92, 23, 86, 20);
		contentPanel.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		model = new DefaultListModel();
		
		final JButton ButtonBuscar = new JButton("Buscar");
		ButtonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username=textFieldUsername.getText();
				mostrarLista();
			}
		});
		ButtonBuscar.setBounds(188, 22, 89, 23);
		contentPanel.add(ButtonBuscar);
		final JList listResultados = new JList(model);
		{
			//JList listResultados = new JList(model);
			listResultados.setBounds(26, 67, 289, 119);
			contentPanel.add(listResultados);
		}
		
		JButton ButtonAñadir = new JButton("Añadir");
		ButtonAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				follows.addAll(listResultados.getSelectedValuesList()) ;
				for(String f: follows) {
					System.out.println(f);
				}
				Bson filterUp = Filters.eq("follows",user.get("follows"));
				DBObject queryUp = new BasicDBObject(filterUp.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry()));
				Bson updateAux = Updates.set("follows",follows);
				DBObject update = new BasicDBObject(updateAux.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry()));
				colUsuario.update(queryUp,update);
				DBCursor cursor8 = colUsuario.find(new BasicDBObject());
				while (cursor8.hasNext()){
				    DBObject documentoAux = cursor8.next();
				    System.out.println(documentoAux.toString());
				}
				cursor8.close();
			}
		});
		ButtonAñadir.setBounds(325, 167, 89, 23);
		contentPanel.add(ButtonAñadir);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void mostrarLista() {
		System.out.println(username);
		colUsuario= database.getCollection("usuario");		
		Bson filter = Filters.regex("username",username);
		//DBObject query = new BasicDBObject("username", username);
		DBObject query = new BasicDBObject(filter.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry()));
		System.out.println(query.toString());
		DBCursor cursor  = colUsuario.find(query);
		while (cursor.hasNext()){
		    DBObject documentoAux = cursor.next();
		    System.out.println(documentoAux.toString());
		    model.addElement(documentoAux.get("nome"));
		}
		cursor.close();
	}
}
