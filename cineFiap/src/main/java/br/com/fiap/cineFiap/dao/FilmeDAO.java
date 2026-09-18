package br.com.fiap.cineFiap.dao;

import br.com.fiap.cineFiap.enums.CategoriaFilmeEnum;
import br.com.fiap.cineFiap.enums.ClassificacaoIndicativaEnum;
import br.com.fiap.cineFiap.enums.SimNaoEnum;
import br.com.fiap.cineFiap.models.Filme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmeDAO {

    private Connection conexao;

    public void cadastro(Filme filme){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            String sql = "INSERT INTO TBL_FILME (" +
                    "ID_FILME, TX_NOME, NR_DURACAO, TP_CATEGORIA, " +
                    "TP_CLASSIFICACAO, NR_ANO, TX_CAPA, TX_DIRETOR, " +
                    "TX_ELENCO, TX_DESCRICAO, NR_AVALIACAO, CHK_EM_CARTAZ) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conexao.prepareStatement(sql);
            ps.setLong(1, filme.getId());
            ps.setString(2, filme.getNome());
            ps.setInt(3, filme.getDuracao());
            ps.setString(4, filme.getCategoria().name());
            ps.setString(5, filme.getClassificacao().name());
            ps.setInt(6, filme.getAno());
            ps.setString(7, filme.getCapa());
            ps.setString(8, filme.getDiretor());
            ps.setString(9, filme.getElenco());
            ps.setString(10, filme.getDescricao());
            ps.setDouble(11, filme.getAvaliacao());
            ps.setString(12, filme.getEmCartaz().name());
            ps.close();
            conexao.close();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void exclusao(long id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            ps = conexao.prepareStatement("DELETE FROM TBL_FILME " +
                    " WHERE ID_FILME = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();
            conexao.close();


        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void alterar(Filme filme){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            String sql = "UPDATE TBL_FILME SET TX_NOME = ?, " +
                    "NR_DURACAO = ?, TP_CATEGORIA = ?, " +
                    "TP_CLASSIFICACAO = ?, NR_ANO = ?, " +
                    "TX_CAPA = ?, TX_DIRETOR = ?, TX_ELENCO = ?, " +
                    "TX_DESCRICAO = ?, NR_AVALIACAO = ?, " +
                    "CHK_EM_CARTAZ = ? WHERE ID_FILME = ?";
            ps = conexao.prepareStatement(sql);

            ps.setString(1, filme.getNome());
            ps.setInt(2, filme.getDuracao());
            ps.setString(3, filme.getCategoria().name());
            ps.setString(4, filme.getClassificacao().name());
            ps.setInt(5, filme.getAno());
            ps.setString(6, filme.getCapa());
            ps.setString(7, filme.getDiretor());
            ps.setString(8, filme.getElenco());
            ps.setString(9, filme.getDescricao());
            ps.setDouble(10, filme.getAvaliacao());
            ps.setString(11, filme.getEmCartaz().name());
            ps.setLong(12, filme.getId());

            ps.executeUpdate();
            ps.close();
            conexao.close();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Filme consultaNomeFilme(String nome){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        Filme filme = new Filme();
        try{
            ps = conexao.prepareStatement("Select * from tbl_filme where tx_nome = ?");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                filme.setId(rs.getLong(1));
                filme.setNome(rs.getString(2));
                filme.setDuracao(rs.getInt(3));
                filme.setCategoria(
                        CategoriaFilmeEnum.valueOf(rs.getString(4))
                );
                filme.setClassificacao(
                        ClassificacaoIndicativaEnum.valueOf(rs.getString(5))
                );
                filme.setAno(rs.getInt(6));
                filme.setCapa(rs.getString(7));
                filme.setDiretor(rs.getString(8));
                filme.setElenco(rs.getString(9));
                filme.setDescricao(rs.getString(10));
                filme.setAvaliacao(rs.getDouble(11));
                filme.setEmCartaz(
                        SimNaoEnum.valueOf(rs.getString(12))
                );

                rs.close();
                ps.close();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            try{
                if(conexao != null && !conexao.isClosed())
                    conexao.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return filme;
    }

    public Filme consultaAnoFilme(int ano){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        Filme filme = new Filme();
        try{
            ps = conexao.prepareStatement("Select * from tbl_filme where NR_ANO = ?");
            ps.setInt(6, ano);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                filme.setId(rs.getLong(1));
                filme.setNome(rs.getString(2));
                filme.setDuracao(rs.getInt(3));
                filme.setCategoria(
                        CategoriaFilmeEnum.valueOf(rs.getString(4))
                );
                filme.setClassificacao(
                        ClassificacaoIndicativaEnum.valueOf(rs.getString(5))
                );
                filme.setAno(rs.getInt(6));
                filme.setCapa(rs.getString(7));
                filme.setDiretor(rs.getString(8));
                filme.setElenco(rs.getString(9));
                filme.setDescricao(rs.getString(10));
                filme.setAvaliacao(rs.getDouble(11));
                filme.setEmCartaz(
                        SimNaoEnum.valueOf(rs.getString(12))
                );

                rs.close();
                ps.close();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return filme;
    }
}
