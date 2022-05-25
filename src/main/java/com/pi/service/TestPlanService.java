package com.pi.service;

import com.pi.Model.ElementModel;
import com.pi.Model.PageElements;
import com.pi.Model.TestPlanList;
import com.pi.components.*;
import com.pi.pages.Browser;
import com.pi.pages.Pages;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestPlanService implements Runnable {
    List<ElementModel> planList;
    Map<ElementModel, String> resultMap = new HashMap<>();
    Pages pages = new Pages();

    public TestPlanService(TestPlanList testPlanList) {
        planList = testPlanList.getTesPlanList();
    }

    @Override
    public void run() {
        try {
            Pages.setUpChromeDriver();

            for (ElementModel actual : planList) {
                createNewElement(actual);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pages.close();
            saveResult();
        }
    }

    private void createNewElement(ElementModel elementModel) {
        try {
            if (elementModel.getType().equals("GoTo")) {
                if (elementModel.getFindBy().equals("HomePage")) Pages.homePage().goTo();
                else if (elementModel.getFindBy().equals("LoginPage")) Pages.loginPage().goTo();
                else pages.goTo(elementModel.getLocatorText());
            } else {
                Element element = convertToElement(elementModel);
                invokeMethod(element, elementModel);
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put(elementModel, e.getStackTrace().toString());
        }
    }

    private Locator convertToLocator(ElementModel elementModel) {
        Locator locator = new Locator();
        Method method;
        try {
            method = locator.getClass().getMethod(elementModel.getFindBy(), String.class);
            return (Locator) method.invoke(locator, elementModel.getLocatorText());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            resultMap.put(elementModel, e.getStackTrace().toString());
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
            case "Image":
                return new Image(locator);
            case "Div":
                return new Div(locator);
            case "Span":
                return new Span(locator);
            case "Table":
                return new Table(locator);
            case "Inny":
                return new Element(Browser.driver.findElement(locator.getBy()));
        }
        return new Element(Browser.driver.findElement(locator.getBy()));
    }

    private void invokeMethod(Element element, ElementModel elementModel) {
        Method method;
        Object result = null;
        String action = elementModel.getAction().substring(0, elementModel.getAction().indexOf("("));
        action = action.equals("sendKeys") ? "sendKeysString" : action;
        String parameters = getParam(elementModel);

        try {
            if (!elementModel.getAction().endsWith("()") && elementModel.getParameter() != null) {
                method = element.getClass().getMethod(action, String.class);
                result = method.invoke(element, parameters);
            } else {
                method = element.getClass().getMethod(action);
                result = method.invoke(element);
            }
            if (elementModel.getAssertion()) assertElement(elementModel, result);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | AssertionError e) {
            e.printStackTrace();
            resultMap.put(elementModel, e.getStackTrace().toString());
        }
    }


    private String getParam(ElementModel elementModel) {
        String param = elementModel.getParameter().toString();
        if (elementModel.getAssertion()){
            List<String> list = (List<String>) elementModel.getParameter();
            param = list.get(0);
        }
        return param;
    }

    private void assertElement(ElementModel elementModel, Object result) {
        List<String> list = (List<String>) elementModel.getParameter();
        switch (list.get(1)){
            case "==":
                System.out.println("Assert rezultat: " + (result).equals(list.get(2)));
                if (!result.equals(list.get(2))) throw new AssertionError("" + result + " - nie równa się - " + list.get(2));
                break;
            case "!=":
                System.out.println("Assert rezultat: " + !(result).equals(list.get(2)));
                if (result.equals(list.get(2))) throw new AssertionError("" + result + " - jest równy - " + list.get(2));
                break;
        }
    }

    private void saveResult() {
        if (resultMap.isEmpty()){
            SelectedElementService.testAppFrame.addResultToLabel(true);
        }
        else {
            SelectedElementService.testAppFrame.addResultToLabel(false);
            try {
                FileOutputStream writeData = new FileOutputStream("src/main/resources/TestResult.txt");
                ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

                writeStream.writeObject(resultMap);
                writeStream.flush();
                writeStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
