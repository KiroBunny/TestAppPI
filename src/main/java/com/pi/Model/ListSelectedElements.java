package com.pi.Model;

import java.util.ArrayList;
import java.util.List;

public class ListSelectedElements {
    List<ElementModel> elements;

    public ListSelectedElements(List<ElementModel> elements) {
        this.elements = elements;
    }

    public void addElement(ElementModel element){
        if (elements == null) elements = new ArrayList<>();
        elements.add(element);
    }

    public void removeElement(int index){
        if (elements == null) elements = new ArrayList<>();
        elements.remove(index);
    }

    public int getElementIndex(ElementModel element){
        return elements.indexOf(element);
    }

    public void clear(){
        elements.clear();
    }

    public List<ElementModel> getElements() {
        return elements;
    }

    public void setElements(List<ElementModel> elements) {
        this.elements = elements;
    }
}
