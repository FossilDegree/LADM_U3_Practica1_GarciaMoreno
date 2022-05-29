package mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.ui.actualizar

import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.Inventario
import mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.databinding.ActivityActualizarInventarioBinding
import mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.ui.dialog.DatePickerFragment
import java.text.SimpleDateFormat
import java.util.*

class ActualizarInventario : AppCompatActivity() {
    lateinit var binding:ActivityActualizarInventarioBinding
    var inventario = Inventario(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarInventarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buscar.setOnClickListener {

            inventario=inventario.mostrarCodigo(binding.codigobarras.text.toString())
            if(!inventario.codigobarras.equals("")) {
                binding.tipoequipo.setText(inventario.tipoequipo)
                binding.caracteristicas.setText(inventario.caracteristicas)
                val format = SimpleDateFormat("yyyy-MM-dd")
                binding.fecha.setText(format.format(Date(inventario.fechacompra)))


                binding.tipoequipo.setEnabled(true)
                binding.caracteristicas.setEnabled(true)
                binding.fecha.setEnabled(true)
                binding.actualizar.setEnabled(true)
                binding.eliminar.setEnabled(true)
                binding.limpiar.setEnabled(true)
            }


        }
        binding.actualizar.setOnClickListener {
            inventario.codigobarras=binding.codigobarras.text.toString()
            inventario.tipoequipo=binding.tipoequipo.text.toString()
            inventario.caracteristicas=binding.caracteristicas.text.toString()
            var fecha=binding.fecha.text.toString()
            val df = SimpleDateFormat("yyyy-MM-dd")
            inventario.fechacompra=df.parse(fecha).time
            if(inventario.actualizar()) {
                binding.codigobarras.text.clear()
                binding.tipoequipo.text.clear()
                binding.caracteristicas.text.clear()
                binding.fecha.text.clear()

                binding.tipoequipo.setEnabled(false)
                binding.caracteristicas.setEnabled(false)
                binding.fecha.setEnabled(false)
                binding.actualizar.setEnabled(false)
                binding.eliminar.setEnabled(false)
                binding.limpiar.setEnabled(false)

                AlertDialog.Builder(this)
                    .setTitle("Atención")
                    .setMessage("Registro actualizado")
                    .setPositiveButton("Aceptar",{i,d->})
                    .show()
            }else{
                AlertDialog.Builder(this)
                    .setTitle("Atención")
                    .setMessage("Error al actualizar")
                    .setPositiveButton("Aceptar",{i,d->})
                    .show()
            }
        }
        binding.eliminar.setOnClickListener {
            if(inventario.eliminar()){
                binding.codigobarras.text.clear()
                binding.tipoequipo.text.clear()
                binding.caracteristicas.text.clear()
                binding.fecha.text.clear()

                binding.tipoequipo.setEnabled(false)
                binding.caracteristicas.setEnabled(false)
                binding.fecha.setEnabled(false)
                binding.actualizar.setEnabled(false)
                binding.eliminar.setEnabled(false)
                binding.limpiar.setEnabled(false)

                inventario.codigobarras=""
                inventario.tipoequipo=""
                inventario.caracteristicas=""
                inventario.fechacompra=0L

                AlertDialog.Builder(this)
                    .setTitle("Atención")
                    .setMessage("Registro actualizado")
                    .setPositiveButton("Aceptar",{i,d->})
                    .show()
            }else{
                AlertDialog.Builder(this)
                    .setTitle("Atención")
                    .setMessage("Error al eliminar")
                    .setPositiveButton("Aceptar",{i,d->})
                    .show()
            }
        }
        binding.limpiar.setOnClickListener {
            binding.codigobarras.text.clear()
            binding.tipoequipo.text.clear()
            binding.caracteristicas.text.clear()
            binding.fecha.text.clear()

            binding.tipoequipo.setEnabled(false)
            binding.caracteristicas.setEnabled(false)
            binding.fecha.setEnabled(false)
            binding.actualizar.setEnabled(false)
            binding.eliminar.setEnabled(false)
            binding.limpiar.setEnabled(false)

            inventario.codigobarras=""
            inventario.tipoequipo=""
            inventario.caracteristicas=""
            inventario.fechacompra=0L
            //binding.codigobarras.set
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