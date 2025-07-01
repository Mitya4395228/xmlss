package io.xmlss.elements

class NamedCell(val name: String) : AbstractSelfClosingElement("ss:NamedCell") {

    override val attributes = mapOf("ss:Name" to name)

}