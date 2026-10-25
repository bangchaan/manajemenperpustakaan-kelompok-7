package strukturdata;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import model.Mahasiswa;

public class AntreanPeminjaman {
    private Queue<Mahasiswa> antrean = new LinkedList<>();

    // false jika NIM yang sama sudah ada di antrean
    public boolean tambah(Mahasiswa mahasiswa) {
        for (Mahasiswa m : antrean) {
            if (m.getNim().equalsIgnoreCase(mahasiswa.getNim())) {
                return false;
            }
        }
        antrean.add(mahasiswa);
        return true;
    }

    // Mengeluarkan mahasiswa terdepan dengan poll(). null jika antrean kosong.
    public Mahasiswa layani() {
        return antrean.poll();
    }

    // Hanya melihat mahasiswa terdepan dengan peek(). null jika antrean kosong.
    public Mahasiswa lihatTerdepan() {
        return antrean.peek();
    }

    // Salinan urutan antrean (depan -> belakang) untuk ditampilkan oleh View
    public ArrayList<Mahasiswa> getDaftar() {
        return new ArrayList<>(antrean);
    }

    public int getJumlah() {
        return antrean.size();
    }
}