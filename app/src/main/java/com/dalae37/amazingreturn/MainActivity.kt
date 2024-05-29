package com.dalae37.amazingreturn

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<android.widget.Button>(R.id.start).setOnClickListener(View.OnClickListener {
            if (checkPermission()) {
                startReturn()
            }
        })

        findViewById<android.widget.Button>(R.id.permission).setOnClickListener(View.OnClickListener {
            grantPermission()
        })

        findViewById<ImageButton>(R.id.mainTOlicense).setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(applicationContext, LicenseActivity::class.java)
            startActivity(intent)
        })

        findViewById<android.widget.Button>(R.id.instruction).setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(applicationContext, InstructionActivity::class.java)
            startActivity(intent)
        })

    }

    private fun startReturn() {
        val intent: Intent = Intent(applicationContext, ReturnActivity::class.java)
        startService(intent)
    }

    private fun checkPermission(): Boolean {
        if (!Settings.canDrawOverlays(applicationContext)) {
            Toast.makeText(
                applicationContext,
                "권한 버튼을 눌러 앱 위에 그리기 권한과 앱 알림 설정을 허용해주세요.",
                Toast.LENGTH_SHORT
            )
                .show()
            return false
        } else {
            return true
        }
    }

    private fun grantPermission() {
        val intent: Intent = Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            Uri.parse("package:$packageName")
        )
        startActivity(intent)
    }
}