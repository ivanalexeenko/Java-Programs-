package com.session;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class FieldsNode {

    private Node node;

    public FieldsNode(Node node) {
        this.node = node;
    }

    public String getName() {

        NamedNodeMap attributes = node.getAttributes();

        Node nameAttribute = attributes.getNamedItem("name");

        return nameAttribute.getNodeValue();
    }

}
