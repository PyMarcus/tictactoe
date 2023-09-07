package com.example.tictactoe.server

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.Exception
import java.net.Socket
import com.example.tictactoe.map.Map
import com.example.tictactoe.messages.Messages
import kotlin.system.exitProcess

/*
* Executa sobre a nova thread criada.
* Sumariamente, le a mensagem do cliente, que foi escrita no socket, e
* separa a linha da coluna, pelo metodo split
* e, desse modo, armazena na matriz, nas posicoes
* correspondentes.
*
* Alem disso, existe uma funcao de verificacao que
* inibe de digitar numeros fora do intervalo
* */
class ClientHandler (private val socket: Socket) : Runnable{

    private var firstTime: Int = 0

    override fun run(){
        try {
            val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
            val writer = PrintWriter(socket.getOutputStream(), true)
            Messages.WELCOME.send()
            println("You're playing against the Player2 now!")

            while (true) {
                try{
                    if(this.firstTime == 0){
                        println("Starting Map")
                        Map.initialMap()
                        this.firstTime ++
                    }
                    val clientMessage = reader.readLine() ?: break
                    val requestMap = clientMessage.split("-")

                    var r = Map.mapItClient(requestMap[0].toInt(), requestMap[1].toInt())
                    if(r != ""){
                        socket.close()
                        exitProcess(0)
                    }

                    print("Line: ")

                    var line = readln().trim().toInt()
                    checkInput(line)
                    if(!checkInput(line))continue

                    print("Column: ")
                    var column = readln().trim().toInt()
                    if(!checkInput(column))continue

                    writer.println("$line+$column")

                    Map.mapItServer(line, column)
                }catch (e: Exception){
                    continue
                }
            }

            println("Player2 (${socket.inetAddress.hostAddress}) was disconnected.")
            socket.close()

        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun checkInput(data: Int): Boolean{
        if(data > 2  || data < 0){
            return false
        }
        return true
    }
}