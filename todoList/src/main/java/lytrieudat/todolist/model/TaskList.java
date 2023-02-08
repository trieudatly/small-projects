/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lytrieudat.todolist.model;

import java.io.File;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import lytrieudat.todolist.helper.XFile;

/**
 *
 * @author lytri
 */
public class TaskList {

    private ArrayList<Task> list = new ArrayList<>();
    private String path = "todo.dat";

    public void saveToFile() throws Exception {
        XFile.writeObject(path, list);
    }

    public void loadFromFile() throws Exception {
        File file = new File(path);
        if (file.exists()) {
            list = (ArrayList<Task>) XFile.readObject(path);
        } else {
            saveToFile();
        }
    }

    public void add(Task task) {
        list.add(task);
    }

    public boolean deleteByDetail(String taskDetail) {
        for (Task task : list) {
            if (task.getDetail().equals(taskDetail)) {
                list.remove(task);
                return true;
            }
        }
        return false;
    }

    public boolean updateByDetail(String taskDetail, String update) {
        for (Task task : list) {
            if (task.getDetail().equals(taskDetail)) {
                task.setDetail(update);
                return true;
            }
        }
        return false;
    }

    public void clearList() throws Exception {
        list.clear();
        saveToFile();
    }

    public void checkTask(Task task) {
        if (!task.isChecked()) {
            task.setChecked(true);
        } else {
            task.setChecked(false);
        }
    }

    public Task findByDetail(String taskDetail) {
        for (Task task : list) {
            if (task.getDetail().equals(taskDetail)) {

                return task;
            }
        }
        return null;
    }

    public void renderToTable(DefaultTableModel tblModel) {
        tblModel.setRowCount(0);
        for (Task task : list) {
            tblModel.addRow(new Object[]{
                task.getDate(),
                task.getDetail(),
                task.isChecked()});
        }
        tblModel.fireTableDataChanged();
    }
}
