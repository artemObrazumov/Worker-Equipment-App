package com.quackAboutIt.workingequipmentapp.core.data

const val BASE_URL = "https://novel-polecat-light.ngrok-free.app"

fun constructUrl(url: String): String =
    when {
        url.contains(BASE_URL) -> url
        url.startsWith("/") -> "${BASE_URL}${url.drop(1)}"
        else -> "${BASE_URL}/$url"
    }