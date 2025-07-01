package io.xmlss.elements

import kotlin.collections.mapOf
import kotlin.collections.listOf

class Worksheet(
    val name: String,
    val table: Table? = null,
    val cWorksheetOptions: CWorksheetOptions? = null,
    val names: Names? = null,
    val autoFilter: AutoFilter? = null,
    val xWorksheetOptions: XWorksheetOptions? = null,
    val protected: Boolean = false,
    val rightToLeft: Boolean = false
) : AbstractElement("ss:Worksheet") {

    init {
        require(Regex("[^/\\\\?*\\[\\]]") matches name) {
            "Sheet names must conform to the legal names of Excel sheets and, thus, cannot contain /, \\, ?, *, [, ]."
        }
    }

    override val attributes = mapOf(
        "ss:Name" to name,
        "ss:Protected" to if (protected) protected else null,
        "ss:RightToLeft" to if (rightToLeft) rightToLeft else null
    )

    override fun children() = listOf(names, table, cWorksheetOptions, xWorksheetOptions, autoFilter)

}