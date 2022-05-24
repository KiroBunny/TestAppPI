package com.pi.service;

import com.pi.GUI.TestAppFrame;
import com.pi.Model.ElementModel;
import com.pi.Model.TestPlanList;
import com.pi.Model.TestSettings;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SelectedElementService {
    private static SelectedElementService instance;
    public static TestAppFrame testAppFrame;
    static TestPlanList testPlanList = new TestPlanList();
    TestPlanService testPlanService;

    private SelectedElementService() {
        testPlanService = new TestPlanService(testPlanList);
    }

    public static SelectedElementService getInstance() {
        if (instance == null) {
            instance = new SelectedElementService();
        }
        return instance;
    }

    public static TestAppFrame getTestAppFrame() {
        return testAppFrame;
    }

    public void showTestPlanList() {
        List<String> list = new ArrayList<>();

        for (ElementModel e : testPlanList.getTesPlanList()) {
            list.add(e.toString());
        }
        testAppFrame.addToTestPlanListPanel(list);
    }

    public void addElementToPlanList(int action, String parameter) {
        int index = testAppFrame.getElementOptionsPanel().getSelectedElement();
        int locatorIndex = testAppFrame.getElementOptionsPanel().getSelectedLocator();
        String locatorText = testAppFrame.getElementOptionsPanel().getLocatorText();

        if (index < 0 || locatorIndex < 0 || action < 0 || locatorText.equals("")) {
            System.err.println("Podaj dobre wartości");
        } else {
            testPlanList.add(new ElementModel(index, locatorText, locatorIndex, action, parameter));
            showTestPlanList();
        }
    }

    public void addElementToPlanList(String text) {
        if (text.equals("")) {
            System.err.println("---Podaj odpowiedni adres strony---");
        } else if (text.equals(TestSettings.pageAddress)) {
            testPlanList.add(new ElementModel("GoTo", text, "HomePage", "", ""));
            showTestPlanList();
        } else if (text.contains("login")) {
            testPlanList.add(new ElementModel("GoTo", text, "LoginPage", "", ""));
            showTestPlanList();
        } else {
            testPlanList.add(new ElementModel("GoTo", text, "", "", ""));
            showTestPlanList();
        }
    }

    public void addElementToPlanList(int assertIndex, String parameter, int equalsIndex, String equals) {
        int index = testAppFrame.getElementOptionsPanel().getSelectedElement();
        int locatorIndex = testAppFrame.getElementOptionsPanel().getSelectedLocator();
        String value = testAppFrame.getElementOptionsPanel().getLocatorText();

        if (index < 0 || locatorIndex < 0 || value.equals("") || assertIndex < 0 || equalsIndex < 0 || equals.equals("")) {
            System.err.println("Podaj dobre wartości");
        } else {
            List<String> paramEquals = new ArrayList<>();
            paramEquals.add(parameter);
            paramEquals.add(equalsIndex == 0 ? "==" : "!=");
            paramEquals.add(equals);
            testPlanList.add(new ElementModel(index, value, locatorIndex, assertIndex, paramEquals, true));
            showTestPlanList();
        }
    }

    public void startPlan() {
        new Thread(testPlanService).start();
    }

    public void clearList() {
        testPlanList.clear();
        showTestPlanList();
    }

    public void clearOneElement() {
        int id = testAppFrame.getTestPlanList().getSelectedIndex();
        testPlanList.remove(testPlanList.getTesPlanList().get(id));
        showTestPlanList();
    }

    public void saveToFile() {
        String filePath = getFilePath();
        if (!filePath.equals("")) {
            try {
                FileOutputStream writeData = new FileOutputStream(filePath);
                ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

                writeStream.writeObject(testPlanList.getTesPlanList());
                writeStream.flush();
                writeStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else System.err.println("Nie znaleziono pliku ");
    }

    public void readFromFile() {
        String filePath = getFilePath();
        if (!filePath.equals("")) {
            try {
                FileInputStream readData = new FileInputStream(filePath);
                ObjectInputStream readStream = new ObjectInputStream(readData);

                ArrayList<ElementModel> list = (ArrayList<ElementModel>) readStream.readObject();
                readStream.close();

                testPlanList.setTesPlanList(list);
                showTestPlanList();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else System.err.println("Nie znaleziono pliku ");
    }

    private String getFilePath() {
        JFrame parentFrame = new JFrame();

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle("Specify a file to save");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileChooser.setFileFilter(filter);
        int userSelection = fileChooser.showSaveDialog(parentFrame);


        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String fileToSave = fileChooser.getSelectedFile().getAbsolutePath();
            System.out.println("Save as file: " + fileToSave);
            return fileToSave;
        }
        return "";
    }
}
