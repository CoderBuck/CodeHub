package github.coderbuck.codehub

import github.coderbuck.codehub.bean.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

object HtmlParser {

    /**
     * 获取提交日历
     */
    fun getGraph(html: String): CommitGraphBean {
        val document = Jsoup.parse(html)
        val body = document.body()
        val svg_element = body.select("div.js-calendar-graph > svg").first()
        val svg_g_element = svg_element.select("g").first()

        val width = svg_element.attr("width").trim().toInt()
        val height = svg_element.attr("height").trim().toInt()
        val transform = getTranslate(svg_g_element)
        val columns = getColumns(svg_g_element)
        val months = getMonths(svg_g_element)
        val wdays = getWdays(svg_g_element)
        return CommitGraphBean(width, height, transform, columns, months, wdays)
    }

    private fun getTranslate(element: Element): TranslateBean {
        // translate(10, 20)
        val transform = element.attr("transform")
        val x = transform.substringAfter("(").substringBefore(",").trim().toInt()
        val y = transform.substringAfter(",").substringBefore(")").trim().toInt()
        return TranslateBean(x, y)
    }

    private fun getColumns(svg_g_element: Element): List<ColumnBean> {
        val svg_g_g_elements = svg_g_element.select("g > g")
        val columns = svg_g_g_elements.map { element ->
            val transform = getTranslate(element)
            val cells = element.select("rect.day")
                .map { rect ->
                    CellBean(
                        rect.attr("width").trim().toInt(),
                        rect.attr("height").trim().toInt(),
                        rect.attr("x").trim().toInt(),
                        rect.attr("y").trim().toInt(),
                        rect.attr("data-count").trim().toInt(),
                        rect.attr("fill").trim(),
                        rect.attr("data-date").trim()
                    ).apply {
                        if (data_date.equals("2020-04-18")) {
                            println("gwf: CellBean ================")
                            println("gwf: CellBean " + element)
                        }
                    }
                }.toList()

            if (transform.y == 20) {
//                println("ggg eee " + element.toString())
            }
            return@map ColumnBean(transform, cells)
        }.toList()
        return columns
    }

    private fun getMonths(svg_g_element: Element): List<MonthBean> {
        val elements = svg_g_element.select("text.month")
        return elements.map {
            MonthBean(
                it.attr("x").trim().toInt(),
                it.attr("y").trim().toInt(),
                it.text()
            )
        }.toList()
    }

    private fun getWdays(svg_g_element: Element): List<WdayBean> {
        val elements = svg_g_element.select("text.wday")
        return elements.map {
            WdayBean(
                it.attr("dx").trim().toInt(),
                it.attr("dy").trim().toInt(),
                it.text()
            )
        }.toList()
    }
}