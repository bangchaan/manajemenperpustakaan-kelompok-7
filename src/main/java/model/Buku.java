package model;

public class Buku {
    private String judul;
    private int indeksKategori; // indeks pada array KategoriBuku
    private boolean dipinjam;
    private Mahasiswa peminjam;

    public Buku(String judul, int indeksKategori) {
        this.judul = judul;
        this.indeksKategori = indeksKategori;
        this.dipinjam = false;
        this.peminjam = null;
    }

    public String getJudul() {
        return judul;
    }

    public int getIndeksKategori() {
        return indeksKategori;
    }

    public boolean isDipinjam() {
        return dipinjam;
    }

    public Mahasiswa getPeminjam() {
        return peminjam;
    }

    // Mengubah status menjadi DIPINJAM. false jika buku sudah dipinjam atau mahasiswa kosong.
    public boolean pinjam(Mahasiswa mahasiswa) {
        if (dipinjam || mahasiswa == null) {
            return false;
        }
        this.dipinjam = true;
        this.peminjam = mahasiswa;
        return true;
    }

    // Mengubah status menjadi TERSEDIA. false jika buku memang tidak sedang dipinjam.
    public boolean kembalikan() {
        if (!dipinjam) {
            return false;
        }
        this.dipinjam = false;
        this.peminjam = null;
        return true;
    }

    public String getStatus() {
        if (dipinjam) {
            return "DIPINJAM oleh " + peminjam.getInfo();
        }
        return "TERSEDIA";
    }
}