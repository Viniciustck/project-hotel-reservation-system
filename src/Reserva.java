public class Reserva {

    private String nomeHospede;
    private TipoQuarto tipoQuarto;
    private int numeroDias;
    private double valorDiaria;

    public Reserva(String nomeHospede, TipoQuarto tipoQuarto, int numeroDias, double valorDiaria) {
        this.nomeHospede = nomeHospede;
        this.tipoQuarto = tipoQuarto;
        setNumeroDias(numeroDias);
        setValorDiaria(valorDiaria);
    }

    public String getNomeHospede() { return nomeHospede; }
    public void setNomeHospede(String nomeHospede) { this.nomeHospede = nomeHospede; }
    public TipoQuarto getTipoQuarto() { return tipoQuarto; }
    public void setTipoQuarto(TipoQuarto tipoQuarto) { this.tipoQuarto = tipoQuarto; }
    public int getNumeroDias() { return numeroDias; }
    public double getValorDiaria() { return valorDiaria; }

    public void setNumeroDias(int numeroDias) {
        if (numeroDias <= 0) {
            throw new IllegalArgumentException("O número de dias deve ser maior que zero.");
        }
        this.numeroDias = numeroDias;
    }

    public void setValorDiaria(double valorDiaria) {
        if (valorDiaria <= 0) {
            throw new IllegalArgumentException("O valor da diária deve ser maior que zero.");
        }
        this.valorDiaria = valorDiaria;
    }

    public double calcularValorTotal() {
        return this.numeroDias * this.valorDiaria;
    }

    @Override
    public String toString() {
        return "Reserva:" +
                "\n  Hóspede: " + nomeHospede +
                "\n  Quarto: " + tipoQuarto +
                "\n  Dias: " + numeroDias +
                "\n  Valor Diária: R$" + String.format("%.2f", valorDiaria) +
                "\n  Valor Total: R$" + String.format("%.2f", calcularValorTotal());
    }
}