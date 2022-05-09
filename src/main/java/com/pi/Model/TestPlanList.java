package com.pi.Model;

import com.pi.components.Element;

import java.util.HashMap;
import java.util.Map;

public class TestPlanList {
    Map<Element, String> tesPlanList = new HashMap();

    public void add(Element element, String value) {
        tesPlanList.put(element, value);
    }

    public String remove(Element element) {
        return tesPlanList.remove(element);
    }

    public void clear() {
        tesPlanList.clear();
    }

    public Map<Element, String> getTesPlanList() {
        return tesPlanList;
    }

    public void setTesPlanList(Map<Element, String> tesPlanList) {
        this.tesPlanList = tesPlanList;
    }
}
