package strukturdata;

import java.util.Stack;

import model.Transaksi;

public class RiwayatTransaksi {
    private Stack<Transaksi> tumpukan = new Stack<>();

    public void catat(Transaksi transaksi) {
        tumpukan.push(transaksi);
    }

    // Mengambil dan menghapus transaksi teratas. null jika Stack kosong.
    public Transaksi ambilTerakhir() {
        if (tumpukan.isEmpty()) {
            return null;
        }
        return tumpukan.pop();
    }

    // Hanya melihat transaksi teratas. null jika Stack kosong.
    public Transaksi lihatTerakhir() {
        if (tumpukan.isEmpty()) {
            return null;
        }
        return tumpukan.peek();
    }

    // Indeks 0 = transaksi paling lama, getJumlah()-1 = paling baru
    public Transaksi getTransaksi(int indeks) {
        if (indeks < 0 || indeks >= tumpukan.size()) {
            return null;
        }
        return tumpukan.get(indeks);
    }

    public int getJumlah() {
        return tumpukan.size();
    }
}