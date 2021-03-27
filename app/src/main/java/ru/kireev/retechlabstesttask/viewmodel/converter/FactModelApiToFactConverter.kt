package ru.kireev.retechlabstesttask.viewmodel.converter

import ru.kireev.retechlabstesttask.model.models.FactModelApi
import ru.kireev.retechlabstesttask.viewmodel.entity.Fact
import java.text.SimpleDateFormat
import java.util.*

fun convertFactModelApi(factModelApi: FactModelApi): Fact {
    var outputDate = ""
    var outputTime = ""

    val cameDate = factModelApi.createdAt.split("T")[0]
    val cameTime = factModelApi.createdAt.split("T")[1]

    if (cameDate.isNotEmpty()) {
        val cameDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = cameDateFormat.parse(cameDate) ?: Date(cameDate)
        outputDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(date)
    }

    if (cameTime.isNotEmpty()) {
        outputTime = cameTime.substring(0, 5)
    }

    return Fact(factModelApi._id, "$outputDate $outputTime", factModelApi.text)
}