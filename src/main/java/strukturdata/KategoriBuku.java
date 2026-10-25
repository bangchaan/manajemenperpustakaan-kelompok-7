package strukturdata;

public class KategoriBuku {
    private String[] daftar = {"Pemrograman", "Matematika", "Jaringan Komputer", "Basis Data"};

    public int getJumlah() {
        return daftar.length;
    }

    public String getNama(int indeks) {
        if (indeks < 0 || indeks >= daftar.length) {
            return "(tidak diketahui)";
        }
        return daftar[indeks];
    }
}
