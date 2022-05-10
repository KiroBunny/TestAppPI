package com.pi.Model;

import java.util.ArrayList;
import java.util.List;

public class TestPlanList {
    List<ElementModel> testPlanList = new ArrayList<>();

    public void add(ElementModel element) {
        testPlanList.add(element);
    }

    public boolean remove(ElementModel element) {
        return testPlanList.remove(element);
    }

    public void clear() {
        testPlanList.clear();
    }

    public List<ElementModel> getTesPlanList() {
        return testPlanList;
    }

    public void setTesPlanList(List<ElementModel> tesPlanList) {
        this.testPlanList = tesPlanList;
    }
}
