package com.modelos.subsystem;

import com.modelos.facade.Facade;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class Texts {

	public JFrame frame;
	private JPanel titlePanel;
	private JLabel titleLabel;
	private JLabel logoLabel;
	private int mouseX;
	private int mouseY;

	/**
	 * Create the application.
	 */
	public Texts(JFrame ventanaAnterior, List<String> textos) {
		initialize(ventanaAnterior, textos);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JFrame ventanaAnterior, List<String> textos) {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
        frame.setTitle("Lista de personas");
        frame.setUndecorated(true);
        
        // Crear el panel del título
  		titlePanel = new JPanel();
 		titlePanel.setBackground(new Color(240, 240, 240));
 		titlePanel.setLayout(new FlowLayout());

 		// Crear el logo
 		ImageIcon logo = new ImageIcon(Facade.class.getResource("/assets/logo.png"));
 		ImageIcon logoMin = new ImageIcon(new ImageIcon(Facade.class.getResource("/assets/logo.png")).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
 		logoLabel = new JLabel(logoMin);
 		titlePanel.add(logoLabel);
 		frame.setIconImage(logo.getImage());

 		// Crear el título
 		titleLabel = new JLabel("Lista de personas");
 		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
 		titleLabel.setForeground(Color.BLACK);
 		titlePanel.add(titleLabel);
  		
  		// Agregar el panel del título al frame
  		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		
		// Crear los encabezados de la tabla
		String[] encabezados = { "Texto"};

		// Crear los datos de la tabla a partir de la lista de personas
		Object[][] datos = new Object[textos.size()][1];
		for (int i = 0; i < textos.size(); i++) {
		    datos[i][0] = textos.get(i);
		}
		
		// Crea la tabla
		JTable tabla = new JTable(datos, encabezados);
		// Ajusta el tamaño de la fuente
		tabla.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabla.setRowHeight(30);
		// Ajusta el color de fondo y el color de la fuente
		tabla.setBackground(new Color(248, 248, 255));
		tabla.setForeground(Color.DARK_GRAY);
		// Ajusta los bordes
		tabla.setGridColor(new Color(230, 230, 250));
		// Ajusta el ancho de las columnas para que se ajusten al contenido
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		// Permite desplazamiento de la tabla
		JScrollPane scrollPane = new JScrollPane(tabla);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		// Personaliza el encabezado de la tabla
		JTableHeader header = tabla.getTableHeader();
		header.setBackground(new Color(248, 248, 255));
		header.setForeground(Color.DARK_GRAY);
		header.setFont(new Font("Tahoma", Font.BOLD, 16));
 	    
		// Botón Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnVolver.setBackground(new Color(229, 57, 53)); // Rojo similar a Material Design
        btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Muestra la ventana original y oculta esta ventana
				ventanaAnterior.setVisible(true);
                frame.setVisible(false);
			}
		});
        frame.getContentPane().add(btnVolver, BorderLayout.SOUTH);

        // Efecto de cambio de color al pasar el mouse
        btnVolver.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnVolver.setBackground(new Color(183, 28, 28)); // Rojo más oscuro
            }

            public void mouseExited(MouseEvent e) {
                btnVolver.setBackground(new Color(229, 57, 53)); // Restaurar el color original
            }
        });
		frame.getContentPane().add(btnVolver, BorderLayout.SOUTH);
		
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
		
		frame.pack(); //Se establece el tamaño según lo que tenga adentro
        
        frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 35, 35));
        frame.setVisible(true);
	}
}
