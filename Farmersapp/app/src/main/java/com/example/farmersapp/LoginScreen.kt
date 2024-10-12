package com.example.farmersapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class LoginScreen: AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        auth = FirebaseAuth.getInstance()

        val button1 = findViewById<FloatingActionButton>(R.id.back)
        var editTextMobile = findViewById<EditText>(R.id.editTextPhone);
        val buttongetOtp = findViewById<Button>(R.id.buttonotp);


        button1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        buttongetOtp.setOnClickListener {
            var mobileNumber = editTextMobile.text.toString().trim()
            if (mobileNumber.isNotEmpty()) {
//                val intent = Intent(this, OtpActivity::class.java)
//                intent.putExtra("mobile_number", mobileNumber)
//                startActivity(intent)
                startPhoneNumberVerification(mobileNumber)

            } else {
                Toast.makeText(this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun startPhoneNumberVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+91$phoneNumber")
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    // Auto-retrieval scenario, you might skip the OTP entry here
                }
                override fun onVerificationFailed(e: FirebaseException) {
                    Toast.makeText(this@LoginScreen, e.message, Toast.LENGTH_LONG).show()
                }
                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ){
                    val intent = Intent(this@LoginScreen, OtpActivity::class.java)
                    intent.putExtra("verificationId", verificationId)
                    intent.putExtra("mobile_number", phoneNumber)
                    startActivity(intent)
                }
            }).build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

}