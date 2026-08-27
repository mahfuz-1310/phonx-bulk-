package com.example.data

import com.example.model.Country

object CountryData {
    val ALL_COUNTRIES: List<Country> = listOf(
        Country("BD", "Bangladesh", "BGD", "South Asia"),
        Country("IN", "India", "IND", "South Asia"),
        Country("PK", "Pakistan", "PAK", "South Asia"),
        Country("NP", "Nepal", "NPL", "South Asia"),
        Country("LK", "Sri Lanka", "LKA", "South Asia"),
        Country("US", "United States", "USA", "North America"),
        Country("CA", "Canada", "CAN", "North America"),
        Country("GB", "United Kingdom", "GBR", "Europe"),
        Country("AU", "Australia", "AUS", "Oceania"),
        Country("NZ", "New Zealand", "NZL", "Oceania"),
        Country("DE", "Germany", "DEU", "Europe"),
        Country("FR", "France", "FRA", "Europe"),
        Country("IT", "Italy", "ITA", "Europe"),
        Country("ES", "Spain", "ESP", "Europe"),
        Country("PT", "Portugal", "PRT", "Europe"),
        Country("NL", "Netherlands", "NLD", "Europe"),
        Country("BE", "Belgium", "BEL", "Europe"),
        Country("SE", "Sweden", "SWE", "Europe"),
        Country("NO", "Norway", "NOR", "Europe"),
        Country("DK", "Denmark", "DNK", "Europe"),
        Country("FI", "Finland", "FIN", "Europe"),
        Country("PL", "Poland", "POL", "Europe"),
        Country("UA", "Ukraine", "UKR", "Europe"),
        Country("RU", "Russia", "RUS", "Europe"),
        Country("TR", "Turkey", "TUR", "Middle East"),
        Country("SA", "Saudi Arabia", "SAU", "Middle East"),
        Country("AE", "United Arab Emirates", "ARE", "Middle East"),
        Country("QA", "Qatar", "QAT", "Middle East"),
        Country("EG", "Egypt", "EGY", "Middle East & Africa"),
        Country("ID", "Indonesia", "IDN", "Southeast Asia"),
        Country("MY", "Malaysia", "MYS", "Southeast Asia"),
        Country("SG", "Singapore", "SGP", "Southeast Asia"),
        Country("TH", "Thailand", "THA", "Southeast Asia"),
        Country("VN", "Vietnam", "VNM", "Southeast Asia"),
        Country("PH", "Philippines", "PHL", "Southeast Asia"),
        Country("JP", "Japan", "JPN", "East Asia"),
        Country("KR", "South Korea", "KOR", "East Asia"),
        Country("CN", "China", "CHN", "East Asia"),
        Country("BR", "Brazil", "BRA", "South America"),
        Country("MX", "Mexico", "MEX", "North America"),
        Country("AR", "Argentina", "ARG", "South America"),
        Country("ZA", "South Africa", "ZAF", "Africa"),
        Country("NG", "Nigeria", "NGA", "Africa"),
        Country("IE", "Ireland", "IRL", "Europe"),
        Country("GR", "Greece", "GRC", "Europe"),
        Country("CH", "Switzerland", "CHE", "Europe"),
        Country("AT", "Austria", "AUT", "Europe")
    )

    fun getById(id: String): Country {
        return ALL_COUNTRIES.find { it.id.equals(id, ignoreCase = true) }
            ?: ALL_COUNTRIES.first()
    }
}
