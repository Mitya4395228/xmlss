package io.xmlss.elements

import kotlin.collections.mapOf

class Styles(val styles: List<Style> = listOf()) : AbstractElement("ss:Styles") {

    override fun children() = styles

}

class Style(
    val id: StyleID,
    val alignment: Alignment? = null,
    val borders: Borders,
    val font: Font? = null,
    val interior: Interior? = null,
    val numberFormat: NumberFormat? = null,
    val protection: Protection? = null,
    val name: String? = null,
    val parent: StyleID? = null
) : AbstractElement("ss:Style") {

    override val attributes = mapOf("ss:ID" to id, "ss:Name" to name, "ss:Parent" to parent)

    override fun children() = listOf(alignment, borders, font, interior, numberFormat, protection)

}

@JvmInline
value class StyleID(private val id: String) {

    override fun toString() = id

}

class Alignment(
    val horizontal: HorizontalAlignment = HorizontalAlignment.Automatic,
    val indent: UInt = 0u,
    val readingOrder: ReadingOrderAlignment = ReadingOrderAlignment.Context,
    val rotate: Double = 0.0,
    val shrinkToFit: Boolean = false,
    val vertical: VerticalAlignment = VerticalAlignment.Automatic,
    val verticalText: Boolean = false,
    val wrapText: Boolean = false
) : AbstractSelfClosingElement("ss:Alignment") {

    override val attributes = mapOf(
        "ss:Horizontal" to if (horizontal == HorizontalAlignment.Automatic) null else horizontal.name,
        "ss:Indent" to if (indent == 0u) null else indent,
        "ss:ReadingOrder" to if (readingOrder == ReadingOrderAlignment.Context) null else readingOrder.name,
        "ss:Rotate" to if (rotate == 0.0) null else rotate,
        "ss:ShrinkToFit" to if (shrinkToFit) 1 else null,
        "ss:Vertical" to if (vertical == VerticalAlignment.Automatic) null else vertical.name,
        "ss:VerticalText" to if (verticalText) 1 else null,
        "ss:WrapText" to if (wrapText) 1 else null
    )

}

enum class HorizontalAlignment {
    Automatic, Left, Center, Right, Fill, Justify, CenterAcrossSelection, Distributed, JustifyDistributed
}

enum class ReadingOrderAlignment {
    RightToLeft, LeftToRight, Context
}

enum class VerticalAlignment {
    Automatic, Top, Bottom, Center, Justify, Distributed, JustifyDistributed
}

class Borders(val borders: List<Border> = listOf()) : AbstractElement("ss:Borders") {

    override fun children() = borders

}

class Border(
    val position: BorderPosition,
    val color: Color? = null,
    val lineStyle: BorderLineStyle = BorderLineStyle.None,
    val weight: BorderWeight = BorderWeight.HAIRLINE
) : AbstractSelfClosingElement("ss:Border") {

    override val attributes =
        mapOf(
            "ss:Position" to position.name,
            "ss:Color" to color,
            "ss:LineStyle" to if (lineStyle == BorderLineStyle.None) null else lineStyle.name,
            "ss:Weight" to if (weight == BorderWeight.HAIRLINE) null else weight
        )

}

enum class BorderPosition {
    Left, Top, Right, Bottom, DiagonalLeft, DiagonalRight
}

enum class BorderLineStyle {
    None, Continuous, Dash, Dot, DashDot, DashDotDot, SlantDashDot, Double
}

@JvmInline
value class BorderWeight(private val weight: Double) {

    companion object {
        val HAIRLINE = BorderWeight(0.0)
        val THIN = BorderWeight(1.0)
        val MEDIUM = BorderWeight(2.0)
        val THICK = BorderWeight(3.0)
    }

    override fun toString() = weight.toString()

}

class Font(
    val bold: Boolean = false,
    val color: Color? = null,
    val fontName: FontName = FontName.ARIAL,
    val italic: Boolean = false,
    val outline: Boolean = false,
    val shadow: Boolean = false,
    val size: Double = 10.0,
    val strikeThrough: Boolean = false,
    val underline: FontUnderline = FontUnderline.None,
    val verticalAlign: FontVerticalAlign = FontVerticalAlign.None,
    val charSet: UInt = 0u,
    val family: FontFamily = FontFamily.Automatic
) : AbstractSelfClosingElement("ss:Font") {

    init {
        require(size > 0.0) {
            "Size must be strictly greater than 0"
        }
    }

    override val attributes = mapOf(
        "ss:Bold" to if (bold) 1 else null,
        "ss:Color" to color,
        "ss:FontName" to if (fontName == FontName.ARIAL) null else fontName,
        "ss:Italic" to if (italic) 1 else null,
        "ss:Outline" to if (outline) 1 else null,
        "ss:Shadow" to if (shadow) 1 else null,
        "ss:Size" to if (size == 10.0) null else size,
        "ss:StrikeThrough" to if (strikeThrough) 1 else null,
        "ss:Underline" to if (underline == FontUnderline.None) null else underline.name,
        "ss:VerticalAlign" to if (verticalAlign == FontVerticalAlign.None) null else verticalAlign.name,
        "x:CharSet" to if (charSet == 0u) null else charSet,
        "x:Family" to if (family == FontFamily.Automatic) null else family.name
    )

}

@JvmInline
value class FontName(val name: String) {

    companion object {
        val ARIAL = FontName("Arial")
    }

    override fun toString() = name

}

enum class FontUnderline {
    None, Single, Double, SingleAccounting, DoubleAccounting
}

enum class FontVerticalAlign {
    None, Subscript, Superscript
}

enum class FontFamily {
    Automatic, Decorative, Modern, Roman, Script, Swiss
}

class Interior(
    val pattern: InteriorPattern = InteriorPattern.None,
    val color: Color? = null,
    val patternColor: Color? = null
) : AbstractSelfClosingElement("ss:Interior") {

    override val attributes = mapOf(
        "ss:Color" to color,
        "ss:Pattern" to if (pattern == InteriorPattern.None) null else pattern.name,
        "ss:PatternColor" to patternColor
    )

}

enum class InteriorPattern {
    None,
    Solid,
    Gray75,
    Gray50,
    Gray25,
    Gray125,
    Gray0625,
    HorzStripe,
    VertStripe,
    ReverseDiagStripe,
    DiagStripe,
    DiagCross,
    ThickDiagCross,
    ThinHorzStripe,
    ThinVertStripe,
    ThinReverseDiagStripe,
    ThinDiagStripe,
    ThinHorzCross,
    ThinDiagCross
}

class NumberFormat(val format: NumberFormatType = NumberFormatType.GENERAL) : AbstractSelfClosingElement("ss:NumberFormat") {

    override val attributes = mapOf("ss:Format" to format.formatType)

}

enum class NumberFormatType(val formatType: String) {
    GENERAL("General"),
    GENERAL_NUMBER("General Number"),
    GENERAL_DATE("General Date"),
    LONG_DATE("Long Date"),
    MEDIUM_DATE("Medium Date"),
    SHORT_DATE("Short Date"),
    LONG_TIME("Long Time"),
    MEDIUM_TIME("Medium Time"),
    SHORT_TIME("Short Time"),
    CURRENCY("Currency"),
    EURO_CURRENCY("Euro Currency"),
    FIXED("Fixed"),
    STANDARD("Standard"),
    PERCENT("Percent"),
    SCIENTIFIC("Scientific"),
    YES_NO("Yes/No"),
    TRUE_FALSE("True/False"),
    ON_OFF("On/Off")
}

class Protection(val protected: Boolean = true, val hideFormula: Boolean = false) : AbstractSelfClosingElement("ss:Protection") {

    override val attributes = mapOf(
        "ss:Protected" to if (protected) 1 else 0,
        "x:HideFormula" to if (hideFormula) 1 else null
    )

}
