package org.example.controledeestoque.dao;
import org.example.controledeestoque.database.ConexãoBD;
import org.example.controledeestoque.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ProdutoDAO {
    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection conn = ConexãoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco")
                );
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }

        return produtos;
    }

    public void adicionarProduto(Produto produto) {
        String sql = "INSERT INTO produtos (nome, descricao, quantidade, preco) VALUES (?, ?, ?, ?)";

        try (Connection conn =ConexãoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setDouble(4, produto.getPreco());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
        }
    }

    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, descricao = ?, quantidade = ?, preco = ? WHERE id = ?";

        try (Connection conn = ConexãoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setDouble(4, produto.getPreco());
            stmt.setInt(5, produto.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    public void deletarProduto(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (Connection conn = ConexãoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar produto: " + e.getMessage());
        }
    }
}
