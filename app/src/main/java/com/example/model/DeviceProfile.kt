package com.example.model

import java.util.UUID

data class DeviceProfile(
    val id: String = UUID.randomUUID().toString(),
    val brand: String,
    val model: String,
    val androidVersion: String,
    val ram: String,
    val storage: String,
    val screenResolution: String,
    val cpu: String,
    val gpu: String,
    val deviceName: String,
    val timestamp: Long = System.currentTimeMillis()
) {
    fun serialize(): String {
        return listOf(id, brand, model, androidVersion, ram, storage, screenResolution, cpu, gpu, deviceName, timestamp.toString()).joinToString("|||")
    }

    companion object {
        fun deserialize(str: String): DeviceProfile? {
            val parts = str.split("|||")
            if (parts.size < 11) return null
            return DeviceProfile(
                id = parts[0],
                brand = parts[1],
                model = parts[2],
                androidVersion = parts[3],
                ram = parts[4],
                storage = parts[5],
                screenResolution = parts[6],
                cpu = parts[7],
                gpu = parts[8],
                deviceName = parts[9],
                timestamp = parts[10].toLongOrNull() ?: System.currentTimeMillis()
            )
        }
    }
}

object DeviceDataStore {
    val brands = listOf(
        "Samsung", "Google", "Xiaomi", "Redmi", "POCO", "OnePlus",
        "OPPO", "vivo", "Realme", "Motorola", "Nokia", "Sony", "ASUS"
    )

    val androidVersions = listOf(
        "Android 10", "Android 11", "Android 12", "Android 13", "Android 14", "Android 15", "Android 16"
    )

    val brandModels = mapOf(
        "Samsung" to listOf(
            Triple("Galaxy S24 Ultra", "Snapdragon 8 Gen 3", "Adreno 750"),
            Triple("Galaxy S24", "Exynos 2400", "Xclipse 940"),
            Triple("Galaxy Z Fold 5", "Snapdragon 8 Gen 2", "Adreno 740"),
            Triple("Galaxy A55", "Exynos 1480", "Xclipse 530"),
            Triple("Galaxy Tab S9", "Snapdragon 8 Gen 2", "Adreno 740")
        ),
        "Google" to listOf(
            Triple("Pixel 9 Pro XL", "Google Tensor G4", "Mali-G715"),
            Triple("Pixel 9", "Google Tensor G4", "Mali-G715"),
            Triple("Pixel 8a", "Google Tensor G3", "Mali-G715"),
            Triple("Pixel Fold", "Google Tensor G2", "Mali-G715")
        ),
        "Xiaomi" to listOf(
            Triple("Xiaomi 14 Ultra", "Snapdragon 8 Gen 3", "Adreno 750"),
            Triple("Xiaomi 14", "Snapdragon 8 Gen 3", "Adreno 750"),
            Triple("Xiaomi 13T Pro", "MediaTek Dimensity 9200+", "Immortalis-G715")
        ),
        "Redmi" to listOf(
            Triple("Redmi Note 13 Pro+", "MediaTek Dimensity 7200-Ultra", "Mali-G610"),
            Triple("Redmi Note 13", "Snapdragon 685", "Adreno 610"),
            Triple("Redmi 13C", "MediaTek Helio G85", "Mali-G52")
        ),
        "POCO" to listOf(
            Triple("POCO F6 Pro", "Snapdragon 8 Gen 2", "Adreno 740"),
            Triple("POCO X6 Pro", "MediaTek Dimensity 8300 Ultra", "Mali-G615"),
            Triple("POCO M6 Pro", "MediaTek Helio G99 Ultra", "Mali-G57")
        ),
        "OnePlus" to listOf(
            Triple("OnePlus 12", "Snapdragon 8 Gen 3", "Adreno 750"),
            Triple("OnePlus 12R", "Snapdragon 8 Gen 2", "Adreno 740"),
            Triple("OnePlus Open", "Snapdragon 8 Gen 2", "Adreno 740")
        ),
        "OPPO" to listOf(
            Triple("Find X7 Ultra", "Snapdragon 8 Gen 3", "Adreno 750"),
            Triple("Reno 11 Pro", "MediaTek Dimensity 8200", "Mali-G610"),
            Triple("OPPO A79", "MediaTek Dimensity 6020", "Mali-G57")
        ),
        "vivo" to listOf(
            Triple("X100 Pro", "MediaTek Dimensity 9300", "Immortalis-G720"),
            Triple("V30 Pro", "MediaTek Dimensity 8200", "Mali-G610"),
            Triple("vivo Y28", "MediaTek Helio G85", "Mali-G52")
        ),
        "Realme" to listOf(
            Triple("GT 5 Pro", "Snapdragon 8 Gen 3", "Adreno 750"),
            Triple("Realme 12 Pro+", "Snapdragon 7s Gen 2", "Adreno 710"),
            Triple("Realme C67", "Snapdragon 685", "Adreno 610")
        ),
        "Motorola" to listOf(
            Triple("Edge 50 Pro", "Snapdragon 7 Gen 3", "Adreno 720"),
            Triple("Moto G85", "Snapdragon 6s Gen 3", "Adreno 619"),
            Triple("Razr 50 Ultra", "Snapdragon 8s Gen 3", "Adreno 735")
        ),
        "Nokia" to listOf(
            Triple("Nokia G42", "Snapdragon 480+", "Adreno 619"),
            Triple("Nokia X30", "Snapdragon 695", "Adreno 619"),
            Triple("Nokia XR21", "Snapdragon 695", "Adreno 619")
        ),
        "Sony" to listOf(
            Triple("Xperia 1 VI", "Snapdragon 8 Gen 3", "Adreno 750"),
            Triple("Xperia 10 VI", "Snapdragon 6 Gen 1", "Adreno 710")
        ),
        "ASUS" to listOf(
            Triple("ROG Phone 8 Pro", "Snapdragon 8 Gen 3", "Adreno 750"),
            Triple("Zenfone 11 Ultra", "Snapdragon 8 Gen 3", "Adreno 750")
        )
    )

    val ramOptions = listOf("6 GB", "8 GB", "12 GB", "16 GB", "24 GB")
    val storageOptions = listOf("128 GB", "256 GB", "512 GB", "1 TB")
    val resolutionOptions = listOf("720 × 1600", "1080 × 2400", "1080 × 2340", "1220 × 2712", "1440 × 3200")

    fun generateRandom(): DeviceProfile {
        val brand = brands.random()
        val modelsForBrand = brandModels[brand] ?: listOf(Triple("Generic Device", "Octa-core", "Adreno"))
        val modelTriple = modelsForBrand.random()
        val model = modelTriple.first
        val cpu = modelTriple.second
        val gpu = modelTriple.third
        val android = androidVersions.random()
        val ram = ramOptions.random()
        val storage = storageOptions.random()
        val res = resolutionOptions.random()
        val deviceName = model

        return DeviceProfile(
            brand = brand,
            model = model,
            androidVersion = android,
            ram = ram,
            storage = storage,
            screenResolution = res,
            cpu = cpu,
            gpu = gpu,
            deviceName = deviceName
        )
    }

    fun generateCustom(
        brand: String,
        model: String,
        androidVersion: String,
        ram: String,
        storage: String,
        resolution: String
    ): DeviceProfile {
        val modelsForBrand = brandModels[brand]
        val modelTriple = modelsForBrand?.find { it.first == model } ?: Triple(model, "Octa-core ARM", "Adreno / Mali")
        return DeviceProfile(
            brand = brand,
            model = modelTriple.first,
            androidVersion = androidVersion,
            ram = ram,
            storage = storage,
            screenResolution = resolution,
            cpu = modelTriple.second,
            gpu = modelTriple.third,
            deviceName = model
        )
    }
}
