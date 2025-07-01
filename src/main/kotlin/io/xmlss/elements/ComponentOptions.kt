package io.xmlss.elements

import kotlin.collections.mapOf

class ComponentOptions(val toolbar: Toolbar) : AbstractElement("c:ComponentOptions") {

    override fun children() = listOf(toolbar)

}

class Toolbar(val hideOfficeLogo: HideOfficeLogo, val hidden: Boolean = false) : AbstractElement("c:Toolbar") {

    override val attributes = mapOf("ss:Hidden" to if (hidden) hidden else null)

    override fun children() = listOf(hideOfficeLogo)

}

class HideOfficeLogo : AbstractSelfClosingElement("c:HideOfficeLogo")