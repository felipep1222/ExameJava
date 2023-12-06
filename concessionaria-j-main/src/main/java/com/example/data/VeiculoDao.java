package com.example.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Cliente;
import com.example.model.Veiculo;

public class VeiculoDao {

    private Connection conexao;

    public VeiculoDao() {
        try {
            conexao = ConnectionFactory.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO veiculos (marca, modelo, ano, valor, cliente_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, veiculo.getMarca());
            comando.setString(2, veiculo.getModelo());
            comando.setInt(3, veiculo.getAno());
            comando.setBigDecimal(4, veiculo.getValor());
            comando.setLong(5, veiculo.getCliente().getId());
            comando.executeUpdate();
        }
    }

    public List<Veiculo> listarTodos() throws SQLException {
        String sql = "SELECT veiculos.*, clientes.nome, clientes.email FROM veiculos INNER JOIN clientes ON veiculos.cliente_id=clientes.id";
        try (PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            List<Veiculo> lista = new ArrayList<>();

            while (resultado.next()) {
                lista.add(new Veiculo(
                        resultado.getLong("id"),
                        resultado.getString("marca"),
                        resultado.getString("modelo"),
                        resultado.getInt("ano"),
                        resultado.getBigDecimal("valor"),
                        new Cliente(
                                resultado.getLong("cliente_id"),
                                resultado.getString("nome"),
                                resultado.getString("email")
                        )
                ));
            }

            return lista;
        }
    }

    public void apagar(Veiculo veiculo) throws SQLException {
        String sql = "DELETE FROM veiculos WHERE id=?";
        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setLong(1, veiculo.getId());
            comando.executeUpdate();
        }
    }

    public void atualizar(Veiculo veiculo) throws SQLException {
        String sql = "UPDATE veiculos SET marca=?, modelo=?, ano=?, valor=? WHERE id=?";
        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, veiculo.getMarca());
            comando.setString(2, veiculo.getModelo());
            comando.setInt(3, veiculo.getAno());
            comando.setBigDecimal(4, veiculo.getValor());
            comando.setLong(5, veiculo.getId());
            comando.executeUpdate();
        }
    }
}
