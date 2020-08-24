package dev.leonlatsch.photok.ui.setup

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.leonlatsch.photok.model.database.entity.Password
import dev.leonlatsch.photok.model.repositories.PasswordRepository
import dev.leonlatsch.photok.other.PASSWORD_REGEX
import dev.leonlatsch.photok.other.emptyString
import dev.leonlatsch.photok.security.EncryptionManager
import kotlinx.coroutines.launch
import org.mindrot.jbcrypt.BCrypt
import java.util.regex.Pattern

class SetupViewModel @ViewModelInject constructor(
    private val passwordRepository: PasswordRepository,
    private val encryptionManager: EncryptionManager
) : ViewModel() {

    var passwordText = emptyString()
    var confirmPasswordText = emptyString()

    fun savePassword() = viewModelScope.launch {
        if (validatePassword()) {
            val bcryptHash = BCrypt.hashpw(passwordText, BCrypt.gensalt())
            val password = Password(bcryptHash)
            passwordRepository.insert(password)
            encryptionManager.generateAndSetKey(passwordText)
        } else {
            println("No good password")
        }

    }

    private fun validatePassword(): Boolean {
        return passwordText.isNotEmpty()
                && confirmPasswordText.isNotEmpty()
                && Pattern.matches(PASSWORD_REGEX, passwordText)
    }
}