/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lytrieudat.todolist.model;

import java.io.File;
import java.time.LocalDate;
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
    //private int currentIndex = 0;
    LocalDate today = LocalDate.now();

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

    private void initNewTask() {
        list.add(new Task("Have a nice day", today, false));

    }

    public void add(Task task) {
        list.add(task);
    }

    public void update(Task task) {

    }

    public void clearList() throws Exception {
        list.clear();
        saveToFile();
    }

//    public void setCurrentTask(Task task) {
//        currentIndex = list.indexOf(task);
//    }
//
//    public Task getCurrentTask() {
//        if (list.size() == 0) {
//            return null;
//        }
//        return list.get(currentIndex);
//    }
//    public boolean CheckTask(Task task) throws Exception {
//        Task existTask = findByDetail(task.getDetail());
//        boolean flag = false;
//        if (existTask != null) {
//            existTask.setChecked(true);
//            saveToFile();
//            flag = true;
//
//        }
//        return flag;
//    }
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
