package io.xmlss.elements

class Workbook(
    val worksheets: List<Worksheet>,
    val componentOptions: ComponentOptions? = null,
    val smartTagType: SmartTagType? = null,
    val names: Names? = null,
    val styles: Styles? = null
) : AbstractElement("ss:Workbook") {

    init {
        require(worksheets.isNotEmpty()) {
            "Worksheet is required element"
        }
    }

    //https://en.wikipedia.org/wiki/Microsoft_Office_XML_formats#Excel_XML_spreadsheet_example
    //https://learn.microsoft.com/en-us/previous-versions/office/developer/office-xp/aa140066(v=office.10)
    //https://github.com/redding/xmlss/blob/master/examples/styles.xml

    override fun children() =
        listOf(listOf(styles, names), worksheets, listOf(componentOptions, smartTagType)).flatten()

}