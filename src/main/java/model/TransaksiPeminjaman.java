package model;


public class TransaksiPeminjaman extends Transaksi {
    private int indeksHari;  
    private String namaHari; 

    public TransaksiPeminjaman(Buku buku, Mahasiswa mahasiswa, int indeksHari, String namaHari) {
        super(buku, mahasiswa);
        this.indeksHari = indeksHari;
        this.namaHari = namaHari;
    }

    @Override
    public int getIndeksHari() {
        return indeksHari;
    }

    @Override
    public String deskripsi() {
        return "PINJAM | Buku: " + getBuku().getJudul() + " | Mahasiswa: " + getMahasiswa().getInfo()
                + " | Hari: " + namaHari;
    }

    // Membatalkan peminjaman: buku kembali TERSEDIA
    @Override
    public String batalkan() {
        if (getBuku().kembalikan()) {
            return "Pembatalan peminjaman berhasil: buku kembali TERSEDIA dan statistik dikurangi 1.";
        }
        return "Transaksi dihapus dari riwayat, tetapi buku sudah tidak berstatus dipinjam.";
    }
}
