import java.time.LocalDate;


public class Sinalizacao {
    private String descricao;
    private int anoExtracao;
    private int mesExtracao;
    private int diaExtracao;
    private int horaExtracao;
    private int minExtracao;
    private double numInicial;
    private double numFinal;
    private String lado;
    private String localInstalacao;
    private int dia;
    private int mes;
    private int ano;
    private LocalDate dataImp;

    public Sinalizacao(String descricao, int anoExtracao, int mesExtracao, int diaExtracao,
                       int horaExtracao, int minExtracao, double numInicial, double numFinal,
                       String lado, String localInstalacao, int diaImplantacao, int mesImplantacao, int anoImplantacao) {
        this.descricao = descricao;
        this.anoExtracao = anoExtracao;
        this.mesExtracao = mesExtracao;
        this.diaExtracao = diaExtracao;
        this.horaExtracao = horaExtracao;
        this.minExtracao = minExtracao;
        this.numInicial = numInicial;
        this.numFinal = numFinal;
        this.lado = lado;
        this.localInstalacao = localInstalacao;
        this.dia = diaImplantacao;
        this.mes = mesImplantacao;
        this.ano = anoImplantacao;
        this.dataImp = LocalDate.of(anoImplantacao, mesImplantacao, diaImplantacao);
    }

    public LocalDate getDataImplantacao() {
        return dataImp;
    }

    public int getAnoExtracao() {
        return anoExtracao;
    }

    public int getMesExtracao() {
        return mesExtracao;
    }

    public int getDiaExtracao() {
        return diaExtracao;
    }

    public int getHoraExtracao() {
        return horaExtracao;
    }

    public int getMinExtracao() {
        return minExtracao;
    }

    public double getNumInicial() {
        return numInicial;
    }

    public double getNumFinal() {
        return numFinal;
    }

    public String getLocalInstalacao() {
        return localInstalacao;
    }

    public int getDiaImplantacao() {
        return dia;
    }

    public int getMesImplantacao() {
        return mes;
    }

    public int getAnoImplantacao() {
        return ano;
    }

    @Override
    public String toString() {
        return "Sinalizacao [descricao=" + descricao + ", anoExtracao=" + anoExtracao + ", mesExtracao="
                + mesExtracao + ", diaExtracao=" + diaExtracao + ", horaExtracao=" + horaExtracao
                + ", minExtracao=" + minExtracao + ", numInicial=" + numInicial + ", numFinal=" + numFinal
                + ", lado=" + lado + ", localInstalacao=" + localInstalacao + ", diaImplantacao=" + dia
                + ", mesImplantacao=" + mes + ", anoImplantacao=" + ano + "]";
    }
}
