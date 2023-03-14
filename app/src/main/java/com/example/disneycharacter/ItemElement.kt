package com.example.disneycharacter

class ItemElement {
    private var name:String = ""
    private var icon:Int

    constructor(name: String, icon: Int){
        this.name = name
        this.icon = icon
    }

    public fun getName(): String{
        return this.name
    }

    public fun getIcon(): Int{
        return this.icon
    }

    public fun setName(name: String){
        this.name = name
    }

    public fun setIcon(icon: Int){
        this.icon = icon
    }

}