package com.example.pam_act5.ui.theme

import android.icu.text.NumberFormat
import androidx.lifecycle.ViewModel
import com.example.pam_act5.data.FormState
import com.example.pam_act5.data.OrderUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


private const val HARGA_PER_CUP = 3000
class OrderViewModel : ViewModel(){
    private val _stateUI = MutableStateFlow((OrderUIState()))
    private val _nameSTATE = MutableStateFlow(FormState())
    val stateUI: StateFlow<OrderUIState> = _stateUI.asStateFlow()
    val nameST : StateFlow<FormState> = _nameSTATE.asStateFlow()

    fun  setNama(list: MutableList<String>){
        _nameSTATE.update { stateSaatIni -> stateSaatIni.copy(
            nama = list[0],
            alamat = list[1],
            phone = list[2]
        ) }
    }
    fun setJumlah(jmlEsMenantea:Int){
        _stateUI.update { stateSaatIni -> stateSaatIni.copy(
            jumlah = jmlEsMenantea,
            harga = hitungHarga(jumlah = jmlEsMenantea)
        )
        }
    }

    fun setRasa(rasaPilihan: String){
        _stateUI.update { stateSaatIni -> stateSaatIni.copy(rasa = rasaPilihan) }
    }

    fun resetOrder(){
        _stateUI.value = OrderUIState()
    }

    private fun hitungHarga(
        jumlah: Int = _stateUI.value.jumlah
    ): String {
        val kalkulasiHarga = jumlah * HARGA_PER_CUP

        return NumberFormat.getNumberInstance().format(kalkulasiHarga)
    }
}