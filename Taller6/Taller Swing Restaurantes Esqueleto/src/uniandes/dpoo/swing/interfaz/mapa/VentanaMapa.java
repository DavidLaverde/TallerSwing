package uniandes.dpoo.swing.interfaz.mapa;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import uniandes.dpoo.swing.interfaz.principal.VentanaPrincipal;
import uniandes.dpoo.swing.mundo.Restaurante;

@SuppressWarnings("serial")
public class VentanaMapa extends JFrame implements ActionListener
{
    /**
     * El comando para reconocer al radio que muestra sólo los restaurantes visitados
     */
    private static final String VISITADOS = "VISITADOS";

    /**
     * El comando para reconocer al radio que muestra todos los restaurantes
     */
    private static final String TODOS = "TODOS";

    /**
     * El panel con el mapa
     */
    private PanelMapaVisualizar panelMapa;

    /**
     * El radio button para hacer que se muestren todos los restaurantes. Si este está activo, radioVisitados debe estar inactivo.
     */
    private JRadioButton radioTodos;

    /**
     * El radio button para hacer que se muestren sólo los restaurantes visitados. Si este está activo, radioTodos debe estar inactivo.
     */
    private JRadioButton radioVisitados;

    /**
     * La referencia a la ventana principal
     */
    private VentanaPrincipal ventanaPrincipal;

    public VentanaMapa(VentanaPrincipal ventanaPrincipal, List<Restaurante> restaurantes) {
        this.ventanaPrincipal = ventanaPrincipal;

        setLayout(new BorderLayout());

        panelMapa = new PanelMapaVisualizar();
        panelMapa.actualizarMapa(restaurantes); // Muestra los que se pasaron inicialmente
        add(panelMapa, BorderLayout.CENTER);

        
        JPanel panelOpciones = new JPanel();

        // Crear botones
        radioTodos = new JRadioButton("Mostrar todos", true); // Seleccionado por defecto
        radioTodos.setActionCommand(TODOS);
        radioTodos.addActionListener(this);

        radioVisitados = new JRadioButton("Solo visitados");
        radioVisitados.setActionCommand(VISITADOS);
        radioVisitados.addActionListener(this);

        // Agrupar los botones para que se excluyan mutuamente
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(radioTodos);
        grupo.add(radioVisitados);

        // Añadirlos al panel
        panelOpciones.add(radioTodos);
        panelOpciones.add(radioVisitados);

        // Añadir el panel de botones a la parte inferior
        add(panelOpciones, BorderLayout.SOUTH);

        // Configurar ventana
        pack();
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    @Override
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( TODOS.equals( comando ) )
        {
            panelMapa.actualizarMapa( ventanaPrincipal.getRestaurantes( true ) );
        }
        else if( VISITADOS.equals( comando ) )
        {
            panelMapa.actualizarMapa( ventanaPrincipal.getRestaurantes( false ) );
        }
    }

}
