package io.xmlss.elements

class Row(
    val cells: List<Cell> = listOf(),
    val caption: String? = null,
    val autoFitHeight: Boolean = true,
    val height: Double? = null,
    val hidden: Boolean? = null,
    val index: UInt? = null,
    val span: UInt? = null,
    val styleID: StyleID? = null
) : AbstractElement("ss:Row") {

    constructor (cells: List<String>) : this(cells.map { Cell(it) })

    init {
        require(height == null || height >= 0.0) {
            "Height must be greater than or equal to 0"
        }
    }

    override val attributes = mapOf(
        "c:Caption" to caption,
        "ss:AutoFitHeight" to if (autoFitHeight) null else 0,
        "ss:Height" to height,
        "ss:Hidden" to hidden,
        "ss:Index" to index,
        "ss:Span" to span,
        "ss:StyleID" to styleID
    )

    override fun children() = cells

}