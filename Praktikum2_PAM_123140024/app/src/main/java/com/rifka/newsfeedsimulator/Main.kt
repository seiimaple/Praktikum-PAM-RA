package com.rifka.newsfeedsimulator

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.random.Random

// --- 1. MODEL DATA ---
data class News(val id: Int, val title: String, val category: String)
data class FormattedNews(val id: Int, val displayTitle: String)

// --- 2. STATEFLOW IMPLEMENTATION ---
class NewsManager {
    // Menyimpan state jumlah berita yang berhasil dibaca
    private val _readCount = MutableStateFlow(0)
    val readCount: StateFlow<Int> = _readCount.asStateFlow()

    fun markAsRead() {
        _readCount.value++
    }
}

// --- 3. COROUTINES USAGE ---
// Pemanggilan data (network call) secara async
suspend fun fetchNewsDetail(newsId: Int): String {
    delay(1000) // Simulasi network delay 1 detik
    return "Ini adalah detail konten lengkap untuk berita dengan ID $newsId."
}

// --- 4. IMPLEMENTASI FLOW & BONUS ERROR HANDLING ---
fun newsFlow(): Flow<News> = flow {
    var id = 1
    val categories = listOf("Teknologi", "Olahraga", "Politik", "Hiburan")

    while (true) {
        delay(2000) // Berita baru setiap 2 detik
        val category = categories.random()
        val news = News(id, "Berita Terkini Ke-$id", category)

        // Simulasi error jaringan acak untuk memicu catch
        if (Random.nextInt(100) < 10) {
            throw Exception("Koneksi internet terputus secara tiba-tiba!")
        }

        emit(news)
        id++
    }
}.catch { e ->
    // Penanganan error dengan operator catch
    println("⚠️ [SYSTEM ERROR]: ${e.message}")
    println("⚠️ Menghentikan aliran berita untuk sementara...")
}

fun main() = runBlocking {
    val newsManager = NewsManager()
    val targetCategory = "Teknologi"

    println("=== Memulai News Feed Simulator ===")
    println("Mencari kategori khusus: $targetCategory\n")

    // Coroutine mandiri untuk memantau pembaruan StateFlow
    launch {
        newsManager.readCount.collect { count ->
            if (count > 0) {
                println("📈 [STATE]: Total berita kategori $targetCategory yang telah dibaca: $count")
            }
        }
    }

    // --- 5. PENGGUNAAN OPERATORS ---
    newsFlow()
        .filter { news ->
            // Operator Filter: Memilah berita berdasarkan kategori
            news.category == targetCategory
        }
        .map { news ->
            // Operator Map: Mengubah struktur data untuk tampilan
            FormattedNews(news.id, "[${news.category.uppercase()}] - ${news.title}")
        }
        .onEach { formattedNews ->
            // Operator onEach: Memberikan side effect saat data lewat
            println("🔔 Ditemukan: ${formattedNews.displayTitle} (Memuat detail...)")
        }
        .collect { formattedNews ->
            // Terminal Operator: Memicu aliran data

            // Menggunakan async dan Dispatchers.IO untuk pemrosesan paralel
            val detailDeferred = async(Dispatchers.IO) {
                fetchNewsDetail(formattedNews.id)
            }

            // Menunggu data selesai di-fetch tanpa memblokir thread
            val detail = detailDeferred.await()

            println("📖 Isi Berita: $detail")
            println("--------------------------------------------------")

            // Memperbarui StateFlow setelah berita selesai diproses
            newsManager.markAsRead()
        }
}