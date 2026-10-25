package view;


public class PerpustakaanView {

    public void tampilkanPrompt(String teks) {
        System.out.print(teks);
    }

    // ======================================================
    // MENU
    // ======================================================

    public void tampilkanMenuUtama() {
        System.out.println();
        System.out.println("=========================================");
        System.out.println("   SISTEM MANAJEMEN PERPUSTAKAAN KAMPUS  ");
        System.out.println("=========================================");
        System.out.println("1. Tampilkan kategori buku");
        System.out.println("2. Kelola statistik peminjaman");
        System.out.println("3. Kelola lokasi penyimpanan buku");
        System.out.println("4. Kelola koleksi buku");
        System.out.println("5. Kelola riwayat transaksi");
        System.out.println("6. Kelola antrean peminjaman");
        System.out.println("7. Keluar dari program");
    }

    public void tampilkanMenuStatistik() {
        tampilkanSubmenu("Kelola Statistik Peminjaman", new String[]{
            "Input/koreksi jumlah peminjaman secara manual",
            "Tampilkan tabel statistik",
            "Hitung total per kategori dan total keseluruhan"});
    }

    public void tampilkanMenuLokasi() {
        tampilkanSubmenu("Kelola Lokasi Penyimpanan Buku", new String[]{
            "Tempatkan buku pada lokasi",
            "Tampilkan isi lokasi tertentu",
            "Cari lokasi berdasarkan judul buku",
            "Tampilkan isi satu rak",
            "Kosongkan lokasi tertentu"});
    }

    public void tampilkanMenuKoleksi() {
        tampilkanSubmenu("Kelola Koleksi Buku", new String[]{
            "Tambah buku",
            "Tampilkan seluruh buku",
            "Hapus buku"});
    }

    public void tampilkanMenuTransaksi() {
        tampilkanSubmenu("Kelola Riwayat Transaksi", new String[]{
            "Pinjam buku",
            "Kembalikan buku",
            "Tampilkan transaksi terakhir",
            "Tampilkan seluruh riwayat transaksi",
            "Batalkan transaksi terakhir"});
    }

    public void tampilkanMenuAntrean() {
        tampilkanSubmenu("Kelola Antrean Peminjaman", new String[]{
            "Tambah mahasiswa ke antrean",
            "Tampilkan seluruh antrean",
            "Layani mahasiswa terdepan (proses peminjaman)",
            "Keluarkan mahasiswa terdepan tanpa meminjam"});
    }

    private void tampilkanSubmenu(String judul, String[] opsi) {
        System.out.println("\n--- " + judul + " ---");
        for (int i = 0; i < opsi.length; i++) {
            System.out.println((i + 1) + ". " + opsi[i]);
        }
        System.out.println("0. Kembali");
    }

    // ======================================================
    // OUTPUT
    // ======================================================

    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }

    // Daftar bernomor dengan judul
    public void tampilkanDaftar(String judul, String[] baris) {
        System.out.println("\n--- " + judul + " ---");
        for (int i = 0; i < baris.length; i++) {
            System.out.println((i + 1) + ". " + baris[i]);
        }
    }

    // Daftar pilihan bernomor tanpa judul (untuk memilih hari)
    public void tampilkanPilihan(String[] pilihan) {
        for (int i = 0; i < pilihan.length; i++) {
            System.out.println("  " + (i + 1) + ". " + pilihan[i]);
        }
    }

    // Tabel: baris = kategori, kolom = hari
    public void tampilkanTabelStatistik(String[] namaKategori, String[] namaHari, int[][] nilai) {
        System.out.println("\n--- Statistik Peminjaman per Minggu ---");
        System.out.printf("%-20s", "Kategori");
        for (int j = 0; j < namaHari.length; j++) {
            System.out.printf("%8s", namaHari[j]);
        }
        System.out.println();
        for (int i = 0; i < namaKategori.length; i++) {
            System.out.printf("%-20s", namaKategori[i]);
            for (int j = 0; j < namaHari.length; j++) {
                System.out.printf("%8d", nilai[i][j]);
            }
            System.out.println();
        }
    }

    public void tampilkanTotalStatistik(String[] namaKategori, int[] total, int totalSemua) {
        System.out.println("\n--- Total Peminjaman ---");
        for (int i = 0; i < namaKategori.length; i++) {
            System.out.println(namaKategori[i] + " : " + total[i]);
        }
        System.out.println("TOTAL KESELURUHAN : " + totalSemua);
    }

    // isiPosisi[p] berisi judul buku, atau null jika posisi kosong
    public void tampilkanIsiRak(int nomorLantai, int nomorRak, String[] isiPosisi) {
        System.out.println("\nIsi Lantai " + nomorLantai + ", Rak " + nomorRak + ":");
        for (int p = 0; p < isiPosisi.length; p++) {
            String isi = isiPosisi[p];
            if (isi == null) {
                isi = "(kosong)";
            }
            System.out.println("  Posisi " + (p + 1) + ": " + isi);
        }
    }
}