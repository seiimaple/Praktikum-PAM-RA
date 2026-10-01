package com.rifka.helloworldkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform