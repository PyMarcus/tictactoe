package com.example.tictactoe
import com.example.tictactoe.server.*

fun main(){
    val server = Server("localhost", 9999)
    server.run()
}