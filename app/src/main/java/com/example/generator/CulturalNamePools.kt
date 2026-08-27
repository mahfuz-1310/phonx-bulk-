package com.example.generator

object CulturalNamePools {

    data class CultureData(
        val maleNames: List<String>,
        val femaleNames: List<String>,
        val unisexNames: List<String>,
        val surnames: List<String>,
        val cuteMale: List<String> = emptyList(),
        val cuteFemale: List<String> = emptyList(),
        val stylishMale: List<String> = emptyList(),
        val stylishFemale: List<String> = emptyList(),
        val uniqueMale: List<String> = emptyList(),
        val uniqueFemale: List<String> = emptyList(),
        val classicMale: List<String> = emptyList(),
        val classicFemale: List<String> = emptyList(),
        val royalPrefixes: List<String> = emptyList(),
        val royalMale: List<String> = emptyList(),
        val royalFemale: List<String> = emptyList(),
        val gamingPrefixes: List<String> = emptyList(),
        val gamingSuffixes: List<String> = emptyList(),
        val aestheticAdjectives: List<String> = emptyList()
    )

    // Bangladesh
    val BANGLADESH = CultureData(
        maleNames = listOf(
            "Aarav", "Tahmid", "Mahfuz", "Sami", "Nabil", "Tanvir", "Fahim", "Rayan",
            "Shakib", "Tamim", "Siam", "Zubair", "Ayman", "Farhan", "Nasif", "Imtiaz",
            "Anik", "Rifat", "Sabbir", "Mushfiq", "Saif", "Ehsan", "Asif", "Adnan",
            "Arham", "Zayan", "Shayan", "Wasif", "Nahian", "Tashfin", "Arman", "Kazi",
            "Sadman", "Mahi", "Shafin", "Rohan", "Afif", "Tawsif", "Miraz", "Hasan",
            "Mahmudul", "Mustafizur", "Mehedi", "Soumya", "Liton", "Taskin", "Rubel",
            "Nasir", "Anamul", "Zahid", "Arif", "Shohag", "Shihab", "Rashed", "Shuvo",
            "Parvez", "Monir", "Masud", "Kamrul", "Biplob", "Jubayer", "Shahriar", "Habib"
        ),
        femaleNames = listOf(
            "Ayesha", "Samira", "Nusrat", "Tazrin", "Anika", "Sadia", "Fariha", "Mehnaz",
            "Sumaiya", "Farzana", "Tahsin", "Bushra", "Nafisa", "Afia", "Suhana", "Tasnim",
            "Jannat", "Lamia", "Mithila", "Nabila", "Sharmin", "Sabrina", "Rumana", "Ishrat",
            "Mehreen", "Nazifa", "Humaira", "Raisa", "Arisha", "Zaheera", "Anjum", "Rawnak",
            "Farhana", "Maliha", "Tanjina", "Roshni", "Mayeesha", "Sanjida", "Munira", "Nowshin",
            "Zannatul", "Rukhsana", "Shahana", "Sultana", "Khadija", "Fatima", "Mariam", "Shabnam",
            "Rubina", "Yasmin", "Shirin", "Parvin", "Nazma", "Salma", "Rehana", "Nasima"
        ),
        unisexNames = listOf(
            "Tahsin", "Nibir", "Shakil", "Arin", "Nabil", "Shayan", "Rayhan", "Zayan", "Tanvir", "Ayan", "Samar", "Kiran", "Niloy", "Shoron", "Alve"
        ),
        surnames = listOf(
            "Ahmed", "Khan", "Chowdhury", "Hossain", "Rahman", "Hasan", "Islam", "Sikder",
            "Majumder", "Talukder", "Bhuiyan", "Sarker", "Uddin", "Miah", "Kazi", "Dewan",
            "Akter", "Ali", "Alam", "Molla", "Bari", "Haider", "Gazi", "Pal", "Dey", "Bhowmik"
        ),
        cuteMale = listOf("Sami", "Mahi", "Aarav", "Rayan", "Anik", "Shuvo", "Rifat", "Adil", "Zayn", "Rohan", "Shafin", "Arin", "Nil"),
        cuteFemale = listOf("Mimi", "Piu", "Mahi", "Rimi", "Tuli", "Mitu", "Riya", "Muna", "Tina", "Dia", "Nila", "Shuchi", "Himi", "Tisha"),
        stylishMale = listOf("Ayman", "Zayan", "Shayan", "Tashfin", "Arman", "Zubair", "Nahian", "Arham", "Siam", "Nabil", "Fahim", "Rayyan"),
        stylishFemale = listOf("Mehreen", "Nazifa", "Humaira", "Zaheera", "Raisa", "Arisha", "Mehnaz", "Suhana", "Tazrin", "Nafisa", "Tahsin"),
        uniqueMale = listOf("Tashdeed", "Nibras", "Shazid", "Mashrur", "Irtiza", "Zuhayr", "Tanzeem", "Muntasir", "Shahmeer", "Iftikhar"),
        uniqueFemale = listOf("Nusheen", "Inshirah", "Tabassum", "Manha", "Eshana", "Irtaza", "Safana", "Roshna", "Mahnoor", "Nuha"),
        classicMale = listOf("Mohammad", "Habibur", "Shamsur", "Motiur", "Khandaker", "Abdur", "Nurul", "Anwar", "Sirajul", "Mokhlesur"),
        classicFemale = listOf("Fatema", "Khadija", "Rabeya", "Rokeya", "Sufia", "Jahanara", "Hosneara", "Shamsunnahar", "Nurjahan", "Saleha"),
        royalPrefixes = listOf("Shahzada", "Mirza", "Nawabzada", "Kazi", "Dewan", "Chowdhury", "Khan Bahadur"),
        royalMale = listOf("Shahriar", "Aurangzeb", "Jahangir", "Humayun", "Alamgir", "Shamsuddin", "Nasiruddin", "Sikandar"),
        royalFemale = listOf("Nurjahan", "Mumtaz", "Mehrunnisa", "Zebunnisa", "Jahanara", "Roshanara", "Bakhtunnisa"),
        gamingPrefixes = listOf("Shadow", "Viper", "Phantom", "Bengal", "Apex", "Nova", "Titan", "Vortex"),
        gamingSuffixes = listOf("Strike", "Pulse", "Fury", "Blaze", "Storm", "Drift", "Ghost", "Edge")
    )

    // India
    val INDIA = CultureData(
        maleNames = listOf(
            "Aarav", "Vihaan", "Aditya", "Rohan", "Reyansh", "Arjun", "Kabir", "Ishaan",
            "Dhruv", "Atharva", "Advait", "Aayush", "Shaurya", "Karan", "Dev", "Vikram",
            "Ananya", "Raghav", "Varun", "Sameer", "Siddharth", "Pranav", "Nikhil", "Rishi",
            "Harsh", "Akash", "Manish", "Gaurav", "Yash", "Kartik", "Tanmay", "Kunal",
            "Alok", "Chirag", "Abhishek", "Deepak", "Gautam", "Mohit", "Sachin", "Rahul"
        ),
        femaleNames = listOf(
            "Aanya", "Diya", "Saanvi", "Ananya", "Pari", "Isha", "Navya", "Rhea",
            "Kavya", "Avani", "Meera", "Tara", "Anika", "Pooja", "Shreya", "Neha",
            "Aditi", "Tanvi", "Simran", "Riya", "Sneha", "Kritika", "Priyanka", "Nandini",
            "Swati", "Rashmi", "Divya", "Anushka", "Deepika", "Payal", "Garima", "Sakshi"
        ),
        unisexNames = listOf("Arya", "Kiran", "Sonu", "Harpreet", "Gurpreet", "Navneet", "Jaspreet", "Milan", "Deep", "Kamal", "Shashi"),
        surnames = listOf(
            "Sharma", "Verma", "Patel", "Gupta", "Singh", "Kumar", "Iyer", "Rao",
            "Reddy", "Nair", "Mukherjee", "Banerjee", "Chatterjee", "Bose", "Mehta", "Shah",
            "Joshi", "Kulkarni", "Deshmukh", "Choudhury", "Das", "Dutta", "Kapoor", "Malhotra"
        ),
        cuteMale = listOf("Aru", "Kavi", "Devu", "Chintu", "Riku", "Sunny", "Bunny", "Niku"),
        cuteFemale = listOf("Pari", "Pihu", "Chhavi", "Gori", "Mimi", "Tannu", "Guddu", "Kuki"),
        stylishMale = listOf("Reyansh", "Shaurya", "Advait", "Vivaan", "Reyan", "Samarth", "Zayan"),
        stylishFemale = listOf("Nyra", "Siya", "Anvi", "Myra", "Amaira", "Kiara", "Shanaya"),
        uniqueMale = listOf("Ekansh", "Hridaan", "Nirvaan", "Ojas", "Taksheel", "Vidyut", "Yuvan"),
        uniqueFemale = listOf("Anahita", "Dishita", "Gia", "Inaya", "Lavanya", "Vritika", "Ziva"),
        classicMale = listOf("Ramchandra", "Gopal", "Shankar", "Vishnu", "Raghunath", "Harishchandra", "Purushottam"),
        classicFemale = listOf("Saraswati", "Lakshmi", "Gayatri", "Savitri", "Kalyani", "Devaki", "Rukmini"),
        royalPrefixes = listOf("Maharaj", "Raja", "Kunwar", "Yuvraj", "Thakur"),
        royalMale = listOf("Vikramaditya", "Rajaraja", "Chhatrapati", "Prithviraj", "Harshavardhana"),
        royalFemale = listOf("Padmavati", "Sanyogita", "Maharani", "Rani", "Rajkumari"),
        gamingPrefixes = listOf("Rudra", "Garuda", "Agni", "Vajra", "Shiva", "Trident", "Surya"),
        gamingSuffixes = listOf("Gamer", "Pro", "X", "Prime", "Knight", "Warrior", "Shadow")
    )

    // Pakistan
    val PAKISTAN = CultureData(
        maleNames = listOf(
            "Hamza", "Bilal", "Zain", "Shahmeer", "Danyal", "Zubair", "Talha", "Haris",
            "Usman", "Saad", "Ammar", "Shahzaib", "Umer", "Waleed", "Abdullah", "Ibrahim",
            "Mustafa", "Ali", "Hassan", "Hussein", "Faisal", "Kamran", "Salman", "Imran",
            "Babar", "Shaheen", "Rizwan", "Shadab", "Fakhar", "Haris", "Naseem", "Saim"
        ),
        femaleNames = listOf(
            "Areeba", "Hania", "Zara", "Mahnoor", "Kinza", "Laiba", "Aiman", "Minal",
            "Hoorain", "Alizeh", "Zoya", "Eshal", "Iqra", "Hira", "Sana", "Sadia",
            "Komal", "Ayeza", "Sajal", "Yumna", "Mahira", "Sarah", "Mehwish", "Saba"
        ),
        unisexNames = listOf("Shayan", "Zayan", "Daniyal", "Farah", "Rayyan", "Rohail", "Naveed", "Sohail"),
        surnames = listOf(
            "Malik", "Khan", "Chaudhry", "Shah", "Butt", "Qureshi", "Mirza", "Abbasi",
            "Siddiqui", "Sheikh", "Bhatti", "Jutt", "Raza", "Ansari", "Dar", "Baig", "Mughal", "Gill"
        ),
        cuteMale = listOf("Zuni", "Mani", "Shani", "Bobi", "Sunny", "Dani", "Ali"),
        cuteFemale = listOf("Hani", "Zari", "Aini", "Mano", "Chanda", "Billo", "Doll"),
        stylishMale = listOf("Shahmeer", "Zaviyar", "Azaan", "Shahzaib", "Rayyan", "Zaryab"),
        stylishFemale = listOf("Alizeh", "Hoorain", "Mirha", "Anaya", "Hareem", "Ayat"),
        uniqueMale = listOf("Aariz", "Zorawar", "Shehryar", "Roohan", "Moeez", "Haziq"),
        uniqueFemale = listOf("Inaya", "Zimal", "Hooriya", "Manahil", "Rameen", "Wareesha"),
        classicMale = listOf("Sultan", "Nawaz", "Akhtar", "Liaquat", "Iftikhar", "Mumtaz"),
        classicFemale = listOf("Nasreen", "Parveen", "Bilquis", "Shamim", "Zubaida", "Tahira"),
        royalPrefixes = listOf("Mirza", "Sardar", "Nawabzada", "Shahzada", "Malik"),
        royalMale = listOf("Jahangir", "Aurangzeb", "Alamgir", "Shahjahan", "Babar"),
        royalFemale = listOf("Jahanara", "Nurjahan", "Mumtaz", "Zebunnisa", "Gauhar"),
        gamingPrefixes = listOf("Falcon", "Cobra", "Shaheen", "Thunder", "Slayer", "Pak"),
        gamingSuffixes = listOf("Sniper", "Beast", "Legend", "Ghost", "Strike", "Force")
    )

    // USA / UK / Canada / Australia / English
    val WESTERN_ANGLO = CultureData(
        maleNames = listOf(
            "Liam", "Noah", "Oliver", "James", "Elijah", "William", "Henry", "Lucas",
            "Benjamin", "Theodore", "Mateo", "Levi", "Sebastian", "Daniel", "Jack", "Alexander",
            "Owen", "Asher", "Samuel", "Ethan", "Leo", "Jackson", "Mason", "Ezra",
            "John", "Hudson", "Luca", "David", "Logan", "Luke", "Julian", "Carter",
            "Dylan", "Austin", "Hunter", "Caleb", "Miles", "Christian", "Colton", "Wyatt"
        ),
        femaleNames = listOf(
            "Olivia", "Emma", "Charlotte", "Amelia", "Sophia", "Isabella", "Ava", "Mia",
            "Evelyn", "Luna", "Harper", "Camila", "Sofia", "Scarlett", "Elizabeth", "Eleanor",
            "Emily", "Chloe", "Mila", "Violet", "Penelope", "Gianna", "Aria", "Abigail",
            "Ella", "Avery", "Hazel", "Nora", "Layla", "Lily", "Aurora", "Zoey"
        ),
        unisexNames = listOf(
            "Jordan", "Taylor", "Morgan", "Casey", "Riley", "Avery", "Alex", "Rowan",
            "Quinn", "Cameron", "Dakota", "Skyler", "Peyton", "Reese", "Hayden", "Kendall", "River", "Sage"
        ),
        surnames = listOf(
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis",
            "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas",
            "Taylor", "Moore", "Jackson", "Martin", "Lee", "Perez", "Thompson", "White",
            "Harris", "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson", "Walker", "Young",
            "Allen", "King", "Wright", "Scott", "Torres", "Nguyen", "Hill", "Flores",
            "Green", "Adams", "Nelson", "Baker", "Hall", "Rivera", "Campbell", "Mitchell",
            "Carter", "Roberts", "Gomez", "Phillips", "Evans", "Turner", "Diaz", "Parker",
            "Cruz", "Edwards", "Collins", "Reyes", "Stewart", "Morris", "Morales", "Murphy",
            "Cook", "Rogers", "Gutierrez", "Ortiz", "Morgan", "Cooper", "Peterson", "Bailey",
            "Reed", "Kelly", "Howard", "Ramos", "Kim", "Cox", "Ward", "Richardson",
            "Watson", "Brooks", "Chavez", "Wood", "James", "Bennett", "Gray", "Mendoza"
        ),
        cuteMale = listOf("Leo", "Finn", "Milo", "Toby", "Ollie", "Teddy", "Archie", "Max", "Sammy", "Benny"),
        cuteFemale = listOf("Lily", "Daisy", "Ruby", "Chloe", "Poppy", "Rosie", "Evie", "Maisie", "Zoe", "Pippa"),
        stylishMale = listOf("Julian", "Sebastian", "Gabriel", "Damian", "Felix", "Xavier", "Jasper", "Dominic"),
        stylishFemale = listOf("Serena", "Genevieve", "Giselle", "Vivienne", "Celeste", "Clara", "Fiona", "Sienna"),
        uniqueMale = listOf("Caspian", "Leander", "Orion", "Soren", "Zephyr", "Ronan", "Callum", "Dorian"),
        uniqueFemale = listOf("Aurelia", "Calliope", "Ophelia", "Lyra", "Elowen", "Isolde", "Thalia", "Vesper"),
        classicMale = listOf("Arthur", "George", "Edward", "Charles", "Henry", "Walter", "Thomas", "Richard"),
        classicFemale = listOf("Eleanor", "Margaret", "Beatrice", "Dorothy", "Florence", "Clara", "Victoria", "Alice"),
        royalPrefixes = listOf("Lord", "Sir", "Baron", "Duke of", "Viscount", "Earl of"),
        royalMale = listOf("Wellington", "Montgomery", "Sterling", "Kensington", "Windsor", "Harrington", "Pembroke"),
        royalFemale = listOf("Victoria", "Catherine", "Alexandra", "Guinevere", "Elizabeth", "Adelaide", "Charlotte"),
        gamingPrefixes = listOf("Vortex", "Apex", "Nova", "Cyber", "Ghost", "Rogue", "Specter", "Titan", "Blaze"),
        gamingSuffixes = listOf("Strike", "Viper", "Hawk", "Storm", "Wolf", "Shadow", "Knight", "Edge", "Frost")
    )

    // Germany / Austria / Switzerland
    val GERMANIC = CultureData(
        maleNames = listOf(
            "Maximilian", "Alexander", "Paul", "Elias", "Leon", "Louis", "Lukas", "Felix",
            "Jonas", "Noah", "Finn", "Ben", "Niklas", "Tim", "Moritz", "Jan", "David", "Philipp",
            "Florian", "Sebastian", "Tobias", "Matthias", "Fabian", "Julian", "Simon", "Johannes"
        ),
        femaleNames = listOf(
            "Emma", "Mia", "Hannah", "Emilia", "Sophia", "Lina", "Ella", "Clara",
            "Lea", "Marie", "Mila", "Luisa", "Johanna", "Laura", "Anna", "Amelie",
            "Lara", "Nele", "Sophie", "Mathilda", "Frida", "Charlotte", "Elena", "Paula"
        ),
        unisexNames = listOf("Robin", "Kim", "Sascha", "Toni", "Alex", "Mika", "Luca", "René"),
        surnames = listOf(
            "Müller", "Schmidt", "Schneider", "Fischer", "Weber", "Meyer", "Wagner", "Becker",
            "Schulz", "Hoffmann", "Schäfer", "Koch", "Bauer", "Richter", "Klein", "Wolf",
            "Schröder", "Neumann", "Schwarz", "Zimmermann", "Braun", "Krüger", "Hofmann", "Hartmann"
        ),
        cuteMale = listOf("Fritz", "Luki", "Benni", "Niki", "Maxi", "Joni", "Timi"),
        cuteFemale = listOf("Leni", "Mimi", "Elli", "Finja", "Mina", "Lotta", "Greta"),
        stylishMale = listOf("Maximilian", "Florian", "Julian", "Sebastian", "Valentin", "Konstantin"),
        stylishFemale = listOf("Emilia", "Valentina", "Johanna", "Carlotta", "Helena", "Isabella"),
        uniqueMale = listOf("Leopold", "Severin", "Tillman", "Korbinian", "Balthasar", "Albrecht"),
        uniqueFemale = listOf("Anneliese", "Marlen", "Eleonora", "Rosalie", "Sulamith", "Wilhelmina"),
        classicMale = listOf("Heinrich", "Friedrich", "Wilhelm", "Ludwig", "Konrad", "Otto", "Karl"),
        classicFemale = listOf("Hildegard", "Gisela", "Gertrud", "Margarete", "Elisabeth", "Frieda"),
        royalPrefixes = listOf("Von", "Baron Von", "Graf", "Prinz"),
        royalMale = listOf("Hohenzollern", "Habsburg", "Bismarck", "Wittelsbach", "Waldeck"),
        royalFemale = listOf("Augusta", "Theresia", "Luise", "Adelheid", "Margarethe"),
        gamingPrefixes = listOf("Kaiser", "Blitz", "Panzer", "Frost", "Iron", "Valkyrie"),
        gamingSuffixes = listOf("Jäger", "Sturm", "Wolf", "Kraft", "Schwert")
    )

    // France / Belgium
    val FRENCH = CultureData(
        maleNames = listOf(
            "Gabriel", "Léo", "Raphaël", "Arthur", "Louis", "Lucas", "Adam", "Jules",
            "Hugo", "Maël", "Liam", "Noah", "Paul", "Tiago", "Sacha", "Gabin",
            "Nathan", "Mohamed", "Théo", "Tom", "Timéo", "Ethan", "Nolan", "Maxence"
        ),
        femaleNames = listOf(
            "Jade", "Louise", "Ambre", "Alba", "Emma", "Rose", "Alice", "Romy",
            "Anna", "Lina", "Léna", "Mia", "Lou", "Juliette", "Julia", "Chloé",
            "Léonie", "Zoé", "Inès", "Camille", "Agathe", "Victoria", "Manon", "Margaux"
        ),
        unisexNames = listOf("Camille", "Sacha", "Maxime", "Stéphane", "Dominique", "Morgan", "Lou", "Charlie"),
        surnames = listOf(
            "Martin", "Bernard", "Thomas", "Petit", "Robert", "Richard", "Durand", "Dubois",
            "Moreau", "Laurent", "Simon", "Michel", "Lefebvre", "Leroy", "Roux", "David",
            "Bertrand", "Morel", "Fournier", "Girard", "Bonnet", "Dupont", "Lambert", "Fontaine"
        ),
        cuteMale = listOf("Loulou", "Doudou", "Titi", "Nono", "Coco", "Juju"),
        cuteFemale = listOf("Mimi", "Lili", "Chouchou", "Ninon", "Zélie", "Fifi"),
        stylishMale = listOf("Adrien", "Théodore", "Étienne", "Bastien", "Gaspard", "Valentin"),
        stylishFemale = listOf("Céleste", "Geneviève", "Capucine", "Solène", "Éloïse", "Apolline"),
        uniqueMale = listOf("Balthazar", "Zéphyr", "Ambroise", "Célestin", "Arsène", "Lucien"),
        uniqueFemale = listOf("Séraphine", "Olympe", "Azalée", "Clothilde", "Philomène", "Astrée"),
        classicMale = listOf("François", "Henri", "Jean-Pierre", "Alain", "Philippe", "Jacques"),
        classicFemale = listOf("Françoise", "Geneviève", "Jacqueline", "Simone", "Monique", "Colette"),
        royalPrefixes = listOf("De", "Duc de", "Marquis de", "Chevalier de", "Comte de"),
        royalMale = listOf("Bourbon", "Valois", "Capet", "Montmorency", "Lafayette"),
        royalFemale = listOf("Marie-Antoinette", "Catherine", "Anne", "Éléonore", "Marguerite"),
        gamingPrefixes = listOf("Ombre", "Fleur", "Légion", "Spectre", "Mirage", "Éclair"),
        gamingSuffixes = listOf("Lame", "Chasseur", "Vitesse", "Force", "Fantôme")
    )

    // Japan
    val JAPAN = CultureData(
        maleNames = listOf(
            "Haruto", "Yuto", "Sota", "Yuki", "Hayato", "Haruki", "Ryusei", "Kaito",
            "Ren", "Takumi", "Daiki", "Kazuki", "Koki", "Taiga", "Shota", "Kenji",
            "Hiroshi", "Riku", "Sho", "Tsubasa", "Shin", "Naoki", "Daisuke", "Ryota"
        ),
        femaleNames = listOf(
            "Yui", "Rio", "Hina", "Koharu", "Hinata", "Mei", "Mio", "Saki",
            "Yuna", "Kokona", "Akari", "Nanami", "Sakura", "Aoi", "Rin", "Kanon",
            "Miyu", "Honoka", "Ayaka", "Misaki", "Ema", "Riko", "Sara", "Kaede"
        ),
        unisexNames = listOf("Ren", "Aoi", "Hinata", "Kaede", "Sora", "Hikaru", "Makoto", "Akira", "Shinobu", "Nao"),
        surnames = listOf(
            "Sato", "Suzuki", "Takahashi", "Tanaka", "Watanabe", "Ito", "Yamamoto", "Nakamura",
            "Kobayashi", "Kato", "Yoshida", "Yamada", "Sasaki", "Yamaguchi", "Saito", "Matsumoto",
            "Inoue", "Kimura", "Hayashi", "Shimizu", "Yamazaki", "Mori", "Abe", "Ikeda"
        ),
        cuteMale = listOf("Ren-kun", "Yuki", "Koko", "Momo", "Taka", "Pochi"),
        cuteFemale = listOf("Mimi", "Nana", "Kiki", "Riri", "Momo", "Niko", "Yuyu"),
        stylishMale = listOf("Kazuma", "Renji", "Reo", "Arata", "Minato", "Asahi"),
        stylishFemale = listOf("Kiyomi", "Sumire", "Sayuri", "Suzume", "Chiyo", "Hanabi"),
        uniqueMale = listOf("Rintaro", "Kanata", "Ibuki", "Tomoya", "Senna", "Kiyoshi"),
        uniqueFemale = listOf("Hotaru", "Kaguya", "Amaterasu", "Chihiro", "Tsukiko", "Hoshiko"),
        classicMale = listOf("Ichiro", "Saburo", "Goro", "Kenzo", "Tatsuo", "Noboru"),
        classicFemale = listOf("Chiyo", "Fumiko", "Kazuko", "Tomiko", "Setsuko", "Shizuka"),
        royalPrefixes = listOf("Shinno", "Miya", "Lord", "Daimyo"),
        royalMale = listOf("Tokugawa", "Fujiwara", "Minamoto", "Taira", "Oda", "Toyotomi"),
        royalFemale = listOf("Himiko", "Masako", "Sadako", "Kiko", "Aiko", "Michiko"),
        gamingPrefixes = listOf("Ronin", "Kage", "Shinobi", "Katana", "Ryu", "Samurai", "Zero"),
        gamingSuffixes = listOf("Slash", "Blade", "Striker", "Storm", "Shuriken", "Ghost")
    )

    // South Korea
    val KOREA = CultureData(
        maleNames = listOf(
            "Minjun", "Seojun", "Doyun", "Yejun", "Siwoo", "Hajun", "Jiho", "Juwan",
            "Junwoo", "Yujun", "Hyunwoo", "Gunwoo", "Woo-jin", "Sunwoo", "Eunwoo", "Minjae",
            "Taehyung", "Jungkook", "Jimin", "Namjoon", "Yoongi", "Hoseok", "Seokjin", "Donghyun"
        ),
        femaleNames = listOf(
            "Seo-yeon", "Seo-yun", "Ji-woo", "Seo-hyeon", "Min-seo", "Ha-eun", "Ha-yoon", "Ji-a",
            "Ji-min", "Chae-won", "Soo-ah", "Ji-yoon", "Eun-seo", "Da-eun", "Ye-eun", "So-yoon",
            "Jennie", "Jisoo", "Rosé", "Lisa", "IU", "Nayeon", "Sana", "Momo"
        ),
        unisexNames = listOf("Ji-woo", "Si-won", "Min-seo", "Ha-neul", "Se-jin", "Yu-jin", "Ji-an", "Da-som"),
        surnames = listOf(
            "Kim", "Lee", "Park", "Choi", "Jung", "Kang", "Cho", "Yoon",
            "Jang", "Lim", "Han", "Oh", "Seo", "Shin", "Kwon", "Hwang",
            "Ahn", "Song", "Ryu", "Hong", "Ko", "Moon", "Yang", "Son"
        ),
        cuteMale = listOf("Min-i", "Jun-i", "Bin-i", "Woo-ri", "Bomi", "Kookie"),
        cuteFemale = listOf("Hani", "Somi", "Bomi", "Yeri", "Chuu", "Mimi", "Nari"),
        stylishMale = listOf("Eunwoo", "Sunwoo", "Rowoon", "Hyunjin", "Taemin", "Jaehyun"),
        stylishFemale = listOf("Wonyoung", "Karina", "Minji", "Sullyoon", "Kazuha", "Yuna"),
        uniqueMale = listOf("Haneul", "Baram", "Miru", "Garam", "San", "Roa"),
        uniqueFemale = listOf("Areum", "Iseul", "Boram", "Dasom", "Sol", "Bora"),
        classicMale = listOf("Chul-soo", "Young-ho", "Man-seok", "Dong-soo", "Kwang-ho"),
        classicFemale = listOf("Young-sook", "Soon-ja", "Myung-hee", "Ok-ja", "Eun-sook"),
        royalPrefixes = listOf("Prince", "Crown Prince", "Lord", "Daegun"),
        royalMale = listOf("Sejong", "Gwanghaegun", "Jeongjo", "Taejo", "Gojong"),
        royalFemale = listOf("Myeongseong", "Inhyeon", "Seondeok", "Sinwon", "Hwang"),
        gamingPrefixes = listOf("K-God", "Faker", "Seoul", "Challenger", "Ace", "Nova"),
        gamingSuffixes = listOf("Carry", "Sniper", "Viper", "Phantom", "Shot")
    )

    // China
    val CHINA = CultureData(
        maleNames = listOf(
            "Yize", "Haoyu", "Yuxuan", "Yichen", "Zihan", "Haoran", "Junhao", "Tianyu",
            "Mingze", "Zixuan", "Jian", "Wei", "Lei", "Tao", "Peng", "Bo",
            "Qiang", "Chao", "Hao", "Liang", "Dong", "Xin", "Feng", "Gang"
        ),
        femaleNames = listOf(
            "Yinuo", "Xinyi", "Yihan", "Zihan", "Yutong", "Kexin", "Mengqi", "Ruoxi",
            "Ting", "Fang", "Jing", "Yan", "Li", "Na", "Min", "Juan",
            "Xiu", "Yun", "Ling", "Hong", "Ping", "Hui", "Lan", "Qian"
        ),
        unisexNames = listOf("Chen", "Yan", "Lin", "Hui", "Jia", "Tian", "An", "Yuan", "Rong", "Bo"),
        surnames = listOf(
            "Wang", "Li", "Zhang", "Liu", "Chen", "Yang", "Huang", "Zhao",
            "Wu", "Zhou", "Xu", "Sun", "Ma", "Zhu", "Hu", "Guo",
            "He", "Gao", "Lin", "Luo", "Zheng", "Liang", "Xie", "Song"
        ),
        cuteMale = listOf("Xiaobao", "Tian-tian", "Ming-ming", "Dong-dong", "Hao-hao"),
        cuteFemale = listOf("Bao-bao", "Ting-ting", "Ni-ni", "Mei-mei", "Duo-duo", "Yuan-yuan"),
        stylishMale = listOf("Yichen", "Zixuan", "Haochen", "Junxi", "Yuran", "Zihan"),
        stylishFemale = listOf("Ruoxi", "Xinyue", "Ziqing", "Yuxin", "Qianxun", "Menghan"),
        uniqueMale = listOf("Qianfan", "Canghai", "Zhuoyue", "Fengrui", "Lingyun", "Boming"),
        uniqueFemale = listOf("Dieyi", "Luoxia", "Qinghe", "Wanqing", "Ziyun", "Yulian"),
        classicMale = listOf("Dehua", "Guoqiang", "Jianguo", "Zhonghua", "Weimin", "Xiangdong"),
        classicFemale = listOf("Xiuying", "Guilan", "Shuzhen", "Yulan", "Guifen", "Cuiping"),
        royalPrefixes = listOf("Huangdi", "Wangye", "Taizi", "Daren"),
        royalMale = listOf("Qianlong", "Kangxi", "Yongzheng", "Taizong", "Hanwu"),
        royalFemale = listOf("Wu Zetian", "Cixi", "Xiaozhuang", "Yang Guifei", "Diaochan"),
        gamingPrefixes = listOf("Dragon", "Shadow", "Warlord", "Panda", "Dynasty", "Mythic"),
        gamingSuffixes = listOf("Fury", "Strike", "Blade", "Legend", "Immortal", "Emperor")
    )

    // Saudi Arabia / UAE / Qatar / Egypt / Arabic
    val ARABIC = CultureData(
        maleNames = listOf(
            "Mohammed", "Ali", "Ahmed", "Omar", "Youssef", "Ibrahim", "Khaled", "Zaid",
            "Tariq", "Sultan", "Mansoor", "Fahad", "Nasser", "Saud", "Rashed", "Hamad",
            "Majid", "Zubair", "Amir", "Kareem", "Samir", "Rami", "Mustafa", "Bassem"
        ),
        femaleNames = listOf(
            "Fatima", "Maryam", "Aisha", "Noor", "Sara", "Lina", "Reem", "Dana",
            "Hessa", "Salma", "Mona", "Layla", "Yasmin", "Hala", "Rania", "Farida",
            "Ghada", "Amira", "Dina", "Jana", "Nourhan", "Zainab", "Habiba", "Maya"
        ),
        unisexNames = listOf("Noor", "Nour", "Rayan", "Bayan", "Shams", "Samar", "Amal", "Diaa", "Iman"),
        surnames = listOf(
            "Al-Mansoor", "Al-Fassi", "Al-Hashemi", "Al-Ghamdi", "Al-Otaibi", "Al-Masri",
            "Al-Dosari", "Al-Shehri", "Al-Qasimi", "Al-Maktoum", "Al-Nahyan", "Al-Thani",
            "Haddad", "Khalil", "Farouk", "Mahmoud", "Tawfiq", "Soliman", "Khoury", "Nader"
        ),
        cuteMale = listOf("Hamudi", "Aloush", "Zizo", "Simba", "Mido", "Tito"),
        cuteFemale = listOf("Mimi", "Lola", "Didi", "Riri", "Toto", "Kiki", "Nunu"),
        stylishMale = listOf("Zayn", "Fares", "Tamim", "Jad", "Kenan", "Samer", "Raffi"),
        stylishFemale = listOf("Celine", "Talia", "Nadine", "Lana", "Karma", "Yara", "Kinda"),
        uniqueMale = listOf("Qais", "Ghaith", "Arkan", "Baraa", "Taym", "Bahaa"),
        uniqueFemale = listOf("Razan", "Joud", "Aya", "Mayar", "Rawan", "Saja"),
        classicMale = listOf("Abdel-Rahman", "Mahmoud", "Salahuddin", "Nuruddin", "Faruq"),
        classicFemale = listOf("Khadija", "Umm Kulthum", "Samira", "Fawzia", "Nemat"),
        royalPrefixes = listOf("Sheikh", "Prince", "Emir", "Sultan", "Sayyid"),
        royalMale = listOf("Al-Saud", "Al-Sabah", "Al-Khalifa", "Al-Busaidi", "Al-Hussein"),
        royalFemale = listOf("Sheikha", "Princess", "Lalla", "Malika", "Sultana"),
        gamingPrefixes = listOf("Desert", "Sultan", "Falcon", "Mirage", "Oasis", "Scorpion"),
        gamingSuffixes = listOf("Sniper", "Warrior", "Ghost", "Strike", "Fury")
    )

    // Brazil / Portugal / Spain / Latin America
    val IBERIAN_LATIN = CultureData(
        maleNames = listOf(
            "Mateo", "Santiago", "Lucas", "Gabriel", "Leonardo", "Enzo", "Matias", "Sebastian",
            "Thiago", "Diego", "Alejandro", "Bernardo", "Rafael", "Rodrigo", "Felipe", "Gustavo",
            "Carlos", "Javier", "Manuel", "Bruno", "Eduardo", "Adrian", "Fernando", "Alvaro"
        ),
        femaleNames = listOf(
            "Sofia", "Valentina", "Isabella", "Camila", "Lucia", "Martina", "Elena", "Valeria",
            "Mariana", "Helena", "Alice", "Laura", "Beatriz", "Manuela", "Luana", "Catalina",
            "Juliana", "Carolina", "Gabriela", "Bianca", "Clara", "Larissa", "Paula", "Daniela"
        ),
        unisexNames = listOf("Alex", "Cris", "Dani", "Guadalupe", "Cruz", "Pau", "Ariel", "Noa"),
        surnames = listOf(
            "Silva", "Santos", "Oliveira", "Souza", "Rodrigues", "Ferreira", "Alves", "Pereira",
            "Garcia", "Rodriguez", "Gonzalez", "Fernandez", "Lopez", "Martinez", "Sanchez", "Perez",
            "Gomez", "Martin", "Jimenez", "Ruiz", "Hernandez", "Diaz", "Moreno", "Alvarez"
        ),
        cuteMale = listOf("Tico", "Zeca", "Nico", "Lolo", "Pepe", "Tito", "Beto"),
        cuteFemale = listOf("Loli", "Tata", "Bibi", "Cata", "Pepa", "Gabi", "Mimi"),
        stylishMale = listOf("Lorenzo", "Matteo", "Dante", "Breno", "Alonso", "Vinicius"),
        stylishFemale = listOf("Antonella", "Giovanna", "Constanza", "Agustina", "Isadora", "Fiorella"),
        uniqueMale = listOf("Baltasar", "Iker", "Thiago", "Gael", "Joaquim", "Dario"),
        uniqueFemale = listOf("Itziar", "Maitena", "Soraya", "Nayara", "Dulce", "Aitana"),
        classicMale = listOf("Antonio", "Jose", "Francisco", "Joao", "Pedro", "Alfonso"),
        classicFemale = listOf("Maria", "Carmen", "Concepcion", "Dolores", "Teresa", "Lourdes"),
        royalPrefixes = listOf("Don", "Dom", "Duque de", "Conde de", "Infante"),
        royalMale = listOf("Bragança", "Borbón", "Castilla", "Aragón", "Medina"),
        royalFemale = listOf("Isabel", "Leonor", "Carlota", "Teresa Cristina", "Leopoldina"),
        gamingPrefixes = listOf("El", "Jaguar", "Fuego", "Tormenta", "Matador", "Sombra"),
        gamingSuffixes = listOf("Pro", "Gamer", "Sniper", "Strike", "Ninja")
    )

    // Russia / Ukraine / Slavic
    val SLAVIC = CultureData(
        maleNames = listOf(
            "Alexander", "Maxim", "Dmitry", "Ivan", "Mikhail", "Artem", "Nikita", "Daniil",
            "Andrei", "Kirill", "Yaroslav", "Bogdan", "Vladislav", "Timofey", "Ilya", "Roman",
            "Sergei", "Oleg", "Pavel", "Viktor", "Denis", "Anton", "Konstantin", "Stanislav"
        ),
        femaleNames = listOf(
            "Anastasia", "Maria", "Daria", "Anna", "Polina", "Victoria", "Ekaterina", "Ksenia",
            "Sofia", "Alisa", "Veronika", "Arina", "Valeria", "Margarita", "Diana", "Olga",
            "Elena", "Tatiana", "Natalia", "Yulia", "Svetlana", "Irina", "Oksana", "Lyudmila"
        ),
        unisexNames = listOf("Sasha", "Zhenya", "Valera", "Slava", "Vanya", "Misha", "Pasha", "Yura"),
        surnames = listOf(
            "Ivanov", "Smirnov", "Kuznetsov", "Popov", "Vasiliev", "Petrov", "Sokolov", "Mikhailov",
            "Novikov", "Fedorov", "Morozov", "Volkov", "Alekseev", "Lebedev", "Semenov", "Egorov",
            "Shevchenko", "Boyko", "Kovalenko", "Bondarenko", "Tkachenko", "Kravchenko", "Oliynyk"
        ),
        cuteMale = listOf("Sashka", "Vanyusha", "Mishka", "Alyosha", "Dima", "Toshka"),
        cuteFemale = listOf("Nastenka", "Anyuta", "Mashunya", "Olenka", "Polinochka", "Katya"),
        stylishMale = listOf("Matvey", "Svyatoslav", "Miron", "Platon", "Lev", "Demid"),
        stylishFemale = listOf("Miroslava", "Vasilisa", "Esenia", "Milana", "Taisia", "Zlata"),
        uniqueMale = listOf("Rostislav", "Vsevolod", "Dobrynya", "Radomir", "Ostap", "Lubomir"),
        uniqueFemale = listOf("Yaroslava", "Snezana", "Bozhena", "Ladomira", "Zvenislava", "Lyubava"),
        classicMale = listOf("Vladimir", "Nikolai", "Gennady", "Yuri", "Boris", "Anatoly"),
        classicFemale = listOf("Valentina", "Tamara", "Nadezhda", "Vera", "Lyubov", "Galina"),
        royalPrefixes = listOf("Knyaz", "Count", "Tsar", "Grand Duke"),
        royalMale = listOf("Romanov", "Rurik", "Golitsyn", "Yusupov", "Sheremetev"),
        royalFemale = listOf("Catherine", "Anastasia", "Olga", "Tatiana", "Maria"),
        gamingPrefixes = listOf("Red", "Bear", "Siberian", "Cossack", "Tsar", "Frost"),
        gamingSuffixes = listOf("Sniper", "Beast", "Iron", "Vortex", "Slayer")
    )

    // Fallback/Generic mapping
    fun getPoolForCountry(countryId: String): CultureData {
        return when (countryId.uppercase()) {
            "BD" -> BANGLADESH
            "IN", "NP", "LK" -> INDIA
            "PK" -> PAKISTAN
            "US", "CA", "GB", "AU", "NZ", "IE" -> WESTERN_ANGLO
            "DE", "AT", "CH", "NL", "BE", "SE", "NO", "DK", "FI" -> GERMANIC
            "FR" -> FRENCH
            "JP" -> JAPAN
            "KR" -> KOREA
            "CN", "SG", "TW", "HK" -> CHINA
            "SA", "AE", "QA", "EG", "TR", "ID", "MY" -> ARABIC
            "BR", "PT", "ES", "MX", "AR", "CO", "CL", "PE" -> IBERIAN_LATIN
            "RU", "UA", "PL" -> SLAVIC
            else -> WESTERN_ANGLO
        }
    }
}
