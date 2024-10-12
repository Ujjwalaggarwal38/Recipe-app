package com.example.farmersapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class SignUpScreen: AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        auth = FirebaseAuth.getInstance()

        val button1 = findViewById<FloatingActionButton>(R.id.language)
        val button2 = findViewById<FloatingActionButton>(R.id.back1)
        val button3 = findViewById<FloatingActionButton>(R.id.forwardbutton)
        val button4 = findViewById<Button>(R.id.loginbutton)
        var editTextMobile = findViewById<EditText>(R.id.phonetext)


        button2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        button3.setOnClickListener {
            var mobileNumber = editTextMobile.text.toString().trim()
//            val intent = Intent(this, OtpActivity2::class.java)
//            intent.putExtra("mobile_number",mobileNumber)
//            startActivity(intent)

            if (mobileNumber.isNotEmpty()) {
                startPhoneNumberVerification(mobileNumber)
            } else {
                Toast.makeText(this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show()
            }
        }

        button4.setOnClickListener {
            val intent = Intent(this, FarmersScreen::class.java)
            startActivity(intent)
        }



    }
    private fun startPhoneNumberVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+91$phoneNumber") // Adjust country code as necessary
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    // Auto-retrieval scenario, you might skip the OTP entry here
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Toast.makeText(this@SignUpScreen, e.message, Toast.LENGTH_LONG).show()
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    val intent = Intent(this@SignUpScreen, OtpActivity2::class.java)
                    intent.putExtra("verificationId", verificationId)
                    intent.putExtra("mobile_number", phoneNumber)
                    startActivity(intent)
                }
            }).build()

        PhoneAuthProvider.verifyPhoneNumber(options)}
}