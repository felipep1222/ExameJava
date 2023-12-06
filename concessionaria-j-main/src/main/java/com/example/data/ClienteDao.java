package com.example.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Cliente;

public class ClienteDao {

    private Connection conexao;

    public ClienteDao() {
        try {
            conexao = ConnectionFactory.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, email) VALUES (?, ?)";
        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, cliente.getNome());
            comando.setString(2, cliente.getEmail());
            comando.executeUpdate();
        }
    }

    public List<Cliente> listarTodos() throws SQLException {
        String sql = "SELECT * FROM clientes";
        try (PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            List<Cliente> lista = new ArrayList<>();

            while (resultado.next()) {
                lista.add(new Cliente(
                        resultado.getLong("id"),
                        resultado.getString("nome"),
                        resultado.getString("email")
                ));
            }

            return lista;
        }
    }

    public void apagar(Cliente cliente) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id=?";
        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setLong(1, cliente.getId());
            comando.executeUpdate();
        }
    }

    public void atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nome=?, email=? WHERE id=?";
        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, cliente.getNome());
            comando.setString(2, cliente.getEmail());
            comando.setLong(3, cliente.getId());
            comando.executeUpdate();
        }
    }
}
