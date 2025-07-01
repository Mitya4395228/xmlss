package io.xmlss.elements

abstract class AbstractSelfClosingElement(tag: String) : AbstractElement(tag) {

    override fun children(): List<AbstractElement> = listOf()

    override fun toString(): String {
        val str = StringBuilder()
        val attr = attr()
        if (attr.length > 0) str.append("<$tag $attr />") else str.append("<$tag />")
        return str.toString()
    }

}