package com.hanzeel.iptvandroidstudio

import android.util.Log

class ChannelRepository {

    fun getDefaultChannels(): MutableList<Channel> {
        val defaultChannels = mutableListOf(
            Channel(1, "Pequeradio TV", "Infantil", "https://canadaremar2.todostreaming.es/live/peque-pequetv.m3u8", "https://static.wixstatic.com/media/76b12f_b725806aac4c416da697ccf6a5c6dd83~mv2.png"),
            Channel(2, "Red Bull TV", "Deportes", "https://rbmn-live.akamaized.net/hls/live/590964/BoRB-AT/master.m3u8", "https://image.roku.com/developer_channels/prod/0560cd3757ba28d0525e524fe0d98c1b95721c5ecf5da464eba3084eeed7f36.png"),
            Channel(3, "AKC TV Dogs", "Animales", "https://install.akctvcontrol.com/speed/broadcast/138/desktop-playlist.m3u8", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTLPq9IAKaYCAvNxg2AWKukgyAtvBbMa9GVZQ&s"),
            Channel(4, "NASA TV", "Educativo", "https://nasatv-lh.akamaihd.net/i/NASA_101@319270/master.m3u8", "https://www.nasa.gov/sites/default/files/thumbnails/image/nasa-logo-web-rgb.png"),
            Channel(5, "France 24", "Noticias", "https://static.france24.com/live/F24_EN_LO_HLS/live_web.m3u8", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b9/France_24_logo.svg/1920px-France_24_logo.svg.png"),
            Channel(6, "Bloomberg TV", "Negocios", "https://liveproduseast.global.ssl.fastly.net/btv/desktop/us_live.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Bloomberg_Television.svg/1920px-Bloomberg_Television.svg.png"),
            Channel(7, "Al Jazeera", "Noticias", "https://live-hls-web-aje.getaj.net/AJE/index.m3u8", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f6/Al_Jazeera_English_logo.svg/1920px-Al_Jazeera_English_logo.svg.png"),
            Channel(8, "DW News", "Noticias", "https://dwstream6-lh.akamaihd.net/i/dwstream6_live@124822/index_1_av-p.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/DW_%28TV%29_logo_2012.svg/1024px-DW_%28TV%29_logo_2012.svg.png"),
            Channel(9, "BBC World News", "Noticias", "https://bcoveliveios-i.akamaihd.net/hls/live/2002716/1246961155001/index.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/BBC_World_News_2019.svg/1024px-BBC_World_News_2019.svg.png"),
            Channel(10, "CNBC", "Negocios", "https://live.cnbc.com/cnbc/playlist.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/28/CNBC_logo.svg/1920px-CNBC_logo.svg.png"),
            Channel(11, "Euronews", "Noticias", "https://rakuten-euronews-1-es.samsung.wurl.com/manifest/playlist.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f4/Euronews.svg/1920px-Euronews.svg.png"),
            Channel(12, "RT Español", "Noticias", "https://rt-esp-gumlet.gumlet.io/stream/rt_espanol/index.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6a/RT_%28TV_channel%29_logo.svg/1920px-RT_%28TV_channel%29_logo.svg.png"),
            Channel(13, "NHK World", "Noticias", "https://nhkwlive.akamaized.net/hls/live/2003456/nhkw_english_2/master.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/NHK_World.svg/1920px-NHK_World.svg.png"),
            Channel(14, "Sky News", "Noticias", "https://skynews-clips-live.secure2.footprint.net/1000k.m3u8", "https://upload.wikimedia.org/wikipedia/en/thumb/1/14/Sky_News_logo.svg/1920px-Sky_News_logo.svg.png"),
            Channel(15, "Arirang TV", "Cultura", "https://amdlive-ch02.arirang.com/hls/live/2002841/stream1/playlist.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1f/Arirang_TV_logo_2017.svg/1920px-Arirang_TV_logo_2017.svg.png"),
            Channel(16, "Fashion TV", "Entretenimiento", "https://bcovlive-a.akamaihd.net/d62426e5c6a14114a6a01ab5dd8a0fe1/us-east-1/6151162474001/playlist.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/FashionTV_logo.svg/1920px-FashionTV_logo.svg.png"),
            Channel(17, "MTV Live", "Música", "https://media2.mtvnservices.com/player/prime/mediagen/mtv/playlist.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/40/MTV_Live.svg/1920px-MTV_Live.svg.png"),
            Channel(18, "VH1", "Música", "https://media2.mtvnservices.com/player/prime/mediagen/vh1/playlist.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/VH1_logo.svg/1920px-VH1_logo.svg.png"),
            Channel(19, "National Geographic", "Documentales", "https://ngcdnglobalservice-a.akamaihd.net/nglive/nglive_720p.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c7/National_Geographic_Logo.svg/1920px-National_Geographic_Logo.svg.png"),
            Channel(20, "Discovery Channel", "Documentales", "https://discovery-live-streaming.com/discovery_stream.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Discovery_Channel_logo.svg/1920px-Discovery_Channel_logo.svg.png"),
            Channel(21, "Animal Planet", "Documentales", "https://animalplanet-live-streaming.com/animalplanet_stream.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/92/Animal_Planet_Logo.svg/1920px-Animal_Planet_Logo.svg.png"),
            Channel(22, "Cartoon Network", "Infantil", "https://cartoon-network-live-streaming.com/cartoon_network_stream.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/Cartoon_Network_logo_2010.svg/1920px-Cartoon_Network_logo_2010.svg.png"),
            Channel(23, "Nickelodeon", "Infantil", "https://nickelodeon-live-streaming.com/nickelodeon_stream.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Nickelodeon_Logo_2023.svg/1920px-Nickelodeon_Logo_2023.svg.png"),
            Channel(24, "Disney Channel", "Infantil", "https://disneychannel-live-streaming.com/disney_channel_stream.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/Disney_Channel_Logo.svg/1920px-Disney_Channel_Logo.svg.png"),
            Channel(25, "Fox News", "Noticias", "https://foxnews-live-streaming.com/foxnews_stream.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Fox_News_Channel_logo.svg/1920px-Fox_News_Channel_logo.svg.png"),
            Channel(26, "ESPN", "Deportes", "https://espn-live-streaming.com/espn_stream.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/ESPN_wordmark.svg/1920px-ESPN_wordmark.svg.png"),
            Channel(27, "Fox Sports", "Deportes", "https://foxsports-live-streaming.com/fox_sports_stream.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cd/Fox_Sports_logo.svg/1920px-Fox_Sports_logo.svg.png"),
            Channel(28, "TNT", "Entretenimiento", "https://tnt-live-streaming.com/tnt_stream.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6f/TNT_Logo_2021.svg/1920px-TNT_Logo_2021.svg.png"),
            Channel(29, "HBO", "Películas", "https://hbo-live-streaming.com/hbo_stream.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/17/HBO_logo.svg/1920px-HBO_logo.svg.png"),
            Channel(30, "Showtime", "Películas", "https://showtime-live-streaming.com/showtime_stream.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/07/Showtime_logo.svg/1920px-Showtime_logo.svg.png"),
            Channel(31, "AMC", "Películas", "https://amc-live-streaming.com/amc_stream.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/AMC_logo_2019.svg/1920px-AMC_logo_2019.svg.png"),
            Channel(32, "Syfy", "Ciencia Ficción", "https://syfy-live-streaming.com/syfy_stream.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Syfy_logo.svg/1920px-Syfy_logo.svg.png"),
            Channel(33, "History Channel", "Historia", "https://historychannel-live-streaming.com/history_channel_stream.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/History_Logo.svg/1920px-History_Logo.svg.png"),
            Channel(34, "Lifetime", "Películas", "https://lifetime-live-streaming.com/lifetime_stream.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e4/Lifetime_logo.svg/1920px-Lifetime_logo.svg.png"),
            Channel(35, "AXN", "Acción", "https://axn-live-streaming.com/axn_stream.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/57/AXN_Logo.svg/1920px-AXN_Logo.svg.png"),
            Channel(36, "Paramount Network", "Películas", "https://paramountnetwork-live-streaming.com/paramount_network_stream.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Paramount_Network_logo.svg/1920px-Paramount_Network_logo.svg.png"),
            Channel(37, "Comedy Central", "Comedia", "https://comedycentral-live-streaming.com/comedy_central_stream.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6a/Comedy_Central_2018.svg/1920px-Comedy_Central_2018.svg.png"),
            Channel(38, "Cinemax", "Películas", "https://cinemax-live-streaming.com/cinemax_stream.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6f/Cinemax_2011_logo.svg/1920px-Cinemax_2011_logo.svg.png"),
            Channel(39, "FXX", "Comedia", "https://fxx-live-streaming.com/fxx_stream.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/FXX_Logo.svg/1920px-FXX_Logo.svg.png"),
            Channel(40, "Bravo", "Reality", "https://bravo-live-streaming.com/bravo_stream.m3u8", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Bravo_2017_logo.svg/1920px-Bravo_2017_logo.svg.png")
        )
        Log.d("ChannelRepository", "Cantidad de canales predeterminados: ${defaultChannels.size}")
        return defaultChannels
    }
}
