

# Sistem Manajemen Perpustakaan Kampus
Perpustakaan kampus memiliki berbagai koleksi buku yang perlu dikelola dengan baik, mulai dari pencatatan koleksi, pengaturan lokasi penyimpanan, hingga pengelolaan transaksi peminjaman dan pengembalian buku. Selain itu, perpustakaan perlu mengatur riwayat transaksi, statistik peminjaman, dan antrean mahasiswa agar proses pelayanan berjalan lebih terstruktur. Oleh karena itu, dibuat program Sistem Manajemen Perpustakaan Kampus menggunakan bahasa pemrograman Java untuk membantu pengelolaan berbagai aktivitas tersebut secara terorganisasi.



## Tugas Pemrograman Java
- ***Mata Kuliah:*** Pemrograman Berorientasi Objek
- ***Dosen Pengampu:*** M. Ibadurrahman Arrasyid Supriyanto M.Kom.
- ***Program Studi:*** Sistem Informasi
- ***Angkatan:*** 2025
- ***Kelompok:*** 7
  
## Anggota Kelompok 7
1. **Muhammad Arzad** - 2509116014 - Kelas A
2. **Fatimatuzzahrah** - 2509116020 - Kelas A
3. **Aulia Aisyah Al Humaira** - 2509116029 - Kelas A
4. **Muhammad Ilham Zaini** - 2509116091 - Kelas C

## Struktur File Proyek

## Fitur Utama

- **Kategori Buku:** Menampilkan kategori buku menggunakan Array 1D.
- **Statistik Peminjaman:** Mengelola jumlah peminjaman berdasarkan kategori dan hari menggunakan Array 2D.
- **Lokasi Penyimpanan:** Mengatur lokasi buku berdasarkan lantai, rak, dan posisi menggunakan Array 3D.
- **Koleksi Buku:** Menambah, mencari, menampilkan, dan menghapus buku menggunakan ArrayList.
- **Riwayat Transaksi:** Menyimpan dan mengelola transaksi menggunakan Stack (LIFO).
- **Antrean Peminjaman:** Mengelola antrean mahasiswa menggunakan Queue (FIFO).

## Teknologi yang Digunakan

- **Bahasa:** Java
- **Input:** `Scanner`
- **Arsitektur:** Model-View-Controller (MVC)
- **Struktur Data:** Array 1D, Array 2D, Array 3D, ArrayList, Stack, dan Queue
- **JDK:** Java Development Kit
- **IDE:** NetBeans, IntelliJ IDEA, atau Eclipse

## Struktur Folder

```text
src/
├── main/
│   └── SistemManajemenPerpustakaan.java
├── controller/
│   └── PerpustakaanController.java
├── view/
│   └── PerpustakaanView.java
├── model/
│   ├── Buku.java
│   ├── Mahasiswa.java
│   ├── Transaksi.java
│   ├── TransaksiPeminjaman.java
│   └── TransaksiPengembalian.java
└── strukturdata/
    ├── KategoriBuku.java
    ├── StatistikPeminjaman.java
    ├── LokasiBuku.java
    ├── KoleksiBuku.java
    ├── RiwayatTransaksi.java
    └── AntreanPeminjaman.java
```

## Penjelasan Struktur Data

| No. | Struktur Data | Implementasi | Kegunaan |
|---|---|---|---|
| 1 | Array 1D | `KategoriBuku.java` | Menyimpan kategori buku. |
| 2 | Array 2D | `StatistikPeminjaman.java` | Menyimpan statistik peminjaman berdasarkan kategori dan hari. |
| 3 | Array 3D | `LokasiBuku.java` | Menyimpan lokasi buku berdasarkan lantai, rak, dan posisi. |
| 4 | ArrayList | `KoleksiBuku.java` | Mengelola daftar buku secara dinamis. |
| 5 | Stack | `RiwayatTransaksi.java` | Menyimpan riwayat transaksi dengan prinsip Last In, First Out (LIFO). |
| 6 | Queue | `AntreanPeminjaman.java` | Mengelola antrean mahasiswa dengan prinsip First In, First Out (FIFO). |

## Penjelasan Package

### 1. `main`

Berisi class `SistemManajemenPerpustakaan.java` sebagai titik awal program. Class ini membuat objek View dan Controller, kemudian memanggil method `jalankan()`.

### 2. `controller`

Berisi class `PerpustakaanController.java` yang mengatur alur program, membaca dan memvalidasi input, menjalankan fitur, serta menghubungkan struktur data dengan View.

### 3. `view`

Berisi class `PerpustakaanView.java` yang bertugas menampilkan menu, submenu, pesan, daftar buku, statistik peminjaman, dan informasi lokasi rak.

### 4. `model`

Berisi class yang merepresentasikan objek dalam sistem perpustakaan.

| File | Keterangan |
|---|---|
| `Buku.java` | Menyimpan data buku dan status peminjaman. |
| `Mahasiswa.java` | Menyimpan nama dan NIM mahasiswa. |
| `Transaksi.java` | Class abstract induk untuk transaksi. |
| `TransaksiPeminjaman.java` | Merepresentasikan transaksi peminjaman buku. |
| `TransaksiPengembalian` | Merepresentasikan transaksi pengembalian buku. |

### 5. strukturdata

Berisi implementasi enam struktur data beserta operasi pengelolaannya, mulai dari kategori buku hingga antrean peminjaman.

#### Alur Kerja Program

1. Program dimulai melalui class SistemManajemenPerpustakaan.

2. Class main membuat objek View dan Controller.

3. Controller menampilkan menu melalui View dan menerima input pengguna.

4. Controller menjalankan operasi yang sesuai menggunakan struktur data dan objek dalam package model.

5. Hasil pemrosesan ditampilkan melalui View.

6. Program terus berjalan hingga pengguna memilih menu keluar.

