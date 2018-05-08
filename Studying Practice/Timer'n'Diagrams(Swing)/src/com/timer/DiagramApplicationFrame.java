package com.timer;

import javafx.scene.chart.PieChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.event.PlotChangeListener;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.util.Rotation;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;



public class DiagramApplicationFrame extends ApplicationFrame {
    private int amount;
    private ArrayList names;
    private ArrayList data;
    private double totalSum;
    private final String DIAGRAM_EXCEPTION_TITLE = "Diagram Exception";
    private  final String NO_DATA_MESSAGE = "Hm, No data here, try to find it somewhere";
    private String diagramTitle;

    private PieDataset dataset;
    private JFreeChart chart;
    private ChartPanel chartPanel;
    private int startAngle = 290;
    private PieSectionLabelGenerator labelGenerator;

    DiagramApplicationFrame(String title) {
        super(title);

        names = new ArrayList();
        data = new ArrayList();
        try {

            Scanner scanner = new Scanner(new File("diagramData.txt"));
            diagramTitle = scanner.nextLine();
            amount = Integer.parseInt(scanner.nextLine());
            if(amount < 0) {
                throw new MyException("Amount Should Be Positive!");
            }
            for(int i = 0;i < amount;i++) {
                names.add(scanner.nextLine());
            }
            for(int i = 0;i < amount;i++) {
                String temp = scanner.nextLine();
                Double num = Double.parseDouble(temp);
                if (num < 0) {
                    throw new MyException("Data Should Be Positive!");
                }
                else {
                    data.add(num);
                    totalSum += num;
                }

            }
            dataset = createDataset();
            chart = createChart(dataset);
            chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(Application.WIDTH,Application.HEIGHT));
            add(chartPanel);


        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Ooops,data file not found =(",DIAGRAM_EXCEPTION_TITLE,2);
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Wrong Number Format =(",DIAGRAM_EXCEPTION_TITLE,2);
        }
        catch (NoSuchElementException e) {
            JOptionPane.showMessageDialog(null,"Hm,Too Few Elements Here",DIAGRAM_EXCEPTION_TITLE,2);
        } catch (MyException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),DIAGRAM_EXCEPTION_TITLE,2);
        }


    }
    private PieDataset createDataset() {
        DefaultPieDataset defaultPieDataset = new DefaultPieDataset();
        for(int i = 0;i < amount;i++) {
            defaultPieDataset.setValue(names.get(i).toString(),(Double) data.get(i));
        }
        return defaultPieDataset;
    }
    private JFreeChart createChart(PieDataset pieDataset) {
        JFreeChart chart = ChartFactory.createPieChart3D(diagramTitle,pieDataset,true,true,false);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(startAngle);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.9f);
        plot.setNoDataMessage(NO_DATA_MESSAGE);
        labelGenerator = new StandardPieSectionLabelGenerator("{0}:{1}",
                NumberFormat.getNumberInstance(),
                NumberFormat.getPercentInstance());
        plot.setLabelGenerator(labelGenerator);

        return chart;
    }
}
