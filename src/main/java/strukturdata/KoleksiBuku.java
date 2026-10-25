package strukturdata;

import java.util.ArrayList;

import model.Buku;


public class KoleksiBuku {
    private ArrayList<Buku> daftar = new ArrayList<>();

    // false jika judul yang sama sudah ada (mencegah duplikat)
    public boolean tambah(Buku buku) {
        if (cariByJudul(buku.getJudul()) != null) {
            return false;
        }
        daftar.add(buku);
        return true;
    }

    public Buku cariByJudul(String judul) {
        for (int i = 0; i < daftar.size(); i++) {
            if (daftar.get(i).getJudul().equalsIgnoreCase(judul)) {
                return daftar.get(i);
            }
        }
        return null;
    }

    // null jika indeks di luar batas
    public Buku getBuku(int indeks) {
        if (indeks < 0 || indeks >= daftar.size()) {
            return null;
        }
        return daftar.get(indeks);
    }

    public boolean hapus(int indeks) {
        if (indeks < 0 || indeks >= daftar.size()) {
            return false;
        }
        daftar.remove(indeks);
        return true;
    }

    public boolean mengandung(Buku buku) {
        return daftar.contains(buku);
    }

    public int getJumlah() {
        return daftar.size();
    }
}