package com.modelos.facade;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.modelos.subsystem.FileEncrypter;
import com.modelos.subsystem.FileReader;
import com.modelos.subsystem.FileWriter;
import com.modelos.subsystem.Texts;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.util.List;

public class Facade {

	public JFrame frame;
	private JTextField texto;
	private JPanel titlePanel;
	private JLabel titleLabel;
	private JLabel logoLabel;
	private int mouseX;
	private int mouseY;
    private String archivo = "src/main/java/com/modelos/test.txt";
	

	/**
	 * Create the application.
	 */
	public Facade() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	    
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setTitle("Lista de personas");
        frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 35, 35));
		frame.getContentPane().setLayout(null);
		
		// Crear el panel del título
		titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, 450, 40);
		titlePanel.setBackground(new Color(240, 240, 240));
		titlePanel.setLayout(new FlowLayout());

		// Crear el logo
		ImageIcon logo = new ImageIcon(Facade.class.getResource("/assets/logo.png"));
		ImageIcon logoMin = new ImageIcon(new ImageIcon(Facade.class.getResource("/assets/logo.png")).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
		logoLabel = new JLabel(logoMin);
		titlePanel.add(logoLabel);
		frame.setIconImage(logo.getImage());
		frame.getContentPane().setLayout(null);

		// Crear el título
		titleLabel = new JLabel("Lista de textos");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titleLabel.setForeground(Color.BLACK);
		titlePanel.add(titleLabel);

		// Agregar el panel del título al frame
		frame.getContentPane().add(titlePanel);
		
		JLabel lblNombre = new JLabel("Texto para guardar:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setForeground(Color.DARK_GRAY);
		lblNombre.setBounds(28, 50, 206, 19);
		frame.getContentPane().add(lblNombre);
		
		texto = new JTextField();
		texto.setBounds(28, 68, 96, 19);
		frame.getContentPane().add(texto);
		texto.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGuardar.setFocusPainted(false);
        btnGuardar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnGuardar.setBackground(new Color(0, 188, 212)); // Cyan similar a Material Design
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String textoString = texto.getText();
				
				// Desencripta, Guarda y Encriptado
				try {
					FileEncrypter encriptador = new FileEncrypter();
					FileWriter escritor = new FileWriter();

		            encriptador.desencriptar(archivo);
		            escritor.escribir(textoString);
		            encriptador.encriptar(archivo);
		            
                    JOptionPane.showMessageDialog(null, "La persona se guardo con éxito", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

			}
		});
		// Efecto de cambio de color al pasar el mouse
		btnGuardar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
            	btnGuardar.setBackground(new Color(0, 151, 167)); // Cyan más oscuro
            }

            public void mouseExited(MouseEvent e) {
            	btnGuardar.setBackground(new Color(0, 188, 212)); // Restaurar el color original
            }
        });
		btnGuardar.setBounds(28, 203, 130, 37);
		frame.getContentPane().add(btnGuardar);
		
		JButton btnLeer = new JButton("Leer");
		btnLeer.setForeground(Color.WHITE);
		btnLeer.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLeer.setFocusPainted(false);
		btnLeer.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnLeer.setBackground(new Color(156, 39, 176)); // Morado similar a Material Design
		btnLeer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Leer desde un archivo
		        try {

		        	FileEncrypter encriptador = new FileEncrypter();
		        	FileReader lector = new FileReader();
		        	
		        	encriptador.desencriptar(archivo);
		            List<String> textos = lector.leer();
		            encriptador.encriptar(archivo);

					if (textos.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No se encontraron textos, por favor ingreselas", "ERROR", JOptionPane.ERROR_MESSAGE);
					} else {
						// Crea y muestra la ventana estado cuando se hace clic en el botón
						Texts ventanaTextos = new Texts(frame, textos);
						ventanaTextos.frame.setVisible(true);

						// Cierra la ventana actual
						frame.setVisible(false);
					}

		        } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// Efecto de cambio de color al pasar el mouse
        btnLeer.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
            	btnLeer.setBackground(new Color(123, 31, 162)); // Morado más oscuro
            }

            public void mouseExited(MouseEvent e) {
            	btnLeer.setBackground(new Color(156, 39, 176)); // Restaurar el color original
            }
        });
		btnLeer.setBounds(28, 250, 130, 37);
		frame.getContentPane().add(btnLeer);
		
		// Botón Salir
        JButton btnSalir = new JButton("Salir");
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSalir.setFocusPainted(false);
        btnSalir.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnSalir.setBackground(new Color(229, 57, 53)); // Rojo similar a Material Design
        btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
        btnSalir.setBounds(168, 250, 111, 37);

        // Efecto de cambio de color al pasar el mouse
        btnSalir.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnSalir.setBackground(new Color(183, 28, 28)); // Rojo más oscuro
            }

            public void mouseExited(MouseEvent e) {
                btnSalir.setBackground(new Color(229, 57, 53)); // Restaurar el color original
            }
        });

        frame.getContentPane().add(btnSalir);
        
        JLabel lblImg = new JLabel("");
        ImageIcon portada = new ImageIcon(new ImageIcon(Facade.class.getResource("/assets/logo.png")).getImage().getScaledInstance(221, 221, Image.SCALE_DEFAULT));
        lblImg.setIcon(portada);
        lblImg.setBounds(229, 50, 221, 221);
        frame.getContentPane().add(lblImg);
		
		// Agregar un MouseListener al panel del título para permitir mover la ventana
		titlePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		titlePanel.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				frame.setLocation(frame.getLocation().x + e.getX() - mouseX,
								  frame.getLocation().y + e.getY() - mouseY);
			}
		});
		
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        // Solicita el foco en el JButton en lugar del JTextField
		        btnSalir.requestFocusInWindow();
		    }
		});
	}
}
