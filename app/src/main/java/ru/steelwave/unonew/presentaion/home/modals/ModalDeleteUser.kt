package ru.steelwave.unonew.presentaion.home.modals

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.EditText
import ru.steelwave.unonew.databinding.ModalCreateUserBinding
import ru.steelwave.unonew.databinding.ModalDeleteUserBinding

class ModalDeleteUser(private val context: Context) {

    private val binding by lazy {
        ModalDeleteUserBinding.inflate(LayoutInflater.from(context), null, false)
    }
    var onDeleteUserClickListener: (() -> Unit)? = null
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
        binding.btnConfirm.setOnClickListener {
            onDeleteUserClickListener?.invoke()
        }
        binding.btnCancel.setOnClickListener {
            closeModal()
        }
    }

    fun closeModal(){
        modalAddUser.dismiss()
    }

}