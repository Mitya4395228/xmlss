package io.xmlss.elements

class Comment(val data: Data, val author: String? = null, val showAlways: Boolean = false) :
    AbstractElement("ss:Comment") {

    override val attributes = mapOf("ss:Author" to author, "ss:ShowAlways" to if (showAlways) 1 else null)

    override fun children() = listOf(data)

}