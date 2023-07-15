package ru.steelwave.unonew.presentaion.home.modals

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import ru.steelwave.unonew.databinding.ModalCreateGameBinding
import ru.steelwave.unonew.databinding.ModalCreateUserBinding

class ModalCreateGame(private val context: Context) {

    private val binding by lazy {
        ModalCreateGameBinding.inflate(LayoutInflater.from(context), null, false)
    }
    var onCreateGameClickListener: ((EditText) -> Unit)? = null
    private val modalAddGame = Dialog(context)

    fun show() {
        modalAddGame.setContentView(binding.root)
        modalAddGame.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val weight = 350 * context.resources.displayMetrics.density.toInt()
        val height = 100 * context.resources.displayMetrics.density.toInt()
        modalAddGame.window?.setLayout(weight, height)
        modalAddGame.show()
        listenerCreateGame()
    }

    private fun listenerCreateGame() {
        binding.btnDone.setOnClickListener {
            onCreateGameClickListener?.invoke(binding.etTarget)
            modalAddGame.dismiss()
            binding.etTarget.text = null
        }
    }

    fun closeModal(){
        modalAddGame.dismiss()
        binding.etTarget.text = null
    }

}