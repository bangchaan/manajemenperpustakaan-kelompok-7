package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.Buku;
import model.Mahasiswa;
import model.Transaksi;
import model.TransaksiPeminjaman;
import model.TransaksiPengembalian;
import strukturdata.AntreanPeminjaman;
import strukturdata.KategoriBuku;
import strukturdata.KoleksiBuku;
import strukturdata.LokasiBuku;
import strukturdata.RiwayatTransaksi;
import strukturdata.StatistikPeminjaman;
import view.PerpustakaanView;


public class PerpustakaanController {

    private PerpustakaanView view;
    private Scanner input = new Scanner(System.in);

    private KategoriBuku kategori = new KategoriBuku();
    private StatistikPeminjaman statistik = new StatistikPeminjaman(kategori.getJumlah());
    private LokasiBuku lokasi = new LokasiBuku();
    private KoleksiBuku koleksi = new KoleksiBuku();
    private RiwayatTransaksi riwayat = new RiwayatTransaksi();
    private AntreanPeminjaman antrean = new AntreanPeminjaman();

    public PerpustakaanController(PerpustakaanView view) {
        this.view = view;
    }

    // ======================================================
    // INPUT (dibaca di Controller, bukan di View)
    // ======================================================

    // Membaca teks, diulang sampai tidak kosong
    private String bacaTeks(String pesan) {
        while (true) {
            view.tampilkanPrompt(pesan);
            String teks = input.nextLine().trim();
            if (!teks.isEmpty()) {
                return teks;
            }
            view.tampilkanPesan("  [!] Input tidak boleh kosong.");
        }
    }

    // Membaca angka bulat, diulang sampai berada dalam rentang min..max
    private int bacaAngka(String pesan, int min, int max) {
        while (true) {
            view.tampilkanPrompt(pesan);
            String teks = input.nextLine().trim();
            try {
                int angka = Integer.parseInt(teks);
                if (angka >= min && angka <= max) {
                    return angka;
                }
                view.tampilkanPesan("  [!] Angka harus antara " + min + " dan " + max + ".");
            } catch (NumberFormatException e) {
                view.tampilkanPesan("  [!] Masukkan angka yang valid.");
            }
        }
    }

    // ======================================================
    // MENU UTAMA
    // ======================================================

    public void jalankan() {
        int pilihan;
        do {
            view.tampilkanMenuUtama();
            pilihan = bacaAngka("Pilih menu (1-7): ", 1, 7);

            switch (pilihan) {
                case 1:
                    tampilkanKategori();
                    break;
                case 2:
                    menuStatistik();
                    break;
                case 3:
                    menuLokasi();
                    break;
                case 4:
                    menuKoleksi();
                    break;
                case 5:
                    menuTransaksi();
                    break;
                case 6:
                    menuAntrean();
                    break;
                case 7:
                    view.tampilkanPesan("Terima kasih. Program selesai.");
                    break;
            }
        } while (pilihan != 7);
    }

    // ======================================================
    // BANTUAN: kategori (Array 1D) dan pilihan input
    // ======================================================

    private String[] ambilNamaKategori() {
        String[] hasil = new String[kategori.getJumlah()];
        for (int i = 0; i < hasil.length; i++) {
            hasil[i] = kategori.getNama(i);
        }
        return hasil;
    }

    private String[] ambilNamaHari() {
        String[] hasil = new String[statistik.getJumlahHari()];
        for (int i = 0; i < hasil.length; i++) {
            hasil[i] = statistik.getNamaHari(i);
        }
        return hasil;
    }

    private void tampilkanKategori() {
        view.tampilkanDaftar("Daftar Kategori Buku", ambilNamaKategori());
    }

    // Mengembalikan indeks kategori (0-based)
    private int pilihKategori() {
        tampilkanKategori();
        return bacaAngka("Pilih nomor kategori: ", 1, kategori.getJumlah()) - 1;
    }

    // Mengembalikan indeks hari (0-based)
    private int pilihHari() {
        view.tampilkanPilihan(ambilNamaHari());
        return bacaAngka("Pilih nomor hari: ", 1, statistik.getJumlahHari()) - 1;
    }

    private int bacaLantai() {
        return bacaAngka("Lantai (1-" + LokasiBuku.JUMLAH_LANTAI + "): ", 1, LokasiBuku.JUMLAH_LANTAI) - 1;
    }

    private int bacaRak() {
        return bacaAngka("Nomor rak (1-" + LokasiBuku.JUMLAH_RAK + "): ", 1, LokasiBuku.JUMLAH_RAK) - 1;
    }

    private int bacaPosisi() {
        return bacaAngka("Posisi (1-" + LokasiBuku.JUMLAH_POSISI + "): ", 1, LokasiBuku.JUMLAH_POSISI) - 1;
    }

    // ======================================================
    // STATISTIK (Array 2D)
    // Satu array dipakai untuk dua cara pengisian:
    //   1. otomatis : bertambah saat peminjaman berhasil, berkurang saat dibatalkan
    //   2. manual   : aturStatistik() menimpa angka sebagai KOREKSI oleh petugas
    // ======================================================

    private void menuStatistik() {
        int pilihan;
        do {
            view.tampilkanMenuStatistik();
            pilihan = bacaAngka("Pilih: ", 0, 3);

            switch (pilihan) {
                case 1:
                    aturStatistik();
                    break;
                case 2:
                    tampilkanTabelStatistik();
                    break;
                case 3:
                    tampilkanTotalStatistik();
                    break;
            }
        } while (pilihan != 0);
    }

    private void aturStatistik() {
        int indeksKategori = pilihKategori();
        int indeksHari = pilihHari();
        int jumlah = bacaAngka("Jumlah peminjaman baru (0-1000): ", 0, 1000);
        if (statistik.setJumlah(indeksKategori, indeksHari, jumlah)) {
            view.tampilkanPesan("Berhasil (koreksi manual): " + kategori.getNama(indeksKategori)
                    + " pada hari " + statistik.getNamaHari(indeksHari) + " diatur menjadi " + jumlah
                    + ". Nilai ini menimpa hitungan otomatis dari transaksi.");
        } else {
            view.tampilkanPesan("Gagal: data tidak valid.");
        }
    }

    private void tampilkanTabelStatistik() {
        int[][] nilai = new int[kategori.getJumlah()][statistik.getJumlahHari()];
        for (int i = 0; i < nilai.length; i++) {
            for (int j = 0; j < nilai[i].length; j++) {
                nilai[i][j] = statistik.getJumlah(i, j);
            }
        }
        view.tampilkanTabelStatistik(ambilNamaKategori(), ambilNamaHari(), nilai);
    }

    private void tampilkanTotalStatistik() {
        int[] total = new int[kategori.getJumlah()];
        for (int i = 0; i < total.length; i++) {
            total[i] = statistik.getTotalKategori(i);
        }
        view.tampilkanTotalStatistik(ambilNamaKategori(), total, statistik.getTotalSemua());
    }

    // ======================================================
    // LOKASI BUKU (Array 3D)
    // ======================================================

    private void menuLokasi() {
        int pilihan;
        do {
            view.tampilkanMenuLokasi();
            pilihan = bacaAngka("Pilih: ", 0, 5);

            switch (pilihan) {
                case 1:
                    tempatkanBuku();
                    break;
                case 2:
                    lihatLokasi();
                    break;
                case 3:
                    cariLokasiBuku();
                    break;
                case 4:
                    tampilkanIsiRak();
                    break;
                case 5:
                    kosongkanLokasi();
                    break;
            }
        } while (pilihan != 0);
    }

    private void tempatkanBuku() {
        String judul = bacaTeks("Judul buku yang akan ditempatkan: ");
        int lantai = bacaLantai();
        int rak = bacaRak();
        int posisi = bacaPosisi();

        Buku buku = koleksi.cariByJudul(judul);
        if (buku == null) {
            view.tampilkanPesan("Gagal: buku belum terdaftar. Tambahkan dulu di menu Kelola Koleksi Buku.");
            return;
        }
        int[] lokasiLama = lokasi.cari(buku.getJudul());
        if (lokasiLama != null) {
            view.tampilkanPesan("Gagal: buku sudah berada di Lantai " + (lokasiLama[0] + 1) + ", Rak "
                    + (lokasiLama[1] + 1) + ", Posisi " + (lokasiLama[2] + 1)
                    + ". Kosongkan lokasi tersebut terlebih dahulu jika ingin memindahkan.");
            return;
        }
        String isi = lokasi.getIsi(lantai, rak, posisi);
        if (isi != null) {
            view.tampilkanPesan("Gagal: lokasi sudah terisi oleh buku \"" + isi + "\".");
            return;
        }
        lokasi.tempatkan(lantai, rak, posisi, buku.getJudul());
        view.tampilkanPesan("Berhasil: \"" + buku.getJudul() + "\" ditempatkan di Lantai " + (lantai + 1)
                + ", Rak " + (rak + 1) + ", Posisi " + (posisi + 1) + ".");
    }

    private void lihatLokasi() {
        int lantai = bacaLantai();
        int rak = bacaRak();
        int posisi = bacaPosisi();
        String teksLokasi = "Lantai " + (lantai + 1) + ", Rak " + (rak + 1) + ", Posisi " + (posisi + 1);
        String isi = lokasi.getIsi(lantai, rak, posisi);
        if (isi == null) {
            view.tampilkanPesan("Lokasi " + teksLokasi + " masih KOSONG.");
        } else {
            view.tampilkanPesan("Lokasi " + teksLokasi + " berisi: \"" + isi + "\".");
        }
    }

    private void cariLokasiBuku() {
        String judul = bacaTeks("Judul buku yang dicari: ");
        int[] hasil = lokasi.cari(judul);
        if (hasil == null) {
            view.tampilkanPesan("Buku \"" + judul + "\" belum ditempatkan pada lokasi manapun.");
        } else {
            view.tampilkanPesan("Buku ditemukan di Lantai " + (hasil[0] + 1) + ", Rak " + (hasil[1] + 1)
                    + ", Posisi " + (hasil[2] + 1) + ".");
        }
    }

    private void tampilkanIsiRak() {
        int lantai = bacaLantai();
        int rak = bacaRak();
        String[] isiPosisi = new String[LokasiBuku.JUMLAH_POSISI];
        for (int p = 0; p < isiPosisi.length; p++) {
            isiPosisi[p] = lokasi.getIsi(lantai, rak, p); // null jika kosong
        }
        view.tampilkanIsiRak(lantai + 1, rak + 1, isiPosisi);
    }

    private void kosongkanLokasi() {
        int lantai = bacaLantai();
        int rak = bacaRak();
        int posisi = bacaPosisi();
        String isi = lokasi.getIsi(lantai, rak, posisi);
        if (lokasi.kosongkan(lantai, rak, posisi)) {
            view.tampilkanPesan("Buku \"" + isi + "\" dikeluarkan dari lokasi.");
        } else {
            view.tampilkanPesan("Lokasi tersebut sudah kosong.");
        }
    }

    // ======================================================
    // KOLEKSI BUKU (ArrayList)
    // ======================================================

    private void menuKoleksi() {
        int pilihan;
        do {
            view.tampilkanMenuKoleksi();
            pilihan = bacaAngka("Pilih: ", 0, 3);

            switch (pilihan) {
                case 1:
                    tambahBuku();
                    break;
                case 2:
                    tampilkanSemuaBuku();
                    break;
                case 3:
                    hapusBuku();
                    break;
            }
        } while (pilihan != 0);
    }

    private void tambahBuku() {
        String judul = bacaTeks("Judul buku: ");
        int indeksKategori = pilihKategori();
        if (koleksi.tambah(new Buku(judul, indeksKategori))) {
            view.tampilkanPesan("Berhasil: buku \"" + judul + "\" (" + kategori.getNama(indeksKategori)
                    + ") ditambahkan. Status: TERSEDIA.");
        } else {
            view.tampilkanPesan("Gagal: buku dengan judul tersebut sudah ada di koleksi.");
        }
    }

    private void tampilkanSemuaBuku() {
        int jumlah = koleksi.getJumlah();
        if (jumlah == 0) {
            view.tampilkanPesan("Koleksi buku masih kosong.");
            return;
        }
        String[] baris = new String[jumlah];
        for (int i = 0; i < jumlah; i++) {
            Buku buku = koleksi.getBuku(i);
            baris[i] = buku.getJudul() + " | " + kategori.getNama(buku.getIndeksKategori())
                    + " | " + buku.getStatus();
        }
        view.tampilkanDaftar("Daftar Koleksi Buku (" + jumlah + " buku)", baris);
    }

    private void hapusBuku() {
        if (koleksi.getJumlah() == 0) {
            view.tampilkanPesan("Koleksi buku kosong, tidak ada yang bisa dihapus.");
            return;
        }
        tampilkanSemuaBuku();
        int jumlah = koleksi.getJumlah();
        int nomor = bacaAngka("Nomor buku yang dihapus (1-" + jumlah + "): ", 1, jumlah);

        Buku buku = koleksi.getBuku(nomor - 1);
        if (buku.isDipinjam()) {
            view.tampilkanPesan("Gagal: buku sedang dipinjam oleh " + buku.getPeminjam().getInfo()
                    + ". Kembalikan dulu sebelum dihapus.");
            return;
        }
        koleksi.hapus(nomor - 1);
        lokasi.kosongkanByJudul(buku.getJudul()); // sinkron dengan Array 3D
        view.tampilkanPesan("Berhasil: buku \"" + buku.getJudul()
                + "\" dihapus dari koleksi (dan dari lokasi rak jika ada).");
    }

    // ======================================================
    // TRANSAKSI (Stack)
    // ======================================================

    private void menuTransaksi() {
        int pilihan;
        do {
            view.tampilkanMenuTransaksi();
            pilihan = bacaAngka("Pilih: ", 0, 5);

            switch (pilihan) {
                case 1:
                    pinjamBuku();
                    break;
                case 2:
                    kembalikanBuku();
                    break;
                case 3:
                    tampilkanTransaksiTerakhir();
                    break;
                case 4:
                    tampilkanRiwayat();
                    break;
                case 5:
                    batalkanTransaksiTerakhir();
                    break;
            }
        } while (pilihan != 0);
    }

    private void pinjamBuku() {
        if (koleksi.getJumlah() == 0) {
            view.tampilkanPesan("Koleksi buku masih kosong. Tambahkan buku terlebih dahulu.");
            return;
        }
        String nama = bacaTeks("Nama mahasiswa: ");
        String nim = bacaTeks("NIM: ");
        String judul = bacaTeks("Judul buku yang dipinjam: ");
        int hari = pilihHari();
        view.tampilkanPesan(prosesPinjam(new Mahasiswa(nama, nim), judul, hari));
    }

    // Logika inti peminjaman (dipakai juga oleh layaniAntrean). Mengembalikan pesan hasil.
    private String prosesPinjam(Mahasiswa mahasiswa, String judul, int indeksHari) {
        Buku buku = koleksi.cariByJudul(judul);
        if (buku == null) {
            return "Gagal: buku \"" + judul + "\" tidak terdaftar di koleksi.";
        }
        if (!buku.pinjam(mahasiswa)) {
            return "Gagal: buku \"" + buku.getJudul() + "\" sedang dipinjam oleh "
                    + buku.getPeminjam().getInfo() + ".";
        }
        statistik.tambah(buku.getIndeksKategori(), indeksHari);
        riwayat.catat(new TransaksiPeminjaman(buku, mahasiswa, indeksHari, statistik.getNamaHari(indeksHari)));
        return "Berhasil: \"" + buku.getJudul() + "\" dipinjam oleh " + mahasiswa.getInfo()
                + " pada hari " + statistik.getNamaHari(indeksHari) + ".";
    }

    private void kembalikanBuku() {
        if (koleksi.getJumlah() == 0) {
            view.tampilkanPesan("Koleksi buku masih kosong.");
            return;
        }
        String judul = bacaTeks("Judul buku yang dikembalikan: ");
        Buku buku = koleksi.cariByJudul(judul);
        if (buku == null) {
            view.tampilkanPesan("Gagal: buku tidak terdaftar di koleksi.");
            return;
        }
        Mahasiswa peminjamLama = buku.getPeminjam();
        if (!buku.kembalikan()) {
            view.tampilkanPesan("Gagal: buku \"" + buku.getJudul() + "\" tidak sedang dipinjam.");
            return;
        }
        riwayat.catat(new TransaksiPengembalian(buku, peminjamLama));
        view.tampilkanPesan("Berhasil: \"" + buku.getJudul() + "\" dikembalikan oleh "
                + peminjamLama.getInfo() + ". Status kini TERSEDIA.");
    }

    private void tampilkanTransaksiTerakhir() {
        Transaksi terakhir = riwayat.lihatTerakhir();
        if (terakhir == null) {
            view.tampilkanPesan("Belum ada transaksi.");
            return;
        }
        view.tampilkanPesan("Transaksi terakhir: " + terakhir.deskripsi());
    }

    private void tampilkanRiwayat() {
        int jumlah = riwayat.getJumlah();
        if (jumlah == 0) {
            view.tampilkanPesan("Belum ada transaksi.");
            return;
        }
        String[] baris = new String[jumlah];
        for (int i = 0; i < jumlah; i++) {
            baris[i] = riwayat.getTransaksi(jumlah - 1 - i).deskripsi(); // terbaru di indeks 0
        }
        view.tampilkanDaftar("Riwayat Transaksi (terbaru di atas)", baris);
    }

    private void batalkanTransaksiTerakhir() {
        Transaksi transaksi = riwayat.ambilTerakhir();
        if (transaksi == null) {
            view.tampilkanPesan("Tidak ada transaksi yang bisa dibatalkan.");
            return;
        }
        String pesan = "Membatalkan: " + transaksi.deskripsi() + "\n";

        // Hanya transaksi peminjaman yang punya hari (>= 0): statistiknya dikurangi
        int indeksHari = transaksi.getIndeksHari();
        if (indeksHari >= 0) {
            statistik.kurangi(transaksi.getBuku().getIndeksKategori(), indeksHari);
        }

        if (koleksi.mengandung(transaksi.getBuku())) {
            // POLYMORPHISM: batalkan() berbeda isinya untuk peminjaman dan pengembalian
            pesan = pesan + transaksi.batalkan();
        } else {
            pesan = pesan + "Transaksi dihapus dari riwayat, tetapi status buku tidak dapat dipulihkan "
                    + "(buku sudah tidak ada di koleksi).";
        }
        view.tampilkanPesan(pesan + "\n"
                + "Catatan: mahasiswa yang sudah dilayani dari antrean tidak dimasukkan kembali ke antrean.");
    }

    // ======================================================
    // ANTREAN (Queue)
    // ======================================================

    private void menuAntrean() {
        int pilihan;
        do {
            view.tampilkanMenuAntrean();
            pilihan = bacaAngka("Pilih: ", 0, 4);

            switch (pilihan) {
                case 1:
                    tambahAntrean();
                    break;
                case 2:
                    tampilkanAntrean();
                    break;
                case 3:
                    layaniAntrean();
                    break;
                case 4:
                    keluarkanAntreanTerdepan();
                    break;
            }
        } while (pilihan != 0);
    }

    private void tambahAntrean() {
        String nama = bacaTeks("Nama mahasiswa: ");
        String nim = bacaTeks("NIM: ");
        Mahasiswa mahasiswa = new Mahasiswa(nama, nim);
        if (antrean.tambah(mahasiswa)) {
            view.tampilkanPesan("Berhasil: " + mahasiswa.getInfo() + " masuk antrean urutan ke-"
                    + antrean.getJumlah() + ".");
        } else {
            view.tampilkanPesan("Gagal: mahasiswa dengan NIM " + nim + " sudah ada di antrean.");
        }
    }

    private void tampilkanAntrean() {
        ArrayList<Mahasiswa> daftar = antrean.getDaftar();
        if (daftar.isEmpty()) {
            view.tampilkanPesan("Antrean kosong.");
            return;
        }
        String[] baris = new String[daftar.size()];
        for (int i = 0; i < baris.length; i++) {
            baris[i] = daftar.get(i).getInfo();
        }
        view.tampilkanDaftar("Antrean Peminjaman (depan -> belakang)", baris);
    }

    // Melayani mahasiswa terdepan. Dikeluarkan dari antrean (poll) hanya jika peminjaman berhasil.
    private void layaniAntrean() {
        Mahasiswa terdepan = antrean.lihatTerdepan();
        if (terdepan == null) {
            view.tampilkanPesan("Antrean kosong, tidak ada mahasiswa yang bisa dilayani.");
            return;
        }
        view.tampilkanPesan("Mahasiswa terdepan: " + terdepan.getInfo());
        String judul = bacaTeks("Judul buku yang ingin dipinjam: ");
        int hari = pilihHari();

        Buku buku = koleksi.cariByJudul(judul);
        boolean bisaDipinjam = (buku != null && !buku.isDipinjam());
        String hasil = prosesPinjam(terdepan, judul, hari);
        if (!bisaDipinjam) {
            view.tampilkanPesan(hasil + "\n" + terdepan.getNama()
                    + " tetap berada di antrean terdepan. Coba lagi dengan buku lain atau keluarkan dari antrean.");
            return;
        }
        antrean.layani();
        view.tampilkanPesan(hasil + "\n" + terdepan.getNama()
                + " selesai dilayani. Sisa antrean: " + antrean.getJumlah());
    }

    private void keluarkanAntreanTerdepan() {
        Mahasiswa mahasiswa = antrean.layani();
        if (mahasiswa == null) {
            view.tampilkanPesan("Antrean kosong.");
            return;
        }
        view.tampilkanPesan(mahasiswa.getInfo() + " dikeluarkan dari antrean tanpa meminjam buku.");
    }
}