package com.example.homeservice

import com.example.homeservice.Service

class BookService {
    lateinit var bookingStatus: String
    lateinit var grandTotal: String
    lateinit var address: String
    lateinit var services: List<Service>//List of ordered services

    constructor() {}

    constructor(
        bookingStatus: String,
        grandTotal: String,
        address: String,
        services: List<Service>
    ) {
        this.bookingStatus = bookingStatus
        this.grandTotal = grandTotal
        this.address = address
        this.services = services
    }
}
