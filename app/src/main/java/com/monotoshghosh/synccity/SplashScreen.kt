package com.monotoshghosh.synccity

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.bumptech.glide.Glide
import com.monotoshghosh.synccity.databinding.ActivityContactOtherDeptBinding
import com.monotoshghosh.synccity.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
//        enableEdgeToEdge()
        setContentView(binding.root)

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        window.statusBarColor = Color.TRANSPARENT
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        hideNavigationBar()

        // Set up the VideoView
        val videoView: VideoView = binding.videoView
        val videoPath = "android.resource://" + packageName + "/" + R.raw.splashscrvideo
        val uri = Uri.parse(videoPath)
        videoView.setVideoURI(uri)

        // Start playing the video
        videoView.start()

        // Transition to the main activity after the video finishes (3 seconds)
        videoView.setOnCompletionListener {
            intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }


//        // Load and apply the animation to the TextView
//        val teamRbtTextView = binding.root.findViewById<TextView>(R.id.textViewSplashScr)
//        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in_scale)
//        teamRbtTextView.startAnimation(animation)


        // SPLASH SCREEN
//        Handler().postDelayed({
//            intent = Intent(this,MainActivity::class.java   )
//            startActivity(intent)
//            finish()
//        },2000)
    }

    private fun hideNavigationBar() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.navigationBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}
