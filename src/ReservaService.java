import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;
import java.util.stream.Collectors;

public class ReservaService {

    private final List<Reserva> reservas;
    private static final int CAPACIDADE_MAXIMA = 10;

    public ReservaService() {
        this.reservas = new ArrayList<>(CAPACIDADE_MAXIMA);
    }

    public void adicionarReserva(Reserva reserva) throws HotelLotadoException {
        if (this.reservas.size() >= CAPACIDADE_MAXIMA) {
            throw new HotelLotadoException("Erro: Hotel lotado! Capacidade máxima de " + CAPACIDADE_MAXIMA + " reservas atingida.");
        }
        this.reservas.add(reserva);
    }

    public List<Reserva> getTodasReservas() {
        return Collections.unmodifiableList(this.reservas);
    }

    public List<Reserva> buscarPorNome(String termoBusca) {
        String termoLower = termoBusca.toLowerCase();
        return this.reservas.stream()
                .filter(r -> r.getNomeHospede().toLowerCase().contains(termoLower))
                .collect(Collectors.toList());
    }

    public void ordenarPorDiasDecrescente() {
        if (this.reservas.size() < 2) {
            return;
        }
        Comparator<Reserva> comparador = Comparator
                .comparingInt(Reserva::getNumeroDias)
                .reversed();
        Collections.sort(this.reservas, comparador);
    }

    public int getTotalReservas() {
        return this.reservas.size();
    }
}