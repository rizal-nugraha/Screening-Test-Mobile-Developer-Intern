package id.kotlin.screeningtest_mobiledeveloperintern


class Guest {
    var image: Int
    var nama: String
    var birthDate: String? = null

    constructor() {
        image = R.drawable.guest
        nama = ""
    }

    constructor(image: Int, nama: String, birthDate: String?) {
        this.image = image
        this.nama = nama
        this.birthDate = birthDate
    }
}