package mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.ui.insertar

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.Inventario
import mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.databinding.ActivityInsertarInventarioBinding
import mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.ui.dialog.DatePickerFragment
import java.text.SimpleDateFormat
import java.time.LocalDateTime

class InsertarInventario : AppCompatActivity() {
    lateinit var binding: ActivityInsertarInventarioBinding
    var inventario = Inventario(this)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertarInventarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.insertar.setOnClickListener {
            var codigobarras=binding.codigobarras.text.toString()
            var tipoequipo=binding.tipoequipo.text.toString()
            var caracteristicas=binding.caracteristicas.text.toString()
            var fecha=binding.fecha.text.toString()
            val df = SimpleDateFormat("yyyy-MM-dd")
            var fechacompra=df.parse(fecha).time

            inventario.codigobarras=codigobarras
            inventario.tipoequipo=tipoequipo
            inventario.caracteristicas=caracteristicas
            inventario.fechacompra=fechacompra

            if(inventario.insertar()){
                AlertDialog.Builder(this)
                    .setMessage("Datos insertados correctamente")
                    .show()
                binding.codigobarras.text.clear()
                binding.tipoequipo.text.clear()
                binding.caracteristicas.text.clear()
                binding.fecha.text.clear()
            }else{
                AlertDialog.Builder(this)
                    .setMessage("Error al insertar")
                    .show()
            }
        }
        binding.fecha.setOnClickListener {
            showDatePickerDialog()
        }
    }
    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            //val selectedDate = day.toString() + " / " + (month + 1) + " / " + year
            val selectedDate = year.toString()+"-"+ (month + 1) +"-"+day
            binding.fecha.setText(selectedDate)
        })

        newFragment.show(supportFragmentManager, "datePicker")
    }
}