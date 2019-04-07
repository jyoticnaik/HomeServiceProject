package com.example.homeservice

public class Service{
    private var ServiceName: String?
    private var ServicePrice: String?
    private var ServiceTotal: String?

    constructor(ServiceName: String?, ServicePrice: String?, ServiceTotal: String?) {
        this.ServiceName = ServiceName
        this.ServicePrice = ServicePrice
        this.ServiceTotal = ServiceTotal
    }

    fun getserviceName():String? {
        return ServiceName
    }
    fun getservicePrice():String? {
        return ServicePrice
    }
    fun getserviceTotal():String? {
        return ServiceTotal
    }

    fun setserviceName() {
        this.ServiceName = ServiceName
    }
    fun setservicePrice(){
        this.ServicePrice = ServicePrice
    }
    fun  setserviceTotal(){
        this.ServiceTotal = ServiceTotal
    }
}