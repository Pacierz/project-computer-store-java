package com.app.computerstore.database;

import com.app.computerstore.exceptions.AddException;
import com.app.computerstore.exceptions.DeleteException;
import com.app.computerstore.exceptions.GetException;
import com.app.computerstore.exceptions.UpdateException;
import com.app.computerstore.models.Product;
import com.app.computerstore.models.ProductRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class ProductRepositoryImplementation implements ProductRepository {
    Connection connection = DatabaseConnection.getConnection();

    @Override
    public ObservableList<Product> getProducts(TableView<Product> tableView) {
        ObservableList<Product> list = null;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id, name, category, price, quantity FROM products;"
            );
            ResultSet rs = ps.executeQuery();

            list = FXCollections.observableArrayList();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String category = rs.getString("category");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");

                list.addAll(Product.of(id, name, category, price, quantity));
            }

            rs.close();
            ps.close();

            tableView.setItems(list);
        } catch (SQLException e) {
            throw new GetException("Error getting Products", e);
        }

        return list;
    }

    @Override
    public void addProduct(Product product) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO products(name, category, price, quantity) VALUES (?, ?, ?, ?);"
            );
            ps.setString(1, product.getName());
            ps.setString(2, product.getCategory());
            ps.setFloat(3, product.getPrice());
            ps.setInt(4, product.getQuantity());

            log.info(ps.executeUpdate() + " row inserted");
            ps.close();
        } catch (SQLException e) {
            throw new AddException("Error adding Product", e);
        }
    }

    @Override
    public void editProduct(TableView<Product> tableView, TableColumn<Product, String> columnName, TableColumn<Product, String> columnCategory, TableColumn<Product, Float> columnPrice, TableColumn<Product, Integer> columnQuantity) {
        columnName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnName.setOnEditCommit(event -> {
            Product product = event.getTableView().getItems().get(event.getTablePosition().getRow());
            product.setName(event.getNewValue());
            updateProduct(product);
        });

        columnCategory.setCellFactory(TextFieldTableCell.forTableColumn());
        columnCategory.setOnEditCommit(event -> {
            Product product = event.getTableView().getItems().get(event.getTablePosition().getRow());
            product.setCategory(event.getNewValue());
            updateProduct(product);
        });

        columnPrice.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        columnPrice.setOnEditCommit(event -> {
            Product product = event.getTableView().getItems().get(event.getTablePosition().getRow());
            product.setPrice(event.getNewValue());
            updateProduct(product);
        });

        columnQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnQuantity.setOnEditCommit(event -> {
            Product product = event.getTableView().getItems().get(event.getTablePosition().getRow());
            product.setQuantity(event.getNewValue());
            updateProduct(product);
        });
    }

    @Override
    public void updateProduct(Product product) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE products SET name=?, category=?, price=?, quantity=? WHERE id=?;"
            );
            ps.setString(1, product.getName());
            ps.setString(2, product.getCategory());
            ps.setFloat(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getId());

            log.info(ps.executeUpdate() + " row updated");
            ps.close();
        } catch (SQLException e) {
            throw new UpdateException("Error updating Product", e);
        }
    }

    @Override
    public void deleteProduct(Product product) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM products WHERE id = ?;"
            );
            ps.setInt(1, product.getId());
            log.info(ps.executeUpdate() + " row deleted");
            ps.close();
        } catch (SQLException e) {
            throw new DeleteException("Error deleting Product", e);
        }
    }

    @Override
    public void sortById(TableView<Product> tableView, TableColumn<Product, Integer> columnId) {
        columnId.setSortType(TableColumn.SortType.ASCENDING);
        tableView.getSortOrder().add(columnId);
        tableView.sort();
    }
}