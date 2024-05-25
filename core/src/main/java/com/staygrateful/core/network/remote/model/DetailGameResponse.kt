package com.staygrateful.core.network.remote.model

data class DetailGameResponse(
    val achievements_count: Int?,
    val added: Int?,
    val added_by_status: AddedByStatus?,
    val additions_count: Int?,
    val alternative_names: List<String?>?,
    val background_image: String?,
    val background_image_additional: String?,
    val clip: Any?,
    val creators_count: Int?,
    val description: String?,
    val description_raw: String?,
    val developers: List<Developer?>?,
    val dominant_color: String?,
    val esrb_rating: EsrbRating?,
    val game_series_count: Int?,
    val genres: List<Genre?>?,
    val id: Int?,
    val metacritic: Int?,
    val metacritic_platforms: List<MetacriticPlatform?>?,
    val metacritic_url: String?,
    val movies_count: Int?,
    val name: String?,
    val name_original: String?,
    val parent_achievements_count: Int?,
    val parent_platforms: List<ParentPlatform?>?,
    val parents_count: Int?,
    val platforms: List<Platform?>?,
    val playtime: Int?,
    val publishers: List<Publisher?>?,
    val rating: Double?,
    val rating_top: Int?,
    val ratings: List<Rating?>?,
    val ratings_count: Int?,
    val reactions: Reactions?,
    val reddit_count: Int?,
    val reddit_description: String?,
    val reddit_logo: String?,
    val reddit_name: String?,
    val reddit_url: String?,
    val released: String?,
    val reviews_count: Int?,
    val reviews_text_count: Int?,
    val saturated_color: String?,
    val screenshots_count: Int?,
    val slug: String?,
    val stores: List<Store?>?,
    val suggestions_count: Int?,
    val tags: List<Tag?>?,
    val tba: Boolean?,
    val twitch_count: Int?,
    val updated: String?,
    val user_game: Any?,
    val website: String?,
    val youtube_count: Int?
) {

    val developersName: String? get() = developers?.filterNotNull()?.mapNotNull { it.name }?.joinToString(separator = ", ")

    data class AddedByStatus(
        val beaten: Int?,
        val dropped: Int?,
        val owned: Int?,
        val playing: Int?,
        val toplay: Int?,
        val yet: Int?
    )

    data class Developer(
        val games_count: Int?,
        val id: Int?,
        val image_background: String?,
        val name: String?,
        val slug: String?
    )

    data class EsrbRating(
        val id: Int?,
        val name: String?,
        val slug: String?
    )

    data class Genre(
        val games_count: Int?,
        val id: Int?,
        val image_background: String?,
        val name: String?,
        val slug: String?
    )

    data class MetacriticPlatform(
        val metascore: Int?,
        val platform: Platform?,
        val url: String?
    ) {
        data class Platform(
            val name: String?,
            val platform: Int?,
            val slug: String?
        )
    }

    data class ParentPlatform(
        val platform: Platform?
    ) {
        data class Platform(
            val id: Int?,
            val name: String?,
            val slug: String?
        )
    }

    data class Platform(
        val platform: Platform?,
        val released_at: String?,
        val requirements: Requirements?
    ) {
        data class Platform(
            val games_count: Int?,
            val id: Int?,
            val image: Any?,
            val image_background: String?,
            val name: String?,
            val slug: String?,
            val year_end: Any?,
            val year_start: Int?
        )

        data class Requirements(
            val minimum: String?,
            val recommended: String?
        )
    }

    data class Publisher(
        val games_count: Int?,
        val id: Int?,
        val image_background: String?,
        val name: String?,
        val slug: String?
    )

    data class Rating(
        val count: Int?,
        val id: Int?,
        val percent: Double?,
        val title: String?
    )

    data class Reactions(
        val `1`: Int?,
        val `10`: Int?,
        val `11`: Int?,
        val `12`: Int?,
        val `13`: Int?,
        val `14`: Int?,
        val `15`: Int?,
        val `16`: Int?,
        val `18`: Int?,
        val `2`: Int?,
        val `20`: Int?,
        val `21`: Int?,
        val `3`: Int?,
        val `4`: Int?,
        val `5`: Int?,
        val `6`: Int?,
        val `7`: Int?,
        val `8`: Int?,
        val `9`: Int?
    )

    data class Store(
        val id: Int?,
        val store: Store?,
        val url: String?
    ) {
        data class Store(
            val domain: String?,
            val games_count: Int?,
            val id: Int?,
            val image_background: String?,
            val name: String?,
            val slug: String?
        )
    }

    data class Tag(
        val games_count: Int?,
        val id: Int?,
        val image_background: String?,
        val language: String?,
        val name: String?,
        val slug: String?
    )
}