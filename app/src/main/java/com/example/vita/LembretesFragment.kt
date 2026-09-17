package com.example.vita

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class LembretesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Certifique-se de ter criado o layout fragment_lembretes.xml correspondente
        return inflater.inflate(R.layout.fragment_lembretes, container, false)
    }
}