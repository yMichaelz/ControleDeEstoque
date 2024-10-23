package org.example.controledeestoque.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.controledeestoque.model.Produto;
import org.example.controledeestoque.dao.ProdutoDAO;

public class ProdutoControler {
    @FXML
    private TextField nomeField, descricaoField, quantidadeField, precoField;
    @FXML
    private Button adicionarButton, atualizarButton, deletarButton;
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
        String quantidadeText = quantidadeField.getText();
        String precoText = precoField.getText();

        // Verificar se os campos obrigatórios estão preenchidos
        if (nome.isEmpty() || quantidadeText.isEmpty() || precoText.isEmpty()) {
            System.out.println("Por favor, preencha todos os campos obrigatórios.");
            return; // Não prossegue se os campos estiverem vazios
        }

        // Tentar converter os campos de quantidade e preço
        try {
            int quantidade = Integer.parseInt(quantidadeText);
            double preco = Double.parseDouble(precoText);

            Produto produto = new Produto(0, nome, descricao, quantidade, preco);
            produtoDAO.adicionarProduto(produto);
            carregarProdutos();
        } catch (NumberFormatException e) {
            System.out.println("Erro: Certifique-se de que a quantidade e o preço sejam números válidos.");
        }
    }

    @FXML
    private void atualizarProduto() {
        Produto produtoSelecionado = tabelaProdutos.getSelectionModel().getSelectedItem();
        if (produtoSelecionado != null) {
            produtoSelecionado.setNome(nomeField.getText());
            produtoSelecionado.setDescricao(descricaoField.getText());
            produtoSelecionado.setQuantidade(Integer.parseInt(quantidadeField.getText()));
            produtoSelecionado.setPreco(Double.parseDouble(precoField.getText()));
            produtoDAO.atualizarProduto(produtoSelecionado);
            carregarProdutos();
            limparCampos();
        }
    }

    @FXML
    private void deletarProduto() {
        Produto produtoSelecionado = tabelaProdutos.getSelectionModel().getSelectedItem();
        if (produtoSelecionado != null) {
            produtoDAO.deletarProduto(produtoSelecionado.getId());
            carregarProdutos();
            limparCampos();
        }
    }

    private void limparCampos() {
        nomeField.clear();
        descricaoField.clear();
        quantidadeField.clear();
        precoField.clear();
    }
}