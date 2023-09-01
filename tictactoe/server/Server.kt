package com.example.tictactoe.server

class Server() {
    /*
    * TCP class that receive 2 client connections.
    * */
    private var ipAddress: String? = ""
    private var port: Int? = 0

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

    // run: Execute the server on ip and port, by default localhost:9999
    public fun run(){
        
    }
}