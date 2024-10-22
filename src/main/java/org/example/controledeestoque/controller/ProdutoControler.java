package org.example.controledeestoque.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.controledeestoque.model.Produto;
import org.example.controledeestoque.dao.ProdutoDAO;

public class ProdutoControler {
    @FXML
    private TextField nomeField, descricaoField, quantidadeField, precoField;
    @FXML
    private Button adicionarButton;
    @FXML
    private TableView<Produto> tabelaProdutos;
    @FXML
    private TableColumn<Produto, Integer> idColumn;
    @FXML
    private TableColumn<Produto, String> nomeColumn;
    @FXML
    private TableColumn<Produto, Integer> quantidadeColumn;
    @FXML
    private TableColumn<Produto, Double> precoColumn;

    private ProdutoDAO produtoDAO = new ProdutoDAO();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        quantidadeColumn.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        precoColumn.setCellValueFactory(new PropertyValueFactory<>("preco"));

        carregarProdutos();
    }

    @FXML
    private void carregarProdutos() {
        tabelaProdutos.getItems().setAll(produtoDAO.listarProdutos());
    }

    @FXML
    private void adicionarProduto() {
        String nome = nomeField.getText();
        String descricao = descricaoField.getText();
        int quantidade = Integer.parseInt(quantidadeField.getText());
        double preco = Double.parseDouble(precoField.getText());

        Produto produto = new Produto(0, nome, descricao, quantidade, preco);
        produtoDAO.adicionarProduto(produto);
        carregarProdutos();
    }
}
