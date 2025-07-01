package io.xmlss.elements

import kotlin.collections.mapOf

class SmartTagType(val name: String, val namespaceuri: String) : AbstractSelfClosingElement("o:SmartTagType") {

    override val attributes = mapOf("o:name" to name, "o:namespaceuri" to namespaceuri)

}