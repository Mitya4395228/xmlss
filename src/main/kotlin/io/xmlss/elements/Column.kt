package io.xmlss.elements

class Column(
    val caption: String? = null,
    val autoFitWidth: Boolean = true,
    val hidden: Boolean? = null,
    val index: UInt? = null,
    val span: UInt? = null,
    val styleID: StyleID? = null,
    val width: Double? = null
) : AbstractSelfClosingElement("ss:Column") {

    init {
        require(width == null || width >= 0.0) {
            "Width must be greater than or equal to 0"
        }
    }

    override val attributes = mapOf(
        "c:Caption" to caption,
        "ss:AutoFitWidth" to if (autoFitWidth) null else 0,
        "ss:Hidden" to hidden,
        "ss:Index" to index,
        "ss:Span" to span,
        "ss:StyleID" to styleID,
        "ss:Width" to width
    )

}