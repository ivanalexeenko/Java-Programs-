package com.session;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SessionNode {
    private Node node;

    public SessionNode(Node node) {
        this.node = node;
    }
    public List<SessionDataNode> getSession() {
        ArrayList<SessionDataNode> toyNodes = new ArrayList<SessionDataNode>();

        NodeList nodeList = node.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                SessionDataNode toyNode = new SessionDataNode(node);
                toyNodes.add(toyNode);
            }
        }

        return toyNodes;
    }

}
