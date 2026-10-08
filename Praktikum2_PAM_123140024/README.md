# News Feed Simulator

Tugas Praktikum 2 Pengembangan Aplikasi Mobile - Aplikasi konsol Kotlin yang mensimulasikan pembaruan *news feed* secara langsung menggunakan **Kotlin Coroutines** dan **Kotlin Flow**.

**Informasi Mahasiswa:**
* **Nama:** Rifka Priseilla Br Silitonga
* **NIM:** 123140024
* **Program Studi:** Teknik Informatika
* **Institut:** Institut Teknologi Sumatera (ITERA)

## Fitur dan Syarat Terpenuhi
1. **Implementasi Flow**: Menggunakan `flow { }` builder untuk memancarkan berita secara *infinite loop* setiap 2 detik.
2. **Penggunaan Operator**: Memanfaatkan `.filter{}` untuk memilah kategori berita, `.map{}` untuk mengubah format data objek ke bentuk tampilan, dan `.onEach{}` untuk *side effect*.
3. **StateFlow**: Digunakan dalam kelas `NewsManager` untuk mencatat dan mempertahankan *state* jumlah berita yang telah sukses diproses dan dibaca.
4. **Coroutines**: Menggunakan `async(Dispatchers.IO)` dan `.await()` untuk mengambil detail berita secara *asynchronous* tanpa memblokir sistem.
5. **Bonus (+10%)**: Menambahkan simulasi koneksi terputus secara acak (peluang 10%) dan menggunakan operator `.catch{}` untuk *error handling*.

## Cara Menjalankan Aplikasi
1. Buka proyek ini menggunakan **Android Studio** atau **IntelliJ IDEA**.
2. Pastikan Anda memiliki koneksi internet agar *Gradle* dapat mengunduh pustaka `kotlinx-coroutines-core`.
3. Buka file pada direktori berikut: `tugaskonsol/src/main/java/com/rifka/tugaskonsol/Main.kt`.
4. Klik tombol **Run** (ikon Play berwarna hijau) yang berada di sebelah kiri fungsi `fun main() = runBlocking { ... }`.
5. Proses terminal akan berjalan dan memunculkan *output streaming* data berita di jendela *Run* bagian bawah. Anda dapat menghentikan program kapan saja dengan mengklik tombol kotak merah (Stop).