package AD.AD06;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Rexistro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNome;
	private JTextField textFieldUsername;
	private JTextField textFieldContrasinal;
	DBCollection colUsuario;

	/**
	 * Launch the application.
	 
	
	public static void main(String[] args) {
		try {
			Rexistro dialog = new Rexistro();
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
	public Rexistro(DB database ) {
		
		colUsuario= database.getCollection("usuario");
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel LabelNome = new JLabel("Nome");
			LabelNome.setBounds(72, 70, 46, 14);
			contentPanel.add(LabelNome);
		}
		
		JLabel LabelUsername = new JLabel("Username");
		LabelUsername.setBounds(72, 95, 60, 14);
		contentPanel.add(LabelUsername);
		
		JLabel LabelContrasinal = new JLabel("Contrasinal");
		LabelContrasinal.setBounds(72, 120, 60, 14);
		contentPanel.add(LabelContrasinal);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(142, 67, 86, 20);
		contentPanel.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(142, 92, 86, 20);
		contentPanel.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldContrasinal = new JTextField();
		textFieldContrasinal.setBounds(142, 117, 86, 20);
		contentPanel.add(textFieldContrasinal);
		textFieldContrasinal.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						crearUsuario();
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
	
	public  void crearUsuario() {
        //Creamos o documento
		List<String> follows = Arrays.asList();
        DBObject usuario = new BasicDBObject()
                .append("nome", textFieldNome.getText())
                .append("username",textFieldUsername.getText() )
                .append("contrasinal",textFieldContrasinal.getText())
                .append("follows", follows);

        //Insertamolo documento
        colUsuario.insert(usuario);
        System.out.println("Inserción realizada con éxito");
		
	}
}
