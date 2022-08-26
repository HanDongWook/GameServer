package com.example.gameserver

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class ReadFile {
    // 천 이하 Int 한 줄에 하나씩 백만개까지만 읽음, 총
    fun read() {
        val file = File("filepath.txt")
        val reader = BufferedReader(FileReader(file))
        val intMap = HashMap<Int, Int>()

        var totalValue = 0
        var min = 0
        var max = 0
        val count = 0
        reader.lines().forEach {
            val value = it.toInt()
            if (intMap[value] == null) {
                intMap.put(value, 1)

            } else {
                val count = intMap.get(value)!!
                intMap[value] = count + 1
            }
        }

        for ((key, value) in intMap) {
            totalValue = totalValue + (key * value)
        }
        val average = totalValue/intMap.size

    }
}