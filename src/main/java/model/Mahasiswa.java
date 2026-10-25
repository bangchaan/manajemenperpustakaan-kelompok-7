package model;


public class Mahasiswa {
    private String nama;
    private String nim;

    public Mahasiswa(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    // Contoh hasil: "Budi (2401001)"
    public String getInfo() {
        return nama + " (" + nim + ")";
    }
}