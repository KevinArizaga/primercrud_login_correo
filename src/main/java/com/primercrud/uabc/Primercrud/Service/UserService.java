package com.primercrud.uabc.Primercrud.Service;

import com.primercrud.uabc.Primercrud.Entity.ConfirmationToken;
import com.primercrud.uabc.Primercrud.Entity.Rol;
import com.primercrud.uabc.Primercrud.Entity.User;
import com.primercrud.uabc.Primercrud.Entity.Usuario;
import com.primercrud.uabc.Primercrud.Enums.RolNombre;
import com.primercrud.uabc.Primercrud.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSenderService emailSenderService;
    @Autowired
    RolService rolService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<User> optionalUser = userRepository.findByEmail(email);

        return optionalUser.orElseThrow(() -> new UsernameNotFoundException(
                MessageFormat.format("Usuario con el correo {0} no se encontro.", email)));
    }

    /**
     * Metodo para enviar correo de confirmación
     *
     * @param userMail correo a enviar token
     * @param token    token generado
     */
    public void sendConfirmationMail(String userMail, String token) {

        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userMail);
        mailMessage.setSubject("Link de confirmación!");
        mailMessage.setFrom("<MAIL>");
        mailMessage.setText(
                "Gracias por registrate. Porfavor abre el siguiente link para activar tu cuenta " +
                        "http://localhost:8080/university/registro/confirm?token="
                        + token);
        emailSenderService.sendEmail(mailMessage);
    }

    /**
     * Metodo para crear usuario
     *
     * @param user usuario nuevo
     */
    public void signUpUser(User user) {
        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(user.getPassword());
        final ConfirmationToken confirmationToken = new ConfirmationToken(user);
        user.setToken(confirmationToken.getConfirmationToken());
        final User createdUser = userRepository.save(user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        sendConfirmationMail(user.getEmail(), confirmationToken.getConfirmationToken());
        //confirmUser(confirmationToken.getConfirmationToken());
    }

    public void confirmUser(String token) {
        Usuario usuario = new Usuario();
        User user = userRepository.findByToken(token);
        Set<Rol> roles = new HashSet<>();
        usuario.setNombreUsuario(user.getEmail());
        usuario.setPassword(user.getPassword());
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
    }

    public User findUserByToken(String token){
        return userRepository.findByToken(token);
    }
}
