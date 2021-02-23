package com.junka.myhero.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.junka.myhero.R
import com.junka.myhero.databinding.ActivityMainBinding

const val RC_SIGN_IN = 1500

class MainActivity : AppCompatActivity() {

    private lateinit var hostFragment: NavHostFragment
    private var user = FirebaseAuth.getInstance().currentUser

    var binding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        setUpNavigation()

        if(user == null){
            tryLogin()
        }

    }

    private fun setUpNavigation() {
        hostFragment = supportFragmentManager.findFragmentById(R.id.mainNavigationFragment) as NavHostFragment
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                // Inicio sesion Success

            } else {
                response?.let { idpResponse ->
                    idpResponse.error?.let {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
                tryLogin()
            }
        }
    }

    private fun tryLogin(){
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.FacebookBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.ic_superhero) // Set logo drawable
                .setTheme(R.style.Theme_MyHero) // Set theme
                .build(),
            RC_SIGN_IN
        )
    }

//    AuthUI.getInstance()
//    .signOut(this)
//    .addOnCompleteListener {
//        // ...
//    }
}