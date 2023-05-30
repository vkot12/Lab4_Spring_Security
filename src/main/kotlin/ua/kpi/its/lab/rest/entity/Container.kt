package ua.kpi.its.lab.rest.entity

interface Container<T> {

    fun add(element: T)

    fun remove(index: Int)

    fun update(index: Int, element: T)

    fun get(index: Int): T

    fun getAll(): List<T>
}

class CinemaContainer : Container<Magazine> {
    private val magazines: MutableList<Magazine> = mutableListOf()

    override fun add(element: Magazine) {
        magazines.add(element)
    }

    override fun remove(index: Int) {
        magazines.removeAt(index)
    }

    override fun update(index: Int, element: Magazine) {
        magazines[index] = element
    }

    override fun get(index: Int): Magazine {
        return magazines[index]
    }

    override fun getAll(): List<Magazine> {
        return magazines
    }
}