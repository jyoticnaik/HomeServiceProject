package com.example.homeservice

class orders {

    lateinit var bookingStatus: String
    lateinit var address: String
    lateinit var grandTotal: String


    constructor() {}

    constructor(bookingStatus: String, address: String, grandTotal: String) {
        this.bookingStatus = bookingStatus
        this.address = address
        this.grandTotal = grandTotal
    }
}
