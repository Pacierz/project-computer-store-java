package com.app.computerstore.database;

import com.app.computerstore.exceptions.AddException;
import com.app.computerstore.exceptions.DeleteException;
import com.app.computerstore.exceptions.GetException;
import com.app.computerstore.exceptions.UpdateException;
import com.app.computerstore.models.Worker;
import com.app.computerstore.models.WorkerRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class WorkerRepositoryImplementation implements WorkerRepository {
    Connection connection = DatabaseConnection.getConnection();

    @Override
    public ObservableList<Worker> getWorkers(TableView<Worker> tableView) {
        ObservableList<Worker> list = null;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id, name, surname, phone_number, email, address, salary FROM workers;"
            );
            ResultSet rs = ps.executeQuery();

            list = FXCollections.observableArrayList();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int phoneNumber = rs.getInt("phone_number");
                String email = rs.getString("email");
                String address = rs.getString("address");
                int salary = rs.getInt("salary");

                list.addAll(Worker.of(id, name, surname, phoneNumber, email, address, salary));
            }

            rs.close();
            ps.close();

            tableView.setItems(list);
        } catch (SQLException e) {
            throw new GetException("Error getting Workers", e);
        }

        return list;
    }

    @Override
    public void addWorker(Worker worker) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO workers(name, surname, phone_number, email, address, salary) VALUES (?, ?, ?, ?, ?, ?);"
            );
            ps.setString(1, worker.getName());
            ps.setString(2, worker.getSurname());
            ps.setInt(3, worker.getPhoneNumber());
            ps.setString(4, worker.getEmail());
            ps.setString(5, worker.getAddress());
            ps.setInt(6, worker.getSalary());

            log.info(ps.executeUpdate() + " row inserted");
            ps.close();
        } catch (SQLException e) {
            throw new AddException("Error adding Worker", e);
        }

    }

    @Override
    public void editWorker(TableView<Worker> tableView, TableColumn<Worker, String> columnName, TableColumn<Worker, String> columnSurname, TableColumn<Worker, Integer> columnPhoneNumber, TableColumn<Worker, String> columnEmail, TableColumn<Worker, String> columnAddress, TableColumn<Worker, Integer> columnSalary) {
        columnName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnName.setOnEditCommit(event -> {
            Worker worker = event.getTableView().getItems().get(event.getTablePosition().getRow());
            worker.setName(event.getNewValue());
            updateWorker(worker);
        });

        columnSurname.setCellFactory(TextFieldTableCell.forTableColumn());
        columnSurname.setOnEditCommit(event -> {
            Worker worker = event.getTableView().getItems().get(event.getTablePosition().getRow());
            worker.setSurname(event.getNewValue());
            updateWorker(worker);
        });

        columnPhoneNumber.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnPhoneNumber.setOnEditCommit(event -> {
            Worker worker = event.getTableView().getItems().get(event.getTablePosition().getRow());
            worker.setPhoneNumber(event.getNewValue());
            updateWorker(worker);
        });

        columnEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        columnEmail.setOnEditCommit(event -> {
            Worker worker = event.getTableView().getItems().get(event.getTablePosition().getRow());
            worker.setEmail(event.getNewValue());
            updateWorker(worker);
        });

        columnAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        columnAddress.setOnEditCommit(event -> {
            Worker worker = event.getTableView().getItems().get(event.getTablePosition().getRow());
            worker.setAddress(event.getNewValue());
            updateWorker(worker);
        });

        columnSalary.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnSalary.setOnEditCommit(event -> {
            Worker worker = event.getTableView().getItems().get(event.getTablePosition().getRow());
            worker.setSalary(event.getNewValue());
            updateWorker(worker);
        });
    }

    @Override
    public void updateWorker(Worker worker) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE workers SET name=?, surname=?, phone_number=?, email=?, address=?, salary=? WHERE id=?;"
            );
            ps.setString(1, worker.getName());
            ps.setString(2, worker.getSurname());
            ps.setInt(3, worker.getPhoneNumber());
            ps.setString(4, worker.getEmail());
            ps.setString(5, worker.getAddress());
            ps.setInt(6, worker.getSalary());
            ps.setInt(7, worker.getId());

            log.info(ps.executeUpdate() + " row updated");
            ps.close();
        } catch (SQLException e) {
            throw new UpdateException("Error updating Worker", e);
        }
    }

    @Override
    public void deleteWorker(Worker worker) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM workers WHERE id = ?;"
            );
            ps.setInt(1, worker.getId());
            log.info(ps.executeUpdate() + " row deleted");
            ps.close();
        } catch (SQLException e) {
            throw new DeleteException("Error deleting Worker", e);
        }
    }

    @Override
    public void sortById(TableView<Worker> tableView, TableColumn<Worker, Integer> columnId) {
        columnId.setSortType(TableColumn.SortType.ASCENDING);
        tableView.getSortOrder().add(columnId);
        tableView.sort();
    }
}