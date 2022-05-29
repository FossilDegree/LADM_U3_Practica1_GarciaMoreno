package mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.ui.insertar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.databinding.ActivityInsertarAsignacionBinding

class InsertarAsignacion : AppCompatActivity() {
    lateinit var binding : ActivityInsertarAsignacionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertarAsignacionBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}