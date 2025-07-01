package io.xmlss.elements

class CWorksheetOptions(val displayCustomHeaders: DisplayCustomHeaders) : AbstractElement("c:WorksheetOptions") {

    override fun children() = listOf(displayCustomHeaders)

}

class DisplayCustomHeaders : AbstractSelfClosingElement("c:DisplayCustomHeaders")