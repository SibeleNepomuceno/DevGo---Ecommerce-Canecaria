/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.Model.Bean;

import ecommerce.Model.Dao.ContatoDAO;
import ecommerce.Model.DaoImplementation.ContatoDAOImpl;
import ecommerce.Model.MetodosAcessores.Contato;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/*  @author sibele */
@ManagedBean(name = "ContatoBean")
@SessionScoped
public class ContatoBean {

    private Contato contato = new Contato();
    private int id_contato;

    public ContatoBean() {
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public int getId_contato() {
        return id_contato;
    }

    public void setId_contato(int id_contato) {
        this.id_contato = id_contato;
    }

    /*Responsavel pelo insert dos formularios de solicitação*/
    public void CadastrarSolicitacaoContato() throws Exception {
        ContatoDAO contatos = new ContatoDAOImpl();
        try {
            contatos.CadastrarSolicitacaoContato(contato);
        } catch (SQLException erro) {
            System.err.println("Não foi possivel realizar solicitação");
        }
        contato = new Contato();
    }

    /* Verifica quantos chamados tem em cada fila */
    public int CountSolicitacaoChamadoContato(int cod_tipo_solicitacao) throws Exception {
        ContatoDAO contatos = new ContatoDAOImpl();
        int quantidadeChamados = contatos.CountSolicitacaoChamadoContato(cod_tipo_solicitacao);

        return quantidadeChamados;
    }

    /* Lista os produtos cadastrados no banco */
    public List<Contato> ListarSolicitacoesContatos() throws Exception {
        ContatoDAO contatosDao = new ContatoDAOImpl();
        List<Contato> ListarSolicitacoesContatos = null;
        ListarSolicitacoesContatos = contatosDao.ListarSolicitacoesContatos();

        if (ListarSolicitacoesContatos.isEmpty()) {
            ListarSolicitacoesContatos = null;
        }
        return ListarSolicitacoesContatos;
    }

    public String encontrarFilaPorId() throws Exception {
        ContatoDAO contatos = new ContatoDAOImpl();
        List<Contato> ListarSolicitacoesContatos = null;
        try {
            ListarSolicitacoesContatos = contatos.encontrarFilaPorId(id_contato);
        } catch (SQLException erro) {
            System.err.println("Não foi possivel localizar fila");
        }
        return "RespondendoFaleConosco";
    }

    public List<Contato> listEncontrarFilaPorId() throws Exception {
        ContatoDAO contatos = new ContatoDAOImpl();
        List<Contato> ListarSolicitacoesContatos = null;
        try {
            ListarSolicitacoesContatos = contatos.encontrarFilaPorId(id_contato);
        } catch (SQLException erro) {
            System.err.println("Não foi possivel localizar fila");
        }
        return ListarSolicitacoesContatos;
    }

 
    public String finalizandoChamado(int cod_chamado) throws Exception {
        ContatoDAO contatos = new ContatoDAOImpl();
       try {
            contatos.finalizarChamado(contato, cod_chamado);
        } catch (SQLException erro) {
            System.err.println("Não foi possivel realizar solicitação");
        }
       return "AcompanhamentoFaleConosco";
    }

}
