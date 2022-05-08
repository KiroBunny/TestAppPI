package com.pi.Model;

import java.util.ArrayList;
import java.util.List;

public class ListSelectedElements {
    List<Element> elements;

    public ListSelectedElements(List<Element> elements) {
        this.elements = elements;
    }

    public void addElement(Element element){
        if (elements == null) elements = new ArrayList<>();
        elements.add(element);
    }

    public void removeElement(int index){
        if (elements == null) elements = new ArrayList<>();
        elements.remove(index);
    }

    public int getElementIndex(Element element){
        return elements.indexOf(element);
    }

    public void clear(){
        elements.clear();
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }
}
