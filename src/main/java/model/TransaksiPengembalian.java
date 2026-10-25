package model;


public class TransaksiPengembalian extends Transaksi {

    public TransaksiPengembalian(Buku buku, Mahasiswa mahasiswa) {
        super(buku, mahasiswa);
    }

    @Override
    public String deskripsi() {
        return "KEMBALI | Buku: " + getBuku().getJudul() + " | Mahasiswa: " + getMahasiswa().getInfo();
    }

    @Override
    public String batalkan() {
        if (getBuku().pinjam(getMahasiswa())) {
            return "Pembatalan pengembalian berhasil: buku kembali berstatus DIPINJAM oleh "
                    + getMahasiswa().getInfo() + ".";
        }
        return "Transaksi dihapus dari riwayat, tetapi status buku tidak dapat dipulihkan "
                + "(buku sedang dipinjam).";
    }
}