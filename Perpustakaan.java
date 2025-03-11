import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Kelas Buku untuk menyimpan informasi tentang buku
class Buku {
    private final String judul;
    private final String penulis;
    private final int tahunTerbit;

    public Buku(String judul, String penulis, int tahunTerbit) {
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
    }

    public String getJudul() {
        return judul;
    }

    public void tampilkanInfo() {
        System.out.println("--------------------------------");
        System.out.println("Judul       : " + judul);
        System.out.println("Penulis     : " + penulis);
        System.out.println("Tahun Terbit: " + tahunTerbit);
        System.out.println("--------------------------------");
    }
}

// Kelas utama untuk menjalankan sistem manajemen perpustakaan
public class Perpustakaan {
    private final ArrayList<Buku> daftarBuku;
    private final Scanner scanner;

    public Perpustakaan() {
        daftarBuku = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void jalankan() {
        int pilihan = 0;
        do {
            try {
                System.out.println("\n=== SISTEM MANAJEMEN PERPUSTAKAAN ===");
                System.out.println("1. Tambah Buku");
                System.out.println("2. Tampilkan Semua Buku");
                System.out.println("3. Cari Buku");
                System.out.println("4. Keluar");
                System.out.print("Masukkan pilihan: ");
                
                if (scanner.hasNextInt()) {
                    pilihan = scanner.nextInt();
                    scanner.nextLine(); // Konsumsi newline
                    System.out.println("\n[DEBUG] Pilihan yang dipilih: " + pilihan);
                } else {
                    System.out.println("Input harus berupa angka! Coba lagi.\n");
                    scanner.nextLine(); // Konsumsi input yang salah
                    continue;
                }

                switch (pilihan) {
                    case 1 -> tambahBuku();
                    case 2 -> tampilkanSemuaBuku();
                    case 3 -> cariBuku();
                    case 4 -> System.out.println("Terima kasih telah menggunakan sistem ini!\n");
                    default -> System.out.println("Pilihan tidak valid, coba lagi.\n");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Terjadi kesalahan input. Silakan coba lagi.\n");
                scanner.nextLine(); // Reset scanner jika error
            }
        } while (pilihan != 4);
    }

    private void tambahBuku() {
        System.out.print("Masukkan judul buku: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan nama penulis: ");
        String penulis = scanner.nextLine();

        int tahunTerbit = 0;
        boolean inputValid = false;
        while (!inputValid) {
            try {
                System.out.print("Masukkan tahun terbit: ");
                tahunTerbit = scanner.nextInt();
                scanner.nextLine(); // Konsumsi newline
                inputValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Tahun harus berupa angka! Coba lagi.\n");
                scanner.nextLine(); // Konsumsi input yang salah
            }
        }

        Buku bukuBaru = new Buku(judul, penulis, tahunTerbit);
        daftarBuku.add(bukuBaru);

        System.out.println("\nBuku berhasil ditambahkan!");
        System.out.println("[DEBUG] Buku berhasil ditambahkan!");
        System.out.println("[DEBUG] Jumlah buku saat ini: " + daftarBuku.size() + "\n");
    }

    private void tampilkanSemuaBuku() {
        System.out.println("\n[DEBUG] Jumlah total buku: " + daftarBuku.size());

        if (daftarBuku.isEmpty()) {
            System.out.println("Tidak ada buku dalam perpustakaan.\n");
            return;
        }

        System.out.println("\n=== DAFTAR BUKU ===");
        for (Buku buku : daftarBuku) {
            buku.tampilkanInfo();
        }
    }

    private void cariBuku() {
        System.out.print("Masukkan judul buku yang ingin dicari: ");
        String judulCari = scanner.nextLine();

        System.out.println("\n[DEBUG] Mencari buku dengan judul: " + judulCari);

        boolean ditemukan = false;
        for (Buku buku : daftarBuku) {
            if (buku.getJudul().equalsIgnoreCase(judulCari)) {
                System.out.println("Buku ditemukan:");
                buku.tampilkanInfo();
                ditemukan = true;
                break;
            }
        }

        System.out.println("\n[DEBUG] Status pencarian: " + (ditemukan ? "Ditemukan" : "Tidak ditemukan"));

        if (!ditemukan) {
            System.out.println("Buku tidak ditemukan.\n");
        }
    }

    public static void main(String[] args) {
        Perpustakaan sistem = new Perpustakaan();
        sistem.jalankan();
    }
}