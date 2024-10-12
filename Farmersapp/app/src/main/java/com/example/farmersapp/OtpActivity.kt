package com.example.farmersapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class OtpActivity: AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var verificationId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.otp_screen)

        val textmobile = findViewById<TextView>(R.id.textView9)

        verificationId = intent.getStringExtra("verificationId").toString()
        var mobileNumber = intent.getStringExtra("mobile_number")
        textmobile.text=mobileNumber

        var editTextOtp1 = findViewById<EditText>(R.id.editotp)
        var buttonVerifyOtp = findViewById<Button>(R.id.buttonverify)



        buttonVerifyOtp.setOnClickListener{
//            val otp = "${editTextOtp1.text}${editTextOtp2.text}${editTextOtp3.text}${editTextOtp4.text}"
//
//            if (otp.length == 4) {
//                // Proceed with OTP verification
//                Toast.makeText(this, "OTP Verified: $otp", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "Please enter a valid 4-digit OTP", Toast.LENGTH_SHORT).show()
//            }
            val otp = editTextOtp1.text.toString().trim()

            if (otp.length == 6) {
                verifyCode(otp)
            } else {
                Toast.makeText(this, "Please enter a valid 6-digit OTP", Toast.LENGTH_SHORT).show()
            }

        }
    }
    private fun verifyCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        signInWithCredential(credential)
    }
    private fun signInWithCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                    // Navigate to the main screen
                } else {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
    }
}