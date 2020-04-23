package github.coderbuck.codehub.bean

/**
 * 提交贡献图标
 */
data class CommitGraphBean(
    val width: Int,
    val height: Int,
    val translate: TranslateBean,   // 基础偏移
    val columns: List<ColumnBean>,  // 方块
    val months: List<MonthBean>,    // 月份
    val wdays: List<WdayBean>       // 星期
)

/**
 * 小方块
 */
data class CellBean(
    val width: Int,
    val height: Int,
    val x: Int,
    val y: Int,
    val data_count: Int,
    val fill: String,
    val data_date: String
)

/**
 * 偏移
 */
data class TranslateBean(
    val x: Int,
    val y: Int
)

/**
 * 列
 */
data class ColumnBean(
    val transform: TranslateBean,
    val cells: List<CellBean>
)

data class MonthBean(
    val x: Int,
    val y: Int,
    val txt: String
)

data class WdayBean(
    val dx: Int,
    val dy: Int,
    val txt: String
)