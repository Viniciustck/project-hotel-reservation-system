public class Aplicacao {
    public static void main(String[] args) {
        ReservaService service = new ReservaService();
        SistemaHotel ui = new SistemaHotel(service);
        ui.run();
    }
}