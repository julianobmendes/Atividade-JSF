package br.com.cadUser;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
public class Pessoa implements Serializable {

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @SuppressWarnings("unchecked")
    public List<String> getNomes() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = facesContext.getExternalContext().getSessionMap();

        List<String> nomes = (List<String>) sessionMap.get("nomes");

        if (nomes == null) {
            nomes = new ArrayList<>();
            sessionMap.put("nomes", nomes);
        }

        return nomes;
    }

    public void adicionarNome() {
        if (nome != null && !nome.trim().isEmpty()) {
            List<String> nomes = getNomes();
            nomes.add(nome);
            nome = "";
        }
    }
}
