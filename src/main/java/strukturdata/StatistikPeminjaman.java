package strukturdata;

public class StatistikPeminjaman {
    private String[] hari = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"};
    private int[][] data;

    public StatistikPeminjaman(int jumlahKategori) {
        data = new int[jumlahKategori][hari.length];
    }

    public int getJumlahHari() {
        return hari.length;
    }

    public String getNamaHari(int indeks) {
        if (indeks < 0 || indeks >= hari.length) {
            return "(tidak diketahui)";
        }
        return hari[indeks];
    }

    private boolean indeksValid(int indeksKategori, int indeksHari) {
        return indeksKategori >= 0 && indeksKategori < data.length
                && indeksHari >= 0 && indeksHari < hari.length;
    }

    public int getJumlah(int indeksKategori, int indeksHari) {
        if (!indeksValid(indeksKategori, indeksHari)) {
            return 0;
        }
        return data[indeksKategori][indeksHari];
    }

    // Menimpa jumlah peminjaman. false jika indeks/jumlah tidak valid.
    public boolean setJumlah(int indeksKategori, int indeksHari, int jumlah) {
        if (!indeksValid(indeksKategori, indeksHari) || jumlah < 0) {
            return false;
        }
        data[indeksKategori][indeksHari] = jumlah;
        return true;
    }

    public void tambah(int indeksKategori, int indeksHari) {
        if (indeksValid(indeksKategori, indeksHari)) {
            data[indeksKategori][indeksHari]++;
        }
    }

    // Tidak akan membuat angka menjadi negatif
    public void kurangi(int indeksKategori, int indeksHari) {
        if (indeksValid(indeksKategori, indeksHari) && data[indeksKategori][indeksHari] > 0) {
            data[indeksKategori][indeksHari]--;
        }
    }

    public int getTotalKategori(int indeksKategori) {
        int total = 0;
        if (indeksKategori < 0 || indeksKategori >= data.length) {
            return 0;
        }
        for (int j = 0; j < hari.length; j++) {
            total = total + data[indeksKategori][j];
        }
        return total;
    }

    public int getTotalSemua() {
        int total = 0;
        for (int i = 0; i < data.length; i++) {
            total = total + getTotalKategori(i);
        }
        return total;
    }
}