package com.example.vita

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.vita.json.JsonBD

class ProfileFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Referências dos TextViews no layout
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvFocoValor = view.findViewById<TextView>(R.id.tvFocoValor)
        val tvPesoValor = view.findViewById<TextView>(R.id.tvPesoValor)

        // 2. Recuperar o e-mail do usuário logado (salvo no SharedPreferences durante o login)
        val sharedPreferences = requireActivity().getSharedPreferences("UserSession", Context.MODE_PRIVATE)
        val emailLogado = sharedPreferences.getString("logged_email", "")

        // 3. Buscar os dados no JsonBD
        if (!emailLogado.isNullOrEmpty()) {
            val jsonBD = JsonBD(requireContext())
            val usuarioJson = jsonBD.getUserByEmail(emailLogado)

            if (usuarioJson != null) {
                // Extrai as informações do JSON
                val nome = usuarioJson.optString("nome", "Usuário")
                val peso = usuarioJson.optString("peso", "0.0")
                // Se você salvar os dias de foco no JSON, pode resgatar aqui. Caso contrário, usamos um valor padrão ou a meta.
                val foco = usuarioJson.optString("foco", "23")

                // Preenche os campos na tela
                tvName.text = nome
                tvPesoValor.text = peso
                tvFocoValor.text = foco
            }
        }

        // Configuração do clique no card de Lembretes para navegar
        val btnLembretes = view.findViewById<LinearLayout>(R.id.btnLembretes)
        btnLembretes.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_lembretesFragment)
        }
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}