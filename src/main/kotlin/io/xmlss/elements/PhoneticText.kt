package io.xmlss.elements

class PhoneticText(val hint: String, val visible: Boolean = false) : AbstractElement("x:PhoneticText") {

    override val attributes = mapOf("x:Visible" to if (visible) 1 else null)

    override fun children() = listOf(hint)

}