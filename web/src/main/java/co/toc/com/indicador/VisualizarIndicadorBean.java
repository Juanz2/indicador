package co.toc.com.indicador;

import co.toc.com.indicador.entidades.Indicador;
import co.toc.com.indicador.entidades.RegistroIndicador;
import co.toc.com.indicador.servicios.IndicadorServicio;
import co.toc.com.indicador.servicios.RegistroIndicadorServicio;
import co.toc.com.indicador.utilidades.Utilidades;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class VisualizarIndicadorBean implements Serializable {



    @Autowired
    private RegistroIndicadorServicio registroIndicadorServicio;
    @Autowired
    private IndicadorServicio indicadorServicio;

    private int idIndicador;

    @Value("#{param['proceso']}")
    private String procesoBusqueda;
    @Getter
    @Setter
    private List<RegistroIndicador> listaRegistroIndicadores;

    @Getter
    @Setter
    private List<Indicador> listaIndicadoresProceso;

    @PostConstruct
    public void init() {

        //  listaRegistroIndicadores = registroIndicadorServicio.obtenerRegistroIndicador();
        //createLineModel();
        listaIndicadoresProceso = indicadorServicio.obtenerIndicadorProceso(Integer.parseInt(procesoBusqueda));
    }

    /**
     * Obtiene los meses del indicador
     *
     * @return
     */
    public String obtenerMesesIndicador(RegistroIndicador registroIndicador) {
        String mes;
        Utilidades utilidades = new Utilidades();
        mes = utilidades.obtenerMes(registroIndicador);
        return mes;
    }

    /**
     * @param idIndicador
     * @return
     */
    public List<RegistroIndicador> obtenerRegistroIndicador(int idIndicador) {
        return registroIndicadorServicio.obtenerRegistroIndicador(idIndicador);
    }

    /**
     *
     * @return
     */
    public LineChartModel createLineModel(int idIndicador) {

        LineChartModel lineModel;
        List<RegistroIndicador> registroIndicadores;
        registroIndicadores = obtenerRegistroIndicador(idIndicador);
        ChartData data = new ChartData();
        LineChartDataSet dataSet = new LineChartDataSet();
        List<Object> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        Utilidades utilidades = new Utilidades();
        for (RegistroIndicador registroIndicador : registroIndicadores){
            values.add(registroIndicador.getResultadoIndicador());
            labels.add(utilidades.obtenerMes(registroIndicador));
        }
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Resultado");
        dataSet.setBorderColor("rgb(25, 33, 240)");
        dataSet.setTension(0.1);
        data.addChartDataSet(dataSet);
        data.setLabels(labels);

        //Options
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("");
        options.setTitle(title);
        lineModel = new LineChartModel();

        lineModel.setOptions(options);
        lineModel.setData(data);

        return lineModel;
    }
}
