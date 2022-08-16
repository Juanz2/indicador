package co.toc.com.indicador.utilidades;

import co.toc.com.indicador.entidades.RegistroIndicador;

public class Utilidades {


    /**
     * Obtiene el mes del Indicador
     *
     * @param registroIndicador
     * @return
     */
    public String obtenerMes(RegistroIndicador registroIndicador) {

        switch (registroIndicador.getMes()) {
            case "1":
                return "Enero";
            case "2":
                return "Febrero";
            case "3":
                return "Marzo";
            case "4":
                return "Abril";
            case "5":
                return "Mayo";
            case "6":
                return "Junio";
            case "7":
                return "Julio";
            case "8":
                return "Agosto";
            case "9":
                return "Septiembre";
            case "10":
                return "Octubre";
            case "11":
                return "Noviembre";
            case "12":
                return "Diciembre";
        }
        return null;
    }
}
