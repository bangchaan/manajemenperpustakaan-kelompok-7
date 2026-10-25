package com.mycompany.sistemmanajemenperpustakaan;

import controller.PerpustakaanController;
import view.PerpustakaanView;

public class SistemManajemenPerpustakaan {
    public static void main(String[] args) {
        PerpustakaanView view = new PerpustakaanView();
        PerpustakaanController controller = new PerpustakaanController(view);
        controller.jalankan();
    }
}