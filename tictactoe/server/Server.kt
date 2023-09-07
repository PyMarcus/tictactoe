package com.example.tictactoe.server

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.lang.Exception
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.Executors
import com.example.tictactoe.map.Map
import com.example.tictactoe.messages.Messages

/*
*
* Servidor tcp que recebe conexao do cliente, em uma thread, e inicia
* o mapa e o jogo, por consequencia*
*
* */

class Server() {

    private var ipAddress: String = ""
    private var port: Int = 0
    private val threadPool = Executors.newFixedThreadPool(2)

    constructor(ipAddress: String, port: Int):this(){
        this.ipAddress = ipAddress
        this.port = port
    }

    init {
        if(this.ipAddress.isNullOrBlank()){
            this.ipAddress = ServerSettings.Connection.ip
        }
        if(this.port == null){
            this.port = ServerSettings.Connection.port
        }
    }

    public fun run(){
        createSocket()
    }

    private fun createSocket(){
        val serverSocket: ServerSocket = ServerSocket(this.port)
        println("[+]Running server on port ${this.port}")

        try{
            Messages.WELCOME.send()
            while (true){
                val clientSocket = serverSocket.accept()
                val clientHandler = ClientHandler(clientSocket)
                threadPool.execute(clientHandler)
            }

        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}