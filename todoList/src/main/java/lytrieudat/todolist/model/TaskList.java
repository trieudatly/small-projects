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
    private int currentIndex = 0;
    LocalDate today = LocalDate.now();

    public void saveToFile() throws Exception {
        XFile.writeObject(path, list);
    }

    public void loadFromFile() throws Exception {
        File file = new File(path);
        if (file.exists()) {
            list = (ArrayList<Task>) XFile.readObject(path);
        } else {
            initNewTask();
        }
    }

    private void initNewTask() {
        list.add(new Task("01", "go to bed", today, true));

    }

    public void add(Task task) {
        list.add(task);
    }

    public Task getCurrentEmployee() {
        if (list.size() == 0) {
            return null;
        }
        return list.get(currentIndex);
    }

    public void renderToTable(DefaultTableModel tblModel) {
        tblModel.setRowCount(0);
        for (Task task : list) {
            Object[] row = new Object[]{
                task.getId(),
                task.getDetail(),
                task.getDate(),
                task.isChecked()};
            tblModel.addRow(row);
        }
        tblModel.fireTableDataChanged();
    }
}
