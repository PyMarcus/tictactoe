package com.example.tictactoe.messages

enum class Messages {
    WELCOME,
    YOU_WIN,
    YOU_LOSE;

    public fun send(): String{
        return when(this){
            WELCOME -> "Welcome!"
            YOU_WIN -> "You Win!!! :)"
            YOU_LOSE -> "You Lose :("
        }
    }
}