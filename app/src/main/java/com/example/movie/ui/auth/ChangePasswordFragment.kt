package com.example.movie.ui.auth

import android.os.Bundle
import android.view.View
import com.example.movie.R
import com.example.movie.databinding.FragmentChangePasswordBinding
import com.example.movie.ui.baseFragment.BaseFragment
import com.example.movie.utils.UtilFunctions

class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding>(FragmentChangePasswordBinding::inflate) {

    private val utilFunction = UtilFunctions()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Deep link ile gelen verileri işleme
        val data = arguments?.getString("reset_token")
        if (data != null) {
            // Eğer data değeri null değilse, kullanıcının şifre sıfırlama talebi olduğunu ve "reset_token" parametresini içerdiğini anlayabiliriz.
            // Şimdi burada reset_token ile yapmak istediğiniz işlemleri gerçekleştirebilirsiniz.
            // Örneğin, yeni şifreyi almak ve güncellemek gibi işlemler yapabilirsiniz.

            // Örnek olarak, kullanıcının şifresini güncellediğini simule edelim:
            val newPassword = "yeni_sifre123" // Yeni şifreyi alabilirsiniz, örneğin kullanıcıdan alarak veya kendiniz oluşturarak.
            // Şifreyi güncelleme işlemini yapmak için bir fonksiyon çağırabilirsiniz. (Bu örnekte fonksiyonunuz yok, bunu kendi yapılandırmanıza göre uyarlamalısınız)
            updatePassword(newPassword)
        } else {
            // Eğer data değeri null ise, deep link ile bir reset_token parametresi gelmemiş demektir.
            // Bu durumda kullanıcıyı uygulamanızın diğer bir sayfasına yönlendirebilir veya hata mesajı gösterebilirsiniz.
            // Örneğin, "Geçersiz veya eksik şifre sıfırlama talebi" gibi bir mesaj gösterebilirsiniz.
        }
    }

    private fun updatePassword(newPassword: String) {
        // Burada yeni şifreyi alarak, kullanıcının şifresini güncelleyen işlemleri yapabilirsiniz.
        // Bu işlemleri kendi uygulamanızın işleyişine göre yapılandırmalısınız.
        // Örneğin, kullanıcının yeni şifresini sunucuya göndererek güncelleme yapabilirsiniz.
    }
}
