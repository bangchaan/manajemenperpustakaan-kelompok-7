package strukturdata;

public class LokasiBuku {
    public static final int JUMLAH_LANTAI = 3;
    public static final int JUMLAH_RAK = 5;
    public static final int JUMLAH_POSISI = 10;

    private String[][][] data = new String[JUMLAH_LANTAI][JUMLAH_RAK][JUMLAH_POSISI];

    private boolean indeksValid(int lantai, int rak, int posisi) {
        return lantai >= 0 && lantai < JUMLAH_LANTAI
                && rak >= 0 && rak < JUMLAH_RAK
                && posisi >= 0 && posisi < JUMLAH_POSISI;
    }

    // Judul di lokasi tersebut, atau null jika kosong / indeks tidak valid
    public String getIsi(int lantai, int rak, int posisi) {
        if (!indeksValid(lantai, rak, posisi)) {
            return null;
        }
        return data[lantai][rak][posisi];
    }

    // false jika indeks tidak valid atau lokasi sudah terisi
    public boolean tempatkan(int lantai, int rak, int posisi, String judul) {
        if (!indeksValid(lantai, rak, posisi) || data[lantai][rak][posisi] != null) {
            return false;
        }
        data[lantai][rak][posisi] = judul;
        return true;
    }

    // false jika indeks tidak valid atau lokasi memang sudah kosong
    public boolean kosongkan(int lantai, int rak, int posisi) {
        if (!indeksValid(lantai, rak, posisi) || data[lantai][rak][posisi] == null) {
            return false;
        }
        data[lantai][rak][posisi] = null;
        return true;
    }

    // Mencari lokasi berdasarkan judul. Hasil {lantai, rak, posisi} atau null jika tidak ada
    public int[] cari(String judul) {
        for (int l = 0; l < JUMLAH_LANTAI; l++) {
            for (int r = 0; r < JUMLAH_RAK; r++) {
                for (int p = 0; p < JUMLAH_POSISI; p++) {
                    if (data[l][r][p] != null && data[l][r][p].equalsIgnoreCase(judul)) {
                        return new int[]{l, r, p};
                    }
                }
            }
        }
        return null;
    }

    public void kosongkanByJudul(String judul) {
        int[] lokasi = cari(judul);
        if (lokasi != null) {
            data[lokasi[0]][lokasi[1]][lokasi[2]] = null;
        }
    }
}
