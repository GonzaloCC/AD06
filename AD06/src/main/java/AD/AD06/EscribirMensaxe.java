package AD.AD06;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EscribirMensaxe extends JDialog {

	private final JPanel contentPanel = new JPanel();
	DBCollection colMensaxe;
	private JTextField textFieldMensaxe;
	DB database ;
	DBObject user;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			EscribirMensaxe dialog = new EscribirMensaxe();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}/*

	/**
	 * Create the dialog.
	 */
	public EscribirMensaxe(DB database,DBObject user ) {
		this.database=database;
		this.user=user;
		colMensaxe= database.getCollection("mensaxe");
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mensaxe");
		lblNewLabel.setBounds(20, 40, 46, 14);
		contentPanel.add(lblNewLabel);
		{
			textFieldMensaxe = new JTextField();
			textFieldMensaxe.setBounds(20, 65, 404, 152);
			contentPanel.add(textFieldMensaxe);
			textFieldMensaxe.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						engadirMensaxe();
						setVisible(false);
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

public void engadirMensaxe( ) {
	
	List<String> hashtag = new ArrayList<String>() ;
	
	String cadenaDondeBuscar = textFieldMensaxe.getText();
	String loQueQuieroBuscar = "#";
	String[] palabras = cadenaDondeBuscar.split(" ");
	for (String palabra : palabras) {
	    if (palabra.contains(loQueQuieroBuscar)) {
	    	hashtag.add(palabra);        
	    }
	}

	Date fecha = new Date();
	DBObject mensaxe= new BasicDBObject()
	        .append("text", textFieldMensaxe.getText())
	        .append("user", new BasicDBObject()
	                .append("nome",user.get("nome") )
	                .append("username", user.get("username")))
	        .append("date", fecha.toString())
	        .append("hashtag", hashtag);

	colMensaxe.insert(mensaxe);
	
}
}
