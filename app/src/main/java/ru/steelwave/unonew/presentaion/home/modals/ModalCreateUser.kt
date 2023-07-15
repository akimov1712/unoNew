package ru.steelwave.unonew.presentaion.home.modals

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.EditText
import ru.steelwave.unonew.databinding.ModalCreateUserBinding

class ModalCreateUser(private val context: Context) {

    private val binding by lazy {
        ModalCreateUserBinding.inflate(LayoutInflater.from(context), null, false)
    }
    var onCreateUserClickListener: ((EditText) -> Unit)? = null
    private val modalAddUser = Dialog(context)

    fun show() {
        modalAddUser.setContentView(binding.root)
        modalAddUser.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val weight = 350 * context.resources.displayMetrics.density.toInt()
        val height = 100 * context.resources.displayMetrics.density.toInt()
        modalAddUser.window?.setLayout(weight, height)
        modalAddUser.show()
        listenerCreateUser()
    }

    private fun listenerCreateUser() {
        binding.btnDone.setOnClickListener {
            onCreateUserClickListener?.invoke(binding.etName)
            modalAddUser.dismiss()
            binding.etName.text = null
        }
    }

    fun closeModal(){
        modalAddUser.dismiss()
        binding.etName.text = null
    }

}