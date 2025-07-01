package io.xmlss.elements

import kotlin.collections.listOf
import java.time.LocalDateTime

import io.xmlss.util.ElementUtils
import kotlin.collections.mapOf

class Data(val data: String, val type: DataType = DataType.STRING, val tiked: Boolean = false) :
    AbstractElement("ss:Data") {
    constructor(data: Number) : this(data.toString(), DataType.NUMBER)
    constructor(data: Boolean) : this(data.toString(), DataType.BOOLEAN)
    constructor(data: LocalDateTime) : this(data.toString(), DataType.DATETIME)
    constructor(data: RichText) : this(data.toString(), DataType.RICH_TEXT)
    constructor(data: Error) : this(data.toString(), DataType.ERROR)

    override val attributes = mapOf("x:Ticked" to if (tiked) 1 else null, "ss:Type" to type.ssType)

    override fun children() = listOf(data)

}

enum class DataType(val ssType: String) {
    NUMBER("Number"),
    DATETIME("DateTime"),
    BOOLEAN("Boolean"),
    STRING("String"),
    ERROR("Error"),
    RICH_TEXT("String\" xmlns=\"http://www.w3.org/TR/REC-html40")
}

class RichText {

    private var richText = StringBuilder()

    fun append(t: String) = richText.append(t)

    fun append(chunck: ChunkOfRichText) = richText.append(chunck.toString())

    override fun toString() = richText.toString()

}

class ChunkOfRichText(private val text: String) {

    private val tags = HashMap<String, String?>()

    fun bold() = addTag("B")

    fun italic() = addTag("I")

    fun strikethrough() = addTag("S")

    fun subscript() = addTag("Sub")

    fun superscript() = addTag("Sup")

    fun color(color: Color) = addTagAndStyle("Font", "x:Color=\"$color\"")

    fun underline(style: UStyle = UStyle.SINGLE) = addTagAndStyle("U", "x:Style=\"${style.xStyle}\"")

    fun outline() = addTagAndStyle("Span", "ss:Style=\"text-effect:outline\"")

    private fun addTag(tag: String): ChunkOfRichText {
        tags.put(tag, null)
        return this
    }

    private fun addTagAndStyle(tag: String, style: String): ChunkOfRichText {
        tags.put(tag, style)
        return this
    }

    override fun toString(): String {
        val str = StringBuilder(text)
        tags.forEach { (k, v) ->
            run {
                if (v == null) str.insert(0, "<$k>") else str.insert(0, "<$k $v>")
                str.append("</$k>")
            }
        }
        return str.toString()
    }

}

enum class UStyle(val xStyle: String) {
    SINGLE("text-underline:single"),
    DOUBLE("text-underline:double"),
    SINGLE_ACCOUNTING("text-underline:single-accounting"),
    DOUBLE_ACCOUNTING("text-underline:double-accounting")
}

@JvmInline
value class Error(private val error: String) {

    init {
        require(isError(error)) {
            "$error not included in the list of $ERRORS"
        }
    }

    companion object {
        val ERRORS = listOf("#NULL!", "#DIV/0!", "#VALUE!", "#REF!", "#NAME?", "#NUM!", "#N/A", "#CIRC!")
        fun isError(error: String) = error in ERRORS
    }

    override fun toString() = error

}
