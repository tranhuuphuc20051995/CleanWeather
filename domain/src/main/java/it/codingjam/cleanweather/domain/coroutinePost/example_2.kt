package it.codingjam.cleanweather.domain.coroutinePost

import kotlinx.coroutines.*

private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    throwable.printStackTrace()
    System.exit(10)
}

private val viewModelScope = CoroutineScope(Dispatchers.IO + Job() + exceptionHandler)

fun main() {
    viewModelScope.launch {
        try {
            supervisorScope {
                val deferred1 = async(Dispatchers.IO) {
                    println("Start 1...")
                    delay(1000)
                    println("After delay 1")
                }
                val deferred2 = async(Dispatchers.IO) {
                    println("Start 2...")
                    delay(2000)
                    println(1 / 0)
                    println("After delay 2")
                }
                val deferred3 = async(Dispatchers.IO) {
                    println("Start 3...")
                    delay(3000)
                    println("After delay 3")
                }
                deferred1.await()
                deferred2.await()
                deferred3.await()
            }
        } catch (e: Exception) {
            println("Managing the error `${e.message}`...")
        }
    }

    Thread.sleep(5000)

    println("end")
}
