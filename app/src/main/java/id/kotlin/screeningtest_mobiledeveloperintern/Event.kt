package id.kotlin.screeningtest_mobiledeveloperintern


class Event {
    var image: Int
    var nama: String
    var tanggal: String

    constructor() {
        image = R.drawable.event
        nama = ""
        tanggal = ""
    }

    constructor(image: Int, nama: String, tanggal: String) {
        this.image = image
        this.nama = nama
        this.tanggal = tanggal
    }
}