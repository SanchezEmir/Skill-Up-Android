package com.Alkemy.alkemybankbase.utils

import android.util.Patterns

/*
    Es una funcion con tipo lo que hace es recibir dos strings(email y la contraseña) y devuelve un valor boleano
    En el caso de que sea verdadero se admite el mail ingresado y el password
    Utilizamos expresiones regulares para coomparar  los dos strings
 */
fun controlEmailAndPassword(email: String, password: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email)
        .matches() && (password.matches("^[a-zA-Z0-9]*$".toRegex()) && password.isNotEmpty())
}


/*
    Es una funcion con tipo lo que hace es recibir dos strings(email y la contraseña) y devuelve un valor boleano

 */

fun controlChargeBalance(monto: String, moneda: String, descripcion: String): Boolean {
    return (monto.isNotEmpty() && moneda.isNotEmpty() && descripcion.isNotEmpty())
}

