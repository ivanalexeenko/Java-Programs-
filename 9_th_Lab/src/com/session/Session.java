package com.session;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Session {

    private ArrayList<SessionData> sessionDataArrayList;

    public Session(ArrayList<SessionData> sessionDataArrayList) {
        this.sessionDataArrayList = sessionDataArrayList;
    }

    public Session() {
        sessionDataArrayList = new ArrayList<>();
    }

    public ArrayList getSubjects() {
        TreeSet<String> set = new TreeSet<String>();
        Iterator<SessionData> iterator = sessionDataArrayList.iterator();
        while (iterator.hasNext()) {
            set.add(iterator.next().getSubject());
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(set);

        return arrayList;

    }

    public void add(SessionData s) {
        sessionDataArrayList.add(s);
    }

    public void read(String filename) throws IOException, NoSuchElementException, NumberFormatException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        int num = 0;
        String sur;
        String sub;
        int ma;

        while (scanner.hasNext()) {
            num = scanner.nextInt();
            sur = scanner.next();
            sub = scanner.next();
            ma = scanner.nextInt();

            SessionData sessionData = new SessionData(num, sur, sub, ma);
            sessionDataArrayList.add(sessionData);
        }


    }

    public void clear() {
        sessionDataArrayList.clear();
    }

    public String toXMLString() {
        StringBuilder stringBuilder = new StringBuilder("<session>\n");
        Iterator iterator = sessionDataArrayList.iterator();
        while (iterator.hasNext()) {
            SessionData sessionData = (SessionData) iterator.next();
            stringBuilder.append(sessionData.toXMLString());
        }
        stringBuilder.append("</session>");
        return stringBuilder.toString();
    }

    private Document getDocument(File file)  throws ParserConfigurationException, SAXException , IOException{

        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setValidating(false);
        DocumentBuilder builder = f.newDocumentBuilder();
        return builder.parse(file);
    }


    private void readDocument(Document document) throws  Exception {

        clear();
        Node node = document.getChildNodes().item(0);
        SessionNode sessionNode = new SessionNode(node);

        List<SessionDataNode> sessionNodes = sessionNode.getSession();

        for (int i = 0; i < sessionNodes.size(); i++) {

            List<FieldsNode> params = sessionNodes.get(i).getParams();
            List<String> atributes = new ArrayList<>();
            for (int j = 0; j < params.size(); j++) {
                FieldsNode paramNode = params.get(j);
                atributes.add(paramNode.getName());
            }
            Iterator iterator = atributes.iterator();
            if(iterator.hasNext()) {
                sessionDataArrayList.add(new SessionData(
                        Integer.parseInt(iterator.next().toString()),
                        iterator.next().toString(),
                        iterator.next().toString(),
                        Integer.parseInt(iterator.next().toString())
                ));

            }


        }

    }

    public void readFromXML(File file) throws Exception {
        readDocument(getDocument(file));
    }

}
