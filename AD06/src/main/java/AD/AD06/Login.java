package AD.AD06;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
import com.mongodb.client.model.Filters;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldUsername;
	DBCollection colUsuario;
	DB database ;
	private JPasswordField passwordFieldContrasinal;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
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
	public Login(DB database ) {
		this.database=database;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel LabelUserName = new JLabel("Username");
			LabelUserName.setBounds(85, 79, 77, 14);
			contentPanel.add(LabelUserName);
		}
		{
			JLabel LabelContrasinal = new JLabel("Contrasinal");
			LabelContrasinal.setBounds(85, 104, 77, 14);
			contentPanel.add(LabelContrasinal);
		}
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(172, 76, 86, 20);
		contentPanel.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		passwordFieldContrasinal = new JPasswordField();
		passwordFieldContrasinal.setBounds(172, 101, 86, 20);
		contentPanel.add(passwordFieldContrasinal);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						comprobarLogin();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void comprobarLogin() {
		colUsuario= database.getCollection("usuario");
		String username=textFieldUsername.getText();
		String contrasinal=passwordFieldContrasinal.getText();
		
		Bson filter = Filters.or(Filters.eq("username",username),Filters.eq("contrasinal",contrasinal));
		DBObject query = new BasicDBObject(filter.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry()));
		//System.out.println(query2.toString());
		DBCursor cursor  = colUsuario.find(query);
		if (cursor.hasNext()){
			DBObject documento = cursor.next();
			Inicio inicio=new Inicio(database,documento);
			inicio.setVisible(true);
		}else {
			JOptionPane.showMessageDialog(this,"Non existeo usuario ou contrasinal incorrecta");
			textFieldUsername.setText("");
			passwordFieldContrasinal.setText("");
		}
		cursor.close();
		
	}
}
