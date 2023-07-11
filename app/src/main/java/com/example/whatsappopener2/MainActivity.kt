package com.example.whatsappopener2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.isDigitsOnly
import com.example.whatsappopener2.ui.theme.WhatsappOpener2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsappOpener2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
        var number:String="0"
        if(intent.action== Intent.ACTION_PROCESS_TEXT){
            number= intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()

        }
        if(number.isDigitsOnly()){
            startWhatsapp(number)
        }else{
            Toast.makeText(this,"Please check the number", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startWhatsapp(number: String) {
        val intent=Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
        val data:String = if(number[0]=='+'){
            number.substring(1)
        }else if(number.length==10){
            "91" +number
        }else{
            number
        }
        intent.data= Uri.parse("https://wa.me/$data")
        if(packageManager.resolveActivity(intent,0) !=null){
            startActivity(intent)
        }else {
            Toast.makeText(this, "Please install whatsapp", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WhatsappOpener2Theme {
        Greeting("Android")
    }
}