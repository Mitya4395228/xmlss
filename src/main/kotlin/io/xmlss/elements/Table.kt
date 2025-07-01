package io.xmlss.elements

class Table(
    val rows: List<Row> = listOf(),
    val columns: List<Column> = listOf(),
    val defaultColumnWidth: Double = 48.0,
    val defaultRowHeight: Double = 12.75,
    val expandedColumnCount: UInt? = null,
    val expandedRowCount: UInt? = null,
    val leftCell: UInt = 1u,
    val styleID: StyleID? = null,
    val topCell: UInt = 1u,
    val fullColumns: Boolean? = null,
    val fullRows: Boolean? = null
) : AbstractElement("ss:Table") {

    constructor (rows: List<List<String>>) : this(rows.map { Row(it) })

    init {
        require(defaultColumnWidth > 0) {
            "DefaultColumnWidth must be greater than 0"
        }
        require(defaultRowHeight > 0) {
            "DefaultRowHeight must be greater than 0"
        }
        require(leftCell > 0u) {
            "LeftCell must be greater than zero"
        }
        require(topCell > 0u) {
            "TopCell must be greater than zero"
        }
    }

    override val attributes = mapOf(
        "ss:DefaultColumnWidth" to if (defaultColumnWidth == 48.0) null else defaultColumnWidth,
        "ss:DefaultRowHeight" to if (defaultRowHeight == 12.75) null else defaultRowHeight,
        "ss:ExpandedColumnCount" to expandedColumnCount,
        "ss:ExpandedRowCount" to expandedRowCount,
        "ss:LeftCell" to if (leftCell == 1u) null else leftCell,
        "ss:StyleID" to styleID,
        "ss:TopCell" to if (topCell == 1u) null else topCell,
        "x:FullColumns" to fullColumns,
        "x:FullRows" to fullRows
    )

    override fun children() = listOf(columns, rows).flatten()

}