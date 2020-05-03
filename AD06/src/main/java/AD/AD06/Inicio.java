package AD.AD06;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Inicio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	DB database;
	DBObject user;
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			Inicio dialog = new Inicio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public Inicio(final DB database ,final DBObject user ) {
		this.database=database;
		this.user=user;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnNewButton_1 = new JButton("Ver mensaxes de usuarios que sigo");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MensaxesUsuarioSigo mensaxesSigo = new MensaxesUsuarioSigo(database);
					mensaxesSigo.setVisible(true);
				}
			});
			btnNewButton_1.setBounds(118, 51, 201, 23);
			contentPanel.add(btnNewButton_1);
		}
		{
			JButton btnNewButton = new JButton("Ver tódalas mensaxes");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VisualizarMensaxes verMensaxes=new VisualizarMensaxes(database);
					verMensaxes.setVisible(true);
				}
			});
			btnNewButton.setBounds(118, 11, 137, 23);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnNewButton_2 = new JButton("Buscar por hashtag");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BuscarHashtag hashtag=new BuscarHashtag(database);
					hashtag.setVisible(true);
				}
			});
			btnNewButton_2.setBounds(118, 97, 137, 23);
			contentPanel.add(btnNewButton_2);
		}
		{
			JButton btnNewButton_3 = new JButton("Escribir unha mensaxe");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EscribirMensaxe mensaxe=new EscribirMensaxe(database,user);
					mensaxe.setVisible(true);
				}
			});
			btnNewButton_3.setBounds(118, 144, 166, 23);
			contentPanel.add(btnNewButton_3);
		}
		{
			JButton btnNewButton_4 = new JButton("Buscar usuarios");
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BuscarUsuarios usuarios=new BuscarUsuarios(database,user);
					usuarios.setVisible(true);
				}
			});
			btnNewButton_4.setBounds(118, 194, 137, 23);
			contentPanel.add(btnNewButton_4);
		}
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

}
