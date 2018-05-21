package com.session;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Collections;

public class CustomSAXParser extends DefaultHandler {
    private final int PERIOD = 4;
    private final int MARK_INDEX  = 3;
    private final int SUBJECT_INDEX = 2;
    private ArrayList<Integer> marks;
    private ArrayList<String> subjects;
    private ArrayList<String> values;

    private static double average = -1;
    private static String popular = null;

    public static double getAverage() {
        return average;
    }

    public static String getPopular() {
        return popular;
    }

    @Override
    public void startDocument() throws SAXException {
        marks = new ArrayList<>();
        subjects = new ArrayList<>();
        values = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        int length = attributes.getLength();

        for (int i = 0; i < length; i++) {
            String value = attributes.getValue(i);
            values.add(value);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        for(int i = 0;i < values.size();i++) {
            if(i % PERIOD == MARK_INDEX) {
                marks.add(Integer.parseInt(values.get(i)));
            }
            else if(i % PERIOD == SUBJECT_INDEX) {
               subjects.add(values.get(i));
            }
        }
        int sum = 0;
        for (Integer mark : marks) {
            sum += mark;
        }
        if(marks.size() != 0) {
            average = (double) sum / (double) marks.size();
        }
        if(subjects.size() != 0) {
            Collections.sort(subjects);
            int inRow = 0;
            int maxInRow = 0;
            popular = subjects.get(0);
            String current = subjects.get(0);
            for(int i = 0;i < subjects.size();i++) {
                if(subjects.get(i).compareTo(current) == 0) {
                    inRow++;
                    if(inRow > maxInRow) {
                        maxInRow = inRow;
                        popular = "";
                        popular = popular.concat(subjects.get(i));
                    }
                }
                else {
                    inRow = 1;
                    current = "";
                    current = current.concat(subjects.get(i));
                }
            }

        }

    }
}
/*
public class SAXExample
{
    final  String  fileName = "phonebook.xml";
    final  String  TAG_NAME = "name";

    DefaultHandler handler = new DefaultHandler() {
        boolean tagOn = false; // флаг начала разбора тега


        @Override
        public void startElement(String uri, String localName,
                                 String qName, Attributes attributes)
                throws SAXException {
            // Устанавливаем флаг, если тег имеет имя TAG_NAME
            tagOn = (qName.equalsIgnoreCase(TAG_NAME));
            System.out.println("\t<" + qName + ">");
        }


        @Override
        public void characters(char ch[], int start, int length)
                throws SAXException {
            // Проверка флага
            if (tagOn) {
                // Флаг установлен
                System.out.println("\t\t" + new String(ch,start,length));
                tagOn = false;
            }
        }
        @Override
        public void endElement(String uri,String localName,String qName)
                throws SAXException
        {
            super.endElement(uri, localName, qName);
        }

        @Override
        public void startDocument() throws SAXException
        {
            System.out.println("Начало разбора документа!");
        }

        @Override
        public void endDocument() throws SAXException
        {
            System.out.println("Разбор документа завершен!");
        }
    };
    */