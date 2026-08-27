package com.example.model

enum class Gender(val label: String) {
    MALE("Male"),
    FEMALE("Female"),
    UNISEX("Unisex")
}

enum class NameStyle(
    val label: String,
    val description: String
) {
    MODERN("Modern", "Contemporary and commonly usable names"),
    CUTE("Cute", "Short, soft, friendly-sounding names"),
    STYLISH("Stylish", "Fashionable and sophisticated names"),
    UNIQUE("Unique", "Less common but realistic names"),
    CLASSIC("Classic", "Traditional and timeless names"),
    ROYAL("Royal", "Elegant, prestigious, noble names"),
    GAMING("Gaming", "Memorable identities suitable for tags"),
    AESTHETIC("Aesthetic", "Visually pleasing, modern, balanced names")
}

data class Country(
    val id: String,
    val name: String,
    val code: String,
    val region: String
)

enum class FontOption(val displayName: String, val sampleText: String) {
    DEFAULT("Default", "Standard Clean Sans"),
    INTER("Inter", "Geometric Precision"),
    ROBOTO("Roboto", "Modern Android Sans"),
    POPPINS("Poppins", "Geometric Rounded"),
    MONTSERRAT("Montserrat", "Urban Architectural"),
    NUNITO("Nunito", "Friendly Soft Rounded"),
    LATO("Lato", "Warm Corporate Sans"),
    OPEN_SANS("Open Sans", "Neutral Legible"),
    OSWALD("Oswald", "Condensed Bold Display"),
    PLAYFAIR_DISPLAY("Playfair Display", "Classic High-Contrast Serif"),
    MERRIWEATHER("Merriweather", "Literary Editorial Serif"),
    RALEWAY("Raleway", "Elegant Thin Heading"),
    QUICKSAND("Quicksand", "Smooth Curved Display"),
    BEBAS_NEUE("Bebas Neue", "Tall Impact Headline"),
    CINZEL("Cinzel", "Royal Roman Inscription"),
    DANCING_SCRIPT("Dancing Script", "Fluid Signature Script")
}

data class GeneratedName(
    val id: String,
    val name: String,
    val gender: Gender,
    val country: Country,
    val style: NameStyle,
    val font: FontOption,
    val timestamp: Long = System.currentTimeMillis(),
    val isSaved: Boolean = false
)

enum class ThemeMode {
    SYSTEM,
    LIGHT,
    DARK
}
