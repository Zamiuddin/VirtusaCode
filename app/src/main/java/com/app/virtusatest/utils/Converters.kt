package com.app.virtusatest.utils

import androidx.room.TypeConverter
import com.app.virtusatest.utils.Constants.COMMA


class Converters {
    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        return stringListString.split(COMMA).map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = COMMA)
    }
}