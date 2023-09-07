package com.example.tictactoe.map

import java.io.IOException

object Map{
    private var map = Array(3){ arrayOfNulls<String>(3)}


    private fun intro(){
        println()
        println("**THE TICTACTOE GAME **")
    }

    public fun initialMap(){
        intro()
        for(line in this.map){
            for(column in line){
                print("$column | ")
            }
            println()
            println("-------------------")
        }
    }

    public fun mapItClient(lineClient: Int,columnClient: Int){
        clearTerminal()
        for(line in (0..2)){
            for(column in (0..2)){
                if(line == lineClient && column == columnClient){
                    this.map[line][column] = " X "
                }
                print("${this.map[line][column]} | ")
            }
            println()
            println("-------------------")
        }
    }

    public fun mapItServer(lineClient: Int,columnClient: Int){
        clearTerminal()
        for(line in (0..2)){
            for(column in (0..2)){
                if(line == lineClient && column == columnClient){
                    this.map[line][column] = " O "
                }
                print("${this.map[line][column]} | ")
            }
            println()
            println("-------------------")
        }
    }

    private fun clearTerminal() {
        println()
        println()
        println()
        println()
    }
}