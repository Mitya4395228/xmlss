package io.xmlss.elements

import kotlin.collections.listOf

class AutoFilter(val range: String, val autoFilterColumn: AutoFilterColumn? = null) :
    AbstractElement("x:AutoFilter") {

    override val attributes = mapOf("x:Range" to range)

    override fun children() = listOf(autoFilterColumn)

}

class AutoFilterColumn(
    val autoFilterAnd: AutoFilterAnd? = null,
    val autoFilterCondition: AutoFilterCondition? = null,
    val autoFilterOr: AutoFilterOr? = null,
    val hidden: Boolean = false,
    val index: UInt? = null,
    val type: AutoFilterColumnType = AutoFilterColumnType.All,
    val value: Double = 0.0
) : AbstractElement("x:AutoFilterColumn") {

    override val attributes = mapOf(
        "x:Hidden" to if (hidden) hidden else null,
        "x:Index" to index,
        "x:Type" to type.name,
        "x:Value" to if (value == 0.0) null else value
    )

    override fun children() = listOf(autoFilterCondition, autoFilterAnd, autoFilterOr)

}

enum class AutoFilterColumnType {
    All, Blanks, NonBlanks, Top, TopPercent, Bottom, BottomPercent, Custom
}

class AutoFilterAnd(val autoFilterCondition: AutoFilterCondition) : AbstractElement("x:AutoFilterAnd") {

    override fun children() = listOf(autoFilterCondition)

}

class AutoFilterOr(val autoFilterCondition: AutoFilterCondition) : AbstractElement("x:AutoFilterOr") {

    override fun children() = listOf(autoFilterCondition)

}

class AutoFilterCondition(val operator: Operator, val value: String) :
    AbstractSelfClosingElement("x:AutoFilterCondition") {

    override val attributes = mapOf("x:Operator" to operator.name, "x:Value" to value)

}

enum class Operator {
    Equals, DoesNotEqual, GreaterThan, GreaterThanOrEqual, LessThan, LessThanOrEqual
}
