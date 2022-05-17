package com.pi.service;

import com.pi.Model.ElementModel;
import com.pi.Model.PageElements;
import com.pi.Model.TestPlanList;
import com.pi.components.*;
import com.pi.pages.Browser;
import com.pi.pages.Pages;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class TestPlanService implements Runnable {
    List<ElementModel> planList;
    Pages pages = new Pages();

    public TestPlanService(TestPlanList testPlanList) {
        planList = testPlanList.getTesPlanList();
    }

    @Override
    public void run() {
        try {
            System.out.println("Nowy watek");
            Pages.setUpChromeDriver();
            Pages.homePage().goTo();

            for (ElementModel actual : planList) {
                createNewElement(actual);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pages.close();
        }
    }

    private void createNewElement(ElementModel elementModel) {

        if (elementModel.getType().equals("GoTo")) {
            if (elementModel.getFindBy().equals("HomePage")) Pages.homePage().goTo();
            else if (elementModel.getFindBy().equals("LoginPage")) Pages.loginPage().goTo();
            else pages.goTo(elementModel.getLocatorText());
        } else {
            Element element = convertToElement(elementModel);
            invokeMethod(element, elementModel);
        }
    }

    private static Locator convertToLocator(ElementModel elementModel) {
        Locator locator = new Locator();
        Method method;
        try {
            method = locator.getClass().getMethod(elementModel.getFindBy(), String.class);
            return (Locator) method.invoke(locator, elementModel.getLocatorText());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Element convertToElement(ElementModel elementModel) {
        Locator locator = convertToLocator(elementModel);
        assert locator != null;
        switch (PageElements.getElements().get(1)) {
            case "Button":
                return new Button(locator);
            case "InputText":
                return new InputText(locator);
            case "CheckBox":
                return new CheckBox(locator);
        }
        return new Element(Browser.driver.findElement(locator.getBy()));
    }

    private void invokeMethod(Element element, ElementModel elementModel) {
        Method method;
        String action = elementModel.getAction().substring(0, elementModel.getAction().indexOf("("));
        action = action.equals("sendKeys") ? "sendKeysString" : action;

        try {
            if (!elementModel.getAction().endsWith("()") && elementModel.getParameter() != null) {
                method = element.getClass().getMethod(action, String.class);
                method.invoke(element, elementModel.getParameter().toString());
            } else {
                method = element.getClass().getMethod(action);
                method.invoke(element);
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
