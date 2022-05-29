package mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.ui.consultar

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.Inventario
import mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.databinding.ActivityResultadoInventarioBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ResultadoInventario : AppCompatActivity() {
    lateinit var binding:ActivityResultadoInventarioBinding
    var listaIDs = ArrayList<String>()
    var inventarioCompleto = ArrayList<Inventario>()
    var inventario = Inventario(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoInventarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listaIDs = intent.extras!!.getStringArrayList("codigos")!!
        var resultado = intent.extras!!.getStringArrayList("resultado")!!



        binding.lista.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,resultado)
        binding.lista.setOnItemClickListener { adapterView, view, i, l ->

            AlertDialog.Builder(this)
                .setMessage("Código: ${listaIDs.get(i)}")
                .setTitle("Atención")
                .setPositiveButton("Aceptar"){d,i->

                }
                .setNeutralButton("ELIMINAR"){d,i->

                }
        }
    }
}