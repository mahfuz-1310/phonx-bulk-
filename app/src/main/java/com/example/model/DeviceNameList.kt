package com.example.model

object DeviceNameList {
    val manufacturers = listOf(
        "Samsung", "Apple", "Google Pixel", "Xiaomi", "Redmi", "POCO", "OnePlus", "OPPO",
        "vivo", "iQOO", "Realme", "Honor", "Huawei", "Motorola", "Nothing", "ASUS",
        "Sony", "Tecno", "Infinix", "ZTE", "Nokia", "Lenovo", "Meizu", "Sharp",
        "HTC", "Black Shark", "Lava", "Walton", "Symphony", "Ulefone", "Oukitel",
        "Doogee", "Fairphone"
    )

    val devices = mapOf(
        "Samsung" to listOf("Galaxy S24 Ultra", "Galaxy S24+", "Galaxy S24", "Galaxy S23 Ultra", "Galaxy S23+", "Galaxy S23", "Galaxy S23 FE", "Galaxy Z Fold 6", "Galaxy Z Flip 6", "Galaxy Z Fold 5", "Galaxy Z Flip 5", "Galaxy A55 5G", "Galaxy A35 5G", "Galaxy A54 5G", "Galaxy A34 5G", "Galaxy M55 5G", "Galaxy M34 5G", "Galaxy S22 Ultra", "Galaxy S21 Ultra"),
        "Apple" to listOf("iPhone 15 Pro Max", "iPhone 15 Pro", "iPhone 15 Plus", "iPhone 15", "iPhone 14 Pro Max", "iPhone 14 Pro", "iPhone 14 Plus", "iPhone 14", "iPhone 13 Pro Max", "iPhone 13 Pro", "iPhone 13", "iPhone 13 mini", "iPhone SE (3rd Gen)"),
        "Google Pixel" to listOf("Pixel 9 Pro XL", "Pixel 9 Pro", "Pixel 9 Pro Fold", "Pixel 9", "Pixel 8 Pro", "Pixel 8", "Pixel 8a", "Pixel Fold", "Pixel 7 Pro", "Pixel 7", "Pixel 7a", "Pixel 6 Pro", "Pixel 6"),
        "Xiaomi" to listOf("Xiaomi 14 Ultra", "Xiaomi 14 Pro", "Xiaomi 14", "Xiaomi 13 Ultra", "Xiaomi 13 Pro", "Xiaomi 13", "Xiaomi 13T Pro", "Xiaomi 13T", "Xiaomi Mix Fold 3", "Xiaomi Civi 4 Pro"),
        "Redmi" to listOf("Redmi Note 13 Pro+", "Redmi Note 13 Pro", "Redmi Note 13", "Redmi Note 12 Pro+", "Redmi Note 12", "Redmi K70 Pro", "Redmi K70", "Redmi K70E", "Redmi 13C"),
        "POCO" to listOf("POCO F6 Pro", "POCO F6", "POCO X6 Pro", "POCO X6", "POCO M6 Pro", "POCO F5 Pro", "POCO F5", "POCO X5 Pro"),
        "OnePlus" to listOf("OnePlus 12", "OnePlus 12R", "OnePlus Open", "OnePlus 11", "OnePlus 11R", "OnePlus Nord 4", "OnePlus Nord CE 4", "OnePlus Nord CE 3 Lite"),
        "OPPO" to listOf("OPPO Find X7 Ultra", "OPPO Find X7", "OPPO Find N3", "OPPO Find N3 Flip", "OPPO Reno11 Pro", "OPPO Reno11", "OPPO Reno10 Pro+", "OPPO Reno10 Pro"),
        "vivo" to listOf("vivo X100 Ultra", "vivo X100 Pro", "vivo X100", "vivo X Fold3 Pro", "vivo X Fold3", "vivo V30 Pro", "vivo V30", "vivo V29 Pro"),
        "iQOO" to listOf("iQOO 12 Pro", "iQOO 12", "iQOO Neo9 Pro", "iQOO Neo9", "iQOO 11 Pro", "iQOO 11", "iQOO Z9"),
        "Realme" to listOf("realme GT 5 Pro", "realme GT 5", "realme 12 Pro+", "realme 12 Pro", "realme 12+", "realme 11 Pro+", "realme C67"),
        "Honor" to listOf("HONOR Magic6 Pro", "HONOR Magic6", "HONOR Magic V2", "HONOR 90", "HONOR X9b", "HONOR 200 Pro", "HONOR 200"),
        "Huawei" to listOf("HUAWEI Pura 70 Ultra", "HUAWEI Pura 70 Pro+", "HUAWEI Pura 70 Pro", "HUAWEI Pura 70", "HUAWEI Mate 60 Pro+", "HUAWEI Mate 60 Pro", "HUAWEI Mate X5", "HUAWEI Nova 12 Pro"),
        "Motorola" to listOf("motorola edge 50 ultra", "motorola edge 50 pro", "motorola edge 50 fusion", "motorola razr 50 ultra", "motorola razr 50", "moto g84", "moto g54"),
        "Nothing" to listOf("Nothing Phone (2)", "Nothing Phone (2a)", "Nothing Phone (1)"),
        "ASUS" to listOf("ASUS Zenfone 11 Ultra", "ASUS Zenfone 10", "ROG Phone 8 Pro", "ROG Phone 8", "ROG Phone 7 Ultimate"),
        "Sony" to listOf("Xperia 1 VI", "Xperia 5 V", "Xperia 10 VI", "Xperia 1 V", "Xperia PRO-I"),
        "Tecno" to listOf("TECNO CAMON 30 Premier", "TECNO CAMON 30 Pro", "TECNO PHANTOM V Fold", "TECNO PHANTOM V Flip", "TECNO SPARK 20 Pro+"),
        "Infinix" to listOf("Infinix NOTE 40 Pro+", "Infinix NOTE 40 Pro", "Infinix GT 20 Pro", "Infinix ZERO 30 5G"),
        "ZTE" to listOf("ZTE Axon 60 Ultra", "ZTE Axon 50 Ultra", "nubia Z60 Ultra", "nubia RedMagic 9 Pro+"),
        "Nokia" to listOf("Nokia XR21", "Nokia G42 5G", "Nokia X30 5G", "Nokia G22"),
        "Lenovo" to listOf("Lenovo Legion Y70", "Lenovo ThinkPhone"),
        "Meizu" to listOf("Meizu 21 Pro", "Meizu 21", "Meizu 20 Infinity"),
        "Sharp" to listOf("AQUOS R8 pro", "AQUOS R8", "AQUOS sense8"),
        "HTC" to listOf("HTC U23 pro", "HTC U23"),
        "Black Shark" to listOf("Black Shark 5 Pro", "Black Shark 5"),
        "Lava" to listOf("Lava Agni 2 5G", "Lava Blaze Curve 5G", "Lava Storm 5G"),
        "Walton" to listOf("Walton Primo S8 Mini", "Walton Nexus N1"),
        "Symphony" to listOf("Symphony Z70", "Symphony Innova 10"),
        "Ulefone" to listOf("Ulefone Armor 24", "Ulefone Armor 22"),
        "Oukitel" to listOf("Oukitel WP30 Pro", "Oukitel WP27"),
        "Doogee" to listOf("Doogee V30 Pro", "Doogee S110"),
        "Fairphone" to listOf("Fairphone 5", "Fairphone 4")
    )
}
