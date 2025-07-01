package io.xmlss.elements

import kotlin.collections.mapOf

abstract class AbstractElement(val tag: String) {

    open val attributes: Map<String, Any?> = mapOf()

    protected fun attr() =
        attributes.filterValues { it != null }.map { (k, v) -> "$k=\"$v\"" }.joinToString(separator = " ")

    abstract fun children(): List<*>

    private fun innerText() =
        children().filterNotNull().map { it.toString() }.joinToString(separator = "")

    override fun toString(): String {
        val str = StringBuilder()
        val attr = attr()
        if (attr.length > 0) str.append("<$tag $attr>") else str.append("<$tag>")
        str.append(innerText())
        str.append("</$tag>")
        return str.toString()
    }

}