package com.example.tictactoe.client

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

/*
* Executa um loop em socket cliente tcp.
* Sumariamente, envia a mensagem para o servidor, que foi escrita no socket, e
* separa a linha da coluna, pelo metodo split
* e, desse modo, armazena na matriz, nas posicoes
* correspondentes.
*
* Alem disso, existe uma funcao de verificacao que
* inibe de digitar numeros fora do intervalo
* */

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
            try{
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
                if(!checkInputLine(line)) continue

                print("Column: ")
                var column = readln().trim().toInt()
                if(!checkInputLine(column)) continue

                output.println("$line-$column")

                var r = com.example.tictactoe.map.Map.mapItClient(line, column)
                if(r != ""){
                    break
                }

                var response = input.readLine().split("+")

                com.example.tictactoe.map.Map.mapItServer(response[0].toInt(), response[1].toInt())

            }catch (e: Exception){
                continue
            }
        }
    }

    private fun checkInputLine(data: Int): Boolean{
        if(data > 2  || data < 0){
            return false
        }
        return true
    }

    private fun checkInputColumn(data: Int): Boolean{
        if(data > 2  || data < 0){
            return false
        }
        return true
    }
}