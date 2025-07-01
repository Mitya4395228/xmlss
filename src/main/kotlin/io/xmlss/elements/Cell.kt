package io.xmlss.elements

class Cell(
    val data: Data? = null,
    val namedCell: NamedCell? = null,
    val phoneticText: PhoneticText? = null,
    val comment: Comment? = null,
    val smartTags: SmartTags? = null,
    val pasteFormula: String? = null,
    val arrayRange: String? = null,
    val formula: String? = null,
    val hRef: String? = null,
    val index: UInt? = null,
    val mergeAcross: UInt? = null,
    val mergeDown: UInt? = null,
    val styleID: StyleID? = null,
    val hRefScreenTip: String? = null
) : AbstractElement("ss:Cell") {

    constructor (data: String) : this(Data(data))

    override val attributes = mapOf(
        "c:PasteFormula" to pasteFormula,
        "ss:ArrayRange" to arrayRange,
        "ss:Formula" to formula,
        "ss:HRef" to hRef,
        "ss:Index" to index,
        "ss:MergeAcross" to mergeAcross,
        "ss:MergeDown" to mergeDown,
        "ss:StyleID" to styleID,
        "x:HRefScreenTip" to hRefScreenTip
    );

    override fun children() = listOf(namedCell, data, phoneticText, comment, smartTags)

}