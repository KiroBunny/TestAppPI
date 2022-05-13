package com.pi.service;

import com.pi.Model.ElementModel;
import com.pi.Model.PageElements;
import com.pi.Model.TestPlanList;
import com.pi.components.*;
import com.pi.pages.Pages;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class TestPlanService implements Runnable {
    List<ElementModel> planList;
    Pages pages = new Pages();

    public TestPlanService(TestPlanList testPlanList) {
        planList = testPlanList.getTesPlanList();
        //Pages.setUpChromeDriver();
    }

    @Override
    public void run() {
        System.out.println("Nowy watek");
        Pages.setUpChromeDriver();
        Pages.homePage().goTo();

        for (ElementModel actual : planList) {
            createNewElement(actual);
        }

        pages.close();
    }

    private void createNewElement(ElementModel elementModel) {
        if (elementModel.getType().equals("GoTo")) {
            if (elementModel.getFindBy().equals("HomePage")) Pages.homePage().goTo();
            else if (elementModel.getFindBy().equals("LoginPage")) Pages.loginPage().goTo();
            else pages.goTo(elementModel.getValue());
        } else {
            //Locator locator = convertToLocator(elementModel);
            //Element element = convertToElement(elementModel);
        }
    }

    private static Locator convertToLocator(ElementModel elementModel) {
        Locator locator = new Locator();
        Method method;

        try {
            method = locator.getClass().getMethod(PageElements.getLocators()[1], String.class);
            return (Locator) method.invoke(locator, "value");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Element convertToElement(ElementModel elementModel) {
        Locator locator = Locator.id(elementModel.getFindBy());
        switch (PageElements.getElements().get(1)) {
            case "Button":
                return new Button(locator);
            case "InputText":
                return new InputText(locator);
            case "CheckBox":
                return new CheckBox(locator);
        }

        return null;
    }
}
