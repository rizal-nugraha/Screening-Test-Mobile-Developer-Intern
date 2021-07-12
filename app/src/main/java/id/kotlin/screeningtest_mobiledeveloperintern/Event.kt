package id.kotlin.screeningtest_mobiledeveloperintern

class Event {
    var image: Int
    var nama: String
    var tanggal: String
    var tags: ArrayList<String>

    constructor() {
        image = R.drawable.event
        nama = ""
        tanggal = ""
        tags = ArrayList()
    }

    constructor(image: Int, nama: String, tanggal: String, tags: ArrayList<String>) {
        this.image = image
        this.nama = nama
        this.tanggal = tanggal
        this.tags = tags
    }
}