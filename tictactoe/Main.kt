package com.example.tictactoe

import com.example.tictactoe.server.*
import com.example.tictactoe.client.*

/*
*
* Deve-se executar pelo terminal:
*
* Linkagem com: kotlinc Main.kt server/Server.kt server/ServerSettings.kt client/Client.kt
* server/ClientHandler.kt map/Map.kt messages/Messages.kt  -include-runtime -d game.jar

* Execute: java -jar game.jar
*
* Escolha primeiro o server
* Depois, em outro terminal, escolha o client
*
* */

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