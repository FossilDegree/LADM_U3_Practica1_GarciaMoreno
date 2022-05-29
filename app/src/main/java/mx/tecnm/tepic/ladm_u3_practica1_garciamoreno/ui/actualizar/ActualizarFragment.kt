package mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.ui.actualizar

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.databinding.FragmentActualizarBinding
import mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.ui.insertar.InsertarInventario

//import mx.tecnm.tepic.ladm_u3_practica1_garciamoreno.databinding.FragmentSlideshowBinding

class ActualizarFragment : Fragment() {

    private var _binding: FragmentActualizarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(ActualizarViewModel::class.java)

        _binding = FragmentActualizarBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.actInv.setOnClickListener {
            val inv = Intent(requireContext(), ActualizarInventario::class.java)
            startActivity(inv)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}