package com.session;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SessionDataNode implements Serializable {

    private Node node;

    public SessionDataNode(Node node) {
        this.node = node;
    }
    public List<FieldsNode> getParams() {

        ArrayList<FieldsNode> methods = new ArrayList<FieldsNode>();

        NodeList methodNodes = node.getChildNodes();

        for (int i = 0; i < methodNodes.getLength(); i++) {
            node = methodNodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                FieldsNode methodNode = new FieldsNode(node);
                methods.add(methodNode);
            }
        }

        return methods;
    }



}
