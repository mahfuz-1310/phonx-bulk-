package com.example

import android.os.Process
import java.io.BufferedReader
import java.io.InputStreamReader
import android.util.Log

class ShizukuService : IShizukuService.Stub() {
    override fun executeCommand(command: String): String {
        return try {
            val process = Runtime.getRuntime().exec(arrayOf("sh", "-c", command))
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val output = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                output.append(line).append("\n")
            }
            process.waitFor()
            output.toString().trim()
        } catch (e: Exception) {
            "ERROR: " + e.message
        }
    }
}
