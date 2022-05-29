package mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.ui.consultar

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.Inventario
import mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.databinding.ActivityConsultarInventarioBinding
import mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.databinding.FragmentConsultarBinding
import mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.ui.dialog.DatePickerFragment
import mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.ui.insertar.InsertarInventario
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ConsultarInventario : AppCompatActivity() {
    lateinit var binding:ActivityConsultarInventarioBinding
    var inventario = Inventario(this)
    var listaIDs = ArrayList<String>()
    var tipoconsulta = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultarInventarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mostrartodo.setOnClickListener {
            resultadoConsulta(inventario.mostrarTodos())
            /*
            var inventarioCompleto=inventario.mostrarTodos()
            var resultado = ArrayList<String>()
            resultado.clear()
            var x = ""
            listaIDs.clear()
            val format = SimpleDateFormat("yyyy-MM-dd")
            */

            /*
            for(inv in inventarioCompleto){
                x=""
                x = "CÓDIGO: ${inv.codigobarras}\nTIPO: ${inv.tipoequipo}\nCARACTERÍSTICAS: ${inv.caracteristicas}\nFECHA: ${format.format(Date(inv.fechacompra))}"
                resultado.add(x)
                listaIDs.add(inv.codigobarras)
            }
            */
            //val ventana = Intent(this, ResultadoInventario::class.java)
            //ventana.putExtra("completo",inventarioCompleto)
            //ventana.putExtra("resultado",resultado)
            //ventana.putExtra("codigos",listaIDs)
            //ventana.putExtra("tipoconsulta",tipoconsulta)
            /*
            ventana.putExtra("params",arrayOf(
                binding.codigobarras.text.toString(),
                binding.tipoequipo.text.toString(),
                binding.caracteristicas.text.toString(),
                binding.fecha1.text.toString(),
                binding.fecha2.text.toString()
            ))*/
            //startActivity(ventana)
        }
        binding.consultar.setOnClickListener {
            if(
                !(
                binding.codigobarras.text.isBlank()     &&
                binding.tipoequipo.text.isBlank()       &&
                binding.caracteristicas.text.isBlank()  &&
                binding.fecha1.text.isBlank()           &&
                binding.fecha2.text.isBlank()
                        )
            ) {
                val df = SimpleDateFormat("yyyy-MM-dd")
                var fecha1 = 0L
                var fecha2 = Long.MAX_VALUE
                if (binding.fecha1.text.isNotBlank())
                    fecha1 = df.parse(binding.fecha1.text.toString()).time
                if (binding.fecha2.text.isNotBlank())
                    fecha2 = df.parse(binding.fecha2.text.toString()).time

                var inventarioCompleto = inventario.mostrarParam(
                    binding.codigobarras.text.toString(),
                    binding.tipoequipo.text.toString(),
                    binding.caracteristicas.text.toString(),
                    fecha1.toString(),
                    fecha2.toString()
                )
                resultadoConsulta(inventarioCompleto)
            }

        }
        binding.fecha1.setOnClickListener {
            showDatePickerDialog1()
        }
        binding.fecha2.setOnClickListener {
            showDatePickerDialog2()
        }

    }
    fun resultadoConsulta(inventarioCompleto:ArrayList<Inventario>){
        var resultado = ArrayList<String>()
        var x = ""
        listaIDs.clear()
        val format = SimpleDateFormat("yyyy-MM-dd")
        for(inv in inventarioCompleto){
            x=""
            x = "CÓDIGO: ${inv.codigobarras}\nTIPO: ${inv.tipoequipo}\nCARACTERÍSTICAS: ${inv.caracteristicas}\nFECHA: ${format.format(Date(inv.fechacompra))}"
            resultado.add(x)
            listaIDs.add(inv.codigobarras)
        }
        val ventana = Intent(this, ResultadoInventario::class.java)
        ventana.putExtra("resultado",resultado)
        ventana.putExtra("codigos",listaIDs)
        startActivity(ventana)
    }
    private fun showDatePickerDialog1() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            //val selectedDate = day.toString() + " / " + (month + 1) + " / " + year
            val selectedDate = year.toString()+"-"+ (month + 1) +"-"+day
            binding.fecha1.setText(selectedDate)
        })

        newFragment.show(supportFragmentManager, "datePicker")
    }
    private fun showDatePickerDialog2() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            //val selectedDate = day.toString() + " / " + (month + 1) + " / " + year
            val selectedDate = year.toString()+"-"+ (month + 1) +"-"+day
            binding.fecha2.setText(selectedDate)
        })

        newFragment.show(supportFragmentManager, "datePicker")
    }
}