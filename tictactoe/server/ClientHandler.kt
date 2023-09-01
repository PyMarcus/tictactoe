package com.example.tictactoe.server

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.Exception
import java.net.Socket

class ClientHandler (private val socket: Socket) : Runnable{

    override fun run(){
        try {
            val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
            val writer = PrintWriter(socket.getOutputStream(), true)

            writer.println("Bem-vindo ao servidor!")

            while (true) {
                val clientMessage = reader.readLine() ?: break
                println("Mensagem do cliente (${socket.inetAddress.hostAddress}): $clientMessage")
                writer.println("Você disse: $clientMessage")
            }

            println("Cliente (${socket.inetAddress.hostAddress}) desconectado.")
            socket.close()

        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}