package id.kotlin.screeningtest_mobiledeveloperintern

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


class Guest : RealmObject {
    @SerializedName("id")
    @PrimaryKey
    var id = 0

    @SerializedName("name")
    var nama: String

    @SerializedName("birthdate")
    var birthDate: String? = null

    constructor() {
        nama = ""
    }

    constructor(id: Int, nama: String, birthDate: String?) {
        this.id = id
        this.nama = nama
        this.birthDate = birthDate
    }
}