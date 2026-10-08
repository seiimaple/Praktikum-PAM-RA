package com.rifka.tugaskonsol

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.random.Random

// --- 1. BENTUK DATA ---
// Cetakan untuk nyimpen info berita
data class News(val id: Int, val title: String, val category: String)
data class FormattedNews(val id: Int, val displayTitle: String)

// --- 2. MANAJEMEN STATE (StateFlow) ---
class NewsManager {
    // Buat nyimpen jumlah berita yang udah beres dibaca (angkanya bisa dipantau terus)
    private val _readCount = MutableStateFlow(0)
    val readCount: StateFlow<Int> = _readCount.asStateFlow()

    // Fungsi buat nambahin angka jumlah bacaan
    fun markAsRead() {
        _readCount.value++
    }
}

// --- 3. PENGGUNAAN COROUTINES ---
// Fungsi ini pura-puranya lagi download isi berita dari internet
suspend fun fetchNewsDetail(newsId: Int): String {
    delay(1000) // Jeda 1 detik biar kerasa kayak lagi loading beneran
    return "Ini adalah detail konten lengkap untuk berita dengan ID $newsId."
}

// --- 4. BIKIN ALIRAN BERITA (Flow) ---
fun newsFlow(): Flow<News> = flow {
    var id = 1
    val categories = listOf("Teknologi", "Olahraga", "Politik", "Hiburan")

    while (true) {
        delay(2000) // Keluarin data berita baru setiap 2 detik
        val category = categories.random()
        val news = News(id, "Berita Terkini Ke-$id", category)

        // Bikin skenario koneksi error acak (Buat dapet poin bonus tugas)
        if (Random.nextInt(100) < 10) { // Ada 10% kemungkinan koneksi bakal putus
            throw Exception("Koneksi internet terputus secara tiba-tiba!")
        }

        emit(news) // Kirim beritanya ke dalam aliran (stream)
        id++
    }
}.catch { e ->
    // POIN BONUS: Nangkap error di atas biar aplikasinya ngga langsung mati/crash
    println("⚠️ [SYSTEM ERROR]: ${e.message}")
    println("⚠️ Menghentikan aliran berita untuk sementara...")
}

fun main() = runBlocking {
    val newsManager = NewsManager()
    val targetCategory = "Teknologi" // Kita cuma mau nyari berita kategori ini

    println("=== Memulai News Feed Simulator ===")
    println("Mencari kategori khusus: $targetCategory\n")

    // Buka jalur kerja khusus (coroutine) cuma buat mantau angka jumlah bacaan
    launch {
        newsManager.readCount.collect { count ->
            if (count > 0) {
                println("📈 [STATE]: Total berita kategori $targetCategory yang telah dibaca: $count")
            }
        }
    }

    // --- 5. MENGOLAH DATA ALIRAN (Operators) ---
    newsFlow()
        .filter { news ->
            // Saring beritanya, buang yang kategorinya ngga sesuai
            news.category == targetCategory
        }
        .map { news ->
            // Ubah bentuk datanya biar pas diprint kelihatan lebih rapi
            FormattedNews(news.id, "[${news.category.uppercase()}] - ${news.title}")
        }
        .onEach { formattedNews ->
            // Kasih notif pas beritanya lewat, tapi isi lengkapnya belum di-download
            println("🔔 Ditemukan: ${formattedNews.displayTitle} (Memuat detail...)")
        }
        .collect { formattedNews ->
            // Collect ini ibarat tombol "Play". Kalau ngga diketik, alirannya ngga bakal jalan

            // Buka proses baru di belakang layar buat download isi berita (ngga bikin UI ngelag)
            val detailDeferred = async(Dispatchers.IO) {
                fetchNewsDetail(formattedNews.id)
            }

            // Tunggu sampai proses downloadnya kelar
            val detail = detailDeferred.await()

            println("📖 Isi Berita: $detail")
            println("--------------------------------------------------")

            // Lapor ke StateFlow kalau 1 berita udah sukses diproses
            newsManager.markAsRead()
        }
}