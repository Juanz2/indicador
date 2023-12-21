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

    @Value("#{param['proceso']}")
    private String procesoBusqueda;
    @Getter
    @Setter
    private List<RegistroIndicador> listaRegistroIndicadores;

    @Getter
    @Setter
    private List<Indicador> listaIndicadoresProceso;

    @Getter
    @Setter
    private String anio;


    @PostConstruct
    public void init() {

        this.anio = "2023";
        listaIndicadoresProceso = indicadorServicio.obtenerIndicadorProceso(Integer.parseInt(procesoBusqueda));
    }

    /**
     * Obtiene los meses del indicador
     *
     * @return obtiene el nombre del mes del registro de indicador
     */
    public String obtenerMesesIndicador(RegistroIndicador registroIndicador) {
        String mes;
        Utilidades utilidades = new Utilidades();
        mes = utilidades.obtenerMes(registroIndicador);
        return mes;
    }

    /**
     * Obtiene el registro de los indicadores por proceso
     * @param idIndicador indicador del proceso
     *
     * @return lista de registro de indicadores
     */
    public List<RegistroIndicador> obtenerRegistroIndicador(int idIndicador) {
        return registroIndicadorServicio.obtenerRegistroIndicador(idIndicador, this.anio);
    }

    /**
     * Método ecargado de realizar la gráfica del indicador
     * @return lista de datos para la creación de la gráfica
     */
    public LineChartModel createLineModel(int idIndicador) {

        LineChartModel lineModel;
        List<RegistroIndicador> registroIndicadores;
        registroIndicadores = obtenerRegistroIndicador(idIndicador);
        ChartData data = new ChartData();
        LineChartDataSet dataSet = new LineChartDataSet();
        LineChartDataSet dataSetLimiteSuperior = new LineChartDataSet();
        LineChartDataSet dataSetLimiteInferior = new LineChartDataSet();
        List<Object> valuesLimiteSuperior = new ArrayList<>();
        List<Object> valuesLimiteInferior = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        Utilidades utilidades = new Utilidades();
        for (RegistroIndicador registroIndicador : registroIndicadores) {
            labels.add(utilidades.obtenerMes(registroIndicador));
            values.add(registroIndicador.getResultadoIndicador());
            valuesLimiteSuperior.add(registroIndicador.getIndicador().getLimiteSuperior());
            valuesLimiteInferior.add(registroIndicador.getIndicador().getLimiteInferior());
        }
        dataSet.setData(values);

        dataSet.setFill(false);
        dataSet.setLabel("Resultado");
        dataSet.setBorderColor("rgb(25, 33, 240)");
        dataSet.setTension(0.1);
        data.addChartDataSet(dataSet);
        data.setLabels(labels);

        // Se crea dataset para darle color verde al limite superior
        dataSetLimiteSuperior.setData(valuesLimiteSuperior);
        dataSetLimiteSuperior.setLabel("Limite superior");
        dataSetLimiteSuperior.setBorderColor("rgb(0,255,0)");
        data.addChartDataSet(dataSetLimiteSuperior);

        // Se crea dataset para darle color verde al limite inferior
        dataSetLimiteInferior.setData(valuesLimiteInferior);
        dataSetLimiteInferior.setLabel("Limite inferior");
        dataSetLimiteInferior.setBorderColor("rgb(227,21,21)");
        data.addChartDataSet(dataSetLimiteInferior);

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

    /**
     * Retorna el valor en un String
     *
     * @param valor numero en tipo de dato double para pasarlo a String
     * @return numero en formato String
     */
    public String obtenerValorString(double valor) {
        Utilidades utilidades = new Utilidades();
        return utilidades.obtenerValorString(valor);
    }

}
