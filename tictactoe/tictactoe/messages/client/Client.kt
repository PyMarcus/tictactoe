package com.example.tictactoe.client

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class Client {
    private var serverIp: String = ""
    private var serverPort: Int = 0

    constructor(serverIp: String, serverPort: Int){
        this.serverIp = serverIp
        this.serverPort = serverPort
    }

    public fun play(){
        val socket = Socket(this.serverIp, this.serverPort)
        var firstTime: Int = 0

        while (true){
            var input = BufferedReader(InputStreamReader(socket.getInputStream()))
            var output = PrintWriter(socket.getOutputStream(), true)
            if(firstTime.equals(0)){
                println("Playing against Player1")
                var map = com.example.tictactoe.map.Map
                println("Starting Map")
                map.initialMap()
                firstTime ++
            }

            print("Line: ")
            var line = readln().trim().toInt()
            print("Column: ")
            var column = readln().trim().toInt()

            output.println("$line-$column")

            com.example.tictactoe.map.Map.mapItClient(line, column)

            var response = input.readLine().split("+")

            com.example.tictactoe.map.Map.mapItServer(response[0].toInt(), response[1].toInt())

        }
    }
}