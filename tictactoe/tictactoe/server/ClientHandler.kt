package com.example.tictactoe.server

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.Exception
import java.net.Socket
import com.example.tictactoe.map.Map


class ClientHandler (private val socket: Socket) : Runnable{

    private var firstTime: Int = 0

    override fun run(){
        try {
            val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
            val writer = PrintWriter(socket.getOutputStream(), true)

            println("You're playing against the Player2 now!")

            while (true) {
                if(this.firstTime == 0){
                    println("Starting Map")
                    Map.initialMap()
                    this.firstTime ++
                }
                val clientMessage = reader.readLine() ?: break
                val requestMap = clientMessage.split("-")

                Map.mapItClient(requestMap[0].toInt(), requestMap[1].toInt())

                print("Line: ")
                var line = readln().trim().toInt()
                print("Column: ")
                var column = readln().trim().toInt()

                writer.println("$line+$column")

                Map.mapItServer(line, column)

            }

            println("Player2 (${socket.inetAddress.hostAddress}) was disconnected.")
            socket.close()

        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}