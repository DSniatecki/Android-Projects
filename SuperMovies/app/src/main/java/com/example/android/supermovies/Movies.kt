package com.example.android.supermovies

import com.google.gson.Gson

val JSON_SERIALIZER: Gson = Gson()


data class Image(val resourceId: Int)

data class Actor(val name: String, val image: Image)

enum class Category(val color: String, val order: Int) {
    HORROR("#c70404", 1),
    COMEDY("#d6a100", 2),
    ACTION("#0d7bdb", 3),
    THRILLER("#dbdbd9", 4)
}


data class Movie(
        val title: String,
        val category: Category,
        val description: String,
        val image: Image,
        val actors: Collection<Actor>,
        val sceneImages: Collection<Image>
)


data class ListMovie(val movie: Movie, var isFavorite: Boolean)

val DI_CARPIO = Actor("Leonardo DiCaprio", Image(R.drawable.leonardo_dicaprio))

val MOVIES: Collection<Movie> = listOf(
        Movie(
                "Fast and furious 7",
                Category.ACTION,
                "" +
                        "“Furious 7” is a glorious overcompensation, a film so concerned about its rampant" +
                        " machismo that the casual viewer might miss how it Tokyo-drifts atop soap opera" +
                        " bubbles. Like Lee Daniels' hit TV drama “Empire,” “Furious 7” is stuffed with situations" +
                        " that require go-for-broke absurdity, but even Daniels and his nighttime soap predecessor" +
                        " Aaron Spelling would pause before attempting the level of “get the f—k outta here!” style " +
                        "shenanigans director James Wan and writer Chris Morgan employ.",
                Image(R.drawable.fast_and_furious_7_cover),
                listOf(
                        Actor("Vin Petrol", Image(R.drawable.vin_petrol)),
                        Actor("Paul Walker", Image(R.drawable.paul_walker))
                ),
                listOf(
                        Image(R.drawable.fast_and_furious_7_screen1),
                        Image(R.drawable.fast_and_furious_7_screen2)
                )
        ),
        Movie(
                "Harry Potter and the Goblet of Fire",
                Category.COMEDY,
                "The story begins fifty years before the present day, with a description of how the Riddle family was mysteriously killed at supper, and their groundsman, Frank Bryce, was suspected of the crime, then declared innocent. Frank Bryce, now an elderly man, wakes in the night to see a light in the window of the abandoned Riddle House. He investigates and overhears Voldemort and Wormtail plotting to kill a boy named Harry Potter. Voldemort takes note of him and kills him on the spot. Harry Potter wakes up in the night with a throbbing pain in the scar Voldemort gave him. He worries that Voldemort is nearby, and he writes to Sirius Black, his godfather, mentioning the pain in his scar.",
                Image(R.drawable.harry_potter_cover),
                listOf(
                        Actor("Daniel Radcliffe", Image(R.drawable.daniel_radcliffe)),
                        Actor("Emma Watson", Image(R.drawable.emma_watson))
                ),
                listOf(
                        Image(R.drawable.harry_potter_screen1),
                        Image(R.drawable.harry_potter_screen2)
                )
        ),
        Movie(
                "The Wolf of Wall Street",
                Category.COMEDY,
                "Martin Scorsese's \"The Wolf of Wall Street\" is abashed and shameless, exciting and exhausting, disgusting and illuminating; it's one of the most entertaining films ever made about loathsome men. Its star Leonardo DiCaprio has compared it to the story of the Roman emperor Caligula, and he's not far off the mark. Adapted by Terence Winter from the memoir by stockbroker Jordan Belfort, who oozed his way into a fortune in the 1980s and '90s, this is an excessive film about excess, and a movie about appetites whose own appetite for compulsive pleasures seems bottomless. It runs three hours, and was reportedly cut down from four by Scorsese's regular editor Thelma Schoonmaker. It's a testament to Scorsese and Winter and their collaborators that one could imagine watching these cackling swine for five hours, or ten, while still finding them fascinating, and our own fascination with them disturbing. This is a reptilian brain movie. Every frame has scales. ",
                Image(R.drawable.the_wolf_of_wall_street_cover),
                listOf(
                        DI_CARPIO,
                        Actor("Margot Robbie", Image(R.drawable.margot_robbie))
                ),
                listOf(
                        Image(R.drawable.the_wolf_of_wall_street_screen1),
                        Image(R.drawable.the_wolf_of_wall_street_screen2)
                )
        ),
        Movie(
                "World War Z",
                Category.ACTION,
                "Life for former United Nations investigator Gerry Lane and his family seems content. Suddenly, the world is plagued by a mysterious infection turning whole human populations into rampaging mindless zombies. After barely escaping the chaos, Lane is persuaded to go on a mission to investigate this disease. What follows is a perilous trek around the world where Lane must brave horrific dangers and long odds to find answers before human civilization falls.",
                Image(R.drawable.world_war_z_cover),
                listOf(
                        Actor("Brad Pitt", Image(R.drawable.brad_pitt)),
                        Actor("Mireille Enos", Image(R.drawable.mireille_enos))
                ),
                listOf(
                        Image(R.drawable.world_war_z_screen1),
                        Image(R.drawable.world_war_z_screen2)
                )
        ),
        Movie(
                "The Texas Chain Saw Massacre",
                Category.HORROR,
                "In the summer of 1973, newbie director Tobe Hooper—who passed away on August 26, 2017 at the age of 74—and a group of unknown actors ventured out into the Central Texas heat to make a horror movie. Braving blistering temperatures, on-set injuries, and a shoestring budget, they produced one of the most terrifying motion pictures ever made. More than four decades after its release, The Texas Chainsaw Massacre still shocks and thrills audiences with its realistic imagery, unhinged tone, and “based on a true story” marketing—and its status as one of the ultimate cult classics shows no signs of fading. Not bad for a little film that drove the cast and crew insane during production. From marathon shooting days to flying chainsaws to mafia money problems, here are 20 facts about one of the greatest slasher films of all time.",
                Image(R.drawable.texas_chainsaw_massacre_cover),
                listOf(
                        Actor("Allen Danziger", Image(R.drawable.allen_danziger)),
                        Actor("Paul A. Partain", Image(R.drawable.paul_partain))
                ),
                listOf(
                        Image(R.drawable.texas_chainsaw_massacre_screen1),
                        Image(R.drawable.texas_chainsaw_massacre_screen2)
                )
        ),
        Movie(
                "Shutter Island 2010",
                Category.THRILLER,
                "In 1954, up-and-coming U.S. marshal Teddy Daniels is assigned to investigate the disappearance of a patient from Boston's Shutter Island Ashecliffe Hospital. He's been pushing for an assignment on the island for personal reasons, but before long he thinks he's been brought there as part of a twisted plot by hospital doctors whose radical treatments range from unethical to illegal to downright sinister. Teddy's shrewd investigating skills soon provide a promising lead, but the hospital refuses him access to records he suspects would break the case wide open. As a hurricane cuts off communication with the mainland, more dangerous criminals \"escape\" in the confusion, and the puzzling, improbable clues multiply, Teddy begins to doubt everything - his memory, his partner, even his own sanity.",
                Image(R.drawable.shutter_island_cover),
                listOf(
                        DI_CARPIO,
                        Actor("Mark Ruffalo", Image(R.drawable.mark_ruffalo))),
                listOf(
                        Image(R.drawable.shutter_island_screen1),
                        Image(R.drawable.shutter_island_screen2)
                )
        )
)


