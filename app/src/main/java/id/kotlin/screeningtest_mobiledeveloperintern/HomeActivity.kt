package id.kotlin.screeningtest_mobiledeveloperintern

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText


class HomeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val nextButton = findViewById<View>(R.id.nextButton) as Button
        nextButton.setOnClickListener { v -> sendNama(v) }
    }

    private fun sendNama(view: View?) {
        val namaEditText = findViewById<View>(R.id.namaEditText) as EditText
        val nama = namaEditText.text.toString()
        val intent = Intent(this@HomeActivity, MainActivity::class.java)
        intent.putExtra("NAMA", nama)
        startActivity(intent)

    }

    fun checkPalindromeName(name: String?) {
        if (name?.let { isPalindrome(it) } == true) {
            showPalindromeStatus("isPalindrome")
        } else {
            showPalindromeStatus("not palindrome")
        }
    }

    private fun showPalindromeStatus(s: String) {
        AlertDialog.Builder(applicationContext)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    private fun isPalindrome(name: String): Boolean {
        val noSpace = name.toLowerCase().replace(" ", "")
        val words = noSpace.toCharArray()
        var i = 0
        var x = words.size - 1
        while (x > i) {
            if (words[i] != words[x]) {
                return false
            }
            i++
            x--
        }
        return true
    }

}