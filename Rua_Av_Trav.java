public class Rua_Av_Trav {
    private String endereco;
    private String nomeEnd;
    private ListaSinalizacoes lista;

    public Rua_Av_Trav(String endereco, String nomeEnd, ListaSinalizacoes lista) {
        this.endereco = endereco;
        this.nomeEnd = nomeEnd;
        this.lista = lista;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNomeEnd() {
        return nomeEnd;
    }

    public ListaSinalizacoes getLista() {
        return lista;
    }

}
