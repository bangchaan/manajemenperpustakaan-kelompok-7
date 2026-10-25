package model;

public abstract class Transaksi {
    private Buku buku;
    private Mahasiswa mahasiswa;

    public Transaksi(Buku buku, Mahasiswa mahasiswa) {
        this.buku = buku;
        this.mahasiswa = mahasiswa;
    }

    public Buku getBuku() {
        return buku;
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    // Indeks hari peminjaman (0 = Senin ... 6 = Minggu). -1 berarti transaksi ini bukan peminjaman.
    public int getIndeksHari() {
        return -1;
    }

    // Teks ringkas transaksi untuk ditampilkan di riwayat
    public abstract String deskripsi();

    // Mengembalikan status buku ke kondisi sebelum transaksi, lalu mengembalikan pesan hasilnya
    public abstract String batalkan();
}
