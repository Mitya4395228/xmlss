package io.xmlss.elements

class Names(val namedRange: List<NamedRange>) : AbstractElement("ss:Names") {

    override fun children() = namedRange

}

class NamedRange(val name: String, val refersTo: String, val hidden: Boolean = false) :
    AbstractSelfClosingElement("ss:NamedRange") {

    override val attributes =
        mapOf("ss:Name" to name, "ss:RefersTo" to refersTo, "ss:Hidden" to if (hidden) 1 else null)

}