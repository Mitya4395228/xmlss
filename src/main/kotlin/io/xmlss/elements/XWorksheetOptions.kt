package io.xmlss.elements

class XWorksheetOptions(val pageSetup: PageSetup) : AbstractElement("x:WorksheetOptions") {

    override fun children() = listOf(pageSetup)

}

class PageSetup(
    val footer: Footer? = null,
    val header: Header? = null,
    val layout: Layout? = null,
    val pageMargins: PageMargins? = null
) : AbstractElement("x:PageSetup") {

    override fun children() = listOf(footer, header, layout, pageMargins)

}

class Footer(val margin: Double = 0.5, val data: String? = null) : AbstractSelfClosingElement("x:Footer") {

    override val attributes = mapOf("x:Margin" to margin, "x:Data" to data)

}

class Header(val margin: Double = 0.5, val data: String? = null) : AbstractSelfClosingElement("x:Footer") {

    override val attributes = mapOf("x:Margin" to margin, "x:Data" to data)

}


class Layout(
    val centerHorizontal: Boolean = false,
    val centerVertical: Boolean = false,
    val orientation: Orientation = Orientation.Portrait,
    val startPageNumber: UInt? = null
) : AbstractSelfClosingElement("x:Layout") {

    override val attributes = mapOf(
        "x:CenterHorizontal" to if (centerHorizontal) 1 else null,
        "x:CenterVertical" to if (centerHorizontal) 1 else null,
        "x:Orientation" to orientation.name,
        "x:StartPageNumber" to startPageNumber
    )

}

enum class Orientation {
    Portrait, Landscape
}

class PageMargins(val bottom: Double = 1.0, val left: Double = 0.75, val right: Double = 0.75, val top: Double = 1.0) :
    AbstractSelfClosingElement("x:PageMargins") {

    override val attributes = mapOf(
        "x:Bottom" to bottom,
        "x:Left" to left,
        "x:Right" to right,
        "x:Top" to top
    )

}