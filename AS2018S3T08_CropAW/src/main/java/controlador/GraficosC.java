package controlador;

import dao.Graficos;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import modelo.GraficosM;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

@Named(value = "graficosC")
@SessionScoped
public class GraficosC implements Serializable {

    private PieChartModel pieModel;
    Graficos dao;
    private BarChartModel barModel;
    List<GraficosM> lista;
    private List<GraficosM> contar;
    private LineChartModel lineModel;
    private HorizontalBarChartModel hbarModel;

    public void lista() throws Exception {
        dao = new Graficos();
        List<GraficosM> listacul;
        List<GraficosM> listado;
        List<GraficosM> listar;
        try {
            Contarlista();
            listacul = dao.listar();
            listado = dao.listargraf();
            listar = dao.listargrafcultasig();
            graficar(listacul);
            createLineModel(listado);
            createHorizontalBarModel(listar);
        } catch (Exception e) {
            throw e;
        }
    }

    /*Pie model*/
    public void graficar(List<GraficosM> lista) {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();
        PieChartDataSet dataSet = new PieChartDataSet();

        List<Number> values = new ArrayList<>();
        lista.forEach((cm) -> {
            values.add(cm.getCOUNT());
        });
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(75, 192, 192)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(201, 203, 207)");
        bgColors.add("rgb(54, 162, 235)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        lista.forEach((lbl) -> {
            labels.add(lbl.getTIPO());
        });
        data.setLabels(labels);
        
        pieModel.setData(data);
    }

    public void Contarlista() {
        try {
            contar = dao.listarContar();
        } catch (Exception e) {
        }
    }

    public void createLineModel(List<GraficosM> lista) {
        lineModel = new LineChartModel();
        ChartData data = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();

        List<Number> values = new ArrayList<>();
        lista.forEach((info) -> {
            values.add(info.getSUM());
        });
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Suma cosecha");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        dataSet.setLineTension(0.1);
        data.addChartDataSet(dataSet);

        List<String> labels = new ArrayList<>();
        lista.forEach((lbl) -> {
            labels.add(String.valueOf(lbl.getFECHA()));
        });
        data.setLabels(labels);

        //Options
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("");
        options.setTitle(title);

        lineModel.setOptions(options);
        lineModel.setData(data);
    }

    public void createHorizontalBarModel(List<GraficosM> listar) {
        hbarModel = new HorizontalBarChartModel();
        ChartData data = new ChartData();

        HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
        hbarDataSet.setLabel("Cantidad de cultivos");

        List<Number> values = new ArrayList<>();
        listar.forEach((lista) -> {
            values.add(lista.getCOUNT());
        });
        hbarDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(201, 203, 207)");
        hbarDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgba(201, 203, 207)");
        hbarDataSet.setBorderColor(borderColor);
        hbarDataSet.setBorderWidth(1);

        data.addChartDataSet(hbarDataSet);

        List<String> labels = new ArrayList<>();

        listar.forEach((lista) -> {
            labels.add(lista.getSector());
        });
        data.setLabels(labels);
        hbarModel.setData(data);

        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addXAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        options.setTitle(title);

        hbarModel.setOptions(options);
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public Graficos getDao() {
        return dao;
    }

    public void setDao(Graficos dao) {
        this.dao = dao;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public List<GraficosM> getLista() {
        return lista;
    }

    public void setLista(List<GraficosM> lista) {
        this.lista = lista;
    }

    public List<GraficosM> getContar() {
        return contar;
    }

    public void setContar(List<GraficosM> contar) {
        this.contar = contar;
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

    public HorizontalBarChartModel getHbarModel() {
        return hbarModel;
    }

    public void setHbarModel(HorizontalBarChartModel hbarModel) {
        this.hbarModel = hbarModel;
    }


}