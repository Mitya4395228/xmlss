package io.xmlss.elements

class SmartTags(val smartTag: SmartTag) : AbstractElement("o:SmartTags") {

    override fun children() = listOf(smartTag)

}

class SmartTag() : AbstractSelfClosingElement("stN:SmartTag")