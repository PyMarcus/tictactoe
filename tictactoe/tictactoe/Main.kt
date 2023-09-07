package com.example.tictactoe
import com.example.tictactoe.server.*
import com.example.tictactoe.client.*

fun main(){
    println("server or client?")

    when (readln()) {
        "server" -> {
            val server = Server("localhost", 9998)
            server.run()
        }
        "client" -> {
            val client = Client("localhost", 9998)
            client.play()
        }
        else -> {
            println("Invalid option!")
        }
    }
}