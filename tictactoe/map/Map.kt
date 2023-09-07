package com.example.tictactoe.map

import kotlin.system.exitProcess

/*
* Objeto do tipo Mapa
* Este objeto desenha o mapa que
* sera enviado para ambos os jogadores
* sendo atualizado constantemente
* Alem disso, nesse objeto, e feito
* a checagem no jogo, para averiguar
* quem ganhou,perdeu ou se houve empate
*
* */

object Map{
    private var map = Array(3){ arrayOfNulls<String>(3)}
    private var draw: Int = 0


    private fun intro(){
        println()
        println("**THE TICTACTOE GAME **")
    }

    // atualiza o mapa para o serv e client, quando no inicio do game
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

    // atualiza o mapa para o client
    public fun mapItClient(lineClient: Int,columnClient: Int): String{
        clearTerminal()
        for(line in (0..2)){
            for(column in (0..2)){
                if(line == lineClient && column == columnClient && (this.map[line][column] != " O "  &&
                            this.map[line][column] != " X "  )){
                    this.map[line][column] = " X "
                    this.draw ++
                }
                print("${this.map[line][column]} | ")
            }
            println()
            println("-------------------")
        }

        if(checkGameMapStatus() == " X "){
            println("Player2 win!!!")
            exitProcess(0)
        }else if(checkGameMapStatus() == " O "){
            println("Player1 win!!!")
            exitProcess(0)
        }
        else if(draw >= 9){
            println("Empate!!!")
            exitProcess(0)
        }
        return ""
    }

    // atualiza o mapa para o serv
    public fun mapItServer(lineClient: Int,columnClient: Int): String{
        clearTerminal()
        for(line in (0..2)){
            for(column in (0..2)){
                if(line == lineClient && column == columnClient && (!this.map[line][column].equals(" O " ) &&
                            !this.map[line][column].equals(" X " ))){
                    this.map[line][column] = " O "
                    this.draw ++
                }
                print("${this.map[line][column]} | ")
            }
            println()
            println("-------------------")
        }
        if(checkGameMapStatus() == " X "){
            println("Player2 win!!!")
            exitProcess(0)
        }else if(checkGameMapStatus() == " O "){
            println("Player1 win!!!")
            exitProcess(0)
        }else if(draw >= 9){
            println("Empate!!!")
            exitProcess(0)
        }
        return ""
    }

    // verifica o status da partida, ou seja, se houve ou nao ganhador, percorrendo a matriz
    private fun checkGameMapStatus(): String{
        for (line in 0..2) {
            if (map[line][0] != null && map[line][0] == map[line][1] && map[line][1] == map[line][2]) {
                return map[line][0]!!
            }
        }
        for (column in 0..2) {
            if (map[0][column] != null && map[0][column] == map[1][column] && map[1][column] == map[2][column]) {
                return map[0][column]!!
            }
        }
        if (map[0][0] != null && map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
            return map[0][0]!!
        }
        if (map[0][2] != null && map[0][2] == map[1][1] && map[1][1] == map[2][0]) {
            return map[0][2]!!
        }
        return ""
    }

    private fun clearTerminal() {
        println()
        println()
        println()
        println()
    }
}